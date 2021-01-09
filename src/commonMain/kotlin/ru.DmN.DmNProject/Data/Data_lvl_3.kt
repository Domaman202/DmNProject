package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPEFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    override val ext: ArrayList<DmNPReference<IEFMStorage>> = ArrayList()
): DmNPData(name, type, value), IEFMStorage {
    override val fm: DmNPDObjectMap = fm ?: DmNPDObjectMap(this)
}

open class DmNPRFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    override val reference: DmNPDataMap = DmNPDataMap()
): DmNPFMData(name, type, value, fm), IReferenceStorage

open class DmNPAFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    override val annotations: ArrayList<IDmNPData>
): DmNPFMData(name, type, value, fm), IAnnotationStorage
