package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.CS
import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.VM.DmNPReference
import ru.DmN.DmNProject.VM.DmNPVMInterpreter
import ru.DmN.DmNProject.VM.throwCast

object OCMVM {
    fun init() {
        ODCS.OCC["CTS"] = OCVM.CodeToString
        OCManager.OC[OCVM.CodeToString] = { _, vm, _, _ ->
            vm.stack.push(CS.ValueToString(vm.stack.pop()))
        }
        ODCS.OCC["STC"] = OCVM.StringToCode
        OCManager.OC[OCVM.StringToCode] = { _, vm, _, _ ->
            vm.stack.push(ODCS.StringToValue(vm.stack.pop() as String))
        }
        ODCS.OCC["GEC"] = OCVM.GlobalEvalCode
        OCManager.OC[OCVM.GlobalEvalCode] = { _, vm, _, _ ->
            vm.parse(throwCast(vm.stack.pop()))
        }
        ODCS.OCC["GES"] = OCVM.GlobalEvalString
        OCManager.OC[OCVM.GlobalEvalString] = { _, vm, _, _ ->
            vm.parse(throwCast(ODCS.StringToValue(vm.stack.pop() as String)))
        }
        ODCS.OCC["NLEC"] = OCVM.NoLinkEvalCode
        OCManager.OC[OCVM.NoLinkEvalCode] = { _, vm, _, _ ->
            val i = DmNPVMInterpreter(true)
            i.parse(throwCast(vm.stack.pop()))
            vm.stack.push(i)
        }
        ODCS.OCC["NLES"] = OCVM.NoLinkEvalString
        OCManager.OC[OCVM.NoLinkEvalString] = { _, vm, _, _ ->
            val i = DmNPVMInterpreter(true)
            i.parse(throwCast(ODCS.StringToValue(vm.stack.pop() as String)))
            vm.stack.push(i)
        }
        ODCS.OCC["LEC"] = OCVM.LinkEvalCode
        OCManager.OC[OCVM.LinkEvalCode] = { _, vm, _, _ ->
            val i = DmNPVMInterpreter(true)
            i.prev.add(vm)
            vm.next.add(i)
            i.parse(throwCast(vm.stack.pop()))
            vm.stack.push(i)
        }
        ODCS.OCC["LES"] = OCVM.LinkEvalString
        OCManager.OC[OCVM.LinkEvalString] = { _, vm, _, _ ->
            val i = DmNPVMInterpreter(true)
            i.prev.add(vm)
            vm.next.add(i)
            i.parse(throwCast(ODCS.StringToValue(vm.stack.pop() as String)))
            vm.stack.push(i)
        }
    }
}