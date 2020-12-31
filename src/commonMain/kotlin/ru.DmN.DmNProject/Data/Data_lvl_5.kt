package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

class DmNPDataObject
    (
    name: String,
    type: DmNPType,
    fm: DmNPDObjectMap? = null,
    e: ArrayList<DmNPReference<IEFMStorage>>? = null,
    modifiers: ArrayList<DmNPModifiers>? = null,
    reference: DmNPDataMap? = null,
    value: Any? = null
) : DmNPAData(name, type, modifiers, reference, value), IEFMStorage
{
    // Fields and methods
    override var fm: DmNPDObjectMap = fm ?: DmNPDObjectMap(this)
    // Extends
    override var e: ArrayList<DmNPReference<IEFMStorage>> = e ?:  ArrayList()

    operator fun set(name: String, value: DmNPData) { fm[name] = value }
    operator fun get(name: String) = fm[name]
}