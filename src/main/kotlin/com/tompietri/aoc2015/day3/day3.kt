package com.tompietri.aoc2015.day3

fun day3FirstSolution(input: String): Int {
    val visitedHouses = mutableSetOf<Point2D>()
    var currentPosition = Point2D(0, 0)
    visitedHouses.add(currentPosition)
    input.map { Point2D.fromChar(it) }
        .forEach {
            currentPosition = currentPosition.add(it)
            visitedHouses.add(currentPosition)
        }

    return visitedHouses.size
}

fun day3SecondSolution(input: String): Int {
    val visitedHouses = mutableSetOf<Point2D>()
    var currentPosition = Point2D(0, 0)
    var currentRobotPosition = Point2D(0, 0)
    visitedHouses.add(currentPosition)
    input.map { Point2D.fromChar(it) }
        .forEachIndexed { index, it ->
            when (index % 2) {
                0 -> {
                    currentPosition = currentPosition.add(it)
                    visitedHouses.add(currentPosition)
                }
                1 -> {
                    currentRobotPosition = currentRobotPosition.add(it)
                    visitedHouses.add(currentRobotPosition)
                }
            }
        }

    return visitedHouses.size
}

data class Point2D(val x: Int, val y: Int) {
    fun add(other: Point2D) = this.copy(x = x + other.x, y = y + other.y)

    companion object {
        fun fromChar(it: Char) = when (it) {
            '>' -> Point2D(1, 0)
            '<' -> Point2D(-1, 0)
            '^' -> Point2D(0, -1)
            'v' -> Point2D(0, 1)
            else -> error("Impossible")
        }
    }
}