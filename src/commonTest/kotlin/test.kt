import ru.DmN.DmNProject.*
import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.OpCode.*
import ru.DmN.DmNProject.VM.*
import kotlin.reflect.cast
import kotlin.reflect.safeCast
import kotlin.test.Test

/**
 * @author DomamaN202
 * Это примеры-тесты использования DomamaNProject
 */
class testing
{
    @Test
    fun PackageClassMethodTest() {
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
                "Дарова, Пасаны!!!", // Текст который мы выгрузили в стек
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
    fun test()
    {
        val vm = DmNPVM()
        vm.init()

        var o = DmNPDataId("Test", DmNPType.NULL, vm)
    }
}