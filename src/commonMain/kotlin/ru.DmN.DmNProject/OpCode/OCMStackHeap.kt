package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.CDCS.ODCS
import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.VM.DmNPUtils
import ru.DmN.DmNProject.VM.throwCast

object OCMStackHeap {
    fun init() {
        // Stack Heap
        ODCS.OCC["LD"] = OCStackHeap.LoadData
        OCManager.OC[OCStackHeap.LoadData] = { _, vm, _, _ ->
            val le = DmNPUtils.findElement(vm, throwCast(vm.stack.pop()))
            if (le != null) vm.stack.push(le)
        }
        ODCS.OCC["PD"] = OCStackHeap.PushData
        OCManager.OC[OCStackHeap.PushData] = { _, vm, _, _ ->
            val data = vm.stack.pop()!!
            if (data is DmNPData) {
                if (vm.heap.containsKey(data.name)) {
                    vm.heap.remove(data.name)
                    vm.heap.add(data)
                } else {
                    vm.heap.add(data)
                }
            } else {
                vm.stack.push(data)
            }
        }
    }
}