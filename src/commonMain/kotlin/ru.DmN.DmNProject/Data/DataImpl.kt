package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.DmNPReference

interface IDmNPData {
    val name: String
    val type: DmNPType
    val value: Any?
}

interface IExtending {
    val e: ArrayList<DmNPReference<IEFMStorage>>
}
interface IFMStorage {
    val fm: DmNPDObjectMap?
}
interface IModifiersStorage {
    val modifiers: ArrayList<DmNPModifiers>
}
interface IReferenceStorage {
    val reference: DmNPDataMap
}

interface IEFMStorage : IFMStorage, IExtending