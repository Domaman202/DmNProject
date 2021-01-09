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
            arrayListOf(
                OCStack.LoadConstant, // Выполняем выгрузку теста в стек
                "Тееееееееееекст)", // Текст который мы выгрузили в стек
                OCStack.LoadConstant, // Выполняем выгрузку имён в стек
                arrayListOf("System", "Console", "println"), // Именя которые мы выгружаем в стек
                OCInvoke.UnsafeInvokeKotlin // Вызываем kotlin функцию
            )
        )

        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек именя функции
        code.add("main") // Имя функции
        code.add(OCData.CreateMethod) // Создаём функцию

        code.add(OCData.CopySetValue) // Устанавливаем значение из стека в тело функции
        // Stack: [Method "main"]
        // Heap: Empty

        // Создаём класс "Main" и добавляем в него функцию
        // Stack: [Method "main"]
        // Heap: Empty
        code.add(OCStack.LoadAllConstants)
        code.add(arrayListOf(
            null,
            null,
            DmNPType.CLASS,
            "Main",
            DmNPDataVariants.FM
        ))
        code.add(OCData.CreateObject)
        code.add(OCData.CopyAddData)
        // Stack: [Class "Main"]
        // Heap: Empty

        // Создаём пакеты [ru, DmN, test], загружаем в них класс "Main" и выгружаем их в heap
        // Stack: [Class "Main"]
        // Heap: Empty
        code.add(OCStack.LoadConstant)
        code.add(arrayListOf("ru", "DmN", "test"))
        code.add(OCData.CreatePackage)
        code.add(OCStack.CloneStackElement)
        code.add(OCStackHeap.PushData)
        code.add(OCStack.LoadConstant)
        code.add(arrayListOf("DmN", "test"))
        code.add(OCData.FindPackage)
        code.add(OCData.AddToValue)

        val vm = DmNPVMInterpreter() // создаём интерпритатор-виртуальную машину
        vm.init() // вызываем инициализацию виртуальной машини
        vm.parse(code) // парсим код
        //
        val ct = ArrayList<Any?>()
        ct.add(OCStack.LoadConstant)
        ct.add(arrayListOf("ru", "DmN", "test", "Main", "main"))
        ct.add(OCInvoke.UnsafeInvokeVirtual)
        vm.parse(ct)
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

        //
        println("\n\nTest competed!!!")
    }
}