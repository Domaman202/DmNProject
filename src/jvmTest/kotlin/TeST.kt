import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.CDCS.simpleSlice
import ru.DmN.DmNProject.CDCS.smartSlice
import ru.DmN.DmNProject.OpCode.*
import ru.DmN.DmNProject.VM.DmNPVM
import ru.DmN.DmNProject.VM.DmNPVMInterpreter
import ru.DmN.DmNProject.VM.throwCast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object JVMT {
    @JvmStatic
    fun main(args: Array<String>) {
        val fn = readLine()!!
        //
//        lowLevelFileTest()
        mediumLevelFileTest(fn)
        eval(fn)
        //
//        mediumLevelEvalTest()
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

    private fun mediumLevelEvalTest() {
        val file = FileInputStream(File("${readLine()}.ML4auka"))
        //
        val c = ArrayList<Any?>()
        //
        OCManager.init()
        //
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
                            c.add(OCStack.LoadConstant)
                            c.add(ODCS.StringToValue(value.trimStart()))
                        }
                        "callK" -> {
                            c.add(OCStack.LoadConstant)
                            c.add(ODCS.StringToValue(value.trimStart()))
                            c.add(OCInvoke.UnsafeInvokeKotlin)
                        }
                        "callV" -> {
                            c.add(OCStack.LoadConstant)
                            c.add(ODCS.StringToValue(value.trimStart()))
                            c.add(OCInvoke.UnsafeInvokeVirtual)
                        }
                        "create" -> {
                            for (v in smartSlice(value, ' ').reversed())
                                if (v != "") {
                                    c.add(OCStack.LoadConstant)
                                    c.add(ODCS.StringToValue(v.trimStart()))
                                }
                            c.add(OCData.CreateObject)
                        }
                        "load" -> {
                            c.add(OCStack.LoadConstant)
                            c.add(ODCS.StringToValue(value.trimStart()))
                            c.add(OCStackHeap.LoadData)
                        }
                        "push" -> {
                            c.add(OCStackHeap.PushData)
                        }
                        "GF" -> {
                            c.add(OCStack.LoadConstant)
                            c.add(ODCS.StringToValue(value.trimStart()))
                            c.add(OCData.CopyGetData)
                        }
                    }
                } else {
                    if (line[1] == '*') {
                        while (true) {
                            i++
                            if (lines[i] == "*/") break
                        }
                    } else if (line[1] != '/') {
                        c.add(ODCS.StringToOpCode(lines[i].slice(4 until lines[i].length)))
                    }
                }
            }

            i++
        }
        //
        val vm = DmNPVMInterpreter()
        vm.init()
        //
        vm.parse(c)
    }

    private fun mediumLevelFileTest(fn: String) {
        val file = FileInputStream(File("${fn}.ML4auka"))
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
                            result.append("OC:LC$,${value.trimStart()}")
                        }
                        "callK" -> {
                            result.append("OC:LC$,${value.trimStart()}$,OC:UIK")
                        }
                        "callV" -> {
                            result.append("OC:LC$,${value.trimStart()}$,OC:UIV")
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
        val stream = FileOutputStream(File("$fn.C4auka"))
        stream.write(result.toString().toByteArray())
        stream.close()
    }

    fun eval(fn: String) {
        val stream = FileInputStream(File("$fn.C4auka"))
        //
        val vm = DmNPVMInterpreter()
        vm.init()
        //
        vm.parse(throwCast(ODCS.StringToValue(stream.readBytes().decodeToString())))
        //
        stream.close()
    }
}