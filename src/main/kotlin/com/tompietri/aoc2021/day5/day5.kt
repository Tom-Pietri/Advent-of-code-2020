package com.tompietri.aoc2021.day5

import com.tompietri.tools.data.Point2D
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.abs

fun day5FirstSolution(input: List<String>): Int {
    val lines = input.toLines()

    val grid = List(1000) { MutableList(1000) { 0 } }

    lines.filter { it.horizontal || it.vertical }
        .flatMap { it.getAllPoint() }
        .forEach { grid[it.x][it.y]++ }

    return grid.sumOf { line -> line.count { it > 1 } }
}

fun day5SecondSolution(input: List<String>): Int {
    val lines = input.toLines()

    val grid = List(1000) { MutableList(1000) { 0 } }

    lines.flatMap { it.getAllPoint() }
        .forEach { grid[it.x][it.y]++ }

    return grid.sumOf { line -> line.count { it > 1 } }
}

private fun List<String>.toLines() = this.map { line ->
    val (start, end) = line.split(" -> ")
        .map {
            val (x, y) = it.split(",").map(String::toInt)
            Point2D(x, y)
        }
    Line(start, end)
}

data class Line(val start: Point2D, val end: Point2D) {
    val horizontal = start.x == end.x
    val vertical = start.y == end.y
    private val diagonalLength = abs(start.x - end.x)

    fun getAllPoint(): List<Point2D> {
        return if(horizontal) {
            (min(start.y, end.y)..max(start.y, end.y)).map { i -> Point2D(start.x, i) }
        } else if(vertical) {
            (min(start.x, end.x)..max(start.x, end.x)).map { i -> Point2D(i, start.y) }
        } else {
            val (leftX, rightX) = if (start.x < end.x) Pair(start, end) else Pair(end, start)
            val yDirection = if (leftX.y < rightX.y) 1 else -1
            (0..diagonalLength).map { i -> Point2D(leftX.x + i, leftX.y + (i * yDirection)) }
        }
    }
}
