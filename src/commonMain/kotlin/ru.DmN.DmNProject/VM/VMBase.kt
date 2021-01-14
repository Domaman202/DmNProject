package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDObjectMap
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.Stack
import ru.DmN.DmNProject.Data.Math.*
import ru.DmN.DmNProject.OpCode.OpCodeManager

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

    fun init() {
        fastInit()
        initSystem()
    }

    fun fastInit() {
        OpCodeManager.init()
        // function "println" init
        val functionPrintln = DmNPData(
            "println",
            DmNPType.KMETHOD,
            fun (vm: DmNPVM, _: ArrayList<Any?>, _: ListIterator<Any?>, _: IDmNPData) = println(vm.stack.pop())
        )
        heap.add(functionPrintln)
        // class "Class" init
        val classClass = DmNPRFMData(
            "Class",
            DmNPType.CLASS,
            null,
            null,
            DmNPDataMap(heap.DmNPData())
        )
        heap.add(classClass)
        // class "Object" init
        val objectObject = DmNPEFMData(
            "Object",
            DmNPType.CLASS,
            null,
            null,
            arrayListOf(DmNPReference({ }, { classClass }))
        )
        heap.add(objectObject)
        // math functions init
        objectObject.fm.add(DmNPData("Inc", DmNPType.KMETHOD))
    }

    fun initSystem() {
        //
        val packageSystem = DmNPEFMData(
            "System",
            DmNPType.CLASS,
            null,
            null,
            arrayListOf(DmNPReference({ }, { heap["Object"]!! }))
        )
        packageSystem.fm.add(packageSystem)
        heap.add(packageSystem)
        //
        val classConsole = DmNPEFMData(
            "Console",
            DmNPType.CLASS,
            null,
            null,
            arrayListOf(DmNPReference({ }, { heap["Object"]!! }))
        )
        packageSystem.fm.add(classConsole)
        //
        classConsole.fm.add(DmNPData("println", DmNPType.KMETHOD, fun (vm: DmNPVM, _: ArrayList<Any?>, _: ListIterator<Any?>, _: IDmNPData) = println(vm.stack.pop())))
    }
}