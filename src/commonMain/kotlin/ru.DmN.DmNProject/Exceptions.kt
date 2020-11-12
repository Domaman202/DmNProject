package ru.DmN.DmNProject

class ObjectNullPointerException(n: String): Throwable("Object ${n} is null")
class ObjectNotFoundedException: Throwable {
    constructor(vararg ns: String) : super("Object ${ns} not founded")
    constructor(ns: Array<String>) : super("Object ${ns} not founded")
}
class ObjectValueNullPointerException(n: String): Throwable("The object ${n} has a null value")