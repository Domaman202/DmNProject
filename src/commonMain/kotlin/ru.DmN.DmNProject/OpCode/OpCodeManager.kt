package ru.DmN.DmNProject.OpCode

import ru.DmN.DmNProject.Data.*
import ru.DmN.DmNProject.Data.Containers.*
import ru.DmN.DmNProject.*
import ru.DmN.DmNProject.VM.*

/**
 * @author  DomamaN202
 */
class OpCodeManager {
    companion object {
        @Suppress("NON_EXHAUSTIVE_WHEN", "UNCHECKED_CAST")
        fun parse(oc: IOpCode, vm: DmNPVMInterpreter, c: ArrayList<Any?>, ci: ListIterator<*>) {
            when(oc) {
                OCStack.LoadConstant -> {
                    vm.stack.push(ci.next()!!)
                } // LC
                OCStack.UnLoadConstant -> {
                    vm.stack.pop()
                } // ULC
                OCStack.CloneStackElement -> {
                    vm.stack.push(vm.stack.peek()!!)
                } // CSE
                OCException.LoadException -> {
                    if (vm.e)
                        vm.eStack!!.push(ci.next()!! as Throwable)
                    else
                        ci.next()
                } // LE
                OCException.UnLoadException -> {
                    if (vm.e)
                        vm.eStack!!.pop()
                } // ULE
                OCException.ThrowOnVM -> {
                    val e = vm.eStack!!.pop()
                    if (e != null)
                        throw e
                } // TOVM
                OCData.CreatePackage -> {
                    val ns = vm.stack.pop() as ArrayList<String>

                    var le: DmNPData? = null
                    for (i in 0 until ns.size) {
                        if (le == null) {
                            if (vm.heap.containsKey(ns[i])) {
                                le = vm.heap[ns[i]]
                            } else {
                                le = DmNPAData(ns[i], DmNPType.PACKAGE)
                                le.reference.add(vm.heap.DmNPData())
                                le.value = DmNPDataArray()
                                vm.stack.push(le)
                            }
                        } else {
                            val d = le.value as DmNPDataArray
                            le = if (d.containsKey(ns[i])) {
                                d[ns[i]]
                            } else {
                                val ne = DmNPAData(ns[i], DmNPType.PACKAGE)
                                ne.reference.add(le)
                                ne.value = DmNPDataArray()
                                d.add(ne)
                                ne
                            }
                        }
                    }
                } // CP
                OCData.CreateClass -> {
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
                OCData.CreateMethod -> {
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
                OCData.SetValue -> {
                    val v = vm.stack.pop()
                    (vm.stack.pop() as DmNPData).value = v
                } // SV
                OCData.CopySetValue -> {
                    val v = vm.stack.pop()
                    (vm.stack.peek() as DmNPData).value = v
                } // CSV
                OCData.GetValue -> {
                    vm.stack.push((vm.stack.pop() as DmNPData).value)
                } // GV
                OCData.CopyGetValue -> {
                    vm.stack.push((vm.stack.peek() as DmNPData).value)
                } // CGV
                OCData.AddData -> {
                    val d = vm.stack.pop() as DmNPData
                    (vm.stack.pop() as DmNPDataObject).fm.add(d)
                } // AD
                OCData.CopyAddData -> {
                    val d = vm.stack.pop() as DmNPData
                    (vm.stack.peek() as DmNPDataObject).fm.add(d)
                } // CAD
                OCData.RemoveData -> {
                    val n = vm.stack.pop() as String
                    (vm.stack.peek() as DmNPDataObject).fm.remove(n)
                } // RD
                OCData.GetRemoveData -> {
                    val n = vm.stack.pop() as String
                    val o = vm.stack.peek() as DmNPDataObject

                    vm.stack.push(o.fm[n])
                    o.fm.remove(n)
                } // GRD
                OCStackHeap.LoadData -> {
                    val le = DmNPUtils.findElement(vm, vm.stack.pop() as ArrayList<String>)
                    if (le != null)
                        vm.stack.push(le)
                    else
                        return
                } // LD
                OCStackHeap.PushData -> {
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
                OCInvoke.UnsafeInvokeKotlin -> {
                    val n = vm.stack.pop() as ArrayList<String>
                    (DmNPUtils.findElement(vm, n)!!.value as (vm: DmNPVM, c: ArrayList<Any?>, ci: ListIterator<Any?>) -> Unit)(vm, c, ci)
                } // UIK
                OCInvoke.UnsafeInvokeVirtual -> {
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