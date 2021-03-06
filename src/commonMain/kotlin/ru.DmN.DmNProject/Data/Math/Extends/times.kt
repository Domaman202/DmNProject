package ru.DmN.DmNProject.Data.Math.Extends

operator fun Number.times(v: Number): Number {
    return when (v) {
        is Byte     -> times(v)
        is Short    -> times(v)
        is Int      -> times(v)
        is Long     -> times(v)
        is Float    -> times(v)
        is Double   -> times(v)
        else        -> 0
    }
}

operator fun Number.times(v: Byte): Number {
    return when (this) {
        is Byte     -> this.times(v)
        is Short    -> this.times(v)
        is Int      -> this.times(v)
        is Long     -> this.times(v)
        is Float    -> this.times(v)
        is Double   -> this.times(v)
        else        -> this
    }
}

operator fun Number.times(v: Short): Number {
    return when (this) {
        is Byte     -> this.times(v)
        is Short    -> this.times(v)
        is Int      -> this.times(v)
        is Long     -> this.times(v)
        is Float    -> this.times(v)
        is Double   -> this.times(v)
        else        -> this
    }
}

operator fun Number.times(v: Int): Number {
    return when (this) {
        is Byte     -> this.times(v)
        is Short    -> this.times(v)
        is Int      -> this.times(v)
        is Long     -> this.times(v)
        is Float    -> this.times(v)
        is Double   -> this.times(v)
        else        -> this
    }
}

operator fun Number.times(v: Long): Number {
    return when (this) {
        is Byte     -> this.times(v)
        is Short    -> this.times(v)
        is Int      -> this.times(v)
        is Long     -> this.times(v)
        is Float    -> this.times(v)
        is Double   -> this.times(v)
        else        -> this
    }
}

operator fun Number.times(v: Float): Number {
    return when (this) {
        is Byte     -> this.times(v)
        is Short    -> this.times(v)
        is Int      -> this.times(v)
        is Long     -> this.times(v)
        is Float    -> this.times(v)
        is Double   -> this.times(v)
        else        -> this
    }
}

operator fun Number.times(v: Double): Number {
    return when (this) {
        is Byte     -> this.times(v)
        is Short    -> this.times(v)
        is Int      -> this.times(v)
        is Long     -> this.times(v)
        is Float    -> this.times(v)
        is Double   -> this.times(v)
        else        -> this
    }
}