package ru.DmN.DmNProject

/**
 * @author  DomamaN202
 */
class OpCodeManager {
    companion object {
        @Suppress("NON_EXHAUSTIVE_WHEN", "UNCHECKED_CAST")
        fun parse(oc: OpCodes, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>) {
            when(oc) {
                OpCodes.LoadConstant -> {
                    vm.stack.push(ci.next()!!)
                } // LC
                OpCodes.LoadException -> {
                    if (vm.e)
                        vm.eStack!!.push(ci.next()!! as Throwable)
                    else
                        ci.next()
                } // LE
                OpCodes.CloneStackElement -> {
                    vm.stack.push(vm.stack.peek()!!)
                } // CSE
                OpCodes.CreatePackage -> {
                    val ns = vm.stack.pop() as ArrayList<String>

                    var le: DmNPData? = null
                    for (i in 0 until ns.size) {
                        if (le == null) {
                            if (vm.heap.containsKey(ns[i])) {
                                le = vm.heap[ns[i]]
                            } else {
                                le = DmNPData(ns[i], DmNPType.PACKAGE)
//                                le.addReference(vm.heap.DmNPData())
                                le.reference.add(vm.heap.DmNPData())
                                le.value = DmNPDataArray()
                                vm.stack.push(le)
                            }
                        } else {
                            val d = le.value as DmNPDataArray
                            le = if (d.containsKey(ns[i])) {
                                d[ns[i]]
                            } else {
                                val ne = DmNPData(ns[i], DmNPType.PACKAGE)
//                                ne.addReference(le)
                                ne.reference.add(le)
                                ne.value = DmNPDataArray()
                                d.add(ne)
                                ne
                            }
                        }
                    }
                } // CP
                OpCodes.LoadData -> {
                    val le = DmNPUtils.findElement(vm, vm.stack.pop() as ArrayList<String>)
                    if (le != null)
                        vm.stack.push(le)
                    else
                        return
                } // LD
                OpCodes.PushData -> {
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
                } // PD
                OpCodes.CreateClass -> {
                    val n = vm.stack.pop()!! as String
                    val m = vm.stack.pop()!! as ArrayList<DmNPModifiers>
                    var r = vm.stack.pop()!! as DmNPData

                    if (r.type == DmNPType.PACKAGE) {
                        if ((r.value as DmNPDataArray).containsKey(n))
                            (r.value as DmNPDataArray).remove(n)
                        val clss = DmNPDataObject(n, DmNPType.CLASS)
                        clss.modifiers = m
//                        clss.addReference(r)
                        clss.reference.add(r)
                        (r.value as DmNPDataArray).add(clss)
                        vm.stack.push(clss)
                    } else if (r.type == DmNPType.CLASS || r.type == DmNPType.OBJECT) {
                        r = r as DmNPDataObject

                        if (r.fm.containsKey(n))
                            r.fm.remove(n)
                        val clss = DmNPDataObject(n, DmNPType.CLASS)
                        clss.modifiers = m
//                        clss.addReference(r)
                        clss.reference.add(r)
                        r.fm.add(clss)
                        vm.stack.push(clss)
                    }
                } // CC
                OpCodes.CreateMethod -> {
                    val n = vm.stack.pop() as String
                    val m = vm.stack.pop() as ArrayList<DmNPModifiers>
                    val r = vm.stack.pop() as DmNPDataObject

                    if (r.fm.containsKey(n))
                        r.fm.remove(n)

                    val method = DmNPDataObject(n, DmNPType.METHOD)
                    method.modifiers = m
//                    method.addReference(r)
                    method.reference.add(r)

                    r.fm.add(method)
                    vm.stack.push(method)
                } // CM
                OpCodes.SetValue -> {
                    val v = vm.stack.pop()
                    (vm.stack.pop() as DmNPData).value = v
                } // SV
                OpCodes.CopySetValue -> {
                    val v = vm.stack.pop()
                    (vm.stack.peek() as DmNPData).value = v
                } // CSV
                OpCodes.GetValue -> {
                    vm.stack.push((vm.stack.pop() as DmNPData).value)
                } // GV
                OpCodes.CopyGetValue -> {
                    vm.stack.push((vm.stack.peek() as DmNPData).value)
                } // CGV
                OpCodes.AddData -> {
                    val d = vm.stack.pop() as DmNPData
                    (vm.stack.pop() as DmNPDataObject).fm.add(d)
                } // AD
                OpCodes.CopyAddData -> {
                    val d = vm.stack.pop() as DmNPData
                    (vm.stack.peek() as DmNPDataObject).fm.add(d)
                } // CAD
                OpCodes.RemoveData -> {
                    val n = vm.stack.pop() as String
                    (vm.stack.peek() as DmNPDataObject).fm.remove(n)
                } // RD
                OpCodes.GetRemoveData -> {
                    val n = vm.stack.pop() as String
                    val o = vm.stack.peek() as DmNPDataObject

                    vm.stack.push(o.fm[n])
                    o.fm.remove(n)
                } // GRD
                OpCodes.InvokeStaticKotlin -> {
                    val n = vm.stack.pop() as ArrayList<String>
                    val o = DmNPUtils.findElement(vm, n)
                    if (o?.value != null) {
                        if (vm.e) {
                            if (o.modifiers.contains(DmNPModifiers.STATIC)) {
                                try {
                                    (o.value as (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit)(
                                        vm,
                                        c,
                                        ci
                                    )
                                } catch (e: Throwable) {
                                    if (vm.e)
                                        vm.eStack!!.push(e)
                                }
                            } else
                                vm.eStack!!.push(ObjectNoStaticException())
                        } else
                            (o.value as (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit)(vm, c, ci)
                    } else if (vm.e) {
                        if (o == null)
                            vm.eStack!!.push(ObjectNullPointerException())
                        else
                            vm.eStack!!.push(ObjectValueNullPointerException())
                    }
                } // ISK
                OpCodes.UnsafeInvokeKotlin -> {
                    val n = vm.stack.pop() as ArrayList<String>
                    (DmNPUtils.findElement(vm, n) as (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit)(vm, c, ci)
                } // UIK
                OpCodes.InvokeVirtualStatic -> {
                    if (vm.e) {
                        val n = vm.stack.pop() as ArrayList<String>
                        val m = DmNPUtils.findElement(vm, n)
                        if (m != null) {
                            if (vm.e && m.modifiers.contains(DmNPModifiers.STATIC)) {
                                val m_vm = DmNPVMInterpreter()
                                m_vm.prev.add(vm)
                                vm.next.add(m_vm)

                                if (m.value is ArrayList<*>) {
                                    val v = m.value as ArrayList<Any?>
                                    m_vm.parse(v)
                                } else if (vm.e)
                                    vm.eStack!!.push(ObjectValueNullPointerException())

                                vm.next.remove(m_vm)
                            } else if (vm.e)
                                vm.eStack!!.push(ObjectNoStaticException())
                        } else if (vm.e) {
                            vm.eStack!!.push(ObjectNullPointerException())
                        }
                    } else {
                        val m = DmNPUtils.findElement(vm, vm.stack.pop() as ArrayList<String>)
                        if (m != null) {
                            val mVM = DmNPVMInterpreter()
                            mVM.prev.add(vm)
                            vm.next.add(mVM)

                            mVM.parse(m.value as ArrayList<Any?>)

                            vm.next.remove(mVM)
                        }
                    }
                } // IVS
                OpCodes.UnsafeInvokeVirtual -> {
                    val m = DmNPUtils.findElement(vm, vm.stack.pop() as ArrayList<String>)
                    if (m != null) {
                        val mVM = DmNPVMInterpreter()
                        mVM.prev.add(vm)
                        vm.next.add(mVM)

                        mVM.parse(m.value as ArrayList<Any?>)

                        vm.next.remove(mVM)
                    }
                } // UIV
                else -> throw OpCodeNotFoundedException()
            }
        }
    }
}

/**
 * @author  DomamaN202
 */
enum class OpCodes {
    LoadConstant,
    LoadException,
    CloneStackElement,
    CreatePackage,
    CreateClass,
    CreateMethod,
    PushData,
    LoadData,
    AddData,
    CopyAddData,
    RemoveData,
    GetRemoveData,
    SetValue,
    CopySetValue,
    GetValue,
    CopyGetValue,
    InvokeStaticKotlin,
    InvokeKotlin,
    UnsafeInvokeKotlin,
    InvokeVirtualStatic,
    InvokeVirtual,
    UnsafeInvokeVirtual
}

class OpCodeNotFoundedException: Error()