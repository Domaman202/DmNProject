package ru.DmN.DmNProject.Data.Containers

class Stack<T> {
    private val elements: MutableList<T?> = mutableListOf()

    fun isEmpty() = elements.isEmpty()
    val size get() = elements.size

    fun push(item: T?) = elements.add(item)
    fun pop() : T? {
        val item = elements.lastOrNull()
        if (!isEmpty()){
            elements.removeAt(elements.size -1)
        }
        return item
    }
    fun peek() : T? = elements.lastOrNull()

    override fun hashCode(): Int = elements.hashCode()
    override fun equals(other: Any?): Boolean = elements == other
    override fun toString(): String = elements.toString()
}