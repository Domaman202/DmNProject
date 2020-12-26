package ru.DmN.DmNProject.Data.Math.Extends

operator fun Number.plus(v: Byte): Number {
    return when (this) {
        is Byte     -> this.plus(v)
        is Short    -> this.plus(v)
        is Int      -> this.plus(v)
        is Long     -> this.plus(v)
        is Float    -> this.plus(v)
        is Double   -> this.plus(v)
        else        -> this
    }
}

operator fun Number.plus(v: Short): Number {
    return when (this) {
        is Byte     -> this.plus(v)
        is Short    -> this.plus(v)
        is Int      -> this.plus(v)
        is Long     -> this.plus(v)
        is Float    -> this.plus(v)
        is Double   -> this.plus(v)
        else        -> this
    }
}

operator fun Number.plus(v: Int): Number {
    return when (this) {
        is Byte     -> this.plus(v)
        is Short    -> this.plus(v)
        is Int      -> this.plus(v)
        is Long     -> this.plus(v)
        is Float    -> this.plus(v)
        is Double   -> this.plus(v)
        else        -> this
    }
}

operator fun Number.plus(v: Long): Number {
    return when (this) {
        is Byte     -> this.plus(v)
        is Short    -> this.plus(v)
        is Int      -> this.plus(v)
        is Long     -> this.plus(v)
        is Float    -> this.plus(v)
        is Double   -> this.plus(v)
        else        -> this
    }
}

operator fun Number.plus(v: Float): Number {
    return when (this) {
        is Byte     -> this.plus(v)
        is Short    -> this.plus(v)
        is Int      -> this.plus(v)
        is Long     -> this.plus(v)
        is Float    -> this.plus(v)
        is Double   -> this.plus(v)
        else        -> this
    }
}

operator fun Number.plus(v: Double): Number {
    return when (this) {
        is Byte     -> this.plus(v)
        is Short    -> this.plus(v)
        is Int      -> this.plus(v)
        is Long     -> this.plus(v)
        is Float    -> this.plus(v)
        is Double   -> this.plus(v)
        else        -> this
    }
}