package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.VM.DmNPReference

open class DmNPDataMap : MutableMap<String, IDmNPData>, Iterable<IDmNPData>
{
    // Fields
    var da: ArrayList<IDmNPData>

    // Constructors
    constructor()
    { da = ArrayList() }
    constructor(size: Int)
    { da = ArrayList(size) }
    constructor(da: ArrayList<IDmNPData>)
    { this.da = da }
    constructor(vararg e: IDmNPData)
    {
        da = ArrayList()
        da.addAll(e)
    }

    // Methods
    fun DmNPData(): DmNPVData {
        val data = DmNPVData("", DmNPType.REFERENCE)
        data.value = this
        return data
    }
    fun DmNPData(name: String): DmNPVData {
        val data = DmNPVData(name, DmNPType.REFERENCE)
        data.value = this
        return data
    }
    //
    fun add(data: IDmNPData) = put(data.name, data)
    override fun iterator(): Iterator<IDmNPData> = object : Iterator<IDmNPData> {
        var i = 0
        override fun hasNext(): Boolean = i != size
        override fun next(): IDmNPData = da[i++]
    }

    // Default methods
    override fun equals(other: Any?): Boolean = da == other
    override fun hashCode(): Int = da.hashCode()

    // From Map
    override val size: Int
        get() = da.size
    override fun isEmpty(): Boolean = da.isEmpty()
    override fun containsKey(key: String): Boolean = get(key) != null
    override fun containsValue(value: IDmNPData): Boolean = da.contains(value)
    override fun get(key: String): IDmNPData? {
        for (i in 0 until da.size) {
            if (da[i].name == key)
                return da[i]
            else if (i - da.size - 1 >= 0 && da[i - da.size - 1].name == key)
                return da[i - da.size - 1]
        }
        return null
    }

    // From MutableMap
    override fun put(key: String, value: IDmNPData): IDmNPData? {
        if (key == value.name)
            if (da.add(value))
                return value
        return null
    }
    override fun remove(key: String): IDmNPData? {
        val v = this[key]
        da.remove(v)
        return v
    }
    override fun putAll(from: Map<out String, IDmNPData>) {
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
    override val values: MutableCollection<IDmNPData>
        get() = da.toMutableSet()
    override val entries: MutableSet<MutableMap.MutableEntry<String, IDmNPData>>
        get() {
            val m = mutableMapOf<String, IDmNPData>()
            for (i in 0 until da.size)
                m[da[i].name] = da[i]
            return m.entries
        }
}

open class DmNPDObjectMap : DmNPDataMap
{
    var instance: IDmNPData?

    constructor(instance: IDmNPData) : super()
    { this.instance = instance }
    constructor(size: Int, instance: IDmNPData) : super(size)
    { this.instance = instance }
    constructor(da: ArrayList<IDmNPData>, instance: IDmNPData) : super(da)
    { this.instance = instance }

    operator fun get(key: String, instance: IDmNPData? = null, parent: Boolean = true): IDmNPData? {
        var result = super.get(key)

        if (instance != null)
            this.instance = instance

        if (parent && result == null && this.instance is IExtending) {
            for (e in (this.instance!! as IExtending).ext) {
                if (e.get() != this.instance) {
                    result = (e.get() as IFMStorage).fm[key, e.get()]

                    if (result != null)
                        return result
                }
            }

            if (result == null) {
                for (e in (this.instance!! as IExtending).ext) {
                    if (e.get() != this.instance) {
                        f(key, e.get())
                    }
                }
            }
        }

        return result
    }

    fun f(key: String, instance: IDmNPData? = null): IDmNPData? {
        var result = super.get(key)

        if (result == null && instance != null) {
            for (e in (instance as IExtending).ext) {
                if (e.get() != instance) {
                    result = (e.get() as IFMStorage).fm[key, e.get()]

                    if (result != null)
                        return result
                    else
                        f(key, e.get())
                }
            }
        }

        return result
    }

    override fun get(key: String): IDmNPData? {
        return this[key, instance]
    }
}

open class DmNPRDataMap : MutableMap<String, DmNPReference<IDmNPData>>, Iterable<DmNPReference<IDmNPData>>
{
    // Fields
    var da: ArrayList<DmNPReference<IDmNPData>>

    // Constructors
    constructor()
    { da = ArrayList() }
    constructor(size: Int)
    { da = ArrayList(size) }
    constructor(da: ArrayList<DmNPReference<IDmNPData>>)
    { this.da = da }
    constructor(vararg e: DmNPReference<IDmNPData>)
    {
        da = ArrayList()
        da.addAll(e)
    }

    // Methods
    fun DmNPVData(): DmNPVData {
        val data = DmNPVData("", DmNPType.REFERENCE)
        data.value = this
        return data
    }
    fun DmNPVData(name: String): DmNPVData {
        val data = DmNPVData(name, DmNPType.REFERENCE)
        data.value = this
        return data
    }
    //
    fun add(data: DmNPReference<IDmNPData>) = put(data.get().name, data)

    // Default methods
    override fun equals(other: Any?): Boolean = da == other
    override fun hashCode(): Int = da.hashCode()

    // From Map
    override val size: Int
        get() = da.size
    override fun isEmpty(): Boolean = da.isEmpty()
    override fun containsKey(key: String): Boolean = get(key) != null
    override fun containsValue(value: DmNPReference<IDmNPData>): Boolean = da.contains(value)
    override fun get(key: String): DmNPReference<IDmNPData>? {
        for (i in 0 until da.size) {
            if (da[i].get().name == key)
                return da[i]
            else if (i - da.size - 1 >= 0 && da[i - da.size - 1].get().name == key)
                return da[i - da.size - 1]
        }
        return null
    }

    // From MutableMap
    override fun put(key: String, value: DmNPReference<IDmNPData>): DmNPReference<IDmNPData>? {
        if (key == value.get().name)
            if (da.add(value))
                return value
        return null
    }
    override fun remove(key: String): DmNPReference<IDmNPData>? {
        val v = this[key]
        da.remove(v)
        return v
    }
    override fun putAll(from: Map<out String, DmNPReference<IDmNPData>>) {
        for (v in from.values)
            da.add(v)
    }
    override fun clear() = da.clear()
    override val keys: MutableSet<String>
        get() {
            val keys = mutableSetOf<String>()
            for (i in 0 until da.size)
                keys.add(da[i].get().name)
            return keys
        }
    override val values: MutableCollection<DmNPReference<IDmNPData>>
        get() = da.toMutableSet()
    override val entries: MutableSet<MutableMap.MutableEntry<String, DmNPReference<IDmNPData>>>
        get() {
            val m = mutableMapOf<String, DmNPReference<IDmNPData>>()
            for (i in 0 until da.size)
                m[da[i].get().name] = da[i]
            return m.entries
        }

    override fun iterator(): Iterator<DmNPReference<IDmNPData>> = object : Iterator<DmNPReference<IDmNPData>> {
        var i = 0
        override fun hasNext(): Boolean = i != size
        override fun next(): DmNPReference<IDmNPData> = da[i++]
    }
}