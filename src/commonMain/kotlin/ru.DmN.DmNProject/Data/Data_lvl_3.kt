package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.DmNPRDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPEFMData(
    name: String,
    type: DmNPType,
    fm: DmNPDObjectMap? = null,
    ext: DmNPRDataMap? = null
): DmNPFMData(name, type, fm), IEFMStorage {
    override val ext: DmNPRDataMap = ext ?: DmNPRDataMap()
}

open class DmNPVEFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    ext: DmNPRDataMap? = null
): DmNPVFMData(name, type, value, fm), IEFMStorage {
    override val ext: DmNPRDataMap = ext ?: DmNPRDataMap()
}

open class DmNPRFMData(
    name: String,
    type: DmNPType,
    fm: DmNPDObjectMap? = null,
    reference: DmNPDataMap? = null
): DmNPFMData(name, type, fm), IFMStorage, IReferenceStorage
{
    override val reference: DmNPDataMap = reference ?: DmNPDataMap()
}


open class DmNPVRFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    reference: DmNPDataMap? = null
): DmNPVFMData(name, type, value, fm), IFMStorage, IReferenceStorage
{
    override val reference: DmNPDataMap = reference ?: DmNPDataMap()
}

open class DmNPAFMData(
    name: String,
    type: DmNPType,
    fm: DmNPDObjectMap? = null,
    annotations: ArrayList<IDmNPData>? = null
): DmNPFMData(name, type, fm), IFMStorage, IAnnotationStorage
{
    override val annotations: ArrayList<IDmNPData> = annotations ?: ArrayList()
}

open class DmNPVAFMData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    fm: DmNPDObjectMap? = null,
    annotations: ArrayList<IDmNPData>? = null
): DmNPVFMData(name, type, value, fm), IFMStorage, IAnnotationStorage
{
    override val annotations: ArrayList<IDmNPData> = annotations ?: ArrayList()
}
