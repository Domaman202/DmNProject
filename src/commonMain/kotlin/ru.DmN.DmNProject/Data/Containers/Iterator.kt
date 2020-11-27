package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPType

interface DmNPDataIterator : ListIterator<DmNPData>
{
    fun last():         DmNPData
    fun lastIndex():    Int

    fun prevName():     String
    fun lastName():     String
    fun nextName():     String
    fun prevType():     DmNPType
    fun lastType():     DmNPType
    fun nextType():     DmNPType
    fun prevValue():    Any?
    fun lastValue():    Any?
    fun nextValue():    Any?
}