package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.Math.Extends.*

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
    }
}