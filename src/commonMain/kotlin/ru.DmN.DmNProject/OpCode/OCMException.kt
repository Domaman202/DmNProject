package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS

object OCMException {
    fun init() {
        // Exception
        ODCS.OCC["LE"] = OCException.LoadException
        OpCodeManager.OpCodes[OCException.LoadException]    = { _, vm, _, ci    -> vm.eStack!!.push(ci.next() as Throwable) }
        ODCS.OCC["ULE"] = OCException.UnLoadException
        OpCodeManager.OpCodes[OCException.UnLoadException]  = { _, vm, _, _     -> vm.eStack!!.pop() }
        ODCS.OCC["TOVM"] = OCException.ThrowOnVM
        OpCodeManager.OpCodes[OCException.ThrowOnVM]        = { _, vm, _, _     -> throw vm.eStack!!.pop()!! }
    }
}