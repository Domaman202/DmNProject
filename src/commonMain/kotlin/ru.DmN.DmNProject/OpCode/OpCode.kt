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
    CreatePackage,
    CreateMethod,
    CreateVariable,
    CreateObject,

    SetValue,
    CopySetValue,
    GetValue,
    CopyGetValue,

    AddData,
    CopyAddData,
    RemoveData,
    GetRemoveData,

    AddToValue,
    CopyAddToValue,
    RemoveInValue,
    CopyRemoveInValue,

    FindPackage
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

enum class OCHMath: IMathOpCode {
    INC,
    DEC,
    ADD,
    SUB,
    MUL,
    DIV
}

enum class OCVMManager: IOpCode {
    LoadVM,
    LoadLinkVM
}