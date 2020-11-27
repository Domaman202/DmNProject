package ru.DmN.DmNProject.Reflection

import ru.DmN.DmNProject.Data.Containers.DmNPDataMap
import ru.DmN.DmNProject.Data.DmNPDataObject
import ru.DmN.DmNProject.Data.DmNPModifiers
import ru.DmN.DmNProject.Data.DmNPType
import ru.DmN.DmNProject.VM.DmNPVM

class DmNPCompiler
{
    fun MethodCompiler(f: (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit, modifiers: ArrayList<DmNPModifiers>, vm: DmNPVM): DmNPDataObject
    {
        val method: DmNPDataObject = DmNPDataObject(f::class.js.name, DmNPType.KMETHOD, modifiers = modifiers, reference = DmNPDataMap(vm.heap.DmNPData()))
        method.value = f

        return method
    }
}