package ru.DmN.DmNProject.Data

import kotlinx.serialization.Serializable

/**
 * @author  DomamaN202
 */
open class DmNPData(
    open var name: String,
    var type: DmNPType,
    var value: Any? = null
)

/**
 * @author  DomamaN202
 */
@Serializable
enum class DmNPType
{
    NULL,
    OBJECT,
    REFERENCE,

    VAR,
    KMETHOD,
    METHOD,
    CLASS,
    PACKAGE
}

/**
 * @author  DomamaN202
 */
@Serializable
enum class DmNPModifiers
{
    // Access
    PUBLIC,
    PROTECTED,
    PRIVATE,
    //
    STATIC,
    FINAL
}