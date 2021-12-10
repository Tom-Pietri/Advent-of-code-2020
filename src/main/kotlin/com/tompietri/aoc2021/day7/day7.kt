package com.tompietri.aoc2021.day7

import java.lang.Integer.max
import java.lang.Integer.min

fun day7FirstSolution(input: List<Int>): Int {
    val median = input.sorted()[input.size / 2]

    return input.sumOf { distanceBetween(it, median) }
}

fun day7SecondSolution(input: List<Int>): Int {
    val max = input.maxOrNull()!!
    val distances = List(max) { MutableList(input.size) { 0 } }
    input.forEachIndexed { inputIndex, inputValue ->
        for (i in 0 until max) {
            distances[i][inputIndex] = fuelCostPart2(distanceBetween(i, inputValue))
        }
    }

    return distances.minOf { it.sum() }
}

private fun distanceBetween(it: Int, median: Int) = max(it, median) - min(it, median)
private fun fuelCostPart2(it: Int) = (it * (it + 1)) / 2
