package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap

/**
 * @author  DomamaN202
 */
class DmNPDataObject: DmNPAData
{
    // Fields and methods
    var fm: DmNPDataMap
    // Extends
    var e: DmNPDataMap
    // Constructors
    constructor(name: String, type: DmNPType, fm: DmNPDataMap? = null, e: DmNPDataMap? = null, modifiers: ArrayList<DmNPModifiers>? = null, reference: DmNPDataMap? = null): super(name, type, modifiers, reference)
    {
        this.fm = fm ?: DmNPDataMap()
        this.e  = e ?:  DmNPDataMap()
    }
}