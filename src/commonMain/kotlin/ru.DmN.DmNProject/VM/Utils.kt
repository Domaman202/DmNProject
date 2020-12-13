package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject
import kotlin.math.roundToLong

typealias kotlin_function = (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit
inline fun <In, reified Out> throwCast(v: In): Out = if (v is Out) v else throw ClassCastException()

/**
 * @author  DomamaN202
 * @since 1.0
 */
class DmNPUtils
{
    companion object {
        fun callFunction(f: Any?, vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) {
            if (f is ArrayList<*>) {
                val mVM = DmNPVMInterpreter()
                mVM.prev.add(vm)
                vm.next.add(mVM)

                mVM.parse(throwCast(f))

                vm.next.remove(mVM)
            } else {
                throwCast<Any?, kotlin_function>(f)(vm, c, ci)
            }
        }

        fun findElement(
            vm: DmNPVM,
            names: ArrayList<String>,
            p: Boolean = true,
            n: Boolean = true
        ): DmNPData? {
            var le: DmNPData? = null
            for (i in 0 until names.size) {
                le = if (le == null) {
                    val r = findElement(vm, names, i, p, n)
                    if (r.second)
                        return r.first
                    else
                        r.first
                } else {
                    val r = findElement(vm, names, le, i, p, n)
                    if (r.second)
                        return r.first
                    else
                        r.first
                }
            }

            return le
        }

        private fun findElement(
            vm: DmNPVM,
            names: ArrayList<String>,
            i: Int,
            p: Boolean = true,
            n: Boolean = true
        ): Pair<DmNPData?, Boolean> {
            var le: DmNPData? = null

            if (vm.heap.containsKey(names[i])) {
                le = vm.heap[names[i]]
            } else {
                le = findPrev(vm, names, le, p)

                if (le == null)
                    le = findNext(vm, names, le, n)

                return Pair(le, true)
            }

            return Pair(le, false)
        }

        private fun findElement(
            vm: DmNPVM,
            names: ArrayList<String>,
            le_: DmNPData?,
            i: Int,
            p: Boolean = true,
            n: Boolean = true
        ): Pair<DmNPData?, Boolean> {
            var le = le_

            le = when (le!!.value) {
                is Any -> throwCast<Any?, Map<String, DmNPData>>(le.value)[names[i]]
                else -> {
                    if (le is DmNPDataObject) le.fm[names[i]]
                    else return Pair(le, true)
                }
            }

            le = findPrev(vm, names, le, p)
            le = findNext(vm, names, le, n)

            return Pair(le, false)
        }

        private fun findPrev(
            vm: DmNPVM,
            names: ArrayList<String>,
            le_: DmNPData?,
            p: Boolean = true
        ): DmNPData? {
            var le = le_

            if (le == null && vm.prev.size > 0 && p) {
                for (v in vm.prev) {
                    le = findElement(v, names, p = true, n = false)

                    if (le != null)
                        break
                }
            } else if (le == null && vm.prev.size > 1 && !p) {
                for (counter in 1..vm.prev.size + 1) {
                    le = findElement(vm.prev[counter], names, p = true, n = false)

                    if (le != null)
                        break
                }
            }

            return le
        }

        private fun findNext(
            vm: DmNPVM,
            names: ArrayList<String>,
            le_: DmNPData?,
            n: Boolean = true
        ): DmNPData? {
            var le = le_

            if (le == null && vm.next.size > 0 && n) {
                for (v in vm.next) {
                    le = findElement(v, names, p = false, n = true)

                    if (le != null)
                        break
                }
            } else if (le == null && vm.next.size > 1 && !n) {
                for (counter in 1..vm.next.size + 1) {
                    le = findElement(vm.next[counter], names, p = true, n = false)

                    if (le != null)
                        break
                }
            }

            return le
        }
    }
}

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