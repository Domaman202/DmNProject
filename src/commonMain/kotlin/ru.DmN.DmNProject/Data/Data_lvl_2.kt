package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataArray
import ru.DmN.DmNProject.VM.DmNPVM
import ru.DmN.DmNProject.impl.IModifiersStorage
import ru.DmN.DmNProject.impl.IReferenceStorage

open class DmNPADataId: DmNPDataId, IModifiersStorage, IReferenceStorage
{
    var modifiers: List<DmNPModifiers>
    var reference: DmNPDataArray

    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     * @param modifiers Модификаторы обьекта
     */
    constructor(name: String, type: DmNPType, modifiers: List<DmNPModifiers>) : super(name, type)
    {
        this.reference = DmNPDataArray()
        this.modifiers = modifiers
    }

    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     * @param modifiers Модификаторы обьекта
     * @param vm Виртуальная машина
     */
    constructor(name: String, type: DmNPType, modifiers: List<DmNPModifiers>, vm: DmNPVM) : super(name, type, vm)
    {
        this.reference = DmNPDataArray()
        this.modifiers = modifiers
    }
}

/**
 * @author DomamaN202
 */
open class DmNPAData : DmNPData, IModifiersStorage, IReferenceStorage
{
    var modifiers: List<DmNPModifiers>
    var reference: DmNPDataArray

    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     */
    constructor(name: String, type: DmNPType) : super(name, type)
    {
        this.reference = DmNPDataArray()
        this.modifiers = ArrayList()
    }

    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     * @param modifiers Модификаторы обьекта
     */
    constructor(name: String, type: DmNPType, modifiers: List<DmNPModifiers>) : super(name, type)
    {
        this.reference = DmNPDataArray()
        this.modifiers = modifiers
    }
}