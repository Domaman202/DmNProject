package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.DmNPAData
import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject
import ru.DmN.DmNProject.Data.Math.Extends.plus
import ru.DmN.DmNProject.VM.kotlin_function
import ru.DmN.DmNProject.VM.throwCast

object OCMMath {
    fun init() {
        // Math
        // Stack
        OpCodeManager.OpCodes[OCSMath.INC] = { _, vm, c, ci ->
            try {
                val o = vm.stack.pop()

                if (o is Number)
                    o + 1
                else if (o is DmNPData) {
                    if (o is DmNPDataObject) {
                        val inc = o.fm["inc"]

                        if (inc == null)
                            o.value as Number + 1
                        else
                            throwCast<Any?, kotlin_function>(inc).invoke(vm, c, ci)
                    } else
                        o.value as Number + 1
                } else
                    throw Exception()
            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.DEC] = { _, vm, _, _ ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.ADD] = { _, vm, _, _ ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.SUB] = { _, vm, _, _ ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.MUL] = { _, vm, _, _ ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCSMath.DIV] = { _, vm, _, _ ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        // Heap
        OpCodeManager.OpCodes[OCHMath.INC] = { _, vm, c, ci ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCHMath.DEC] = { _, vm, c, ci ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCHMath.ADD] = { _, vm, c, ci ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCHMath.SUB] = { _, vm, c, ci ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCHMath.MUL] = { _, vm, c, ci ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
        OpCodeManager.OpCodes[OCHMath.DIV] = { _, vm, c, ci ->
            try {

            } catch (e: Throwable) {
                if (vm.e)
                    vm.eStack!!.push(e)
                else
                    throw e
            }
        }
    }
}