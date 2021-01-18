package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.VM.*

/**
 * @author  DomamaN202
 */
class OCManager {
    companion object : IOpCodeManager {
        /**
         * Опкоды и функции их выполнения xD
         */
        val OC = HashMap<IOpCode, (oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit>()

        private var isInit = false

        /**
         * Инициализация OCManager-а
         */
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

        /**
         * Парсит опкоды, в случае не нахождения опкода выдаёт ошибку
         * @param oc Опкод
         * @param vm Виртуальная машина на которой будет парсится опкод
         * @param c Массив кода
         * @param ci Итератор кода
         */
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