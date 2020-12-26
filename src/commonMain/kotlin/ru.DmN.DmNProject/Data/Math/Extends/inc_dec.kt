package ru.DmN.DmNProject.Data.Math.Extends

operator fun Number.inc(): Number {
    return when (this) {
        is Byte     -> this.inc()
        is Short    -> this.inc()
        is Int      -> this.inc()
        is Long     -> this.inc()
        is Float    -> this.inc()
        is Double   -> this.inc()
        else        -> this
    }
}

operator fun Number.dec(): Number {
    return when (this) {
        is Byte     -> this.dec()
        is Short    -> this.dec()
        is Int      -> this.dec()
        is Long     -> this.dec()
        is Float    -> this.dec()
        is Double   -> this.dec()
        else        -> this
    }
}