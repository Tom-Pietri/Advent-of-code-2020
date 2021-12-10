package com.tompietri.aoc2021.day8

import com.tompietri.aoc2021.day8.Segments.*

fun day8FirstSolution(input: List<String>): Int {
    val sizes = setOf(2, 4, 3, 7)
    return input.map { it.split(" | ")[1] }
        .map { it.split(" ") }
        .map { it.filter { nb -> sizes.contains(nb.length) }.size }
        .sum()
}


fun day8SecondSolution(input: List<String>): Int {
    return input.sumOf { line ->
        val (uniqueDigits, numbers) = line.split(" | ").map { it.split(" ") }
        val segementConfiguration = findConfiguration(uniqueDigits)
        numbers.map { letters ->
            segementConfiguration.find { pair ->
                letters.toSet().containsAll(pair.second) && pair.second.containsAll(
                    letters.toSet()
                )
            }!!.first
        }.joinToString("")
            .toInt()
    }
}

fun findConfiguration(uniqueDigits: List<String>): List<Pair<Int, Set<Char>>> {
    val potentialValues = Segments.values().associateWith { mutableSetOf<Char>() }
    val wiresForOne = uniqueDigits.find { it.length == 2 }!!.toSet()
    val wiresForFour = uniqueDigits.find { it.length == 4 }!!.toSet()
    val wiresForSeven = uniqueDigits.find { it.length == 3 }!!.toSet()

    potentialValues[TOP_RIGHT]!!.addAll(wiresForOne)
    potentialValues[BOTTOM_RIGHT]!!.addAll(wiresForOne)
    potentialValues[TOP]!!.addAll(wiresForSeven.filterNot { wiresForOne.contains(it) })

    potentialValues[TOP_LEFT]!!.addAll(wiresForFour.filterNot { wiresForOne.contains(it) })
    potentialValues[MIDDLE]!!.addAll(wiresForFour.filterNot { wiresForOne.contains(it) })

    val wiresForEight = uniqueDigits.find { it.length == 7 }!!.toSet()
    potentialValues[BOTTOM_LEFT]!!.addAll(
        wiresForEight.filterNot { wiresForFour.contains(it) }.filterNot { wiresForSeven.contains(it) }
    )
    potentialValues[BOTTOM]!!.addAll(
        wiresForEight.filterNot { wiresForFour.contains(it) }.filterNot { wiresForSeven.contains(it) }
    )

    val zeroSixNine = uniqueDigits.filter { it.length == 6 }
    val nine = s(zeroSixNine, potentialValues, listOf(BOTTOM_LEFT))
    val six = s(zeroSixNine, potentialValues, listOf(TOP_RIGHT))
    val zero = s(zeroSixNine, potentialValues, listOf(MIDDLE))


    val twoThreeFive = uniqueDigits.filter { it.length == 5 }
    val two = s(twoThreeFive, potentialValues, listOf(BOTTOM_RIGHT, TOP_LEFT))
    val three = s(twoThreeFive, potentialValues, listOf(BOTTOM_LEFT, TOP_LEFT))
    val five = s(twoThreeFive, potentialValues, listOf(TOP_RIGHT, BOTTOM_LEFT))


    return listOf(
        Pair(0, zero.toSet()),
        Pair(1, wiresForOne),
        Pair(2, two.toSet()),
        Pair(3, three.toSet()),
        Pair(4, wiresForFour),
        Pair(5, five.toSet()),
        Pair(6, six.toSet()),
        Pair(7, wiresForSeven),
        Pair(8, wiresForEight),
        Pair(9, nine.toSet())
    )
}

private fun s(
    twoThreeFive: List<String>,
    potentialValues: Map<Segments, MutableSet<Char>>,
    segments: List<Segments>
): String {
    return segments.fold(twoThreeFive) { list, side ->
        list.filterNot { it.toSet().containsAll(potentialValues[side]!!) }
    }[0]
}


enum class Segments {
    TOP,
    TOP_LEFT,
    TOP_RIGHT,
    MIDDLE,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    BOTTOM;
}

