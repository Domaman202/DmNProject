package ru.DmN.DmNProject.CDCS

import ru.DmN.DmNProject.OpCode.IOpCode

object CS {
    fun OCToString(oc: IOpCode): String {
        for (e in ODCS.OCC.entries) {
            if (e.value == oc)
                return "OC:${e.key}"
        }
        return "NULL:"
    }

    fun ValueToString(value: Any?): String {
        return when (value) {
            is Boolean  -> "B:$value"
            is Byte     -> "BT:$value"
            is Short    -> "S:$value"
            is Int      -> "I:$value"
            is Long     -> "L:$value"
            is Float    -> "F:$value"
            is Double   -> "D:$value"
            is String   -> "ST:$value"
            is List<*>  -> ArrayToString(value)
            is IOpCode  -> OCToString(value)
            else -> "NULL:"
        }
    }

    fun ArrayToString(arr: List<*>): String {
        val result = StringBuilder()

        result.append("$[")

        for (e in arr) {
            if (e is String) {
                var r: String = e

                var oc = ' '
                var j = 0
                for (i in e.indices) {
                    if ((e[i] == '[' || e[i] == ']' || e[i] == ',') && oc == '$') {
                        r = r.substring(0, i + j) + '$' + r.substring(i + j)
                        j++
                    }
                    oc = e[i]
                }

                result.append("ST:")
                result.append(r)
            } else
                result.append(ValueToString(e))
            result.append("$,")
        }

        result.append("$]")

        return result.toString()
    }
}