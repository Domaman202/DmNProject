package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS

object OCMException {
    fun init() {
        // Exception
        ODCS.OCC["LE"] = OCException.LoadException
        OCManager.OC[OCException.LoadException]    = { _, vm, _, ci    -> vm.eStack!!.push(ci.next() as Throwable) }
        ODCS.OCC["ULE"] = OCException.UnLoadException
        OCManager.OC[OCException.UnLoadException]  = { _, vm, _, _     -> vm.eStack!!.pop() }
        ODCS.OCC["TOVM"] = OCException.ThrowOnVM
        OCManager.OC[OCException.ThrowOnVM]        = { _, vm, _, _     -> throw vm.eStack!!.pop()!! }
    }
}