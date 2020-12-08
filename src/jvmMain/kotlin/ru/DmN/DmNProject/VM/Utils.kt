package ru.DmN.DmNProject.VM

import java.io.BufferedReader
import java.io.FileReader

actual class MFileReader actual constructor(name: String) {
    val reader = BufferedReader(FileReader(name))
}