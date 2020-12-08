package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.VM.DmNPVMInterpreter

interface IOpCode
interface IPlatformOpCode : IOpCode
interface IOpCodeManager
{
    fun parse(oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>)
}