package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.*

/**
 * @author  DomamaN202
 */
open class DmNPDataArray: MutableMap<String, DmNPData>
{
    // Fields
    var dataMap: DmNPDataMap

    // Constructors
    constructor() {
        dataMap = DmNPDataMap()
    }
    constructor(size: Int) {
        dataMap = DmNPDataMap(size)
    }
    constructor(data: DmNPDataMap) {
        dataMap = data
    }

    // Methods
    fun DmNPData(): DmNPData {
        val data = DmNPData("", DmNPType.REFERENCE)
        data.value = this
        return data
    }

    fun add(data: DmNPData) {
        dataMap[data.name] = data
    }

    // From map
    override val size
        get() = dataMap.size
    override fun isEmpty(): Boolean = dataMap.isEmpty()
    override fun containsKey(key: String): Boolean = dataMap.containsKey(key)
    override fun containsValue(value: @UnsafeVariance DmNPData): Boolean = dataMap.containsValue(value)
    override operator fun get(key: String): DmNPData? = dataMap[key]
    // From MutableMap
    override fun put(key: String, value: DmNPData): DmNPData? = dataMap.put(key, value)
    override fun remove(key: String): DmNPData? = dataMap.remove(key)
    override fun putAll(from: Map<out String, DmNPData>) = dataMap.putAll(from)
    override fun clear() = dataMap.clear()
    override val keys: MutableSet<String>
        get() = dataMap.keys
    override val values: MutableCollection<DmNPData>
        get() = dataMap.values
    override val entries: MutableSet<MutableMap.MutableEntry<String, DmNPData>>
        get() = dataMap.entries
}