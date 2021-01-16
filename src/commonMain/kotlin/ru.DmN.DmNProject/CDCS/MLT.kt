package ru.DmN.DmNProject.CDCS

import ru.DmN.DmNProject.VM.DmNPVMInterpreter
import ru.DmN.DmNProject.VM.throwCast

object MLT {
    fun eval(vm: DmNPVMInterpreter, str: String) {
        val script = StringBuilder()
        //
        script.append("$[")
        if (str[0] != '/') {
            val r = simpleSlice(str, ' ')
            val code = r.first
            val value = r.second

            var b = true
            when (code) {
                "push" -> {
                    script.append("OC:LC")
                }
                "callK" -> {
                    b = false

                    script.append("OC:LC$,")
                    script.append("${value.trimStart()}$,")
                    script.append("OC:UIK")
                }
                "callV" -> {
                    b = false

                    script.append("OC:LC$,")
                    script.append("${value.trimStart()}$,")
                    script.append("OC:UIV")
                }
                "create" -> {
                    b = false

                    for (v in smartSlice(value, ' ').reversed())
                        if (v != "")
                            script.append("OC:LC$,${v.trimStart()}$,")
                    script.append("OC:CO")
                }
                "load" -> {
                    b = false

                    script.append("OC:LC$,${value.trimStart()}$,OC:LD")
                }
            }
            script.append("$,")

            if (b) script.append("${value.trimStart()}$,")
        } else {
            if (str[1] != '/')
                script.append("${str.slice(1 until str.length)}$,")
        }
        script.append("$]")

        vm.parse(throwCast(ODCS.StringToValue(script.toString())))
    }
}

fun simpleSlice(str: String, c: Char): Pair<String, String> {
    val i = str.indexOf(c)
    return if (i >= 0)
        Pair(str.slice(0 until i), str.slice(i until str.length))
    else
        Pair(str, "")
}

fun smartSlice(str: String, splitter: Char): ArrayList<String> {
    var b = false

    val l = StringBuilder()
    val r = ArrayList<String>()
    for (i in str.indices) {
        val c = str[i]
        if (c == splitter && !b) {
            r.add(l.toString())
            l.clear()
        } else {
            if (c == '"' && (i == 0 || str[i - 1] != '\\'))
                b = !b
            else
                l.append(c)
        }
    }

    r.add(l.toString())

    return r
}