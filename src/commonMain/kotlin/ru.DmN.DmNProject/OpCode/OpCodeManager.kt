package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.*

/**
 * @author  DomamaN202
 */
class OpCodeManager {
    companion object : IOpCodeManager {
        val OpCodes = HashMap<IOpCode, (oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit>()

        override fun parse(oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>) {
            val f = OpCodes[oc]
            if (f != null)
                f(oc, vm, c, ci)
            else
                throw OpCodeNotFoundedException(oc.toString())
        }

        init {
            // Stack
            OpCodes[OCStack.LoadConstant] = { _, vm, _, ci -> vm.stack.push(ci.next()) }
            OpCodes[OCStack.UnLoadConstant] = { _, vm, _, _ -> vm.stack.pop() }
            OpCodes[OCStack.CloneStackElement] = { _, vm, _, _ -> vm.stack.push(vm.stack.peek()) }
            // Exception
            OpCodes[OCException.LoadException] = { _, vm, _, ci -> vm.eStack!!.push(ci.next() as Throwable) }
            OpCodes[OCException.UnLoadException] = { _, vm, _, _ -> vm.eStack!!.pop() }
            OpCodes[OCException.ThrowOnVM] = { _, vm, _, _ -> throw vm.eStack!!.pop()!! }
            // Stack Heap
            OpCodes[OCStackHeap.LoadData] = { _, vm, _, _ ->
                val le = DmNPUtils.findElement(vm, throwCast(vm.stack.pop()))
                if (le != null) vm.stack.push(le)
            }
            OpCodes[OCStackHeap.PushData] = { _, vm, _, _ ->
                val data = vm.stack.pop()!!
                if (data is DmNPData) {
                    if (vm.heap.containsKey(data.name)) {
                        vm.heap.remove(data.name)
                        vm.heap.add(data)
                    } else {
                        vm.heap.add(data)
                    }
                } else {
                    vm.stack.push(data)
                }
            }
            // Data
            // Data.Object
            OpCodes[OCData.CreatePackage] = { _, vm, _, _ ->
                val ns = throwCast<Any?, ArrayList<String>>(vm.stack.pop())

                var le: DmNPData? = null
                for (i in 0 until ns.size) {
                    if (le == null) {
                        if (vm.heap.containsKey(ns[i])) {
                            le = vm.heap[ns[i]]
                        } else {
                            le = DmNPAData(ns[i], DmNPType.PACKAGE)
                            le.reference.add(vm.heap.DmNPData())
                            le.value = DmNPDataMap()
                            vm.stack.push(le)
                        }
                    } else {
                        val d = le.value as DmNPDataMap
                        le = if (d.containsKey(ns[i])) {
                            d[ns[i]]
                        } else {
                            val ne = DmNPAData(ns[i], DmNPType.PACKAGE)
                            ne.reference.add(le)
                            ne.value = DmNPDataMap()
                            d.add(ne)
                            ne
                        }
                    }
                }
            }
            OpCodes[OCData.CreateClass] = { _, vm, _, _ ->
                val n = vm.stack.pop()!! as String
                val m = throwCast<Any?, ArrayList<DmNPModifiers>>(vm.stack.pop())

                var r = vm.stack.pop()!! as DmNPData

                if (r.type == DmNPType.PACKAGE) {
                    if ((r.value as DmNPDataMap).containsKey(n))
                        (r.value as DmNPDataMap).remove(n)
                    val clss = DmNPDataObject(n, DmNPType.CLASS)
                    clss.modifiers = m
                    clss.reference.add(r)
                    (r.value as DmNPDataMap).add(clss)
                    vm.stack.push(clss)
                } else if (r.type == DmNPType.CLASS || r.type == DmNPType.OBJECT) {
                    r = r as DmNPDataObject

                    if (r.fm.containsKey(n))
                        r.fm.remove(n)
                    val clss = DmNPDataObject(n, DmNPType.CLASS)
                    clss.modifiers = m
                    clss.reference.add(r)
                    r.fm.add(clss)
                    vm.stack.push(clss)
                }
            }
            OpCodes[OCData.CreateMethod] = { _, vm, _, _ ->
                val n = vm.stack.pop() as String
                val m = throwCast<Any?, ArrayList<DmNPModifiers>>(vm.stack.pop())
                val r = vm.stack.pop() as DmNPDataObject

                if (r.fm.containsKey(n))
                    r.fm.remove(n)

                val method = DmNPDataObject(n, DmNPType.METHOD)
                method.modifiers = m
                method.reference.add(r)

                r.fm.add(method)
                vm.stack.push(method)
            }
            OpCodes[OCData.CreateVariable] = { _, vm, _, _ ->
                vm.stack.push(
                    DmNPAData(
                        vm.stack.pop() as String,
                        DmNPType.VAR,
                        throwCast(vm.stack.pop())
                    )
                )
            }
            OpCodes[OCData.CreateObject] = { _, vm, _, _ ->
                vm.stack.push(
                    when (throwCast<Any?, DmNPDataType>(vm.stack.pop())) {
                        DmNPDataType.DmNPData -> DmNPData(
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            vm.stack.pop()
                        )
                        DmNPDataType.DmNPAData -> DmNPAData(
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            vm.stack.pop()
                        )
                        DmNPDataType.DmNPDataObject -> DmNPDataObject(
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            vm.stack.pop()
                        )
                    }
                )
            }
            //
            OpCodes[OCData.SetValue] = { _, vm, _, _ ->
                val v = vm.stack.pop()
                (vm.stack.pop() as DmNPData).value = v
            }
            OpCodes[OCData.CopySetValue] = { _, vm, _, _ ->
                val v = vm.stack.pop()
                (vm.stack.peek() as DmNPData).value = v
            }
            OpCodes[OCData.GetValue] = { _, vm, _, _ ->
                vm.stack.push((vm.stack.pop() as DmNPData).value)
            }
            OpCodes[OCData.CopyGetValue] = { _, vm, _, _ ->
                vm.stack.push((vm.stack.peek() as DmNPData).value)
            }
            OpCodes[OCData.AddData] = { _, vm, _, _ ->
                val d = vm.stack.pop() as DmNPData
                (vm.stack.pop() as DmNPDataObject).fm.add(d)
            }
            OpCodes[OCData.CopyAddData] = { _, vm, _, _ ->
                val d = vm.stack.pop() as DmNPData
                (vm.stack.peek() as DmNPDataObject).fm.add(d)
            }
            OpCodes[OCData.RemoveData] = { _, vm, _, _ ->
                val n = vm.stack.pop() as String
                (vm.stack.peek() as DmNPDataObject).fm.remove(n)
            }
            OpCodes[OCData.GetRemoveData] = { _, vm, _, _ ->
                val n = vm.stack.pop() as String
                val o = vm.stack.peek() as DmNPDataObject

                vm.stack.push(o.fm[n])
                o.fm.remove(n)
            }
            // Invoke
            // Kotlin
            OpCodes[OCInvoke.UnsafeInvokeKotlin] = { _, vm, c, ci ->
                throwCast<Any?, kotlin_function>(
                    DmNPUtils.findElement(
                        vm,
                        throwCast(vm.stack.pop())
                    )!!.value
                )(vm, c, ci)
            }
            OpCodes[OCInvoke.InvokeKotlin] = { _, vm, c, ci ->
                try {
                    throwCast<Any?, kotlin_function>(
                        DmNPUtils.findElement(
                            vm,
                            throwCast(vm.stack.pop())
                        )!!.value
                    )(vm, c, ci)
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCInvoke.InvokeStaticKotlin] = { _, vm, c, ci ->
                try {
                    throwCast<Any?, kotlin_function>(
                        DmNPUtils.findElement(
                            vm,
                            throwCast(vm.stack.pop())
                        )!!.value
                    )(vm, c, ci)
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            // Virtual
            OpCodes[OCInvoke.UnsafeInvokeVirtual] = { _, vm, _, _ ->
                val m = DmNPUtils.findElement(vm, throwCast(vm.stack.pop()))
                if (m != null) {
                    val mVM = DmNPVMInterpreter()
                    mVM.prev.add(vm)
                    vm.next.add(mVM)

                    mVM.parse(throwCast(m.value))

                    vm.next.remove(mVM)
                }
            }
            OpCodes[OCInvoke.InvokeVirtual] = { _, vm, _, _ ->
                try {
                    val n = throwCast<Any?, ArrayList<String>>(vm.stack.pop())
                    val m = DmNPUtils.findElement(vm, n)
                    if (m != null) {
                        val mVM = DmNPVMInterpreter()
                        mVM.prev.add(vm)
                        vm.next.add(mVM)

                        mVM.parse(throwCast(m.value))

                        vm.next.remove(mVM)
                    } else {
                        throw ObjectNullPointerException(n[n.size])
                    }
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCInvoke.InvokeStaticVirtual] = { _, vm, _, _ ->
                try {
                    val n = throwCast<Any?, ArrayList<String>>(vm.stack.pop())
                    val m = DmNPUtils.findElement(vm, n)
                    if (m != null) {
                        val mVM = DmNPVMInterpreter()
                        mVM.prev.add(vm)
                        vm.next.add(mVM)

                        mVM.parse(throwCast(m.value))

                        vm.next.remove(mVM)
                    } else {
                        throw ObjectNullPointerException(n[n.size])
                    }
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            //
            OpCodes[OCMath.Inc] = { _, vm, _, _ ->
                try {
                    vm.stack.push((vm.stack.pop() as IDmNPNumber).inc())
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCMath.Dec] = { _, vm, _, _ ->
                try {
                    vm.stack.push((vm.stack.pop() as IDmNPNumber).dec())
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCMath.Add] = { _, vm, _, _ ->
                try {
                    var v1 = vm.stack.pop()
                    var v2 = vm.stack.pop()
                    //
                    if (v1 is Number)
                        v1 = IDmNPNumber.ofType(v1)
                    if (v2 is Number)
                        v2 = IDmNPNumber.ofType(v2)
                    //
                    vm.stack.push((v1 as IDmNPNumber).add(v2 as IDmNPNumber))
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCMath.Sub] = { _, vm, _, _ ->
                try {
                    vm.stack.push((vm.stack.pop() as IDmNPNumber).sub(vm.stack.pop() as IDmNPNumber))
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCMath.Mul] = { _, vm, _, _ ->
                try {
                    vm.stack.push((vm.stack.pop() as IDmNPNumber).mul(vm.stack.pop() as IDmNPNumber))
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
            OpCodes[OCMath.Div] = { _, vm, _, _ ->
                try {
                    vm.stack.push((vm.stack.pop() as IDmNPNumber).div(vm.stack.pop() as IDmNPNumber))
                } catch (e: Throwable) {
                    if (vm.e)
                        vm.eStack!!.push(e)
                    else
                        throw e
                }
            }
        }
    }
}