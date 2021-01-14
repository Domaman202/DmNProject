package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPAREFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    ext: ArrayList<DmNPReference<IDmNPData>> = ArrayList(),
    reference: DmNPDataMap = DmNPDataMap(),
    override val annotations: ArrayList<IDmNPData> = ArrayList()
) : DmNPREFMData(name, type, value, fm, ext, reference), IAnnotationStorage