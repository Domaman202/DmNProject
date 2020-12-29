package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPEData(
    name: String,
    type: DmNPType,
    value: Any? = null,
    override val e: ArrayList<DmNPReference<IEFMStorage>> = ArrayList()
) : DmNPData(name, type, value), IExtending

open class DmNPData(
    override var name: String,
    override var type: DmNPType,
    override var value: Any? = null
) : IDmNPData