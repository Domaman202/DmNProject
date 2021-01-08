package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPEData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    override val ext: ArrayList<DmNPReference<IEFMStorage>> = ArrayList()
) : DmNPData(name, type, value), IExtending

open class DmNPAData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    override val annotations: ArrayList<IDmNPData> = ArrayList()
) : DmNPData(name, type, value), IAnnotationStorage

open class DmNPFMData : DmNPData, IFMStorage {
    override val fm: DmNPDObjectMap

    constructor(name: String, type: DmNPType, value: Any?, fm: DmNPDObjectMap?) : super(name, type, value) {
        this.fm = fm ?: DmNPDObjectMap(toEFMStorage())
    }

    fun toEFMStorage(): IEFMStorage {
        val fmr = fm

        return object : IEFMStorage {
            override val fm: DmNPDObjectMap
                get() = fmr
            override val ext: ArrayList<DmNPReference<IEFMStorage>>
                get() = ArrayList()
        }
    }
}

open class DmNPRData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    override val reference: DmNPDataMap = DmNPDataMap()
) : DmNPData(name, type, value), IReferenceStorage