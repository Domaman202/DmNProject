package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.VM.DmNPVM

/**
 * @author DomamaN202
 */
open class DmNPDataId: DmNPData
{
    var vm: DmNPVM? = null
    var id: Int     = -1
    override var name: String
        set(value) {
            if (vm != null) {
                val d = (vm!!.heap["_ObjectId_"]!!.value!! as ArrayList<String>)
                val i = d.indexOf(value)
                if (i > -1) {
                    id = i
                    field = ""
                } else {
                    d.add(value)
                    id = d.indexOf(value)
                }
            } else {
                id = -1
                field = value
            }
        }
        get() {
            if (vm != null && id >= 0)
                vm!!.heap["_ObjectId_"]
            return field
        }

    constructor(name: String, type: DmNPType) : super("", type)
    {
        this.name = name
    }

    constructor(name: String, type: DmNPType, vm: DmNPVM) : super("", type)
    {
        this.vm     = vm
        this.name   = name
    }

    constructor(id: Int, type: DmNPType) : super("", type)
    {
        this.id     = id
        this.name   = ""
    }

    constructor(id: Int, type: DmNPType, vm: DmNPVM) : super("", type)
    {
        this.vm     = vm
        this.id     = id
        this.name   = ""
    }
}

/**
 * @author  DomamaN202
 */
open class DmNPData(
    open var name: String,
    var type: DmNPType,
    var value: Any? = null
)

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