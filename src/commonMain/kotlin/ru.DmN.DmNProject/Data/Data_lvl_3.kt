package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

/**
 * @author  DomamaN202
 */
class DmNPDataObject// Constructors
    (
    name: String,
    type: DmNPType,
    fm: DmNPDataMap? = null,
    e: ArrayList<DmNPReference<DmNPData>>? = null,
    modifiers: ArrayList<DmNPModifiers>? = null,
    reference: DmNPDataMap? = null,
    value: Any? = null
) : DmNPAData(name, type, modifiers, reference, value)
{
    // Fields and methods
    var fm: DmNPDataMap = fm ?: DmNPDataMap()
    // Extends
    var e: ArrayList<DmNPReference<DmNPData>> = e ?:  ArrayList()

    operator fun set(name: String, value: DmNPData) { fm[name] = value }
    operator fun get(name: String) = fm[name]
}