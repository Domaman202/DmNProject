package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.*
import ru.DmN.DmNProject.Data.Containers.*
import ru.DmN.DmNProject.impl.*

/**
 * @author  DomamaN202
 */
class DmNPDataObject: DmNPAData
{
    // Fields and methods
    var fm: DmNPDataArray
    // Extends
    var e: DmNPDataArray
    // Constructors
    constructor(name: String, type: DmNPType): super(name, type)
    {
        this.name = name
        this.type = type

        fm = DmNPDataArray()
        e = DmNPDataArray()
    }
    constructor(name: String, type: DmNPType, fm: DmNPDataArray, e: DmNPDataArray): super(name, type)
    {
        this.name = name
        this.type = type

        this.fm = fm
        this.e = e
    }
}

/**
 * @author DomamaN202
 */
open class DmNPAData : DmNPData, IModifiersStorage, IReferenceStorage
{
    open var modifiers: List<DmNPModifiers>
    open var reference: DmNPDataArray

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


/**
 * @author  DomamaN202
 */
open class DmNPData
{
    open var name: String
    open var type: DmNPType
    open var value: Any? = null

    // Constructors
    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     */
    constructor(name: String, type: DmNPType) {
        this.name = name
        this.type = type
    }
}

/**
 * @author  DomamaN202
 */
enum class DmNPType
{
    NULL,
    OBJECT,
    REFERENCE,

    VAR,
    KMETHOD,
    METHOD,
    CLASS,
    PACKAGE
}

/**
 * @author  DomamaN202
 */
enum class DmNPModifiers
{
    // Access
    PUBLIC,
    PROTECTED,
    PRIVATE,
    //
    STATIC,
    FINAL
}