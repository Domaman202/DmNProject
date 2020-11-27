package ru.DmN.DmNProject.VM

class ObjectNullPointerException        (name: String): Throwable("Pointer to the object $name is null!")
class ObjectValueNullPointerException   (name: String): Throwable("The value of the $name object is null")
class ObjectAccessException             (name: String): Throwable("Insufficient permissions to access the $name object")
class ObjectNoStaticException           (name: String): Throwable("The $name object is not static")
class OpCodeNotFoundedException         (name: String): Throwable("Error detecting the $name Opcode")