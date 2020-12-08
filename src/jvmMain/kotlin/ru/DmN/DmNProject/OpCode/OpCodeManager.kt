package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.DmNPData

actual class PROpCodeManager
{
    actual companion object {
        actual fun init() {
            OpCodeManager.OpCodes[OCMath.Inc] = { _, vm, c, ci ->
                try {
                    var o = vm.stack.pop()!!


                } catch (e: Throwable) {

                }
            }
        }
    }
}