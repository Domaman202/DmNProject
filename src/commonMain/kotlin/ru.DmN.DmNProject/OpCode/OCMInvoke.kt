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
        OpCodeManager.OpCodes[OCInvoke.InvokeKotlin] = { _, vm, c, ci ->
            try {
                throwCast<Any?, kotlin_function>(
                    DmNPUtils.findElement(
                        vm,
                        throwCast(vm.stack.pop())
                    )!!.value
                )(vm, c, ci)
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCInvoke.InvokeStaticKotlin] = { _, vm, c, ci ->
            try {
                throwCast<Any?, kotlin_function>(
                    DmNPUtils.findElement(
                        vm,
                        throwCast(vm.stack.pop())
                    )!!.value
                )(vm, c, ci)
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
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
        OpCodeManager.OpCodes[OCInvoke.InvokeVirtual] = { _, vm, _, _ ->
            try {
                val n = throwCast<Any?, ArrayList<String>>(vm.stack.pop())
                val m = DmNPUtils.findElement(vm, n)
                if (m != null) {
                    val mVM = DmNPVMInterpreter()
                    mVM.prev.add(vm)
                    vm.next.add(mVM)

                    mVM.parse(throwCast(m.value))

                    vm.next.remove(mVM)
                } else {
                    throw ObjectNullPointerException(n[n.size])
                }
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCInvoke.InvokeStaticVirtual] = { _, vm, _, _ ->
            try {
                val n = throwCast<Any?, ArrayList<String>>(vm.stack.pop())
                val m = DmNPUtils.findElement(vm, n)
                if (m != null) {
                    val mVM = DmNPVMInterpreter()
                    mVM.prev.add(vm)
                    vm.next.add(mVM)

                    mVM.parse(throwCast(m.value))

                    vm.next.remove(mVM)
                } else {
                    throw ObjectNullPointerException(n[n.size])
                }
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
    }
}