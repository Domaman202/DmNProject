package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.VM.*

object OCMInvoke {
    fun init() {
        // Invoke
        // Kotlin
        OpCodeManager.OpCodes[OCInvoke.UnsafeInvokeKotlin] = { _, vm, c, ci ->
            throwCast<Any?, kotlin_function>(
                DmNPUtils.findElement(
                    vm,
                    throwCast(vm.stack.pop())
                )!!.value
            )(vm, c, ci)
        }
        // Virtual
        OpCodeManager.OpCodes[OCInvoke.UnsafeInvokeVirtual] = { _, vm, _, _ ->
            val m = DmNPUtils.findElement(vm, throwCast(vm.stack.pop()))
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