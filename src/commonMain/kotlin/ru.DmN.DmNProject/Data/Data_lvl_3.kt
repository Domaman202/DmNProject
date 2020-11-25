package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataArray

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