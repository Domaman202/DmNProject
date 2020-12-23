package ru.DmN.DmNProject.Data.Math

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNNull: IDmNPNumber {
    override inline val type: DmNPNType get() = DmNPNType.N
    @Deprecated("Not yet implemented", ReplaceWith("")) override fun set(value: Number) = TODO("Not yet implemented")
    override inline fun get(): Number = 0

    override inline fun inc() = DmNPNByte(1)
    override inline fun dec() = DmNPNByte(-1)
    override inline fun plus(v: IDmNPNumber)            = v
    override inline fun plus(v: Byte):      IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun plus(v: Short):     IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun plus(v: Int):       IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun plus(v: Float):     IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun plus(v: Long):      IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun plus(v: Double):    IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun minus(v: IDmNPNumber)           = v
    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v)!!
    override inline fun times(v: IDmNPNumber)           = v
    override inline fun times(v: Byte):     IDmNPNumber = DmNPNNull()
    override inline fun times(v: Short):    IDmNPNumber = DmNPNNull()
    override inline fun times(v: Int):      IDmNPNumber = DmNPNNull()
    override inline fun times(v: Float):    IDmNPNumber = DmNPNNull()
    override inline fun times(v: Long):     IDmNPNumber = DmNPNNull()
    override inline fun times(v: Double):   IDmNPNumber = DmNPNNull()
    override inline fun div(v: IDmNPNumber)             = v
    override inline fun div(v: Byte):       IDmNPNumber = DmNPNNull()
    override inline fun div(v: Short):      IDmNPNumber = DmNPNNull()
    override inline fun div(v: Int):        IDmNPNumber = DmNPNNull()
    override inline fun div(v: Float):      IDmNPNumber = DmNPNNull()
    override inline fun div(v: Long):       IDmNPNumber = DmNPNNull()
    override inline fun div(v: Double):     IDmNPNumber = DmNPNNull()

    override inline fun toByte(): Byte      = 0
    override inline fun toChar(): Char      = '\u0000'
    override inline fun toShort(): Short    = 0
    override inline fun toInt(): Int        = 0
    override inline fun toFloat(): Float    = 0F
    override inline fun toLong(): Long      = 0
    override inline fun toDouble(): Double  = 0.0
}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNByte(var value: Byte = 0) : IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.B
    //
    override fun set(value: Number) { this.value = value as Byte }
    override fun get(): Byte = value
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    //
    override inline fun toByte(): Byte     = value
    override inline fun toShort(): Short   = value.toShort()
    override inline fun toChar(): Char     = value.toChar()
    override inline fun toInt(): Int       = value.toInt()
    override inline fun toFloat(): Float   = value.toFloat()
    override inline fun toLong(): Long     = value.toLong()
    override inline fun toDouble(): Double = value.toDouble()

}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNShort(var value: Short) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.S
    //
    override fun set(value: Number) { this.value = value as Short }
    override fun get(): Number = value
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    //
    override inline fun toByte(): Byte     = value.toByte()
    override inline fun toShort(): Short   = value
    override inline fun toChar(): Char     = value.toChar()
    override inline fun toInt(): Int       = value.toInt()
    override inline fun toFloat(): Float   = value.toFloat()
    override inline fun toLong(): Long     = value.toLong()
    override inline fun toDouble(): Double = value.toDouble()
}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNChar(var value: Char) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.C
    //
    override fun set(value: Number) { this.value = value.toChar() }
    override fun get(): Number = value.toInt()
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value.toInt())
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value.toInt())
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value.toInt())
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value.toInt())
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value.toInt())
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value.toInt())
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value.toInt())
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value.toInt())
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value.toInt())
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value.toInt())
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value.toInt())
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value.toInt())
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value.toInt())
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value.toInt())
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value.toInt())!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value.toInt())!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value.toInt())!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value.toInt())!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value.toInt())!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value.toInt())!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value.toInt())
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value.toInt())
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value.toInt())
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value.toInt())
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value.toInt())
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value.toInt())
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value.toInt())
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value.toInt())!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value.toInt())!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value.toInt())!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value.toInt())!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value.toInt())!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value.toInt())!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value.toInt())
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value.toInt())
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value.toInt())
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value.toInt())
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value.toInt())
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value.toInt())
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value.toInt())
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value.toInt())!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value.toInt())!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value.toInt())!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value.toInt())!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value.toInt())!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value.toInt())!!
    //
    override inline fun toByte(): Byte     = value.toByte()
    override inline fun toShort(): Short   = value.toShort()
    override inline fun toChar(): Char     = value
    override inline fun toInt(): Int       = value.toInt()
    override inline fun toFloat(): Float   = value.toFloat()
    override inline fun toLong(): Long     = value.toLong()
    override inline fun toDouble(): Double = value.toDouble()
}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNInt(var value: Int) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.I
    //
    override fun set(value: Number) { this.value = value as Int }
    override fun get(): Number = value
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    //
    override inline fun toByte():   Byte    = value.toByte()
    override inline fun toShort():  Short   = value.toShort()
    override inline fun toChar():   Char    = value.toChar()
    override inline fun toInt():    Int     = value
    override inline fun toFloat():  Float   = value.toFloat()
    override inline fun toLong():   Long    = value.toLong()
    override inline fun toDouble(): Double  = value.toDouble()
}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNFloat(var value: Float) : IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.F
    //
    override fun set(value: Number) { this.value = value as Float }
    override fun get(): Number = value
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte + value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short + value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int + value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int + value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float + value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long + value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double + value)
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte - value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short - value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int - value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int - value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float - value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long - value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double - value)
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte * value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short * value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int * value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int * value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float * value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long * value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double * value)
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte / value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short / value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int / value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int / value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float / value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long / value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double / value)
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    //
    override inline fun toByte():   Byte = value.toInt().toByte()
    override inline fun toShort():  Short = value.toInt().toShort()
    override inline fun toChar():   Char = value.toChar()
    override inline fun toInt():    Int = value.toInt()
    override inline fun toFloat():  Float = value
    override inline fun toLong():   Long = value.toLong()
    override inline fun toDouble(): Double = value.toDouble()
}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNLong(var value: Long) : IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.L
    //
    override fun set(value: Number) { this.value = value as Long }
    override fun get(): Number = value
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte + value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short + value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int + value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int + value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float + value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long + value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double + value)
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte - value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short - value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int - value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int - value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float - value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long - value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double - value)
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte * value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short * value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int * value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int * value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float * value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long * value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double * value)
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte / value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short / value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int / value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int / value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float / value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long / value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double / value)
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    //
    override inline fun toByte():   Byte    = value.toByte()
    override inline fun toShort():  Short   = value.toShort()
    override inline fun toChar():   Char    = value.toChar()
    override inline fun toInt():    Int     = value.toInt()
    override inline fun toFloat():  Float   = value.toFloat()
    override inline fun toLong():   Long    = value
    override inline fun toDouble(): Double  = value.toDouble()
}

