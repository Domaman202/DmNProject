package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.CS
import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.VM.throwCast

object OCMVM {
    fun init() {
        ODCS.OCC["CTS"] = OCVM.CodeToString
        OpCodeManager.OpCodes[OCVM.CodeToString] = { _, vm, _, _ ->
            vm.stack.push(CS.ValueToString(vm.stack.pop()))
        }
        ODCS.OCC["STC"] = OCVM.StringToCode
        OpCodeManager.OpCodes[OCVM.StringToCode] = { _, vm, _, _ ->
            vm.stack.push(ODCS.StringToValue(vm.stack.pop() as String))
        }
        ODCS.OCC["EC"] = OCVM.EvalCode
        OpCodeManager.OpCodes[OCVM.EvalCode] = { _, vm, _, _ ->
            vm.parse(throwCast(vm.stack.pop()))
        }
        ODCS.OCC["ES"] = OCVM.EvalString
        OpCodeManager.OpCodes[OCVM.EvalString] = { _, vm, _, _ ->
            vm.parse(throwCast(ODCS.StringToValue(vm.stack.pop() as String)))
        }
    }
}