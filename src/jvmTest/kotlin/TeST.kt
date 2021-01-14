import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.VM.DmNPVMInterpreter
import ru.DmN.DmNProject.VM.throwCast
import java.io.File
import java.io.FileInputStream

object JVMT {
    @JvmStatic
    fun main(args: Array<String>) {
        val file = FileInputStream(File("in.4auka"))
        val result = StringBuffer()
        //
        result.append("$[")
        for (line in file.reader().readLines()) {
            if (line != "")
                result.append("$line$,")
        }
        result.append("$]")
        //
        val vm = DmNPVMInterpreter()
        vm.init()
        //
        vm.parse(throwCast(ODCS.StringToValue(result.toString())))
    }
}