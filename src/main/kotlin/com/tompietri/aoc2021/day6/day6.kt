package com.tompietri.aoc2021.day6

import java.math.BigInteger

fun day6FirstSolution(input: List<Int>): BigInteger {
    return fishiesAfterDays(input, 80)
}

fun day6SecondSolution(input: List<Int>): BigInteger {
    return fishiesAfterDays(input, 256)
}

private fun fishiesAfterDays(input: List<Int>, i: Int): BigInteger {
    var fishes = mutableMapOf<Int, BigInteger>()
    input.forEach { fishes.compute(it) { _, value -> BigInteger.ONE + value } }

    repeat(i) {
        val newFishes = mutableMapOf<Int, BigInteger>()
        fishes.forEach { (day, nb) ->
            if (day == 0) {
                newFishes.compute(6) { _, value -> nb + value }
                newFishes[8] = nb
            } else {
                newFishes.compute(day - 1) { _, value -> nb + value }
            }
        }

        fishes = newFishes
    }

    return fishes.map { it.value }.reduce(BigInteger::add)
}

private operator fun BigInteger.plus(a: BigInteger?): BigInteger = this.add(a ?: BigInteger.ZERO)