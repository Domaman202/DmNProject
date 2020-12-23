package ru.DmN.DmNProject.Data.Math

operator fun Any?.inc(): Any? {
    return when (this) {
        null            -> 1
        is Byte         -> this + 1
        is Short        -> this + 1
        is Int          -> this + 1
        is Float        -> this + 1
        is Long         -> this + 1
        is Double       -> this + 1
        is IDmNPNumber  -> IDmNPNumber.of(this).inc()
        else            -> null
    }
}

operator fun Any?.dec(): Any? {
    return when (this) {
        null            -> -1
        is Byte         -> this - 1
        is Short        -> this - 1
        is Int          -> this - 1
        is Float        -> this - 1
        is Long         -> this - 1
        is Double       -> this - 1
        is IDmNPNumber  -> this - 1
        else            -> null
    }

}

operator fun Any?.plus(v: Any?): Any? {
    return when (this) {
        null            -> v
        is Byte         -> IDmNPNumber.of(this).plus(v)
        is Short        -> IDmNPNumber.of(this).plus(v)
        is Int          -> IDmNPNumber.of(this).plus(v)
        is Float        -> IDmNPNumber.of(this).plus(v)
        is Long         -> IDmNPNumber.of(this).plus(v)
        is Double       -> IDmNPNumber.of(this).plus(v)
        is IDmNPNumber  -> this.plus(v)
        else            -> null
    }
}

operator fun Any?.minus(v: Any?): Any? {
    return when (this) {
        null            -> v
        is Byte         -> IDmNPNumber.of(this).minus(v)
        is Short        -> IDmNPNumber.of(this).minus(v)
        is Int          -> IDmNPNumber.of(this).minus(v)
        is Float        -> IDmNPNumber.of(this).minus(v)
        is Long         -> IDmNPNumber.of(this).minus(v)
        is Double       -> IDmNPNumber.of(this).minus(v)
        is IDmNPNumber  -> this.minus(v)
        else            -> null
    }
}

operator fun Any?.times(v: Any?): Any? {
    return when (this) {
        null            -> v
        is Byte         -> IDmNPNumber.of(this).times(v)
        is Short        -> IDmNPNumber.of(this).times(v)
        is Int          -> IDmNPNumber.of(this).times(v)
        is Float        -> IDmNPNumber.of(this).times(v)
        is Long         -> IDmNPNumber.of(this).times(v)
        is Double       -> IDmNPNumber.of(this).times(v)
        is IDmNPNumber  -> this.times(v)
        else            -> null
    }
}

operator fun Any?.div(v: Any?): Any? {
    return when (this) {
        null            -> v
        is Byte         -> IDmNPNumber.of(this).div(v)
        is Short        -> IDmNPNumber.of(this).div(v)
        is Int          -> IDmNPNumber.of(this).div(v)
        is Float        -> IDmNPNumber.of(this).div(v)
        is Long         -> IDmNPNumber.of(this).div(v)
        is Double       -> IDmNPNumber.of(this).div(v)
        is IDmNPNumber  -> this.div(v)
        else            -> null
    }
}