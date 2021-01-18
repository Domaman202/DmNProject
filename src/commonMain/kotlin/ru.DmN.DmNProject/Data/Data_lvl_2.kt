package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.DmNPRDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPEData(
    name: String,
    type: DmNPType,
    ext: DmNPRDataMap? = null
) : DmNPData(name, type), IExtending
{
    override val ext: DmNPRDataMap = ext ?: DmNPRDataMap()
}

open class DmNPVEData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    ext: DmNPRDataMap? = null
) : DmNPVData(name, type, value), IExtending
{
    override val ext: DmNPRDataMap = ext ?: DmNPRDataMap()
}

open class DmNPAData(
    name: String,
    type: DmNPType,
    annotations: ArrayList<IDmNPData>? = null
) : DmNPData(name, type), IAnnotationStorage
{
    override val annotations: ArrayList<IDmNPData> = annotations ?: ArrayList()
}

open class DmNPVAData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    annotations: ArrayList<IDmNPData>? = null
) : DmNPVData(name, type, value), IAnnotationStorage
{
    override val annotations: ArrayList<IDmNPData> = annotations ?: ArrayList()
}

open class DmNPFMData(
    name: String,
    type: DmNPType,
    fm: DmNPDObjectMap? = null
) : DmNPData(name, type), IFMStorage {
    override val fm: DmNPDObjectMap = fm ?: DmNPDObjectMap(this)

    fun toEFMStorage(): IEFMStorage {
        val fmr = fm

        return object : IEFMStorage {
            override val fm: DmNPDObjectMap
                get() = fmr
            override val ext: DmNPRDataMap
                get() = DmNPRDataMap()
        }
    }
}

open class DmNPVFMData(
    name: String,
    type: DmNPType,
    value: Any?,
    fm: DmNPDObjectMap? = null
) : DmNPVData(name, type, value), IFMStorage {
    override val fm: DmNPDObjectMap = fm ?: DmNPDObjectMap(this)

    fun toEFMStorage(): IEFMStorage {
        val fmr = fm

        return object : IEFMStorage {
            override val fm: DmNPDObjectMap
                get() = fmr
            override val ext: DmNPRDataMap
                get() = DmNPRDataMap()
        }
    }
}

open class DmNPRData(
    name: String,
    type: DmNPType,
    reference: DmNPDataMap? = null
) : DmNPData(name, type), IReferenceStorage
{
    override val reference: DmNPDataMap = reference ?: DmNPDataMap()
}

open class DmNPVRData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    reference: DmNPDataMap? = null
) : DmNPVData(name, type, value), IReferenceStorage
{
    override val reference: DmNPDataMap = reference ?: DmNPDataMap()
}