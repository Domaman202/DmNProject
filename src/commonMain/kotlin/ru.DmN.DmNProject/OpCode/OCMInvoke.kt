package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.IDmNPData
import ru.DmN.DmNProject.VM.*

object OCMInvoke {
    fun init() {
        // Invoke
        // Kotlin
        ODCS.OCC["UIK"] = OCInvoke.UnsafeInvokeKotlin
        OpCodeManager.OpCodes[OCInvoke.UnsafeInvokeKotlin] = { _, vm, c, ci ->
            val names = vm.stack.pop()
            if (names is String) {
                throwCast<kotlin_function>(
                    DmNPUtils.findElement(vm, arrayListOf(names))!!.value
                )(vm, c, ci)
            } else {
                throwCast<kotlin_function>(
                    DmNPUtils.findElement(
                        vm,
                        throwCast(names)
                    )!!.value
                )(vm, c, ci)
            }
        }
        // Virtual
        ODCS.OCC["UIV"] = OCInvoke.UnsafeInvokeVirtual
        OpCodeManager.OpCodes[OCInvoke.UnsafeInvokeVirtual] = { _, vm, _, _ ->
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