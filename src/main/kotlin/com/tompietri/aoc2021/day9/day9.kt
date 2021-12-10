package com.tompietri.aoc2021.day9

import com.tompietri.tools.data.Point2D

fun day9FirstSolution(input: List<String>): Int {
    val lowestPoints = mutableListOf<Int>()
    val inputAsMap = inputToMap(input)

    for(x in input.indices) {
        for(y in input[0].indices) {
            val currentValue = inputAsMap[Point2D(x, y)]!!
            if (areAdjacentValueBigger(x, y, inputAsMap)
            ) {
                lowestPoints.add(currentValue)
            }
        }
    }

    return lowestPoints.sumOf { it + 1 }
}

fun day9SecondSolution(input: List<String>): Int {
    val lowestPoints = mutableListOf<Point2D>()
    val inputAsMap = inputToMap(input)
    for(x in input.indices) for(y in input[0].indices) {
        if (areAdjacentValueBigger(x, y, inputAsMap)) {
            lowestPoints.add(Point2D(x, y))
        }
    }

    return lowestPoints.map { bassinSize(it, inputAsMap) }.sorted().takeLast(3).reduce(Int::times)
}

fun bassinSize(bassinStart: Point2D, inputAsMap: Map<Point2D, Int>): Int {
    val pointsVisited = mutableSetOf<Point2D>()

    val nextStartingPoints = mutableListOf(bassinStart)

    while (nextStartingPoints.isNotEmpty()) {
        val point = nextStartingPoints.removeFirst()
        pointsVisited.add(point)

        val up = Point2D(point.x - 1, point.y)
        if (isPointHigherThanStart(point, up, inputAsMap)) nextStartingPoints.add(up)

        val down = Point2D(point.x + 1, point.y)
        if (isPointHigherThanStart(point, down, inputAsMap)) nextStartingPoints.add(down)

        val left = Point2D(point.x, point.y - 1)
        if (isPointHigherThanStart(point, left, inputAsMap)) nextStartingPoints.add(left)

        val right = Point2D(point.x, point.y + 1)
        if (isPointHigherThanStart(point, right, inputAsMap)) nextStartingPoints.add(right)
    }

    return pointsVisited.size
}

private fun isPointHigherThanStart(start: Point2D, point: Point2D, inputAsMap: Map<Point2D, Int>) =
    inputAsMap[start]!! < (inputAsMap[point] ?: 0) && inputAsMap[point] != 9

private fun areAdjacentValueBigger(
    x: Int,
    y: Int,
    inputAsMap: Map<Point2D, Int>
): Boolean {
    val currentValue = inputAsMap[Point2D(x, y)]!!
    return currentValue < (inputAsMap[Point2D(x - 1, y)] ?: 9) &&
            currentValue < (inputAsMap[Point2D(x + 1, y)] ?: 9) &&
            currentValue < (inputAsMap[Point2D(x, y - 1)] ?: 9) &&
            currentValue < (inputAsMap[Point2D(x, y + 1)] ?: 9)
}

private fun inputToMap(input: List<String>): Map<Point2D, Int> {
    val inputAsMap = input.mapIndexed { x, line ->
        line.mapIndexed { y, v -> Point2D(x, y) to Character.getNumericValue(v) }
    }.flatten().toMap()
    return inputAsMap
}
