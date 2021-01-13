package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.VM.*

/**
 * @author  DomamaN202
 */
class OpCodeManager {
    companion object : IOpCodeManager {
        val OpCodes = HashMap<IOpCode, (oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit>()

        private var isInit = false
        fun init() {
            if (!isInit) {
                OCMStack.init()
                OCMStackHeap.init()
                OCMException.init()
                OCMData.init()
                OCMInvoke.init()
                OCMMath.init()
                OCMVM.init()
                isInit = true
            }
        }

        override fun parse(oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>) {
            val f = OpCodes[oc]
            if (f != null)
                f(oc, vm, c, ci)
            else
                throw OpCodeNotFoundedException(oc.toString())
        }

        init {
            init()
        }
    }
}