package ru.DmN.DmNProject.OpCode

interface IOpCode
/**
 * @author  DomamaN202
 */
enum class OpCodes : IOpCode {
    LoadConstant,
    LoadException,
    CloneStackElement,
    CreatePackage,
    CreateClass,
    CreateMethod,
    PushData,
    LoadData,
    AddData,
    CopyAddData,
    RemoveData,
    GetRemoveData,
    SetValue,
    CopySetValue,
    GetValue,
    CopyGetValue,
    UnsafeInvokeKotlin,
    UnsafeInvokeVirtual
}