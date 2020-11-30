package com.tompietri.aoc2020.utils

import java.io.File

fun getInputFileForDay(dayNumber: Int) : File {
    return File("./src/main/kotlin/com/tompietri/aoc2020/day1/day$dayNumber.input")
}

fun readInputWithSingleLine(dayNumber: Int): String {
    return getInputFileForDay(dayNumber).readLines(Charsets.UTF_8)[0]
}

fun readInputWithMultipleLines(dayNumber: Int): List<String> {
    return getInputFileForDay(dayNumber).readLines(Charsets.UTF_8)
}

fun readSingleNumberInput(dayNumber: Int): Int {
    return readInputWithSingleLine(dayNumber).toInt()
}
