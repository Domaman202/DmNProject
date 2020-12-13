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
    override fun set(value: Number) = TODO("Not yet implemented")
    override fun get(): Number = 0

    override fun inc(): IDmNPNumber = DmNPNByte(1)
    override fun dec(): IDmNPNumber = DmNPNByte(-1)
    override fun add(v: IDmNPNumber): IDmNPNumber = v
    override fun sub(v: IDmNPNumber): IDmNPNumber = v
    override fun mul(v: IDmNPNumber): IDmNPNumber = v
    override fun div(v: IDmNPNumber): IDmNPNumber = v
}

class DmNPNByte(var value: Byte = 0) : IDmNPNumber {
    override val type: DmNPNType get() = DmNPNType.B
    override fun set(value: Number) { this.value = value as Byte }
    override fun get(): Byte = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)!!
        }
    }
}

class DmNPNShort(var value: Short) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.S
    override fun set(value: Number) { this.value = value as Short }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)!!
        }
    }
}


class DmNPNChar(var value: Char) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.C
    override fun set(value: Number) { this.value = value.toChar() }
    override fun get(): Number = value.toInt()

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value.toInt())!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value.toInt())!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value.toInt())!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value.toInt())!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value.toInt())!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value.toInt())!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value.toInt())!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value.toInt())!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value.toInt())!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value.toInt())!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value.toInt())!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value.toInt())!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value.toInt())!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value.toInt())!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value.toInt())!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value.toInt())!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value.toInt())!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value.toInt())!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value.toInt())!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value.toInt())!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value.toInt())!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value.toInt())!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value.toInt())!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value.toInt())!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value.toInt())!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value.toInt())!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value.toInt())!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value.toInt())!!
        }
    }
}

class DmNPNInt(var value: Int) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.I
    override fun set(value: Number) { this.value = value as Int }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)!!
        }
    }
}

class DmNPNFloat(var value: Float) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.F
    override fun set(value: Number) { this.value = value as Float }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)!!
        }
    }
}

class DmNPNLong(var value: Long) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.L
    override fun set(value: Number) { this.value = value as Long }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)!!
        }
    }
}

class DmNPNDouble(var value: Double) : IDmNPNumber
{
    override val type: DmNPNType get() = DmNPNType.D
    override fun set(value: Number) { this.value = value as Double }
    override fun get(): Number = value

    override fun inc(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun dec(): IDmNPNumber = IDmNPNumber.of(value++)!!
    override fun add(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    + value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   + value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     + value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   + value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    + value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  + value)!!
        }
    }
    override fun sub(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    - value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   - value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     - value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   - value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    - value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  - value)!!
        }
    }
    override fun mul(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    * value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   * value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     * value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   * value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    * value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  * value)!!
        }
    }
    override fun div(v: IDmNPNumber): IDmNPNumber {
        return when (v.type) {
            DmNPNType.N -> v
            DmNPNType.B -> IDmNPNumber.of(v.get() as Byte    / value)!!
            DmNPNType.S -> IDmNPNumber.of(v.get() as Short   / value)!!
            DmNPNType.C -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.I -> IDmNPNumber.of(v.get() as Int     / value)!!
            DmNPNType.F -> IDmNPNumber.of(v.get() as Float   / value)!!
            DmNPNType.L -> IDmNPNumber.of(v.get() as Long    / value)!!
            DmNPNType.D -> IDmNPNumber.of(v.get() as Double  / value)!!
        }
    }
}