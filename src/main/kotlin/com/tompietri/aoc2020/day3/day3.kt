package com.tompietri.aoc2020.day3

import java.math.BigInteger

fun day3FirstSolution(input: List<String>): Int {
    return treesForSlope(input, Slope(3, 1))
}

private fun treesForSlope(treeGrid: List<String>, slope: Slope): Int {
    return treeGrid.filterIndexed { index, _ -> index % slope.down == 0 }
            .filterIndexed { index, s -> s[(index * slope.right) % s.length] == '#' }
            .count()
}


fun day3SecondSolution(input: List<String>): BigInteger {
    return listOf(
            Slope(1, 1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1, 2)
    ).map { treesForSlope(input, it) }
            .map { it.toBigInteger() }
            .reduce { acc, nextNumberOfTree -> acc * nextNumberOfTree }
}

data class Slope(val right: Int, val down: Int)

