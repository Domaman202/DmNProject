package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.DmNPData

class DmNPDataMap : MutableMap<String, DmNPData>
{
    // data array
    var da: ArrayList<DmNPData>
    //
    override val size: Int
        get() = da.size
    //
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

    override fun clear() = da.clear()
    override fun containsKey(key: String): Boolean = get(key) != null
    override fun containsValue(value: DmNPData): Boolean = da.contains(value)
    override fun equals(other: Any?): Boolean = da.equals(other)
    override fun hashCode(): Int = da.hashCode()
    override fun get(key: String): DmNPData? {
        for (i in 0 until da.size)
            if (da[i].name == key)
                return da[i]
        return null
    }
    override fun isEmpty(): Boolean = da.isEmpty()
    override fun put(key: String, value: DmNPData): DmNPData? {
        if (key == value.name)
            if (da.add(value))
                return value
        return null
    }
    override fun putAll(from: Map<out String, DmNPData>) {
        for (v in from.values)
            da.add(v)
    }
    override fun remove(key: String): DmNPData? {
        val v = this[key]
        da.remove(v)
        return v
    }

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
                m.put(da[i].name, da[i])
            return m.entries
        }
}
