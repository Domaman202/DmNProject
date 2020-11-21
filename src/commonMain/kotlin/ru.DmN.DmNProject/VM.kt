package ru.DmN.DmNProject

import ru.DmN.DmNProject.Data.*

/**
 * @author  DomamaN202
 * @since 1.0
 */
open class DmNPVM
{
    var instance:    DmNPVM  = this
    var prev:        ArrayList<DmNPVM>
    var next:        ArrayList<DmNPVM>

    var stack: Stack<Any>
    var heap: DmNPDataArray

    /**
     * Стандартный конструктор, создаёт полностью новый обьект
     */
    constructor() {
        stack   = Stack()
        heap    = DmNPDataArray()

        prev = ArrayList()
        next = ArrayList()
    }

    /**
     * Конструктор, но поля для обьекта задаёт пользыватель
     */
    constructor(stack: Stack<Any>, heap: DmNPDataArray, prev: ArrayList<DmNPVM>, next: ArrayList<DmNPVM>) {
        this.stack  = stack
        this.heap   = heap

        this.prev = prev
        this.next = next
    }

    /**
     * Клонирует обект
     *
     * @param vm обьект который будем клонировать
     */
    constructor(vm: DmNPVM) {
        this.stack  = vm.stack
        this.heap   = vm.heap
        this.prev   = vm.prev
        this.next   = vm.next
    }

    /**
     * Инициализатор виртуальной машины
     */
    fun init() {
        // Package_System
        val Package_System = DmNPAData("System", DmNPType.PACKAGE)
        Package_System.reference.add(heap.DmNPData())
        Package_System.value = DmNPDataArray()
        heap.add(Package_System)

        val Package_System_Data = Package_System.value as DmNPDataArray
        // Class_Console
        val Class_Console = DmNPAData("Console", DmNPType.CLASS)
        Class_Console.reference.add(Package_System)
        Class_Console.value = DmNPDataArray()
        Package_System_Data.add(Class_Console)

        val Class_Console_Data = Class_Console.value as DmNPDataArray
        // Class_Console functions
        val Method_WriteLine = DmNPAData("println", DmNPType.KMETHOD)
        Method_WriteLine.reference.add(Class_Console)
        Method_WriteLine.value = fun(vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) { println(vm.stack.pop()) }
        Class_Console_Data.add(Method_WriteLine)
    }
}

/**
 * @author  DomamaN202
 * @since 1.0
 */
open class DmNPVMInterpreter : DmNPVM
{
    var e: Boolean = true
    var eStack: Stack<Throwable>? = null
    var um: Boolean = false

    constructor() : super()
    constructor(e: Boolean) : super()
    {
        this.e = e
        if (e)
            eStack = Stack()
    }

    fun parse(code: ArrayList<Any?>) {
        val codeIterator = code.listIterator()

        while (codeIterator.hasNext()) {
            val codeLine = codeIterator.next()
            if (codeLine is IOpCode)
                OpCodeManager.parse(codeLine, this, code, codeIterator)
        }
    }
}

open class DmNPCompiler : DmNPVM()
{
    fun compile(code: ArrayList<Any?>): ArrayList<String>
    {
        val ocl = StringBuilder()
        val oc = ArrayList<String>()

        val ci = code.listIterator()

        while (ci.hasNext()) {
            var cl = ci.next()
            when (cl) {
                OpCodes.LoadConstant -> {
                    ocl.append("LC ")

                    cl = ci.next()
                    if (cl is ArrayList<*>) {
                        ocl.append('[')
                        for (e in cl) {
                            if (e != null) {
                                TypeToString(e, ocl)
                                ocl.append('.')
                                if (e is String)
                                    ocl.append(TF1(e))
                                else
                                    ocl.append(e)
                                ocl.append(',')
                            } else
                                ocl.append("Null")
                        }
                        ocl.deleteAt(ocl.count() - 1)
                        ocl.append(']')
                    } else
                        TypeToString(cl, ocl)

                    oc.add(ocl.toString())
                    ocl.clear()
                }
            }
        }

        return oc
    }