@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
class DmNPNDouble(var value: Double) : IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.D
    //
    override fun set(value: Number) { this.value = value as Double }
    override fun get(): Number = value
    //
    override inline fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override inline fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    //
    override fun plus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte + value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short + value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int + value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int + value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float + value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long + value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double + value)
        }
    }

    override inline fun plus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    override inline fun plus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v + value)!!
    //
    override fun minus(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte - value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short - value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int - value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int - value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float - value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long - value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double - value)
        }
    }

    override inline fun minus(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    override inline fun minus(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v - value)!!
    //
    override fun times(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte * value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short * value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int * value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int * value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float * value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long * value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double * value)
        }
    }

    override inline fun times(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    override inline fun times(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v * value)!!
    //
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte / value)
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short / value)
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int / value)
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int / value)
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float / value)
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long / value)
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double / value)
        }
    }

    override inline fun div(v: Byte):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Short):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Int):      IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Float):    IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Long):     IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    override inline fun div(v: Double):   IDmNPNumber = IDmNPNumber.ofType(v / value)!!
    //
    override inline fun toByte():   Byte    = value.toInt().toByte()
    override inline fun toShort():  Short   = value.toInt().toShort()
    override inline fun toChar():   Char    = value.toChar()
    override inline fun toInt():    Int     = value.toInt()
    override inline fun toFloat():  Float   = value.toFloat()
    override inline fun toLong():   Long    = value.toLong()
    override inline fun toDouble(): Double  = value
}