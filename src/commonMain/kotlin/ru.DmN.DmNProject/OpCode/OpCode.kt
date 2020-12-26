package ru.DmN.DmNProject.OpCode

enum class OCStack: IOpCode {
    LoadConstant,
    UnLoadConstant,
    CloneStackElement
}

enum class OCException: IOpCode {
    LoadException,
    UnLoadException,
    ThrowOnVM
}

enum class OCData: IOpCode {
    CreatePackage,
    CreateClass,
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
}

enum class OCStackHeap: IOpCode {
    PushData,
    LoadData
}

enum class OCInvoke: IOpCode {
    // Kotlin
    UnsafeInvokeKotlin,
    InvokeKotlin,
    InvokeStaticKotlin,
    // Virtual
    UnsafeInvokeVirtual,
    InvokeVirtual,
    InvokeStaticVirtual
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