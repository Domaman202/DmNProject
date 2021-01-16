package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.DmNPRDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPREFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    ext: DmNPRDataMap? = null,
    reference: DmNPDataMap? = null
) : DmNPEFMData(name, type, value, fm, ext), IReferenceStorage
{
    override val reference: DmNPDataMap = reference ?: DmNPDataMap()
}

open class DmNPAEFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    ext: DmNPRDataMap? = null,
    annotations: ArrayList<IDmNPData>? = null
) : DmNPEFMData(name, type, value, fm, ext), IAnnotationStorage
{
    override val annotations: ArrayList<IDmNPData> = annotations ?: ArrayList()
}