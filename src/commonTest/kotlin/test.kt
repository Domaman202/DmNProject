import ru.DmN.DmNProject.CDCS.CS
import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.Stack
import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataVariants
import ru.DmN.DmNProject.Data.DmNPType
import ru.DmN.DmNProject.OpCode.*
import ru.DmN.DmNProject.VM.*
import kotlin.test.Test

/**
 * @author DomamaN202
 * Это примеры-тесты использования DomamaNProject
 */
class testing {
    @Test
    fun packageClassMethodTest() {
        println("Testing started!\n")
        //
        val code = ArrayList<Any?>()

        // Создаём функцию "main" и добавляем ей тело
        // Stack: Empty
        // Heap: Empty
        code.add(OCStack.LoadConstant) // Выгружаем в стек тело функии
        code.add(
            arrayListOf( // *Тело функции*
                OCStack.LoadConstant, // Выполняем выгрузку теста в стек
                "Тееееееееееекст)", // *Текст который мы выгрузили в стек*
                OCStack.LoadConstant, // Выполняем выгрузку имён в стек
                arrayListOf("System", "Console", "println"), // *Именя которые мы выгружаем в стек*
                OCInvoke.UnsafeInvokeKotlin // Вызываем kotlin функцию
            )
        )
        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек именя функции
        code.add("main") // *Имя функции*
        code.add(OCData.CreateMethod) // Создаём функцию
        code.add(OCData.CopySetValue) // Устанавливаем значение из стека в тело функции
        // Stack: [Method "main"]
        // Heap: Empty

        // Создаём обьект "Main" и добавляем в него функцию
        // Stack: [Method "main"]
        // Heap: Empty
        code.add(OCStack.LoadAllConstants) // Выгружаем в стек параметры обьекта "Main"
        code.add(arrayListOf( // *Параметры обьекта "Main"*
            null,
            null,
            DmNPType.OBJECT, // Указываем что мы создаём обьект
            "Main", // *Имя обьекта*
            DmNPDataVariants.FM // Указываем тип обьекта
        ))
        code.add(OCData.CreateObject) // Создаём обьект
        code.add(OCData.CopyAddData) // Закидываем метод "main" в обьект "Main"
        // Stack: [Class "Main"]
        // Heap: Empty

        // Создаём пакеты [ru, DmN, test], загружаем в них обьект "Main" и выгружаем их в heap
        // Stack: [Class "Main"]
        // Heap: Empty
        code.add(OCStack.LoadConstant) // Загружаем имена пакетов
        code.add(arrayListOf("ru", "DmN", "test")) // *Имена пакетов*
        code.add(OCData.CreatePackage) // Создаём пакеты
        code.add(OCStack.CloneStackElement) // Клонируем пакет "ru"
        code.add(OCStackHeap.PushData) // Выкидываем пакет "ru" в heap
        code.add(OCStack.LoadConstant) // Загружаем в стек имена пакетов [DmN, test]
        code.add(arrayListOf("DmN", "test")) // *Имена пакетов*
        code.add(OCData.FindPackage) // Поиск пакета "test" в пакете "ru"
        code.add(OCData.AddToValue) // Закидываем обьект "Main" в пакет "test"

        val vm = DmNPVMInterpreter() // создаём интерпритатор-виртуальную машину
        vm.init() // вызываем инициализацию виртуальной машини
        vm.parse(code) // парсим код
        //
        val ct = ArrayList<Any?>()
        ct.add(OCStack.LoadConstant) // Выгружаем в стек имена
        ct.add(arrayListOf("ru", "DmN", "test", "Main", "main")) // *имена*
        ct.add(OCInvoke.UnsafeInvokeVirtual) // Вызываем метод "main"
        vm.parse(ct) // Парсим код
        //
        println("\n\nTesting completed!")
    }

    @Test
    fun mathTest()
    {
        println("Test started!!!\n\n")
        //
        val code = ArrayList<Any?>()

        code.add(OCStack.LoadConstant)
        code.add(12)
        code.add(OCStack.LoadConstant)
        code.add(21)
        code.add(OCSMath.ADD)
        code.add(OCStack.LoadConstant)
        code.add(35)
        code.add(OCSMath.SUB)

        val vm = DmNPVMInterpreter()
        vm.parse(code)
        //
        println("\n\nTest competed!!!")
    }

    @Test
    fun opExceptionTest() {
        println("Test started!!!\n\n")
        //
        println(ObjectNullPointerException("\"Имя\"").message)
        println(ObjectValueNullPointerException("\"Имя\"").message)
        println(ObjectAccessException("\"Имя\"").message)
        println(ObjectNoStaticException("\"Имя\"").message)
        println(OpCodeNotFoundedException("\"Имя\"").message)
        //
        println("\n\nTest competed!!!")
    }

    @Test
    fun containersTest() {
        println("Test started!!!\n\n")
        // DmNPDataMap Test
        println("!!!DmNPDataMap Test!!!\n")
        val m1 = DmNPDataMap(); m1.clear()
        val m2 = DmNPDataMap(21); m2.clear()
        val m3 = DmNPDataMap(arrayListOf(DmNPData("T1", DmNPType.NULL), DmNPData("T2", DmNPType.NULL)))
        println("m3[\"T2\"] => ${m3["T2"]}")
        println("m3.DmNPData() => ${m3.DmNPData()}")
        println("m3.DmNPData(\"\\\"Имя\\\"\") => ${m3.DmNPData("\"Имя\"")}")
        m3.clear()
        // Stack Test
        println("\n!!!Stack Test!!!\n")
        val st = Stack<Int>()
        st.push(12)
        println("st.peek() => ${st.peek()}")
        st.pop()
        println("st.size => ${st.size}")
        //
        println("\n\nTest competed!!!")
    }

    @Test
    fun test() {
        println("Test started!!!\n\n")
        //
        val v = ODCS.StringToValue(CS.ArrayToString(arrayListOf(123, arrayListOf(null, null, null), 321)))
        println(v)
        //
        println("\n\nTest competed!!!")
    }
}