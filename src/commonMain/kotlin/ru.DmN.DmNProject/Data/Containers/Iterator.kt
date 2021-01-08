package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.*

interface DmNPDataIterator : ListIterator<IDmNPData>
{
    fun last():         IDmNPData
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

    fun prevAnnotations(): ArrayList<IDmNPData>?
    fun lastAnnotations(): ArrayList<IDmNPData>?
    fun nextAnnotations(): ArrayList<IDmNPData>?

    fun prevReference(): DmNPDataMap?
    fun lastReference(): DmNPDataMap?
    fun nextReference(): DmNPDataMap?
}