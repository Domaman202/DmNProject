package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap

/**
 * @author  DomamaN202
 */
class DmNPDataObject// Constructors
    (
    name: String,
    type: DmNPType,
    fm: DmNPDataMap? = null,
    e: DmNPDataMap? = null,
    modifiers: ArrayList<DmNPModifiers>? = null,
    reference: DmNPDataMap? = null,
    value: Any? = null
) : DmNPAData(name, type, modifiers, reference, value)
{
    // Fields and methods
    var fm: DmNPDataMap = fm ?: DmNPDataMap()
    // Extends
    var e: DmNPDataMap = e ?:  DmNPDataMap()

}