package ru.DmN.DmNProject

/**
 * @author  DomamaN202
 */
open class DmNPDataArray: MutableMap<String, DmNPData>
{
    // Fields
    var DM: HashMap<String, DmNPData>

    // Constructors
    constructor() {
        DM = HashMap()
    }
    constructor(size: Int) {
        DM = HashMap(size)
    }
    constructor(data: Map<String, DmNPData>) {
        DM = data as HashMap<String, DmNPData>
    }

    // Methods
    fun DmNPData(): DmNPData {
        val data = DmNPData("", DmNPType.REFERENCE)
        data.value = this
        return data
    }

    fun add(data: DmNPData) {
        DM[data.name] = data
    }

    // From map
    override val size
        get() = DM.size
    override fun isEmpty(): Boolean = DM.isEmpty()
    override fun containsKey(key: String): Boolean = DM.containsKey(key)
    override fun containsValue(value: @UnsafeVariance DmNPData): Boolean = DM.containsValue(value)
    override operator fun get(key: String): DmNPData? = DM[key]
    // From MutableMap
    override fun put(key: String, value: DmNPData): DmNPData? = DM.put(key, value)
    override fun remove(key: String): DmNPData? = DM.remove(key)
    override fun putAll(from: Map<out String, DmNPData>) = DM.putAll(from)
    override fun clear() = DM.clear()
    override val keys: MutableSet<String>
        get() = DM.keys
    override val values: MutableCollection<DmNPData>
        get() = DM.values
    override val entries: MutableSet<MutableMap.MutableEntry<String, DmNPData>>
        get() = DM.entries
}

/**
 * @author  DomamaN202
 */
class DmNPDataObject: DmNPData
{
    // Fields and methods
    var fm: DmNPDataArray
    // Extends
    var e: DmNPDataArray
    // Constructors
    constructor(name: String, type: DmNPType): super(name, type)
    {
        this.name = name
        this.type = type

        fm = DmNPDataArray()
        e = DmNPDataArray()
    }
    constructor(name: String, type: DmNPType, fm: DmNPDataArray, e: DmNPDataArray): super(name, type)
    {
        this.name = name
        this.type = type

        this.fm = fm
        this.e = e
    }
}

/**
 * @author  DomamaN202
 */
open class DmNPData
{
    var name: String
    var type: DmNPType
    var modifiers: List<DmNPModifiers>
    open var value: Any? = null
    var reference: DmNPDataArray

    // Constructors
    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     */
    constructor(name: String, type: DmNPType) {
        this.name = name
        this.type = type
        this.reference = DmNPDataArray()
        this.modifiers = ArrayList()
    }

    /**
     * @param name Имя обьекта
     * @param type Тип обьекта
     * @param modifiers Модификаторы обьекта
     */
    constructor(name: String, type: DmNPType, modifiers: List<DmNPModifiers>)
    {
        this.name = name
        this.type = type
        this.reference = DmNPDataArray()
        this.modifiers = modifiers
    }

    // Functions
    /**
     * Добавляет ссылку в список ссылок, если такового нет то создаёт его с последующим добавлением ссылки
     * @param r Ссылка которую вы желаете добавить
     */
    fun addReference(r: DmNPData) {
        if (reference == null)
            reference = DmNPDataArray()
        reference.add(r)
    }
}

/**
 * @author  DomamaN202
 */
enum class DmNPType
{
    NULL,
    OBJECT,
    REFERENCE,

    VAR,
    KMETHOD,
    METHOD,
    CLASS,
    PACKAGE
}

/**
 * @author  DomamaN202
 */
enum class DmNPModifiers
{
    // Access
    PUBLIC,
    PROTECTED,
    PRIVATE,
    //
    STATIC,
    FINAL
}

class Stack<T> {
    val elements: MutableList<T?> = mutableListOf()

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    fun push(item: T?) = elements.add(item)

    fun pop() : T? {
        val item = elements.lastOrNull()
        if (!isEmpty()){
            elements.removeAt(elements.size -1)
        }
        return item
    }
    fun peek() : T? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}

/**
 * @author DomamaN202
 */
interface IDmNPFunction
{
    fun run(vm: DmNPVM, o: DmNPData, c: ArrayList<Any?>, ci: ListIterator<Any?>): Unit
}