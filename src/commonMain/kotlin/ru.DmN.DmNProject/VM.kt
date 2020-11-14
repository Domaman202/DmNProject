package ru.DmN.DmNProject

open class DmNPVM
{
    var instance:    DmNPVM  = this
    var prev:        ArrayList<DmNPVM>
    var next:        ArrayList<DmNPVM>

    var stack:   Stack<Any>
    var heap:    DmNPDataArray

    constructor() {
        stack   = Stack()
        heap    = DmNPDataArray()

        prev = ArrayList()
        next = ArrayList()
    }

    constructor(stack: Stack<Any>, heap: DmNPDataArray, prev: ArrayList<DmNPVM>, next: ArrayList<DmNPVM>) {
        this.stack  = stack
        this.heap   = heap

        this.prev = prev
        this.next = next
    }

    fun init() {
        // Package_System
        val Package_System = DmNPData("System", DmNPType.PACKAGE)
        Package_System.reference.add(heap.DmNPData())
        Package_System.value = DmNPDataArray()
        heap.add(Package_System)

        val Package_System_Data = Package_System.value as DmNPDataArray
        // Class_Console
        val Class_Console = DmNPData("Console", DmNPType.CLASS)
        Class_Console.reference.add(Package_System)
        Class_Console.value = DmNPDataArray()
        Package_System_Data.add(Class_Console)

        val Class_Console_Data = Class_Console.value as DmNPDataArray
        // Class_Console functions
        val Method_WriteLine = DmNPData("println", DmNPType.KMETHOD)
        Method_WriteLine.reference.add(Class_Console)
        Method_WriteLine.value = fun(vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) { println(vm.stack.pop()) }
        Class_Console_Data.add(Method_WriteLine)
    }

    //
    fun add_prev_vm(vm: DmNPVM) {
        prev.add(vm)
    }
    fun add_next_vm(vm: DmNPVM) {
        next.add(vm)
    }

    fun remove_prev_vm(vm: DmNPVM) {
        prev.remove(vm)
    }
    fun remove_next_vm(vm: DmNPVM) {
        next.remove(vm)
    }
}

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
            if (codeLine is OpCodes)
                OpCodeManager.parse(codeLine, this, code, codeIterator)
        }
    }
}

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