package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject
import ru.DmN.DmNProject.Data.IDmNPNumber
import ru.DmN.DmNProject.VM.DmNPUtils
import ru.DmN.DmNProject.VM.throwCast

object OCMMath {
    fun init() {
        // Math
        // Stack
        OpCodeManager.OpCodes[OCSMath.Inc] = { _, vm, _, _ ->
            try {
                var v = vm.stack.pop()

                if (v is Number)
                    v = IDmNPNumber.ofType(v)

                vm.stack.push((v as IDmNPNumber).inc())
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.Dec] = { _, vm, _, _ ->
            try {
                var v = vm.stack.pop()

                if (v is Number)
                    v = IDmNPNumber.ofType(v)

                vm.stack.push((v as IDmNPNumber).dec())
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.Add] = { _, vm, _, _ ->
            try {
                var v1 = vm.stack.pop()
                var v2 = vm.stack.pop()
                //
                if (v1 is Number)
                    v1 = IDmNPNumber.ofType(v1)
                if (v2 is Number)
                    v2 = IDmNPNumber.ofType(v2)
                //
                vm.stack.push((v1 as IDmNPNumber).add(v2 as IDmNPNumber))
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.Sub] = { _, vm, _, _ ->
            try {
                var v1 = vm.stack.pop()
                var v2 = vm.stack.pop()
                //
                if (v1 is Number)
                    v1 = IDmNPNumber.ofType(v1)
                if (v2 is Number)
                    v2 = IDmNPNumber.ofType(v2)
                //
                vm.stack.push((v1 as IDmNPNumber).sub(v2 as IDmNPNumber))
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.Mul] = { _, vm, _, _ ->
            try {
                var v1 = vm.stack.pop()
                var v2 = vm.stack.pop()
                //
                if (v1 is Number)
                    v1 = IDmNPNumber.ofType(v1)
                if (v2 is Number)
                    v2 = IDmNPNumber.ofType(v2)
                //
                vm.stack.push((v1 as IDmNPNumber).mul(v2 as IDmNPNumber))
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.Div] = { _, vm, _, _ ->
            try {
                var v1 = vm.stack.pop()
                var v2 = vm.stack.pop()
                //
                if (v1 is Number)
                    v1 = IDmNPNumber.ofType(v1)
                if (v2 is Number)
                    v2 = IDmNPNumber.ofType(v2)
                //
                vm.stack.push((v1 as IDmNPNumber).div(v2 as IDmNPNumber))
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        // Heap
        OpCodeManager.OpCodes[OCHMath.Inc] = { _, vm, c, ci ->
            try {
                val names = throwCast<Any?, ArrayList<String>>(vm.stack.pop())
                val o = throwCast<DmNPData?, DmNPDataObject>(DmNPUtils.findElement(vm, names))

                if (!o.fm.containsKey("inc")) {
                    var v = o.value

                    if (v is Number)
                        v = IDmNPNumber.ofType(v)

                    o.value = (v as IDmNPNumber).inc()
                } else
                    DmNPUtils.callFunction(o.fm["inc"], vm, c, ci)
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
    }
}