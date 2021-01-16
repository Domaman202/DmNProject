import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.CDCS.simpleSlice
import ru.DmN.DmNProject.CDCS.smartSlice
import ru.DmN.DmNProject.VM.DmNPVMInterpreter
import ru.DmN.DmNProject.VM.throwCast
import java.io.File
import java.io.FileInputStream

object JVMT {
    @JvmStatic
    fun main(args: Array<String>) {
//        lowLevelFileTest()
        mediumLevelFileTest()
    }

    private fun lowLevelFileTest() {
        val file = FileInputStream(File("${readLine()}.LL4auka"))
        //
        val result = StringBuffer()
        //
        result.append("$[")
        val lines = file.reader().readLines()
        var i = 0
        while (i < lines.size) {
            val line = lines[i]
            if (line != "")
                if (line[0] != '/') {
                    result.append("$line$,")
                } else if (line[1] == '*')
                    while (true) {
                        i++
                        if (lines[i] == "*/") break
                    }
            i++
        }
        result.append("$]")
        //
        val vm = DmNPVMInterpreter()
        vm.init()
        //
        vm.parse(throwCast(ODCS.StringToValue(result.toString())))
    }

    private fun mediumLevelFileTest() {
        val file = FileInputStream(File("${readLine()}.ML4auka"))
        //
        val result = StringBuffer()
        //
        result.append("$[")
        val lines = file.reader().readLines()
        var i = 0
        while (i < lines.size) {
            val line = lines[i]
            if (line != "") {
                if (line[0] != '/') {
                    val r = simpleSlice(line, ' ')
                    val code = r.first
                    val value = r.second

                    when (code) {
                        "LC" -> {
                            result.append("OC:LC")
                            result.append("${value.trimStart()}$,")
                        }
                        "callK" -> {
                            result.append("OC:LC$,")
                            result.append("${value.trimStart()}$,")
                            result.append("OC:UIK")
                        }
                        "callV" -> {
                            result.append("OC:LC$,")
                            result.append("${value.trimStart()}$,")
                            result.append("OC:UIV")
                        }
                        "create" -> {
                            for (v in smartSlice(value, ' ').reversed())
                                if (v != "")
                                    result.append("OC:LC$,${v.trimStart()}$,")
                            result.append("OC:CO")
                        }
                        "load" -> {
                            result.append("OC:LC$,${value.trimStart()}$,OC:LD")
                        }
                        "push" -> {
                            result.append("OC:PD")
                        }
                        "GF" -> {
                            result.append("OC:LC$,${value.trimStart()}$,OC:CGD")
                        }
                    }
                    result.append("$,")
                } else {
                    if (line[1] == '*')
                        while (true) {
                            i++
                            if (lines[i] == "*/") break
                        }
                    else if (line[1] != '/')
                        result.append("${lines[i].slice(1 until lines[i].length)}$,")
                }
            }

            i++
        }

        result.append("$]")
        //
        val vm = DmNPVMInterpreter()
        vm.init()
        //
        vm.parse(throwCast(ODCS.StringToValue(result.toString())))
    }
}