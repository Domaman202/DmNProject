package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.Containers.*
import ru.DmN.DmNProject.Data.*

/**
 * @author  DomamaN202
 */
open class DmNPVM
{
    var instance:    DmNPVM
    var prev:        ArrayList<DmNPVM>
    var next:        ArrayList<DmNPVM>

    var stack: Stack<Any>
    var heap: DmNPDataMap

    /**
     * Стандартный конструктор, создаёт полностью новый обьект
     */
    constructor() {
        stack   = Stack()
        heap    = DmNPDataMap()

        instance    = this
        prev        = ArrayList()
        next        = ArrayList()
    }

    /**
     * Конструктор, но поля для обьекта задаёт пользыватель
     */
    constructor(stack: Stack<Any>, heap: DmNPDataMap, prev: ArrayList<DmNPVM>, next: ArrayList<DmNPVM>) {
        this.stack  = stack
        this.heap   = heap

        this.instance   = this
        this.prev       = prev
        this.next       = next
    }

    /**
     * Клонирует обект
     *
     * @param vm обьект который будем клонировать
     */
    constructor(vm: DmNPVM) {
        this.stack      = vm.stack
        this.heap       = vm.heap
        this.instance   = vm.instance
        this.prev       = vm.prev
        this.next       = vm.next
    }

    /**
     * Инициализатор виртуальной машины
     */
//    fun fastInit() {
//
//    }

    fun init() {
        // Package_System
        val ps = DmNPAData("System", DmNPType.PACKAGE)
        ps.reference.add(heap.DmNPData())
        ps.value = DmNPDataMap()
        heap.add(ps)

        val psd = ps.value as DmNPDataMap
        // Class_Console
        val cc = DmNPAData("Console", DmNPType.CLASS)
        cc.reference.add(ps)
        cc.value = DmNPDataMap()
        psd.add(cc)

        val ccd = cc.value as DmNPDataMap
        // Class_Console functions
        val mwl = DmNPAData("println", DmNPType.KMETHOD)
        mwl.reference.add(cc)
        mwl.value = fun(vm: DmNPVM, _: ArrayList<Any?>, _: ListIterator<Any?>) { println(vm.stack.pop()) }
        ccd.add(mwl)
    }
}