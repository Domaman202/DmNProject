package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.DmNPRDataMap
import ru.DmN.DmNProject.VM.DmNPReference

interface IDmNPData {
    val name: String
    val type: DmNPType
}

interface IValueStorage {
    var value: Any?
}

interface IExtending {
    val ext: DmNPRDataMap
}
interface IFMStorage {
    val fm: DmNPDObjectMap
}

interface IAnnotationStorage {
    val annotations: ArrayList<IDmNPData>
}
interface IReferenceStorage {
    val reference: DmNPDataMap
}

interface IEFMStorage : IFMStorage, IExtending