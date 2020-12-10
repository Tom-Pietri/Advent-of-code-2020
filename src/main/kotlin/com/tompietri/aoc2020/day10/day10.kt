package com.tompietri.aoc2020.day10

import java.math.BigInteger
import java.util.*


fun day10FirstSolution(input: List<Int>): Int {
    val sortedInput = listOf(0) + input.sorted()
    var count1 = 0
    var count3 = 0

    for(i in 0 until  sortedInput.size - 1) {
        val countToUpdate = sortedInput[i + 1] - sortedInput[i]
        if(countToUpdate == 1) {
            count1++
        } else if (countToUpdate == 3) {
            count3++
        }
    }

    return count1*(count3 + 1)
}

fun day10SecondSolution(input: List<Int>): BigInteger {
    val sortedInput = (listOf(0) + input.sorted()).toSet()

    val currentGroup = mutableSetOf<Int>()
    val groups = mutableSetOf<SortedSet<Int>>()
    sortedInput.forEach { currentValue ->
        currentGroup.add(currentValue)
        val containsNext = sortedInput.contains(currentValue + 1)
        val containsNext2 = sortedInput.contains(currentValue + 2)
        val containsNext3 = sortedInput.contains(currentValue + 3)
        if(containsNext3 && !containsNext && !containsNext2) {
            groups.add(currentGroup.toSet().toSortedSet())
            currentGroup.clear()
        }
    }

    groups.add(currentGroup.toSet().toSortedSet())

    return groups.map { group -> nbWaysToGoFromStartToEnd(group)}
        .fold(BigInteger.ONE) { acc, i ->
            acc * i.toBigInteger()
        }
}


fun nbWaysToGoFromStartToEnd(group: SortedSet<Int>): Int {
    val mapOfDirections = mutableMapOf<Int, List<Int>>()
    group.forEach {
        val mutableList = mutableListOf<Int>()
        for (i in 1..3) {
            val containsNext = group.contains(it + i)
            if (containsNext) {
                mutableList.add(it + i)
            }
        }
        mapOfDirections[it] = mutableList.toList()
    }

    val firstValue = group.first()
    return countAllPathRecursively(firstValue, mapOfDirections, emptyList())
}

fun countAllPathRecursively(currentValue: Int, mapOfDirections: Map<Int, List<Int>>, path: List<Int>): Int {
    val directions = mapOfDirections[currentValue]!!
    return if (directions.isEmpty()) {
        1
    } else {
        directions.map { countAllPathRecursively(it, mapOfDirections, path + currentValue) }.sum()
    }
}
