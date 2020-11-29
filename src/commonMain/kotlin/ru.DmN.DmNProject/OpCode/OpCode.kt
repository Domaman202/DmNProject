package ru.DmN.DmNProject.OpCode

interface IOpCode

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
    UnsafeInvokeKotlin,
    UnsafeInvokeVirtual
}

enum class OCMath: IOpCode {
    Inc,
    Dec,
    Add,
    Sub,
    Mul,
    Div
}