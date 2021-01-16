package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.VM.*

/**
 * @author  DomamaN202
 */
class OCManager {
    companion object : IOpCodeManager {
        val OC = HashMap<IOpCode, (oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit>()

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
            val f = OC[oc]
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