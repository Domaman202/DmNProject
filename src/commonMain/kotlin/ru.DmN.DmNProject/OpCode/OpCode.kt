package ru.DmN.DmNProject.OpCode

enum class OCStack: IOpCode {
    LoadConstant,
    LoadAllConstants,
    UnLoadConstant,
    CloneStackElement,
    Reverse
}

enum class OCException: IOpCode {
    LoadException,
    UnLoadException,
    ThrowOnVM
}

enum class OCData: IOpCode {
    // Data constructors
    CreatePackage,
    CreateMethod,
    CreateVariable,
    CreateObject,
    // Value manipulations
    SetValue,
    CopySetValue,
    GetValue,
    CopyGetValue,
    // FM manipulations
    AddData,
    CopyAddData,
    RemoveData,
    GetRemoveData,
    GetData,
    CopyGetData,
    //
    AddToValue,
    CopyAddToValue,
    RemoveInValue,
    CopyRemoveInValue,
    //
    FindPackage,
    //
    AddExt,
    RemoveExt
}

enum class OCStackHeap: IOpCode {
    PushData,
    LoadData
}

enum class OCInvoke: IOpCode {
    // Kotlin
    UnsafeInvokeKotlin,
    // Virtual
    UnsafeInvokeVirtual
}

enum class OCSMath: IMathOpCode {
    INC,
    DEC,
    ADD,
    SUB,
    MUL,
    DIV
}

enum class OCAMath: IMathOpCode {
    INC,
    DEC,
    ADD,
    SUB,
    MUL,
    DIV
}

enum class OCVM: IOpCode {
    CodeToString,
    StringToCode,

    GlobalEvalCode,
    GlobalEvalString,

    NoLinkEvalCode,
    NoLinkEvalString,

    LinkEvalCode,
    LinkEvalString
}