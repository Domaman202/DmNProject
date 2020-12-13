import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.Stack
import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPModifiers
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

        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек имён пакетов
        code.add(arrayListOf("ru", "DmN", "testing")) // Имена пакетов которые отправятся в стек
        code.add(OCStack.CloneStackElement) // Клонируем текущий элемент стека ( текущий элемент стека дублируеться )
        code.add(OCData.CreatePackage) // Создаём пакеты ( удаляеться текущий элемент стека )
        code.add(OCStackHeap.PushData) // Загружаем пакеты в стек ( текущий элемент стека перемещаеться в heap )
        code.add(OCStackHeap.LoadData) // Выгружаем пакет из стека ( удаляеться текущий элемент стека )

        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек модификаторов класса
        code.add(arrayListOf(DmNPModifiers.PUBLIC)) // Модификаторы класса
        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек имя класса
        code.add("Main") // Имя класса
        code.add(OCData.CreateClass) // Создаём класс

        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек модификаторов функции
        code.add(arrayListOf(DmNPModifiers.PUBLIC)) // Модификаторы функции
        code.add(OCStack.LoadConstant) // Выполняем выгрузку в стек именя функции
        code.add("main") // Имя функции
        code.add(OCData.CreateMethod) // Создаём функцию

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
        code.add(OCData.SetValue) // Устанавливаем значение из стека в тело функции
        //
        val vm = DmNPVMInterpreter() // создаём интерпритатор-виртуальную машину
        vm.init() // вызываем инициализацию виртуальной машини
        vm.parse(code) // парсим код
        //
        val ct = ArrayList<Any?>()
        ct.add(OCStack.LoadConstant)
        ct.add(arrayListOf("ru", "DmN", "testing", "Main", "main"))
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
        code.add(OCMath.Add)
        code.add(OCStack.LoadConstant)
        code.add(35)
        code.add(OCMath.Sub)

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