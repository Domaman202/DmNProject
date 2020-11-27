package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.impl.IModifiersStorage
import ru.DmN.DmNProject.impl.IReferenceStorage

/**
 * @author DomamaN202
 */
open class DmNPAData : DmNPData, IModifiersStorage, IReferenceStorage
{
    var modifiers: ArrayList<DmNPModifiers>
    var reference: DmNPDataMap

    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     */
    constructor(name: String, type: DmNPType, modifiers: ArrayList<DmNPModifiers>? = null, reference: DmNPDataMap? = null) : super(name, type)
    {
        this.reference = reference ?: DmNPDataMap()
        this.modifiers = modifiers ?: ArrayList()
    }
}