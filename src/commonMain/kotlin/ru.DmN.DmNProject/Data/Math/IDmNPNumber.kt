package ru.DmN.DmNProject.Data.Math

import kotlin.math.roundToLong

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
interface IDmNPNumber {
    fun toByte():   Byte
    fun toShort():  Short
    fun toChar():   Char
    fun toInt():    Int
    fun toFloat():  Float
    fun toLong():   Long
    fun toDouble(): Double
    //
    operator fun inc(): IDmNPNumber
    operator fun dec(): IDmNPNumber
    //
    operator fun plus(v: IDmNPNumber):  IDmNPNumber
    operator fun plus(v: Byte):         IDmNPNumber
    operator fun plus(v: Short):        IDmNPNumber
    operator fun plus(v: Int):          IDmNPNumber
    operator fun plus(v: Float):        IDmNPNumber
    operator fun plus(v: Long):         IDmNPNumber
    operator fun plus(v: Double):       IDmNPNumber
    operator fun minus(v: IDmNPNumber): IDmNPNumber
    operator fun minus(v: Byte):        IDmNPNumber
    operator fun minus(v: Short):       IDmNPNumber
    operator fun minus(v: Int):         IDmNPNumber
    operator fun minus(v: Float):       IDmNPNumber
    operator fun minus(v: Long):        IDmNPNumber
    operator fun minus(v: Double):      IDmNPNumber
    operator fun times(v: IDmNPNumber): IDmNPNumber
    operator fun times(v: Byte):        IDmNPNumber
    operator fun times(v: Short):       IDmNPNumber
    operator fun times(v: Int):         IDmNPNumber
    operator fun times(v: Float):       IDmNPNumber
    operator fun times(v: Long):        IDmNPNumber
    operator fun times(v: Double):      IDmNPNumber
    operator fun div(v: IDmNPNumber):   IDmNPNumber
    operator fun div(v: Byte):          IDmNPNumber
    operator fun div(v: Short):         IDmNPNumber
    operator fun div(v: Int):           IDmNPNumber
    operator fun div(v: Float):         IDmNPNumber
    operator fun div(v: Long):          IDmNPNumber
    operator fun div(v: Double):        IDmNPNumber
    //
    fun set(value: Number)
    fun get(): Number
    //
    val type: DmNPNType
    //
    companion object {
        fun of(value: Any?): IDmNPNumber {
            return when (value) {
                is IDmNPNumber  -> return value
                is Double       -> ofDouble(value)
                is Float        -> ofFloat(value)
                is Long         -> ofLong(value)
                is Int          -> ofInt(value)
                is Short        -> ofShort(value)
                is Byte         -> ofByte(value)
                else            -> DmNPNNull()
            }
        }

        fun ofDouble(value: Double): IDmNPNumber {
            return if (value - value.roundToLong() == 0.0) {
                if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
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
                if (value >= Float.MIN_VALUE && value <= Float.MAX_VALUE)
                    DmNPNFloat(value.toFloat())
                else
                    DmNPNDouble(value)
            }
        }

        fun ofFloat(value: Float): IDmNPNumber {
            return if (value - value.roundToLong() == 0.0F) {
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
        }

        fun ofLong(value: Long): IDmNPNumber {
            return if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                DmNPNByte(value.toByte())
            else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
                DmNPNShort(value.toShort())
            else if (value >= Int.MIN_VALUE && value <= Int.MAX_VALUE)
                DmNPNInt(value.toInt())
            else
                DmNPNLong(value)
        }

        inline fun ofInt(value: Int): IDmNPNumber {
            return if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                DmNPNByte(value.toByte())
            else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
                DmNPNShort(value.toShort())
            else
                DmNPNInt(value)
        }

        inline fun ofShort(value: Short): IDmNPNumber {
            return if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
                DmNPNByte(value.toByte())
            else
                DmNPNShort(value)
        }

        inline fun ofByte(value: Byte): IDmNPNumber {
            return DmNPNByte(value)
        }

        fun ofType(value: Any?): IDmNPNumber? {
            return when (value) {
                0               -> DmNPNNull()
                is Byte         -> DmNPNByte(value)
                is Short        -> DmNPNShort(value)
                is Char         -> DmNPNChar(value)
                is Int          -> DmNPNInt(value)
                is Float        -> DmNPNFloat(value)
                is Double       -> DmNPNDouble(value)
                is IDmNPNumber  -> value
                else            -> null
            }
        }
    }
}