    fun decompile(code: ArrayList<String>): ArrayList<Any?>
    {
        val out = ArrayList<Any?>()
        val ci = code.iterator()

        while (ci.hasNext()) {
            val cl = ci.next()
            val oc = cl.substring(0, cl.indexOfAny(charArrayOf('[', ']', '\'', '\"', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'))).trim()
            val poc = cl.substring(cl.indexOfAny(charArrayOf('[', ']', '\'', '\"', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')), cl.length)
            when (oc) {
                "LC" -> {
                    out.add(OpCodes.LoadConstant)
                    if (poc.indexOf('[') > -1)
                        out.add(ParseStringToArray(poc))
                    else
                        out.add(poc)
                }
            }
        }

        return out
    }

    fun TF1(str: String): String
    {
        val os = StringBuilder()

        for (c in str) {
            if (c == '}') {
                os.append('\\')
                os.append(c)
            } else
                os.append(c)
        }

        return os.toString()
    }

    fun TF2(str: String): String
    {
        val os = StringBuilder()

        var i = 1
        while (i < str.length) {
            if (str[i] == '\\' && str[i + 1] == '}')
                i++
            os.append(str[i++])
        }

        return os.toString()
    }
    fun TF3(e: String, ia: Boolean): Any?
    {
        val t =  e.substring(0, e.indexOf('.'))
        val v = e.substring(e.indexOf('.'))

        when (t) {
            "Boolean"   -> return v.toBoolean()
            "Byte"      -> return v.toByte()
            "Char"      -> return v[0]
            "Short"     -> return v.toShort()
            "Int"       -> return v.toInt()
            "Long"      -> return v.toLong()
            "Double"    -> return v.toDouble()
            "String"    -> return (if (ia) TF2(v) else v)
            "Null"      -> return null
            "Nothing"   -> return null
        }

        return null
    }

    fun ParseStringToArray(str: String): ArrayList<Any?> {
        val out = ArrayList<Any?>()
        val si = str.toCharArray().dropLast(1).iterator()

        while (si.next() != '[');

        while (si.hasNext()) {
            val e = StringBuilder()

            var c = si.next()
            while (c != ',') {
                e.append(c)
                if (si.hasNext()) {
                    c = si.next()
                } else
                    break
            }

            out.add(TF3(e.toString(), true))
        }

        return out
    }

    fun TypeToString(e: Any?, ocl: StringBuilder)
    {
        if (e != null) {
            when (e) {
                is Boolean  -> ocl.append("Boolean")
                is Byte     -> ocl.append("Byte")
                is Char     -> ocl.append("Char")
                is Short    -> ocl.append("Short")
                is Int      -> ocl.append("Int")
                is Long     -> ocl.append("Long")
                is Float    -> ocl.append("Float")
                is Double   -> ocl.append("Double")
                is String   -> ocl.append("String")
                is Nothing  -> ocl.append("Nothing")
            }
        } else
            ocl.append("Null")
    }
}

/**
 * @author  DomamaN202
 * @since 1.0
 */
class DmNPUtils
{
    companion object {
        fun findElement(vm: DmNPVM, names: ArrayList<String>, p: Boolean = true, n: Boolean = true): DmNPData? {
            var le: DmNPData? = null
            for (i in 0 until names.size) {
                if (le == null) {
                    if (vm.heap.containsKey(names[i])) {
                        le = vm.heap[names[i]]
                    } else {
                        if (le == null && vm.prev.size > 0 && p) {
                            for (v in vm.prev) {
                                le = DmNPUtils.findElement(v, names, true, false)

                                if (le != null)
                                    break
                            }
                        } else if (le == null && vm.prev.size > 1 && !p) {
                            for (counter in 1..vm.prev.size + 1) {
                                le = DmNPUtils.findElement(vm.prev[counter], names, true, false)

                                if (le != null)
                                    break
                            }
                        }

                        if (le == null && vm.next.size > 0 && n) {
                            for (v in vm.next) {
                                le = DmNPUtils.findElement(v, names, false, true)

                                if (le != null)
                                    break
                            }
                        } else if (le == null && vm.next.size > 1 && !n) {
                            for (counter in 1..vm.next.size + 1) {
                                le = DmNPUtils.findElement(vm.next[counter], names, true, false)

                                if (le != null)
                                    break
                            }
                        }

                        return le
                    }
                } else {
                    if (le.value != null) {
                        le = (le.value as Map<String, DmNPData>)[names[i]]
                    } else if (le is DmNPDataObject) {
                        le = (le as DmNPDataObject).fm[names[i]]
                    } else return le

                    if (le == null && vm.prev.size > 0 && p) {
                        for (v in vm.prev) {
                            le = DmNPUtils.findElement(v, names, true, false)

                            if (le != null)
                                break
                        }
                    } else if (le == null && vm.prev.size > 1 && !p) {
                        for (counter in 1..vm.prev.size + 1) {
                            le = DmNPUtils.findElement(vm.prev[counter], names, true, false)

                            if (le != null)
                                break
                        }
                    }

                    if (le == null && vm.next.size > 0 && n) {
                        for (v in vm.next) {
                            le = DmNPUtils.findElement(v, names, false, true)

                            if (le != null)
                                break
                        }
                    } else if (le == null && vm.next.size > 1 && !n) {
                        for (counter in 1..vm.next.size + 1) {
                            le = DmNPUtils.findElement(vm.next[counter], names, true, false)

                            if (le != null)
                                break
                        }
                    }
                }
            }

            if (le != null)
                return le
            else
                return null
        }
    }
}