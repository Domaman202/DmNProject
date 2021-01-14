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
    ext: DmNPRDataMap = DmNPRDataMap(),
    override val reference: DmNPDataMap = DmNPDataMap()
) : DmNPEFMData(name, type, value, fm, ext), IReferenceStorage

open class DmNPAEFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    ext: DmNPRDataMap = DmNPRDataMap(),
    override var annotations: ArrayList<IDmNPData> = ArrayList()
) : DmNPEFMData(name, type, value, fm, ext), IAnnotationStorage