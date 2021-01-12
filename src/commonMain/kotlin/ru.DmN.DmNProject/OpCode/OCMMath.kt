package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.Math.Extends.*

object OCMMath {
    fun init() {
        // Math
        OpCodeManager.OpCodes[OCSMath.INC] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).inc())
        }
        OpCodeManager.OpCodes[OCSMath.DEC] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).inc())
        }
        OpCodeManager.OpCodes[OCSMath.ADD] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).plus(vm.stack.pop() as Number))
        }
        OpCodeManager.OpCodes[OCSMath.SUB] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).minus(vm.stack.pop() as Number))
        }
        OpCodeManager.OpCodes[OCSMath.MUL] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).times(vm.stack.pop() as Number))
        }
        OpCodeManager.OpCodes[OCSMath.DIV] = { _, vm, _, _ ->
            vm.stack.push((vm.stack.pop() as Number).div(vm.stack.pop() as Number))
        }
    }
}