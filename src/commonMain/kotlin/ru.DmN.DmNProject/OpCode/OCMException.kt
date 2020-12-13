package ru.DmN.DmNProject.OpCode

object OCMException {
    fun init() {
        // Exception
        OpCodeManager.OpCodes[OCException.LoadException]    = { _, vm, _, ci    -> vm.eStack!!.push(ci.next() as Throwable) }
        OpCodeManager.OpCodes[OCException.UnLoadException]  = { _, vm, _, _     -> vm.eStack!!.pop() }
        OpCodeManager.OpCodes[OCException.ThrowOnVM]        = { _, vm, _, _     -> throw vm.eStack!!.pop()!! }
    }
}