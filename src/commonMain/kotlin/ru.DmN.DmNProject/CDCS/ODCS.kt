package ru.DmN.DmNProject.CDCS

import ru.DmN.DmNProject.Data.DmNPDVars
import ru.DmN.DmNProject.Data.DmNPType
import ru.DmN.DmNProject.OpCode.IOpCode

object ODCS {
    val OCC = HashMap<String, IOpCode>()

    fun StringToValue(str: String): Any? {
        if (str[0] != '$' || str[1] != '[') {
            val i = str.indexOf(':')

            if (i >= 0) {
                val type = str.substring(0, i)
                val value = str.substring(i + 1)

                return when (type) {
                    "B"     -> value.toBoolean()
                    "BT"    -> value.toByte()
                    "S"     -> value.toShort()
                    "I"     -> value.toInt()
                    "L"     -> value.toLong()
                    "F"     -> value.toFloat()
                    "D"     -> value.toDouble()
                    "ST"    -> value
                    "NULL"  -> null
                    "OC"    -> StringToOpCode   (value)
                    "DV"    -> StringToDV       (value)
                    "T"     -> StringToT        (value)
                    else -> null
                }
            }

            return str
        } else if (str[0] == '$' && str[1] == '[') {
            val result = ArrayList<Any?>()

            val value = StringBuilder()
            var voc = ' '
            var oc = ' '
            var i = 2
            while (i < str.length) {
                var c = str[i]
                //
                if (c == '[' && oc == '$' && voc != '$') {
                    val res = ArrayList<Any?>()

                    var counter = 0
                    for (j in i until str.length) {
                        c = str[j]

                        if (c == '[' && oc == '$' && voc != '$') counter++
                        else if (c == ']' && oc == '$' && voc != '$') {
                            counter--
                            if (counter == 0) {
                                i = j + 1
                                break
                            }
                        } else if (c == ',' && oc == '$' && voc != '$') {
                            res.add(StringToValue(value.toString()))
                            value.clear()
                        } else {
                            if (c == '$' && str[j + 1] == '$')
                                value.append(c)
                            else if (c != '$') {
                                if (c == ',') {
                                    if (str.length >= j - 1 && str[j - 1] != '$')
                                        value.append(c)
                                    else if (str.length >= j - 2 && str[j - 2] == '$')
                                        value.append(c)
                                } else
                                    value.append(c)
                            }
                        }

                        voc = oc
                        oc = c
                    }

                    result.add(res)
                } else if (c == ']' && oc == '$' && voc != '$') {
                    break
                } else if (c == ',' && oc == '$' && voc != '$') {
                    result.add(StringToValue(value.toString()))
                    value.clear()
                } else {
                    if (c == '$' && str[i + 1] == '$')
                        value.append(c)
                    else if (c != '$') {
                        if (c == ',') {
                            if (str.length >= i - 1 && str[i - 1] != '$')
                                value.append(c)
                            else if (str.length >= i - 2 && str[i - 2] == '$')
                                value.append(c)
                        } else
                            value.append(c)
                    }
                }
                //
                voc = oc
                oc = c
                i++
            }

            return result
        }

        return null
    }

    inline fun StringToOpCode(str: String): IOpCode? = OCC[str]

    fun StringToDV(str: String): DmNPDVars {
        return when (str) {
            "Def"           -> DmNPDVars.Default
            "Ext"           -> DmNPDVars.Extendable
            "Ann"           -> DmNPDVars.Annotateble
            "Ref"           -> DmNPDVars.Referencable
            "FM"            -> DmNPDVars.FM
            "EFM"           -> DmNPDVars.EFM
            "RFM"           -> DmNPDVars.RFM
            "AFM"           -> DmNPDVars.AFM
            "REFM"          -> DmNPDVars.REFM
            "AEFM"          -> DmNPDVars.AEFM
            "AREFM"         -> DmNPDVars.AREFM
            else            -> throw Exception()
        }
    }

    fun StringToT(str: String): DmNPType {
        return when (str) {
            "N" -> DmNPType.NULL
            "O" -> DmNPType.OBJECT
            "R" -> DmNPType.REFERENCE

            "V" -> DmNPType.VAR
            "K" -> DmNPType.KMETHOD
            "M" -> DmNPType.METHOD
            "C" -> DmNPType.CLASS
            "P" -> DmNPType.PACKAGE
            else -> throw Exception()
        }
    }
}