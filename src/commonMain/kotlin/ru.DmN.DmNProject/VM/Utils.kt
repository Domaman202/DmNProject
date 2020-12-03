package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject

typealias kotlin_function = (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit
inline fun <In, reified Out> throwCast(v: In): Out = if (v is Out) v else throw ClassCastException()

/**
 * @author  DomamaN202
 * @since 1.0
 */
class DmNPUtils
{
    companion object {
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