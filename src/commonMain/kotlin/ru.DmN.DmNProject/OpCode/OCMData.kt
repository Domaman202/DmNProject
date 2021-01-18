package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference
import ru.DmN.DmNProject.VM.DmNPUtils
import ru.DmN.DmNProject.VM.throwCast

object OCMData {
    fun init() {
        // Data
        // Data.Object
        ODCS.OCC["CP"] = OCData.CreatePackage
        OCManager.OC[OCData.CreatePackage] = { _, vm, _, _ ->
            val n = vm.stack.pop()!!
            if (n is String) {
                vm.stack.push(DmNPVFMData(n, DmNPType.PACKAGE, DmNPDataMap()))
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
                        DmNPVData(n[i] as String, DmNPType.PACKAGE, DmNPDataMap())
                    } else {
                        ((le as IValueStorage).value as DmNPDataMap).add(DmNPVData(n[i] as String, DmNPType.PACKAGE, DmNPDataMap()))
                    }

                    if (i == 0)
                        ne = le
                }

                vm.stack.push(ne)
            }
        }
        ODCS.OCC["CM"] = OCData.CreateMethod
        OCManager.OC[OCData.CreateMethod] = { _, vm, _, _ ->
            vm.stack.push(DmNPVData(vm.stack.pop() as String, DmNPType.METHOD))
        }
        ODCS.OCC["CV"] = OCData.CreateVariable
        OCManager.OC[OCData.CreateVariable] = { _, vm, _, _ ->
            vm.stack.push(
                DmNPVData(
                    vm.stack.pop() as String,
                    DmNPType.VAR,
                    throwCast(vm.stack.pop())
                )
            )
        }
        ODCS.OCC["CO"] = OCData.CreateObject
        OCManager.OC[OCData.CreateObject] = { _, vm, _, _ ->
            val dt = vm.stack.pop() as DmNPDVars
            val name = vm.stack.pop() as String
            val type = vm.stack.pop() as DmNPType
            val value = vm.stack.pop()

            throw Exception()
        }
        //
        ODCS.OCC["SV"] = OCData.SetValue
        OCManager.OC[OCData.SetValue] = { _, vm, _, _ ->
            (vm.stack.pop() as IValueStorage).value = vm.stack.pop()
        }
        ODCS.OCC["CSV"] = OCData.CopySetValue
        OCManager.OC[OCData.CopySetValue] = { _, vm, _, _ ->
            val o = vm.stack.pop() as IValueStorage
            o.value = vm.stack.pop()
            vm.stack.push(o)
        }
        ODCS.OCC["GV"] = OCData.GetValue
        OCManager.OC[OCData.GetValue] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as IValueStorage).value)
        }
        ODCS.OCC["CGV"] = OCData.CopyGetValue
        OCManager.OC[OCData.CopyGetValue] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.peek() as IValueStorage).value)
        }
        ODCS.OCC["AD"] = OCData.AddData
        OCManager.OC[OCData.AddData] = { _, vm, _, _ ->
            (vm.stack.pop() as IFMStorage).fm.add(vm.stack.pop() as IDmNPData)
        }
        ODCS.OCC["CAD"] = OCData.CopyAddData
        OCManager.OC[OCData.CopyAddData] = { _, vm, _, _ ->
            val o = vm.stack.pop() as IFMStorage
            o.fm.add(vm.stack.pop() as IDmNPData)
            vm.stack.push(o)
        }
        ODCS.OCC["RD"] = OCData.RemoveData
        OCManager.OC[OCData.RemoveData] = { _, vm, _, _ ->
            (vm.stack.pop() as IFMStorage).fm.remove(vm.stack.pop())
        }
        ODCS.OCC["GRD"] = OCData.GetRemoveData
        OCManager.OC[OCData.GetRemoveData] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as IFMStorage).fm.remove(vm.stack.pop()))
        }
        ODCS.OCC["ATV"] = OCData.AddToValue
        OCManager.OC[OCData.AddToValue] = { _, vm, _, _ ->
            ((vm.stack.pop() as IValueStorage).value as DmNPDataMap).add(vm.stack.pop() as IDmNPData)
        }
        ODCS.OCC["CATV"] = OCData.CopyAddToValue
        OCManager.OC[OCData.CopyAddToValue] = { _, vm, _, _ ->
            val o = vm.stack.pop() as IValueStorage
            (o.value as DmNPDataMap).add(vm.stack.pop() as IDmNPData)
            vm.stack.push(o)
        }
        ODCS.OCC["RIV"] = OCData.RemoveInValue
        OCManager.OC[OCData.RemoveInValue] = { _, vm, _, _ ->
            ((vm.stack.pop() as IValueStorage).value as DmNPDataMap).remove(vm.stack.pop())
        }
        ODCS.OCC["CRIV"] = OCData.CopyRemoveInValue
        OCManager.OC[OCData.CopyRemoveInValue] = { _, vm, _, _ ->
            val o = vm.stack.pop() as IValueStorage
            (o.value as DmNPDataMap).remove(vm.stack.pop())
            vm.stack.push(o)
        }
        ODCS.OCC["FP"] = OCData.FindPackage
        OCManager.OC[OCData.FindPackage] = { _, vm, _, _ ->
            vm.stack.push(DmNPUtils.findPackage(
                throwCast(vm.stack.pop()),
                vm.stack.pop() as IDmNPData
        ))}
        ODCS.OCC["AE"] = OCData.AddExt
        OCManager.OC[OCData.AddExt] = { _, vm, _, _ ->
            val o = vm.stack.pop()
            val e = vm.stack.pop()
            (o as IExtending).ext.add(DmNPReference({ }, { e as IDmNPData }))
            vm.stack.push(o)
        }
        ODCS.OCC["RE"] = OCData.RemoveExt
        OCManager.OC[OCData.RemoveExt] = { _, vm, _, _ ->
            val o = vm.stack.pop()
            val n = vm.stack.pop()
            (o as IExtending).ext.remove(n as String)
            vm.stack.push(o)
        }
        ODCS.OCC["GD"] = OCData.GetData
        OCManager.OC[OCData.GetData] = { _, vm, _, _ ->
            val n = vm.stack.pop() as String
            val o = vm.stack.pop()
            if (o is IFMStorage)
                vm.stack.push(o.fm[n])
            else if (o is IExtending) {
                vm.stack.push(DmNPUtils.findWithExtends(o, n))
            }
        }
        ODCS.OCC["CGD"] = OCData.CopyGetData
        OCManager.OC[OCData.CopyGetData] = { _, vm, _, _ ->
            val o = vm.stack.elements[vm.stack.elements.lastIndex - 1]
            if (o is IFMStorage)
                vm.stack.push(o.fm[vm.stack.pop()])
            else if (o is IExtending) {
                vm.stack.push(DmNPUtils.findWithExtends(o, vm.stack.pop() as String))
            }
        }
    }
}