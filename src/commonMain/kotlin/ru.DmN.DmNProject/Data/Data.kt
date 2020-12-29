package ru.DmN.DmNProject.Data

enum class DmNPDataType
{
    DmNPData,
    DmNPAData,
    DmNPDataObject
}

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

object DmNPTypeUtil
{
    fun toString(type: DmNPType): String
    {
        return when (type) {
            DmNPType.NULL       -> "DmNPType:NULL"
            DmNPType.OBJECT     -> "DmNPType:OBJECT"
            DmNPType.REFERENCE  -> "DmNPType:REFERENCE"
            DmNPType.VAR        -> "DmNPType:VAR"
            DmNPType.KMETHOD    -> "DmNPType:KMETHOD"
            DmNPType.METHOD     -> "DmNPType:METHOD"
            DmNPType.CLASS      -> "DmNPType:CLASS"
            DmNPType.PACKAGE    -> "DmNPType:PACKAGE"
        }
    }

    fun ofString(str: String): DmNPType
    {

        return when (str.substring(str.indexOf(':'))) {
            "OBJECT"    -> DmNPType.OBJECT
            "REFERENCE" -> DmNPType.REFERENCE
            "VAR"       -> DmNPType.VAR
            "KMETHOD"   -> DmNPType.KMETHOD
            "METHOD"    -> DmNPType.METHOD
            "CLASS"     -> DmNPType.CLASS
            "PACKAGE"   -> DmNPType.PACKAGE
            else        -> DmNPType.NULL
        }
    }
}

/**
 * @author  DomamaN202
 */
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

object DmNPModifiersUtil
{
    fun toString(type: DmNPModifiers): String
    {
        return when (type) {
            DmNPModifiers.PUBLIC    -> "DmNPModifiers:PUBLIC"
            DmNPModifiers.PROTECTED -> "DmNPModifiers:PROTECTED"
            DmNPModifiers.PRIVATE   -> "DmNPModifiers:PRIVATE"
            DmNPModifiers.STATIC    -> "DmNPModifiers:STATIC"
            DmNPModifiers.FINAL     -> "DmNPModifiers:FINAL"
        }
    }

    fun ofString(str: String): DmNPModifiers
    {

        return when (str.substring(str.indexOf(':'))) {
            "DmNPModifiers:PUBLIC"      -> DmNPModifiers.PUBLIC
            "DmNPModifiers:PROTECTED"   -> DmNPModifiers.PROTECTED
            "DmNPModifiers:PRIVATE"     -> DmNPModifiers.PRIVATE
            "DmNPModifiers:STATIC"      -> DmNPModifiers.STATIC
            "DmNPModifiers:FINAL"       -> DmNPModifiers.FINAL
            else -> throw Exception()
        }
    }
}