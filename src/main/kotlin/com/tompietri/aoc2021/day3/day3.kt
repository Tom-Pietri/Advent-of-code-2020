package com.tompietri.aoc2021.day3

import kotlin.math.pow
import kotlin.reflect.KProperty1

fun day3FirstSolution(input: List<String>): Int {
    val powerConsumption = PowerConsumption(input)
    return bitsToInt(powerConsumption.mostCommon) * bitsToInt(powerConsumption.leastCommon)
}

fun day3SecondSolution(input: List<String>): Int {
    val oxygenGenerator = filterByBitCriteria(input, PowerConsumption::mostCommon)
    val co2ScrubberRating = filterByBitCriteria(input, PowerConsumption::leastCommon)

    return bitsToInt(oxygenGenerator[0]) * bitsToInt(co2ScrubberRating[0])
}

private fun filterByBitCriteria(input: List<String>, kProperty1: KProperty1<PowerConsumption, String>): List<String> {
    var result = input
    for (i in input[0].indices) {
        if (result.size == 1) {
            break
        }
        val powerConsumption = PowerConsumption(result)
        result = result.filter { it[i] == kProperty1.get(powerConsumption)[i] }
    }
    return result
}

private fun bitsToInt(numberAsBits: String) = numberAsBits.reversed().foldIndexed(0) { index, acc, bit ->
    if (bit == '0') {
        acc
    } else {
        acc + ((2.0).pow(index).toInt())
    }
}

class PowerConsumption(input: List<String>) {
    val mostCommon: String
    val leastCommon: String

    init {
        val perPositionCount = MutableList(input[0].length) {0}
        input.forEach { line ->
            line.forEachIndexed { index, c ->
                if(c == '1') {
                    perPositionCount[index]++
                } else {
                    perPositionCount[index]--
                }
            }
        }
        mostCommon = perPositionCount.map { if (it >= 0) 1 else 0 }
            .joinToString("")
        leastCommon = perPositionCount.map { if (it >= 0) 0 else 1 }
            .joinToString("")
    }
}