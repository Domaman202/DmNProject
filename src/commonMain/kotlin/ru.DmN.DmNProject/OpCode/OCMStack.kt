package ru.DmN.DmNProject.OpCode

object OCMStack {
    fun init() {
        // Stack
        OpCodeManager.OpCodes[OCStack.LoadConstant]         = { _, vm, _, ci    -> vm.stack.push(ci.next()) }
        OpCodeManager.OpCodes[OCStack.UnLoadConstant]       = { _, vm, _, _     -> vm.stack.pop() }
        OpCodeManager.OpCodes[OCStack.CloneStackElement]    = { _, vm, _, _     -> vm.stack.push(vm.stack.peek()) }
        OpCodeManager.OpCodes[OCStack.Reverse]              = { _, vm, _, _     -> vm.stack.elements.reverse() }
        OpCodeManager.OpCodes[OCStack.LoadAllConstants]     = { _, vm, _, ci    -> for (e in ci.next() as ArrayList<*>) vm.stack.push(e) }
    }
}