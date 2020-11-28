package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.impl.IModifiersStorage
import ru.DmN.DmNProject.impl.IReferenceStorage

/**
 * @author DomamaN202
 */
open class DmNPAData
/**
 * @param name Имя обьекта
 * @param type Тип обьекта
 */(
    name: String,
    type: DmNPType,
    modifiers: ArrayList<DmNPModifiers>? = null,
    reference: DmNPDataMap? = null
) : DmNPData(name, type), IModifiersStorage, IReferenceStorage
{
    var modifiers: ArrayList<DmNPModifiers> = modifiers ?: ArrayList()
    var reference: DmNPDataMap = reference ?: DmNPDataMap()

}