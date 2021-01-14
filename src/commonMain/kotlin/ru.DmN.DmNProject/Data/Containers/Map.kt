package ru.DmN.DmNProject.Data.Containers

import ru.DmN.DmNProject.Data.*

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
    fun DmNPData(): IDmNPData {
        val data = DmNPData("", DmNPType.REFERENCE)
        data.value = this
        return data
    }
    fun DmNPData(name: String): DmNPData {
        val data = DmNPData(name, DmNPType.REFERENCE)
        data.value = this
        return data
    }
    //
    fun add(data: IDmNPData) = put(data.name, data)
    override fun iterator(): DmNPDataIterator {
        return object : DmNPDataIterator {
            val size: Int get() = da.size
            var c: Int = 0

            override fun prevName(): String = da[--c].name
            override fun lastName(): String = da[c].name
            override fun nextName(): String = da[c++].name

            override fun prevType(): DmNPType = da[--c].type
            override fun lastType(): DmNPType = da[c].type
            override fun nextType(): DmNPType = da[++c].type

            override fun prevValue(): Any? = da[--c].value
            override fun lastValue(): Any? = da[c].value
            override fun nextValue(): Any? = da[++c].value

            override fun prevAnnotations(): ArrayList<IDmNPData>? = if (da[--c] is IAnnotationStorage) (da[c] as IAnnotationStorage).annotations else null
            override fun lastAnnotations(): ArrayList<IDmNPData>? = if (da[c]   is IAnnotationStorage) (da[c] as IAnnotationStorage).annotations else null
            override fun nextAnnotations(): ArrayList<IDmNPData>? = if (da[++c] is IAnnotationStorage) (da[c] as IAnnotationStorage).annotations else null

            override fun prevReference(): DmNPDataMap? = if (da[--c] is IReferenceStorage) (da[c] as IReferenceStorage).reference else null
            override fun lastReference(): DmNPDataMap? = if (da[c] is IReferenceStorage) (da[c] as IReferenceStorage).reference else null
            override fun nextReference(): DmNPDataMap? = if (da[++c] is IReferenceStorage) (da[c] as IReferenceStorage).reference else null

            override fun hasNext():     Boolean = c < size
            override fun next():        IDmNPData = da[++c]
            override fun nextIndex():   Int = c + 1

            override fun last():        IDmNPData = da[c]
            override fun lastIndex():   Int = c

            override fun hasPrevious():     Boolean = c > 0
            override fun previous():        IDmNPData = da[--c]
            override fun previousIndex():   Int = c - 1
        }
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

        if (parent && result == null && this.instance != null) {
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