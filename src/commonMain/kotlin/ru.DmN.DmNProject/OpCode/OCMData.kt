package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.throwCast

object OCMData {
    fun init() {
        // Data
        // Data.Object
        OpCodeManager.OpCodes[OCData.CreatePackage] = { _, vm, _, _ ->
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
        OpCodeManager.OpCodes[OCData.CreateClass] = { _, vm, _, _ ->
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
        OpCodeManager.OpCodes[OCData.CreateMethod] = { _, vm, _, _ ->
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
        OpCodeManager.OpCodes[OCData.CreateVariable] = { _, vm, _, _ ->
            vm.stack.push(
                DmNPAData(
                    vm.stack.pop() as String,
                    DmNPType.VAR,
                    throwCast(vm.stack.pop())
                )
            )
        }
        OpCodeManager.OpCodes[OCData.CreateObject] = { _, vm, _, _ ->
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
        OpCodeManager.OpCodes[OCData.SetValue] = { _, vm, _, _ ->
            val v = vm.stack.pop()
            (vm.stack.pop() as DmNPData).value = v
        }
        OpCodeManager.OpCodes[OCData.CopySetValue] = { _, vm, _, _ ->
            val v = vm.stack.pop()
            (vm.stack.peek() as DmNPData).value = v
        }
        OpCodeManager.OpCodes[OCData.GetValue] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as DmNPData).value)
        }
        OpCodeManager.OpCodes[OCData.CopyGetValue] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.peek() as DmNPData).value)
        }
        OpCodeManager.OpCodes[OCData.AddData] = { _, vm, _, _ ->
            val d = vm.stack.pop() as DmNPData
            (vm.stack.pop() as DmNPDataObject).fm.add(d)
        }
        OpCodeManager.OpCodes[OCData.CopyAddData] = { _, vm, _, _ ->
            val d = vm.stack.pop() as DmNPData
            (vm.stack.peek() as DmNPDataObject).fm.add(d)
        }
        OpCodeManager.OpCodes[OCData.RemoveData] = { _, vm, _, _ ->
            val n = vm.stack.pop() as String
            (vm.stack.peek() as DmNPDataObject).fm.remove(n)
        }
        OpCodeManager.OpCodes[OCData.GetRemoveData] = { _, vm, _, _ ->
            val n = vm.stack.pop() as String
            val o = vm.stack.peek() as DmNPDataObject

            vm.stack.push(o.fm[n])
            o.fm.remove(n)
        }
    }
}