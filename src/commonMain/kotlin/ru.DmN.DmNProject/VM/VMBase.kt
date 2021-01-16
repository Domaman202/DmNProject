package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.DmNPRDataMap
import ru.DmN.DmNProject.Data.Containers.Stack
import ru.DmN.DmNProject.OpCode.OCManager

/**
 * @author  DomamaN202
 */
open class DmNPVM
{
    var instance:    DmNPVM
    var prev:        ArrayList<DmNPVM>
    var next:        ArrayList<DmNPVM>

    var stack: Stack<Any?>
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
     * @param stack стек
     * @param heap хип обьектов
     * @param prev список преведущих виртуальных машин
     * @param next список следующий виртуальных машин
     */
    constructor(stack: Stack<Any?>, heap: DmNPDataMap, prev: ArrayList<DmNPVM>, next: ArrayList<DmNPVM>) {
        this.stack  = stack
        this.heap   = heap

        this.instance   = this
        this.prev       = prev
        this.next       = next
    }

    /**
     * Копирует обект
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
     * Полная инициализация всех систем
     */
    fun init() {
        fastInit()
        initSystem()
    }

    /**
     * Быстрая инициализация - инициализируются только важные компоненты
     */
    fun fastInit() {
        OCManager.init()
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
            DmNPRDataMap(DmNPReference({ }, { classClass }))
        )
        heap.add(objectObject)
        // math functions init
        objectObject.fm.add(DmNPData("Inc", DmNPType.KMETHOD))
    }

    /**
     * Инициализация системных утилит
     */
    fun initSystem() {
        //
        val packageSystem = DmNPEFMData(
            "System",
            DmNPType.CLASS,
            null,
            null,
            DmNPRDataMap(DmNPReference({ }, { heap["Object"]!! }))
        )
        packageSystem.fm.add(packageSystem)
        heap.add(packageSystem)
        //
        val classConsole = DmNPEFMData(
            "Console",
            DmNPType.CLASS,
            null,
            null,
            DmNPRDataMap(DmNPReference({ }, { heap["Object"]!! }))
        )
        packageSystem.fm.add(classConsole)
        //
        classConsole.fm.add(DmNPData("println", DmNPType.KMETHOD, fun (vm: DmNPVM, _: ArrayList<Any?>, _: ListIterator<Any?>, _: IDmNPData) = println(vm.stack.pop())))
    }

    /**
     * Обьект компаньон
     */
    companion object {
        /**
         * Чистая виртуальная машина проинициализированная через fast init
         */
        val fastIVM = DmNPVM()
        /**
         * Чистая виртуальная машина проинициализированная через init (полная инициализация)
         */
        val fullIVM = DmNPVM()

        init {
            fastIVM.fastInit()
            fullIVM.init()
        }
    }
}