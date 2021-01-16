package ru.DmN.DmNProject.Data

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

enum class DmNPDVars {
    Default,
    Extendable,
    Annotateble,
    Referencable,
    FM,
    EFM,
    RFM,
    AFM,
    REFM,
    AEFM,
    AREFM
}