package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.IDmNPData
import ru.DmN.DmNProject.VM.*

object OCMInvoke {
    fun init() {
        // Invoke
        // Kotlin
        ODCS.OCC["UIK"] = OCInvoke.UnsafeInvokeKotlin
        OCManager.OC[OCInvoke.UnsafeInvokeKotlin] = { _, vm, c, ci ->
            val names = vm.stack.pop()
                if (names is String) {
                val f = DmNPUtils.findElement(vm, arrayListOf(names))!!
                throwCast<kotlin_function>(
                    f.value
                )(vm, c, ci, f)
            } else if (names is List<*>) {
                val o = DmNPUtils.findElement(vm, throwCast(names.subList(0, names.lastIndex - 1)))!!
                throwCast<kotlin_function>(
                    DmNPUtils.findWith(o, names[names.lastIndex] as String)!!.value
                )(vm, c, ci, o)
            }
        }
        // Virtual
        ODCS.OCC["UIV"] = OCInvoke.UnsafeInvokeVirtual
        OCManager.OC[OCInvoke.UnsafeInvokeVirtual] = { _, vm, _, _ ->
            val names = vm.stack.pop()

            val m: IDmNPData? = if (names is String)
                DmNPUtils.findElement(vm, arrayListOf(names))
            else
                DmNPUtils.findElement(vm, throwCast(names))

            if (m != null) {
                val mVM = DmNPVMInterpreter()
                mVM.prev.add(vm)
                vm.next.add(mVM)

                mVM.parse(throwCast(m.value))

                vm.next.remove(mVM)
            }
        }
    }
}