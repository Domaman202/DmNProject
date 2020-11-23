package ru.DmN.DmNProject.VM

import ru.DmN.DmNProject.Data.DmNPData
import ru.DmN.DmNProject.Data.DmNPDataObject

/**
 * @author  DomamaN202
 * @since 1.0
 */
class DmNPUtils
{
    companion object {
        fun findElement(vm: DmNPVM, names: ArrayList<String>, p: Boolean = true, n: Boolean = true): DmNPData? {
            var le: DmNPData? = null
            for (i in 0 until names.size) {
                if (le == null) {
                    if (vm.heap.containsKey(names[i])) {
                        le = vm.heap[names[i]]
                    } else {
                        if (vm.prev.size > 0 && p) {
                            for (v in vm.prev) {
                                le = findElement(v, names, p = true, n = false)

                                if (le != null)
                                    break
                            }
                        } else if (vm.prev.size > 1 && !p) {
                            for (counter in 1..vm.prev.size + 1) {
                                le = findElement(vm.prev[counter], names, p = true, n = false)

                                if (le != null)
                                    break
                            }
                        }

                        if (le == null && vm.next.size > 0 && n) {
                            for (v in vm.next) {
                                le = findElement(v, names, p = false, n = true)

                                if (le != null)
                                    break
                            }
                        } else if (le == null && vm.next.size > 1 && !n) {
                            for (counter in 1..vm.next.size + 1) {
                                le = findElement(vm.next[counter], names, p = true, n = false)

                                if (le != null)
                                    break
                            }
                        }

                        return le
                    }
                } else {
//                    le = if (le.value != null) {
//                        (le.value as Map<String, DmNPData>)[names[i]]
//                    } else if (le is DmNPDataObject) {
//                        le.fm[names[i]]
//                    } else return le
                    le = when (le.value) {
                        is Any -> (le.value as Map<String, DmNPData>)[names[i]]
                        else -> if (le is DmNPDataObject) le.fm[names[i]] else return le
                    }

                    if (le == null && vm.prev.size > 0 && p) {
                        for (v in vm.prev) {
                            le = findElement(v, names, p = true, n = false)

                            if (le != null)
                                break
                        }
                    } else if (le == null && vm.prev.size > 1 && !p) {
                        for (counter in 1..vm.prev.size + 1) {
                            le = findElement(vm.prev[counter], names, p = true, n = false)

                            if (le != null)
                                break
                        }
                    }

                    if (le == null && vm.next.size > 0 && n) {
                        for (v in vm.next) {
                            le = findElement(v, names, p = false, n = true)

                            if (le != null)
                                break
                        }
                    } else if (le == null && vm.next.size > 1 && !n) {
                        for (counter in 1..vm.next.size + 1) {
                            le = findElement(vm.next[counter], names, p = true, n = false)

                            if (le != null)
                                break
                        }
                    }
                }
            }

            return le
        }
    }
}