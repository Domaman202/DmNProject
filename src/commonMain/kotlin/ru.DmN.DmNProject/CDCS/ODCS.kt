package ru.DmN.DmNProject.CDCS

object ODCS {
    fun StringToOC(str: String) {

    }

    fun StringToValue(str: String): Any? {
        if (str[0] != '$' || str[1] != '[') {
            val i = str.indexOf(':')
            val type = str.substring(0, i)
            val value = str.substring(i + 1)

            return when (type) {
                "B" -> value.toBoolean()
                "BT" -> value.toByte()
                "S" -> value.toShort()
                "I" -> value.toInt()
                "L" -> value.toLong()
                "F" -> value.toFloat()
                "D" -> value.toDouble()
                "ST" -> value
                "NULL" -> null
                else -> null
            }
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
}