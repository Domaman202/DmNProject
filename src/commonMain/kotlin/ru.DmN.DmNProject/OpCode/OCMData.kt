package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPUtils
import ru.DmN.DmNProject.VM.throwCast

object OCMData {
    fun init() {
        // Data
        // Data.Object
        OpCodeManager.OpCodes[OCData.CreatePackage] = { _, vm, _, _ ->
            val n = vm.stack.pop()!!
            if (n is String) {
                vm.stack.push(DmNPFMData(n, DmNPType.PACKAGE, DmNPDataMap()))
            } else if (n is ArrayList<*>) {
                var ne: IDmNPData? = null
                var le: IDmNPData? = null

                for (i in 0 until n.size) {
                    if (i == 0) {
                        val o = vm.stack.pop()
                        if (o is IDmNPData && o.type == DmNPType.PACKAGE) {
                            le = o
                            ne = o
                        } else
                            vm.stack.push(o)
                    }

                    le = if (le == null) {
                        DmNPData(n[i] as String, DmNPType.PACKAGE, DmNPDataMap())
                    } else {
                        (le.value as DmNPDataMap).add(DmNPData(n[i] as String, DmNPType.PACKAGE, DmNPDataMap()))
                    }

                    if (i == 0)
                        ne = le
                }

                vm.stack.push(ne)
            }
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
            val dt = vm.stack.pop() as DmNPDataVariants
            val name = vm.stack.pop() as String
            val type = vm.stack.pop() as DmNPType
            val value = vm.stack.pop()

            vm.stack.push(
                when (dt) {
                    DmNPDataVariants.Default ->
                        DmNPData(
                            name,
                            type,
                            value
                        )
                    DmNPDataVariants.Annotateble ->
                        DmNPAData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.Referencable ->
                        DmNPRData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.Extendable ->
                        DmNPEData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.FM ->
                        DmNPFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.EFM ->
                        DmNPEFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.RFM ->
                        DmNPRFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.AFM ->
                        DmNPAFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.REFM ->
                        DmNPREFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.AEFM ->
                        DmNPAEFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop())
                        )
                    DmNPDataVariants.AREFM ->
                        DmNPAREFMData(
                            name,
                            type,
                            value,
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop()),
                            throwCast(vm.stack.pop())
                        )
                }
            )
        }
        //
        OpCodeManager.OpCodes[OCData.SetValue] = { _, vm, _, _ ->
//            val v = vm.stack.pop()
//            (vm.stack.pop() as DmNPData).value = v
            (vm.stack.pop() as IDmNPData).value = vm.stack.pop()
        }
        OpCodeManager.OpCodes[OCData.CopySetValue] = { _, vm, _, _ ->
//            val v = vm.stack.pop()
//            (vm.stack.peek() as DmNPData).value = v
            val o = vm.stack.pop() as IDmNPData
            o.value = vm.stack.pop()
            vm.stack.push(o)
        }
        OpCodeManager.OpCodes[OCData.GetValue] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as IDmNPData).value)
        }
        OpCodeManager.OpCodes[OCData.CopyGetValue] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.peek() as IDmNPData).value)
        }
        OpCodeManager.OpCodes[OCData.AddData] = { _, vm, _, _ ->
//            val d = vm.stack.pop() as DmNPData
//            (vm.stack.pop() as IFMStorage).fm.add(d)
            (vm.stack.pop() as IFMStorage).fm.add(vm.stack.pop() as IDmNPData)
        }
        OpCodeManager.OpCodes[OCData.CopyAddData] = { _, vm, _, _ ->
//            val d = vm.stack.pop() as DmNPData
//            (vm.stack.peek() as IFMStorage).fm.add(d)
            val o = vm.stack.pop() as IFMStorage
            o.fm.add(vm.stack.pop() as IDmNPData)
            vm.stack.push(o)
        }
        OpCodeManager.OpCodes[OCData.RemoveData] = { _, vm, _, _ ->
//            val n = vm.stack.pop() as String
//            (vm.stack.peek() as IFMStorage).fm.remove(n)
            (vm.stack.pop() as IFMStorage).fm.remove(vm.stack.pop())
        }
        OpCodeManager.OpCodes[OCData.GetRemoveData] = { _, vm, _, _ ->
//            val n = vm.stack.pop() as String
//            val o = vm.stack.peek() as IFMStorage
//
//            vm.stack.push(o.fm[n])
//            o.fm.remove(n)
            vm.stack.push((vm.stack.pop() as IFMStorage).fm.remove(vm.stack.pop()))
        }
        OpCodeManager.OpCodes[OCData.AddToValue] = { _, vm, _, _ ->
            ((vm.stack.pop() as IDmNPData).value as DmNPDataMap).add(vm.stack.pop() as IDmNPData)
        }
        OpCodeManager.OpCodes[OCData.CopyAddToValue] = { _, vm, _, _ ->
            val o = vm.stack.pop() as IDmNPData
            (o.value as DmNPDataMap).add(vm.stack.pop() as IDmNPData)
            vm.stack.push(o)
        }
        OpCodeManager.OpCodes[OCData.RemoveInValue] = { _, vm, _, _ ->
            ((vm.stack.pop() as IDmNPData).value as DmNPDataMap).remove(vm.stack.pop())
        }
        OpCodeManager.OpCodes[OCData.CopyRemoveInValue] = { _, vm, _, _ ->
            val o = vm.stack.pop() as IDmNPData
            (o.value as DmNPDataMap).remove(vm.stack.pop())
            vm.stack.push(o)
        }
        OpCodeManager.OpCodes[OCData.FindPackage] = { _, vm, _, _ ->
            vm.stack.push(DmNPUtils.findPackage(
                throwCast(vm.stack.pop()),
                vm.stack.pop() as IDmNPData
        ))}
    }
}