package ru.DmN.DmNProject.CDCS

import ru.DmN.DmNProject.Data.DmNPDVars
import ru.DmN.DmNProject.Data.DmNPType
import ru.DmN.DmNProject.OpCode.IOpCode

object CS {
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
            is IOpCode  -> OCToString   (value)
            is DmNPDVars-> DVToString   (value)
            is DmNPType -> TToString    (value)
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

    fun DVToString(v: DmNPDVars): String {
        return "DV:" + when (v) {
            DmNPDVars.Default       -> "Def"
            DmNPDVars.Extendable    -> "Ext"
            DmNPDVars.Annotateble   -> "Ann"
            DmNPDVars.Referencable  -> "Ref"
            DmNPDVars.FM            -> "FM"
            DmNPDVars.EFM           -> "EFM"
            DmNPDVars.RFM           -> "RFM"
            DmNPDVars.AFM           -> "AFM"
            DmNPDVars.REFM          -> "REFM"
            DmNPDVars.AEFM          -> "AEFM"
            DmNPDVars.AREFM         -> "AREFM"
        }
    }

    fun OCToString(oc: IOpCode): String {
        for (e in ODCS.OCC.entries) {
            if (e.value == oc)
                return "OC:${e.key}"
        }
        return "NULL:"
    }

    fun TToString(t: DmNPType): String {
        return "T:" + when (t) {
            DmNPType.NULL       -> "N"
            DmNPType.OBJECT     -> "O"
            DmNPType.REFERENCE  -> "R"

            DmNPType.VAR        -> "V"
            DmNPType.KMETHOD    -> "K"
            DmNPType.METHOD     -> "M"
            DmNPType.CLASS      -> "C"
            DmNPType.PACKAGE    -> "P"
        }
    }
}