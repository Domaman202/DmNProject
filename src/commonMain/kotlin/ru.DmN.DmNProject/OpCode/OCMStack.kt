package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS

object OCMStack {
    fun init() {
        // Stack
        ODCS.OCC["LC"] = OCStack.LoadConstant
        OCManager.OC[OCStack.LoadConstant]         = { _, vm, _, ci    -> vm.stack.push(ci.next()) }
        ODCS.OCC["ULC"] = OCStack.UnLoadConstant
        OCManager.OC[OCStack.UnLoadConstant]       = { _, vm, _, _     -> vm.stack.pop() }
        ODCS.OCC["CSE"] = OCStack.CloneStackElement
        OCManager.OC[OCStack.CloneStackElement]    = { _, vm, _, _     -> vm.stack.push(vm.stack.peek()) }
        ODCS.OCC["R"] = OCStack.Reverse
        OCManager.OC[OCStack.Reverse]              = { _, vm, _, _     -> vm.stack.elements.reverse() }
        ODCS.OCC["LAC"] = OCStack.LoadAllConstants
        OCManager.OC[OCStack.LoadAllConstants]     = { _, vm, _, ci    -> for (e in ci.next() as ArrayList<*>) vm.stack.push(e) }
    }
}