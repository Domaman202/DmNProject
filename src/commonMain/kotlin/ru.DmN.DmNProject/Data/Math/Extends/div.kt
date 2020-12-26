package ru.DmN.DmNProject.Data.Math.Extends

operator fun Number.div(v: Byte): Number {
    return when (this) {
        is Byte     -> this.div(v)
        is Short    -> this.div(v)
        is Int      -> this.div(v)
        is Long     -> this.div(v)
        is Float    -> this.div(v)
        is Double   -> this.div(v)
        else        -> this
    }
}

operator fun Number.div(v: Short): Number {
    return when (this) {
        is Byte     -> this.div(v)
        is Short    -> this.div(v)
        is Int      -> this.div(v)
        is Long     -> this.div(v)
        is Float    -> this.div(v)
        is Double   -> this.div(v)
        else        -> this
    }
}

operator fun Number.div(v: Int): Number {
    return when (this) {
        is Byte     -> this.div(v)
        is Short    -> this.div(v)
        is Int      -> this.div(v)
        is Long     -> this.div(v)
        is Float    -> this.div(v)
        is Double   -> this.div(v)
        else        -> this
    }
}

operator fun Number.div(v: Long): Number {
    return when (this) {
        is Byte     -> this.div(v)
        is Short    -> this.div(v)
        is Int      -> this.div(v)
        is Long     -> this.div(v)
        is Float    -> this.div(v)
        is Double   -> this.div(v)
        else        -> this
    }
}

operator fun Number.div(v: Float): Number {
    return when (this) {
        is Byte     -> this.div(v)
        is Short    -> this.div(v)
        is Int      -> this.div(v)
        is Long     -> this.div(v)
        is Float    -> this.div(v)
        is Double   -> this.div(v)
        else        -> this
    }
}

operator fun Number.div(v: Double): Number {
    return when (this) {
        is Byte     -> this.div(v)
        is Short    -> this.div(v)
        is Int      -> this.div(v)
        is Long     -> this.div(v)
        is Float    -> this.div(v)
        is Double   -> this.div(v)
        else        -> this
    }
}