package ru.DmN.DmNProject.Data

import ru.DmN.DmNProject.VM.*
import kotlin.math.roundToLong

interface IModifiersStorage
interface IReferenceStorage

interface IDmNPNumber
{
    fun inc(): IDmNPNumber
    fun dec(): IDmNPNumber
    //
    fun add(v: IDmNPNumber): IDmNPNumber
    fun sub(v: IDmNPNumber): IDmNPNumber
    fun mul(v: IDmNPNumber): IDmNPNumber
    fun div(v: IDmNPNumber): IDmNPNumber
    //
    fun set(value: Number)
    fun get(): Number
    //
    val type: DmNPNType
    //
    companion object {
        fun of(value: Any?): IDmNPNumber? {
            var n: IDmNPNumber? = null

            if (value is Double) {
                if (value - value.roundToLong() == 0.0) {
                    n = if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                        DmNPNByte(value.toInt().toByte())
                    else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
                        DmNPNShort(value.toInt().toShort())
                    else if (value >= Int.MIN_VALUE && value <= Int.MAX_VALUE)
                        DmNPNInt(value.toInt())
                    else if (value >= Float.MIN_VALUE && value <= Float.MAX_VALUE)
                        DmNPNFloat(value.toFloat())
                    else if (value >= Long.MIN_VALUE && value <= Long.MAX_VALUE)
                        DmNPNLong(value.toLong())
                    else
                        DmNPNDouble(value)
                } else {
                    n = if (value >= Float.MIN_VALUE && value <= Float.MAX_VALUE)
                        DmNPNFloat(value.toFloat())
                    else
                        DmNPNDouble(value)
                }
            } else if (value is Float) {
                n = if (value - value.roundToLong() == 0.0F) {
                    if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                        DmNPNByte(value.toInt().toByte())
                    else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
                        DmNPNShort(value.toInt().toShort())
                    else if (value >= Int.MIN_VALUE && value <= Int.MAX_VALUE)
                        DmNPNInt(value.toInt())
                    else
                        DmNPNFloat(value)
                } else
                    DmNPNFloat(value)
            } else if (value is Long) {
                n = if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                    DmNPNByte(value.toByte())
                else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
                    DmNPNShort(value.toShort())
                else if (value >= Int.MIN_VALUE && value <= Int.MAX_VALUE)
                    DmNPNInt(value.toInt())
                else
                    DmNPNLong(value)
            } else if (value is Int) {
                n = if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                    DmNPNByte(value.toByte())
                else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
                    DmNPNShort(value.toShort())
                else
                    DmNPNInt(value)
            } else if (value is Short) {
                n = if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                    DmNPNByte(value.toByte())
                else
                    DmNPNShort(value)
            } else if (value is Byte)
                n = DmNPNByte(value)

            return n
        }

        fun ofType(value: Any?): IDmNPNumber? {
            return when (value) {
                0 -> DmNPNNull()
                is Byte -> DmNPNByte(value)
                is Short -> DmNPNShort(value)
                is Char -> DmNPNChar(value)
                is Int -> DmNPNInt(value)
                is Float -> DmNPNFloat(value)
                is Double -> DmNPNDouble(value)
                else -> null
            }
        }
    }
}