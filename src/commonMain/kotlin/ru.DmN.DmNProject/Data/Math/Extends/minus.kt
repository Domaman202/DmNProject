package ru.DmN.DmNProject.Data.Math.Extends

operator fun Number.minus(v: Number): Number {
    return when (v) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}

operator fun Number.minus(v: Byte): Number {
    return when (this) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}

operator fun Number.minus(v: Short): Number {
    return when (this) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}

operator fun Number.minus(v: Int): Number {
    return when (this) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}

operator fun Number.minus(v: Long): Number {
    return when (this) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}

operator fun Number.minus(v: Float): Number {
    return when (this) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}

operator fun Number.minus(v: Double): Number {
    return when (this) {
        is Byte     -> this.minus(v)
        is Short    -> this.minus(v)
        is Int      -> this.minus(v)
        is Long     -> this.minus(v)
        is Float    -> this.minus(v)
        is Double   -> this.minus(v)
        else        -> this
    }
}