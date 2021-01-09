package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.Stack
import ru.DmN.DmNProject.Data.DmNPType
import ru.DmN.DmNProject.Data.IDmNPData
import ru.DmN.DmNProject.Data.IExtending
import ru.DmN.DmNProject.Data.IFMStorage

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

        fun findPackage(
            names: ArrayList<String>,
            p: IDmNPData
        ): IDmNPData? {
            var le: IDmNPData? = null

            for (i in 0 until names.size) {
                if (i == 0)
                    le = (p.value as DmNPDataMap)[names[i]]
                else {
                    val name = names[i]
                    val ole = le!!; le = null

                    if (ole.type == DmNPType.PACKAGE)
                        le = (ole.value as DmNPDataMap)[name]
                    else
                        return ole
                }
            }

            return le
        }

        fun findElement(
            vm: DmNPVM,
            names: ArrayList<String>,
            p: Boolean = true,
            n: Boolean = true,
            le_: IDmNPData? = null
        ): IDmNPData? {
            var le = le_

            for (i in 0 until names.size) {
                val name = names[i]

                if (i == 0) {
                    le = vm.heap[name]

                    if (le == null) {
                        if (p)
                            le = findPrev(vm, names, le)

                        if (le == null && n)
                            le = findNext(vm, names, le)
                    }

                    if (le == null)
                        return null
                } else {
                    val result = f1(le, name)

                    if (result.second)
                        return result.first
                    le = result.first
                }
            }

            return le
        }

        private fun f1(le_: IDmNPData?, name: String): Pair<IDmNPData?, Boolean> {
            var le = le_
            val ole = le!!
            if (le.type == DmNPType.PACKAGE) {
                le = (le.value as DmNPDataMap)[name]
            }

            if ((le == null || le == ole) && ole is IFMStorage) {
                le = ole.fm[name]
            }

            if ((le == null || le == ole) && ole is IExtending) {
                le = findE(ole, name)
            }

            if (le == null)
                return Pair(ole, true)
            return Pair(le, false)
        }

        private fun findPrev(vm: DmNPVM, names: ArrayList<String>, ole: IDmNPData?): IDmNPData? {
            for (prev in vm.prev) {
                val le = findElement(prev, names, n = false)

                if (le != null)
                    return le
            }

            return ole
        }

        private fun findNext(vm: DmNPVM, names: ArrayList<String>, ole: IDmNPData?): IDmNPData? {
            for (next in vm.next) {
                val le = findElement(next, names, p = false)

                if (le != null)
                    return le
            }

            return ole
        }

        private fun findE(ole: IExtending, name: String): IDmNPData? {
            var le: IDmNPData? = null

            for (e in ole.ext) {
                le = e.get().fm[name]

                if (le == null)
                    le = findE(e.get(), name)
            }

            return le
        }

//        fun findElement(
//            vm: DmNPVM,
//            names: ArrayList<String>,
//            p: Boolean = true,
//            n: Boolean = true
//        ): IDmNPData? {
//            var le: IDmNPData? = null
//            for (i in 0 until names.size) {
//                le = if (le == null) {
//                    val r = findElement(vm, names, i, p, n)
//                    if (r.second)
//                        return r.first
//                    else
//                        r.first
//                } else {
//                    val r = findElement(vm, names, le, i, p, n)
//                    if (r.second)
//                        return r.first
//                    else
//                        r.first
//                }
//            }
//
//            return le
//        }
//
//        private fun findElement(
//            vm: DmNPVM,
//            names: ArrayList<String>,
//            i: Int,
//            p: Boolean = true,
//            n: Boolean = true
//        ): Pair<IDmNPData?, Boolean> {
//            var le: IDmNPData? = null
//
//            if (vm.heap.containsKey(names[i])) {
//                le = vm.heap[names[i]]
//            } else {
//                le = findPrev(vm, names, le, p)
//
//                if (le == null)
//                    le = findNext(vm, names, le, n)
//
//                return Pair(le, true)
//            }
//
//            return Pair(le, false)
//        }
//
//        private fun findElement(
//            vm: DmNPVM,
//            names: ArrayList<String>,
//            le_: IDmNPData?,
//            i: Int,
//            p: Boolean = true,
//            n: Boolean = true
//        ): Pair<IDmNPData?, Boolean> {
//            var le = le_
//
//            le = when (le!!.value) {
//                is Any -> throwCast<Any?, Map<String, IDmNPData>>(le.value)[names[i]]
//                else -> {
//                    if (le is IFMStorage) le.fm!![names[i]]
//                    else return Pair(le, true)
//                }
//            }
//
//            le = findPrev(vm, names, le, p)
//            le = findNext(vm, names, le, n)
//
//            return Pair(le, false)
//        }
//
//        private fun findPrev(
//            vm: DmNPVM,
//            names: ArrayList<String>,
//            le_: IDmNPData?,
//            p: Boolean = true
//        ): IDmNPData? {
//            var le = le_
//
//            if (le == null && vm.prev.size > 0 && p) {
//                for (v in vm.prev) {
//                    le = findElement(v, names, p = true, n = false)
//
//                    if (le != null)
//                        break
//                }
//            } else if (le == null && vm.prev.size > 1 && !p) {
//                for (counter in 1..vm.prev.size + 1) {
//                    le = findElement(vm.prev[counter], names, p = true, n = false)
//
//                    if (le != null)
//                        break
//                }
//            }
//
//            return le
//        }
//
//        private fun findNext(
//            vm: DmNPVM,
//            names: ArrayList<String>,
//            le_: IDmNPData?,
//            n: Boolean = true
//        ): IDmNPData? {
//            var le = le_
//
//            if (le == null && vm.next.size > 0 && n) {
//                for (v in vm.next) {
//                    le = findElement(v, names, p = false, n = true)
//
//                    if (le != null)
//                        break
//                }
//            } else if (le == null && vm.next.size > 1 && !n) {
//                for (counter in 1..vm.next.size + 1) {
//                    le = findElement(vm.next[counter], names, p = true, n = false)
//
//                    if (le != null)
//                        break
//                }
//            }
//
//            return le
//        }
    }
}

class DmNPReference<T>(val setter: (value: T) -> Unit, val getter: () -> T)
{
    fun set(value: T)   = setter(value)
    fun get(): T        = getter()
}