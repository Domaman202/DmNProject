package ru.DmN.DmNProject.Data

enum class DmNPNType
{
    N,  // Null
    B,  // Byte
    S,  // Short
    C,  // Char
    I,  // Int
    F,  // Float
    L,  // Long
    D   // Double
}

class DmNPNNull: IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.N
    @Deprecated("Not yet implemented", ReplaceWith("TODO(\"Not yet implemented\")")) override fun set(value: Number) = TODO("Not yet implemented")
    override fun get(): Number = 0

    override fun inc(): IDmNPNumber = DmNPNByte(1)
    override fun dec(): IDmNPNumber = DmNPNByte(-1)
    override fun add(v: IDmNPNumber): IDmNPNumber = v
    override fun sub(v: IDmNPNumber): IDmNPNumber = v
    override fun mul(v: IDmNPNumber): IDmNPNumber = v
    override fun div(v: IDmNPNumber): IDmNPNumber = v

    override inline fun toByte(): Byte      = 0
    override inline fun toChar(): Char      = '\u0000'
    override inline fun toShort(): Short    = 0
    override inline fun toInt(): Int        = 0
    override inline fun toFloat(): Float    = 0F
    override inline fun toLong(): Long      = 0
    override inline fun toDouble(): Double  = 0.0
}

class DmNPNByte(var value: Byte = 0) : IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.B
    override fun set(value: Number) { this.value = value as Byte }
    override fun get(): Byte = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value
    override fun toShort(): Short   = value.toShort()
    override fun toChar(): Char     = value.toChar()
    override fun toInt(): Int       = value.toInt()
    override fun toFloat(): Float   = value.toFloat()
    override fun toLong(): Long     = value.toLong()
    override fun toDouble(): Double = value.toDouble()

}

class DmNPNShort(var value: Short) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.S
    override fun set(value: Number) { this.value = value as Short }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value.toByte()
    override fun toShort(): Short   = value
    override fun toChar(): Char     = value.toChar()
    override fun toInt(): Int       = value.toInt()
    override fun toFloat(): Float   = value.toFloat()
    override fun toLong(): Long     = value.toLong()
    override fun toDouble(): Double = value.toDouble()
}


class DmNPNChar(var value: Char) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.C
    override fun set(value: Number) { this.value = value.toChar() }
    override fun get(): Number = value.toInt()

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value.toByte()
    override fun toShort(): Short   = value.toShort()
    override fun toChar(): Char     = value
    override fun toInt(): Int       = value.toInt()
    override fun toFloat(): Float   = value.toFloat()
    override fun toLong(): Long     = value.toLong()
    override fun toDouble(): Double = value.toDouble()
}

class DmNPNInt(var value: Int) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.I
    override fun set(value: Number) { this.value = value as Int }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value.toByte()
    override fun toShort(): Short   = value.toShort()
    override fun toChar(): Char     = value.toChar()
    override fun toInt(): Int       = value
    override fun toFloat(): Float   = value.toFloat()
    override fun toLong(): Long     = value.toLong()
    override fun toDouble(): Double = value.toDouble()
}

class DmNPNFloat(var value: Float) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.F
    override fun set(value: Number) { this.value = value as Float }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value.toInt().toByte()
    override fun toShort(): Short   = value.toInt().toShort()
    override fun toChar(): Char     = value.toChar()
    override fun toInt(): Int       = value.toInt()
    override fun toFloat(): Float   = value
    override fun toLong(): Long     = value.toLong()
    override fun toDouble(): Double = value.toDouble()
}

class DmNPNLong(var value: Long) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.L
    override fun set(value: Number) { this.value = value as Long }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value.toByte()
    override fun toShort(): Short   = value.toShort()
    override fun toChar(): Char     = value.toChar()
    override fun toInt(): Int       = value.toInt()
    override fun toFloat(): Float   = value.toFloat()
    override fun toLong(): Long     = value
    override fun toDouble(): Double = value.toDouble()
}

class DmNPNDouble(var value: Double) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.D
    override fun set(value: Number) { this.value = value as Double }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)
    override fun add(v: IDmNPNumber): IDmNPNumber {
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
    override fun sub(v: IDmNPNumber): IDmNPNumber {
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
    override fun mul(v: IDmNPNumber): IDmNPNumber {
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

    override fun toByte(): Byte     = value.toInt().toByte()
    override fun toShort(): Short   = value.toInt().toShort()
    override fun toChar(): Char     = value.toChar()
    override fun toInt(): Int       = value.toInt()
    override fun toFloat(): Float   = value.toFloat()
    override fun toLong(): Long     = value.toLong()
    override fun toDouble(): Double = value
}