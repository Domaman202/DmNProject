package ru.DmN.DmNProject.Data

open class DmNPData(
    override var name: String,
    override var type: DmNPType,
    override var value: Any? = null
) : IDmNPData