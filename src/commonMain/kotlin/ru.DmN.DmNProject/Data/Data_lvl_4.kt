package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPAEData(
    name: String,
    type: DmNPType,
    modifiers: ArrayList<DmNPModifiers>? = null,
    reference: DmNPDataMap? = null,
    value: Any?,
    override val e: ArrayList<DmNPReference<IEFMStorage>> = ArrayList()
) : DmNPAData(name, type, modifiers, reference, value), IExtending