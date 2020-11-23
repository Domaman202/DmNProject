package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.*
import ru.DmN.DmNProject.*
import ru.DmN.DmNProject.VM.*
import kotlin.reflect.KFunction

/**
 * @author  DomamaN202
 */
class OpCodeManager {
    companion object {
        val OpCodes = HashMap<IOpCode, (oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>) -> Unit>()

        @Suppress("NON_EXHAUSTIVE_WHEN", "UNCHECKED_CAST")
        fun parse(oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>) {
            val f = OpCodes[oc]
            if (f != null)
                f(oc, vm, c, ci)
            else
                throw OpCodeNotFoundedException()
        }

        init {
            // Stack
            OpCodes[OCStack.LoadConstant]       = { _, vm, _, ci -> vm.stack.push(ci.next()) }
            OpCodes[OCStack.UnLoadConstant]     = { _, vm, _, _ -> vm.stack.pop() }
            OpCodes[OCStack.CloneStackElement]  = { _, vm, _, _ -> vm.stack.push(vm.stack.peek()) }
            // Exception
            OpCodes[OCException.LoadException]      = { _, vm, _, ci -> vm.eStack!!.push(ci.next() as Throwable) }
            OpCodes[OCException.UnLoadException]    = { _, vm, _, _ -> vm.eStack!!.pop() }
            OpCodes[OCException.ThrowOnVM]          = { _, vm, _, _ -> throw vm.eStack!!.pop()!! }
            // Stack Heap
            OpCodes[OCStackHeap.LoadData] = { _, vm, _, _ ->
                val le = DmNPUtils.findElement(vm, vm.stack.pop() as ArrayList<String>)
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
                val ns = vm.stack.pop() as ArrayList<String>

                var le: DmNPData? = null
                for (i in 0 until ns.size) {
                    if (le == null) {
                        if (vm.heap.containsKey(ns[i])) {
                            le = vm.heap[ns[i]]
                        } else {
                            le = DmNPAData(ns[i], DmNPType.PACKAGE)
                            le.reference.add(vm.heap.DmNPData())
                            le.value = DmNPDataArray()
                            vm.stack.push(le)
                        }
                    } else {
                        val d = le.value as DmNPDataArray
                        le = if (d.containsKey(ns[i])) {
                            d[ns[i]]
                        } else {
                            val ne = DmNPAData(ns[i], DmNPType.PACKAGE)
                            ne.reference.add(le)
                            ne.value = DmNPDataArray()
                            d.add(ne)
                            ne
                        }
                    }
                }
            }
            OpCodes[OCData.CreateClass] = { oc, vm, c, ci ->
                val n = vm.stack.pop()!! as String
                    val m = vm.stack.pop()!! as ArrayList<DmNPModifiers>
                    var r = vm.stack.pop()!! as DmNPData

                    if (r.type == DmNPType.PACKAGE) {
                        if ((r.value as DmNPDataArray).containsKey(n))
                            (r.value as DmNPDataArray).remove(n)
                        val clss = DmNPDataObject(n, DmNPType.CLASS)
                        clss.modifiers = m
                        clss.reference.add(r)
                        (r.value as DmNPDataArray).add(clss)
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
                    val m = vm.stack.pop() as ArrayList<DmNPModifiers>
                    val r = vm.stack.pop() as DmNPDataObject

                    if (r.fm.containsKey(n))
                        r.fm.remove(n)

                    val method = DmNPDataObject(n, DmNPType.METHOD)
                    method.modifiers = m
                    method.reference.add(r)

                    r.fm.add(method)
                    vm.stack.push(method)
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
            OpCodes[OCInvoke.UnsafeInvokeKotlin] = { _, vm, c, ci ->
                val n = vm.stack.pop() as ArrayList<String>
                (DmNPUtils.findElement(vm, n)!!.value as (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit)(vm, c, ci)
            }
            OpCodes[OCInvoke.UnsafeInvokeVirtual] = { oc, vm, c, ci ->
                val m = DmNPUtils.findElement(vm, vm.stack.pop() as ArrayList<String>)
                if (m != null) {
                    val mVM = DmNPVMInterpreter()
                    mVM.prev.add(vm)
                    vm.next.add(mVM)

                    mVM.parse(m.value as ArrayList<Any?>)

                    vm.next.remove(mVM)
                }
            }
        }
    }
}