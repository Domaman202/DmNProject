package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS

object OCMStack {
    fun init() {
        // Stack
        ODCS.OCC["LC"] = OCStack.LoadConstant
        OpCodeManager.OpCodes[OCStack.LoadConstant]         = { _, vm, _, ci    -> vm.stack.push(ci.next()) }
        ODCS.OCC["ULC"] = OCStack.UnLoadConstant
        OpCodeManager.OpCodes[OCStack.UnLoadConstant]       = { _, vm, _, _     -> vm.stack.pop() }
        ODCS.OCC["CSE"] = OCStack.CloneStackElement
        OpCodeManager.OpCodes[OCStack.CloneStackElement]    = { _, vm, _, _     -> vm.stack.push(vm.stack.peek()) }
        ODCS.OCC["R"] = OCStack.Reverse
        OpCodeManager.OpCodes[OCStack.Reverse]              = { _, vm, _, _     -> vm.stack.elements.reverse() }
        ODCS.OCC["LAC"] = OCStack.LoadAllConstants
        OpCodeManager.OpCodes[OCStack.LoadAllConstants]     = { _, vm, _, ci    -> for (e in ci.next() as ArrayList<*>) vm.stack.push(e) }
    }
}