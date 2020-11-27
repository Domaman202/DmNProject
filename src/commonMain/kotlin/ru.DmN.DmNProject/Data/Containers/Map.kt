package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPType

open class DmNPDataMap : MutableMap<String, DmNPData>
{
    // Fields
    var da: ArrayList<DmNPData>

    // Constructors
    constructor()
    {
        da = ArrayList()
    }
    constructor(size: Int)
    {
        da = ArrayList(size)
    }
    constructor(da: ArrayList<DmNPData>)
    {
        this.da = da
    }
    constructor(vararg e: DmNPData)
    {
        this.da = e as ArrayList<DmNPData>
    }

    // Methods
    fun DmNPData(): DmNPData {
        val data = DmNPData("", DmNPType.REFERENCE)
        data.value = this
        return data
    }

    fun DmNPData(name: String): DmNPData {
        val data = DmNPData(name, DmNPType.REFERENCE)
        data.value = this
        return data
    }

    fun add(data: DmNPData) = put(data.name, data)

    // Default methods
    override fun equals(other: Any?): Boolean = da == other
    override fun hashCode(): Int = da.hashCode()

    // From Map
    override val size: Int
        get() = da.size
    override fun isEmpty(): Boolean = da.isEmpty()
    override fun containsKey(key: String): Boolean = get(key) != null
    override fun containsValue(value: DmNPData): Boolean = da.contains(value)
    override fun get(key: String): DmNPData? {
        for (i in 0 until da.size)
            if (da[i].name == key)
                return da[i]
        return null
    }

    // From MutableMap
    override fun put(key: String, value: DmNPData): DmNPData? {
        if (key == value.name)
            if (da.add(value))
                return value
        return null
    }
    override fun remove(key: String): DmNPData? {
        val v = this[key]
        da.remove(v)
        return v
    }
    override fun putAll(from: Map<out String, DmNPData>) {
        for (v in from.values)
            da.add(v)
    }
    override fun clear() = da.clear()
    override val keys: MutableSet<String>
        get() {
            val keys = mutableSetOf<String>()
            for (i in 0 until da.size)
                keys.add(da[i].name)
            return keys
        }
    override val values: MutableCollection<DmNPData>
        get() = da.toMutableSet()
    override val entries: MutableSet<MutableMap.MutableEntry<String, DmNPData>>
        get() {
            val m = mutableMapOf<String, DmNPData>()
            for (i in 0 until da.size)
                m[da[i].name] = da[i]
            return m.entries
        }
}
