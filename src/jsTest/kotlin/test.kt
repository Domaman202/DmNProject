import ru.DmN.DmNProject.Data.DmNPModifiers
import ru.DmN.DmNProject.Reflection.DmNPCompiler
import ru.DmN.DmNProject.VM.DmNPVM
import kotlin.test.Test

class test {
    @Test
    fun test()
    {
        val compiler = DmNPCompiler()
        //
        val m = compiler.MethodCompiler({ vm, c, ci ->
            println("Test!")
        }, arrayListOf(DmNPModifiers.PUBLIC), DmNPVM())
    }
}