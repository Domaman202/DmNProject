package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.Containers.Stack
import ru.DmN.DmNProject.Data.DmNPType
import ru.DmN.DmNProject.Data.IDmNPData
import ru.DmN.DmNProject.Data.IExtending
import ru.DmN.DmNProject.Data.IFMStorage

typealias kotlin_function = (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>, instance: IDmNPData?) -> Unit
inline fun <reified Out> throwCast(v: Any?): Out = if (v is Out) v else throw ClassCastException()

/**
 * @author  DomamaN202
 * @since 1.0
 */
class DmNPUtils
{
    companion object {
        fun callFunction(f: Any?, vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>, instance: IDmNPData) {
            if (f is ArrayList<*>) {
                val mVM = DmNPVMInterpreter()
                mVM.prev.add(vm)
                vm.next.add(mVM)

                mVM.parse(throwCast(f))

                vm.next.remove(mVM)
            } else {
                throwCast<kotlin_function>(f)(vm, c, ci, instance)
            }
        }

        /**
         * Осуществляет поиск обьекта по пакету
         */
        fun findPackage(
            names: ArrayList<String>,
            p: IDmNPData
        ): IDmNPData? {
            var le: IDmNPData? = null

            for (i in 0 until names.size) {
                le = if (i == 0)
                    (p.value as DmNPDataMap)[names[i]]
                else {
                    val name = names[i]
                    val ole = le!!

                    if (ole.type == DmNPType.PACKAGE)
                        (ole.value as DmNPDataMap)[name]
                    else
                        return ole
                }
            }

            return le
        }

        /**
         * Осуществляет поиск элемента в VM
         * @param vm Виртуальная машина в которой будем проводить поиск
         * @param names Имена элементов по которым будет вестись поиск
         * @param p Парамент разрешает/запрещает (по умолчанию разрешает) использовать преведущие VM для поиска элемента
         * @param n Парамент разрешает/запрещает (по умолчанию разрешает) использовать следующей VM для поиска элемента
         * @param le_ Текущий элемент (если есть)
         */
        fun findElement(
            vm: DmNPVM?,
            names_: Any,
            p: Boolean = true,
            n: Boolean = true,
            le_: IDmNPData? = null
        ): IDmNPData? {
            var le = le_

            val names = if (names_ is String)
                arrayListOf(names_)
            else
                throwCast(names_)

            for (i in 0 until names.size) {
                val name = names[i]

                if (i == 0 && le == null) {
                    le = vm!!.heap[name]

                    if (le == null) {
                        if (p)
                            le = findPrev(vm, names, le)

                        if (le == null && n)
                            le = findNext(vm, names, le)
                    }

                    if (le == null)
                        return null
                } else {
                    val result = findWithElement(le, name)

                    if (result.second)
                        return result.first
                    le = result.first
                }
            }

            return le
        }

        /**
         * Осуществяет поиск обьекта в текущем обьекте
         * @param le_ Текущий обьект
         * @param name Имя нужного обьекта
         */
        fun findWithElement(le_: IDmNPData?, name: String): Pair<IDmNPData?, Boolean> {
            var le = le_
            val ole = le!!
            if (le.type == DmNPType.PACKAGE) {
                le = (le.value as DmNPDataMap)[name]
            }

            if ((le == null || le == ole) && ole is IFMStorage) {
                le = ole.fm[name]
            }

            if ((le == null || le == ole) && ole is IExtending) {
                le = findWithExtends(ole, name)
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

        /**
         * Осуществяет поиск обьекта *ТРЕБУЕТСЯ ДОПИСАТЬ*
         * @param ole Обьект в котором будет осуществляться поиск
         * @param name Имя нужного обьекта
         */
        fun findWithExtends(ole: IExtending, name: String): IDmNPData {
            var le: IDmNPData?

            for (e in ole.ext) {
                le = (e.get() as IFMStorage).fm[name]

                if (le == null && e.get() is IExtending)
                    le = findWithExtends(e.get() as IExtending, name)
                else if (le != null)
                    return le
            }

            return ole as IDmNPData
        }

        fun findWith(
            e: IDmNPData,
            name: String
        ): IDmNPData? {
            var result: IDmNPData? = null

            if (e is IFMStorage)
                result = e.fm[name]
            else if (e is IExtending) {
                for (ext in e.ext) {
                    findWith(ext.get(), name)
                    if (result != null) break
                }
            }

            return result
        }
    }
}

class DmNPReference<T>(val setter: (value: T) -> Unit = { }, val getter: () -> T)
{
    fun set(value: T)   = setter(value)
    fun get(): T        = getter()
}