package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.Containers.*
import ru.DmN.DmNProject.OpCode.*

/**
 * @author  DomamaN202
 * @since 1.0
 */
open class DmNPVMInterpreter : DmNPVM
{
    var e: Boolean = true
    var eStack: Stack<Throwable>? = null
    var um: Boolean = false

    constructor() : super()
    constructor(e: Boolean) : super()
    {
        this.e = e
        if (e)
            eStack = Stack()
    }

    fun parse(code: ArrayList<Any?>) {
        val codeIterator = code.listIterator()

        while (codeIterator.hasNext()) {
            val codeLine = codeIterator.next()
            if (codeLine is IOpCode)
                OpCodeManager.parse(codeLine, this, code, codeIterator)
        }
    }
}