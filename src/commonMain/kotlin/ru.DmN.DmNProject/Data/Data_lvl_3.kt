package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

class DmNPEFData(
    name: String,
    type: DmNPType,
    value: Any?,
    override var fm: DmNPDObjectMap? = null,
    e: ArrayList<DmNPReference<IEFMStorage>> = ArrayList()
) : DmNPEData(name, type, value, e), IEFMStorage


open class DmNPAData
/**
 * @param name Имя обьекта
 * @param type Тип обьекта
 */(
    name: String,
    type: DmNPType,
    modifiers: ArrayList<DmNPModifiers>? = null,
    reference: DmNPDataMap? = null,
    value: Any? = null
) : DmNPData(name, type, value), IModifiersStorage, IReferenceStorage
{
    override var modifiers: ArrayList<DmNPModifiers> = modifiers ?: ArrayList()
    override var reference: DmNPDataMap = reference ?: DmNPDataMap()

}