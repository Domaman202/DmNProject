package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject
import ru.DmN.DmNProject.Data.IDmNPData
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
        ): IDmNPData? {
            var le: IDmNPData? = null
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
        ): Pair<IDmNPData?, Boolean> {
            var le: IDmNPData? = null

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
            le_: IDmNPData?,
            i: Int,
            p: Boolean = true,
            n: Boolean = true
        ): Pair<IDmNPData?, Boolean> {
            var le = le_

            le = when (le!!.value) {
                is Any -> throwCast<Any?, Map<String, IDmNPData>>(le.value)[names[i]]
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
            le_: IDmNPData?,
            p: Boolean = true
        ): IDmNPData? {
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
            le_: IDmNPData?,
            n: Boolean = true
        ): IDmNPData? {
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

class DmNPReference<T>(val setter: (value: T) -> Unit, val getter: () -> T)
{
    fun set(value: T)   = setter(value)
    fun get(): T        = getter()
}