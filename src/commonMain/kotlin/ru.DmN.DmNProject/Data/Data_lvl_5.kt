package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.DmNPRDataMap

open class DmNPAREFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    ext: DmNPRDataMap? = null,
    reference: DmNPDataMap? = null,
    override val annotations: ArrayList<IDmNPData> = ArrayList()
) : DmNPREFMData(name, type, value, fm, ext, reference), IAnnotationStorage