package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.DmNPAData
import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject
import ru.DmN.DmNProject.VM.DmNPUtils

actual class PROpCodeManager
{
    actual companion object {
        actual fun init() {
            OpCodeManager.OpCodes[OCMath.Inc] = { _, vm, c, ci ->
                try {
                    var o: dynamic = vm.stack.pop()!!

                    when(o) {
                        is DmNPData -> {
                            js("o.value++")
                        }
                        is DmNPAData -> {
                            js("o.value++")
                        }
                        is DmNPDataObject -> {
                            DmNPUtils.callFunction(o.fm["inc"]!!.value, vm, c, ci)
                        }

                        else -> o++
                    } as Unit
                } catch (e: Throwable)
                {

                }
            }

            OpCodeManager.OpCodes[OCMath.Dec] = { _, vm, c, ci ->
                try {
                    var o: dynamic = vm.stack.pop()!!

                    when(o) {
                        is DmNPData -> {
                            js("o.value--")
                        }
                        is DmNPAData -> {
                            js("o.value--")
                        }
                        is DmNPDataObject -> {
                            DmNPUtils.callFunction(o.fm["dec"]!!.value, vm, c, ci)
                        }

                        else -> o--
                    } as Unit
                } catch (e: Throwable)
                {

                }
            }
        }
    }
}