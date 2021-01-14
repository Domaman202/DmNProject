package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.IDmNPData
import ru.DmN.DmNProject.Data.IFMStorage
import ru.DmN.DmNProject.Data.Math.Extends.*
import ru.DmN.DmNProject.VM.DmNPUtils

object OCMMath {
    fun init() {
        // Math
        ODCS.OCC["SInc"] = OCSMath.INC
        OpCodeManager.OpCodes[OCSMath.INC] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).inc())
        }
        ODCS.OCC["SDec"] = OCSMath.DEC
        OpCodeManager.OpCodes[OCSMath.DEC] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).inc())
        }
        ODCS.OCC["SAdd"] = OCSMath.ADD
        OpCodeManager.OpCodes[OCSMath.ADD] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).plus(vm.stack.pop() as Number))
        }
        ODCS.OCC["SSub"] = OCSMath.SUB
        OpCodeManager.OpCodes[OCSMath.SUB] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).minus(vm.stack.pop() as Number))
        }
        ODCS.OCC["SMul"] = OCSMath.MUL
        OpCodeManager.OpCodes[OCSMath.MUL] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).times(vm.stack.pop() as Number))
        }
        ODCS.OCC["SDiv"] = OCSMath.DIV
        OpCodeManager.OpCodes[OCSMath.DIV] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).div(vm.stack.pop() as Number))
        }

        ODCS.OCC["AInc"] = OCAMath.INC
        OpCodeManager.OpCodes[OCAMath.INC] = { _, vm, c, ci ->
            val obj = vm.stack.pop()
            DmNPUtils.callFunction((obj as IFMStorage).fm["Inc"], vm, c, ci, obj as IDmNPData)
            vm.stack.push(obj)
        }
        ODCS.OCC["ADec"] = OCAMath.DEC
        OpCodeManager.OpCodes[OCAMath.DEC] = { _, vm, c, ci ->
            val obj = vm.stack.pop()
            DmNPUtils.callFunction((obj as IFMStorage).fm["Dec"], vm, c, ci, obj as IDmNPData)
            vm.stack.push(obj)
        }
        ODCS.OCC["AAdd"] = OCAMath.ADD
        OpCodeManager.OpCodes[OCAMath.ADD] = { _, vm, c, ci ->
            val obj = vm.stack.pop()
            DmNPUtils.callFunction((obj as IFMStorage).fm["Dec"], vm, c, ci, obj as IDmNPData)
            vm.stack.push(obj)
        }
        ODCS.OCC["ASub"] = OCAMath.SUB
        OpCodeManager.OpCodes[OCAMath.SUB] = { _, vm, c, ci ->
            val obj = vm.stack.pop()
            DmNPUtils.callFunction((obj as IFMStorage).fm["Dec"], vm, c, ci, obj as IDmNPData)
            vm.stack.push(obj)
        }
        ODCS.OCC["AMul"] = OCAMath.MUL
        OpCodeManager.OpCodes[OCAMath.MUL] = { _, vm, c, ci ->
            val obj = vm.stack.pop()
            DmNPUtils.callFunction((obj as IFMStorage).fm["Dec"], vm, c, ci, obj as IDmNPData)
            vm.stack.push(obj)
        }
        ODCS.OCC["ADiv"] = OCAMath.DIV
        OpCodeManager.OpCodes[OCAMath.DIV] = { _, vm, c, ci ->
            val obj = vm.stack.pop()
            DmNPUtils.callFunction((obj as IFMStorage).fm["Dec"], vm, c, ci, obj as IDmNPData)
            vm.stack.push(obj)
        }
    }
}