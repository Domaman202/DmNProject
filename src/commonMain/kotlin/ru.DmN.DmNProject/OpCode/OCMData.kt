package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.throwCast

object OCMData {
    fun init() {
        // Data
        // Data.Object
        OpCodeManager.OpCodes[OCData.CreatePackage] = { _, vm, _, _ ->

        }
        OpCodeManager.OpCodes[OCData.CreateClass] = { _, vm, _, _ ->

        }
        OpCodeManager.OpCodes[OCData.CreateMethod] = { _, vm, _, _ ->
            vm.stack.push(DmNPData(vm.stack.pop() as String, DmNPType.METHOD))
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
            (vm.stack.pop() as IFMStorage).fm.add(d)
        }
        OpCodeManager.OpCodes[OCData.CopyAddData] = { _, vm, _, _ ->
            val d = vm.stack.pop() as DmNPData
            (vm.stack.peek() as IFMStorage).fm.add(d)
        }
        OpCodeManager.OpCodes[OCData.RemoveData] = { _, vm, _, _ ->
            val n = vm.stack.pop() as String
            (vm.stack.peek() as IFMStorage).fm.remove(n)
        }
        OpCodeManager.OpCodes[OCData.GetRemoveData] = { _, vm, _, _ ->
            val n = vm.stack.pop() as String
            val o = vm.stack.peek() as IFMStorage

            vm.stack.push(o.fm[n])
            o.fm.remove(n)
        }
    }
}