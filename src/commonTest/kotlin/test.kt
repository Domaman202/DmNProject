import ru.DmN.DmNProject.*
import kotlin.jvm.Synchronized
import kotlin.test.Test

/**
 * @author DomamaN202
 * Это примеры-тесты использования DomamaNProject
 */
class testing
{
    @Test
    fun StacHeapDataTest() {
        println("Testing started!\n")
        //
        val code = ArrayList<Any?>()

        code.add(OpCodes.LoadConstant) // Загружаем в стек хрень ниже
        code.add(arrayListOf("ru", "DmN", "testing")) // Хрень для загрузки
        code.add(OpCodes.LoadConstant) // Загружаем в стек хрень ниже
        code.add(arrayListOf(DmNPModifiers.PUBLIC)) // Модификаторы
        code.add(OpCodes.CreatePackage) // Создаём пакет и пихаем в стек

        code.add(OpCodes.PushData) // Пихаем последний элемент стека в глобальную памяти

        code.add(OpCodes.LoadConstant)
        code.add(arrayListOf("ru", "DmN", "testing")) // пихаем в код элементы для загрузки
        code.add(OpCodes.LoadData) // загружаем элементы из глобальной памяти

        val vm = DmNPVMInterpreter() // создаём интерпритатор-виртуальную машину
        vm.init() // вызываем инициализацию виртуальной машини
        vm.parse(code) // парсим код
        //
        println("\n\nTesting completed!")
    }

    @Test
    fun PackageClassMethodTest() {
        println("Testing started!\n")
        //
        val code = ArrayList<Any?>()

        code.add(OpCodes.LoadConstant) // Выполняем выгрузку в стек имён пакетов
        code.add(arrayListOf("ru", "DmN", "testing")) // Имена пакетов которые отправятся в стек
        code.add(OpCodes.CloneStackElement) // Клонируем текущий элемент стека ( текущий элемент стека дублируеться )
        code.add(OpCodes.CreatePackage) // Создаём пакеты ( удаляеться текущий элемент стека )
        code.add(OpCodes.PushData) // Загружаем пакеты в стек ( текущий элемент стека перемещаеться в heap )
        code.add(OpCodes.LoadData) // Выгружаем пакет из стека ( удаляеться текущий элемент стека )

        code.add(OpCodes.LoadConstant) // Выполняем выгрузку в стек модификаторов класса
        code.add(arrayListOf(DmNPModifiers.PUBLIC)) // Модификаторы класса
        code.add(OpCodes.LoadConstant) // Выполняем выгрузку в стек имя класса
        code.add("Main") // Имя класса
        code.add(OpCodes.CreateClass) // Создаём класс

        code.add(OpCodes.LoadConstant) // Выполняем выгрузку в стек модификаторов функции
        code.add(arrayListOf(DmNPModifiers.PUBLIC)) // Модификаторы функции
        code.add(OpCodes.LoadConstant) // Выполняем выгрузку в стек именя функции
        code.add("main") // Имя функции
        code.add(OpCodes.CreateMethod) // Создаём функцию

        code.add(OpCodes.LoadConstant) // Выгружаем в стек тело функии
        code.add(arrayListOf(
            OpCodes.LoadConstant,
            "Дарова, Пасаны!!!",
            OpCodes.LoadConstant,
            arrayListOf("System", "Console", "println"),
            OpCodes.UnsafeInvokeKotlin
        ))
        code.add(OpCodes.SetValue) // Устанавливаем значение из стека в тело функции
        //
        val vm = DmNPVMInterpreter() // создаём интерпритатор-виртуальную машину
        vm.init() // вызываем инициализацию виртуальной машини
        vm.parse(code) // парсим код
        //
        val ct = ArrayList<Any?>()
        ct.add(OpCodes.LoadConstant)
        ct.add(arrayListOf("ru", "DmN", "testing", "Main", "main"))
        ct.add(OpCodes.UnsafeInvokeVirtual)
        vm.parse(ct)
        //
        println("\n\nTesting completed!")
    }

    @Test
    fun test() {
        //
        val code = ArrayList<Any?>()

        code.add(OpCodes.LoadConstant) // Выполняем выгрузку в стек имён пакетов
        code.add(arrayListOf("ru", "DmN", "testing")) // Имена пакетов которые отправятся в стек
        //
        val cvm = DmNPCompiler()
        val out = cvm.compile(code)
        val cc = cvm.decompile(out)
    }

    @Test
    fun test_() {
        println("\'\"[]{}0123456789".indexOfAny(charArrayOf('[', ']', '{', '}')))
    }
}