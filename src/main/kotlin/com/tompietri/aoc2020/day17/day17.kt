package com.tompietri.aoc2020.day17

import java.lang.Integer.max
import java.lang.Integer.min

fun day17FirstSolution(input: List<String>): Int {
    var grid = mutableMapOf<Point3D, Boolean>()
    input.forEachIndexed { x, it ->
        it.forEachIndexed { y , c ->
            grid[Point3D(x, y, 0)] = c == '#'
        }
    }

    var minX = 0
    var maxX = input[0].length
    var minY = 0
    var maxY = input.size
    var minZ = 0
    var maxZ = 0


    repeat(6) {
        val nextGrid = mutableMapOf<Point3D, Boolean>()
        for (z in minZ - 1..maxZ + 1) {
            for (y in minY - 1..maxY + 1) {
                for (x in minX - 1..maxX + 1) {
                    val currentPoint = Point3D(x, y, z)
                    val nbAdjacents = nbAdjacentActivePoints(grid, currentPoint)

                    val currentPointValue = grid.getOrDefault(currentPoint, false)
                    if ((currentPointValue && nbAdjacents == 2 ) || nbAdjacents == 3) {
                        minX = min(currentPoint.x, minX)
                        minY = min(currentPoint.y, minY)
                        minZ = min(currentPoint.z, minZ)

                        maxX = max(currentPoint.x, maxX)
                        maxY = max(currentPoint.y, maxY)
                        maxZ = max(currentPoint.z, minZ)

                        nextGrid[currentPoint] = true
                    } else {
                        nextGrid[currentPoint] = false
                    }
                }
            }
        }

        grid = nextGrid
    }

    return grid.values.filter { it }.count()
}

fun nbAdjacentActivePoints(grid: Map<Point3D, Boolean>, point: Point3D) : Int {
    var count = 0

    for (x in point.x - 1..point.x + 1) {
        for (y in point.y - 1..point.y + 1) {
            for (z in point.z - 1..point.z + 1) {
                val pointToCheck = Point3D(x, y, z)
                if (pointToCheck != point && grid.getOrDefault(Point3D(x, y, z), false))
                    count++
            }
        }
    }

    return count
}


fun nbAdjacentActivePoints(grid: Map<Point4D, Boolean>, point: Point4D) : Int {
    var count = 0

    for (x in point.x - 1..point.x + 1) {
        for (y in point.y - 1..point.y + 1) {
            for (z in point.z - 1..point.z + 1) {
            for (w in point.w - 1..point.w + 1) {
                val pointToCheck = Point4D(x, y, z, w)
                if (pointToCheck != point && grid.getOrDefault(Point4D(x, y, z, w), false))
                    count++
                }
            }
        }
    }

    return count
}

fun day17SecondSolution(input: List<String>): Int {
    var grid = mutableMapOf<Point4D, Boolean>()
    input.forEachIndexed { x, it ->
        it.forEachIndexed { y , c ->
            grid[Point4D(x, y, 0, 0)] = c == '#'
        }
    }

    var minX = 0
    var maxX = input[0].length
    var minY = 0
    var maxY = input.size

    var minZ = 0
    var maxZ = 0
    var minW = 0
    var maxW = 0


    repeat(6) {
        val nextGrid = mutableMapOf<Point4D, Boolean>()
        for (w in minW - 1..maxW + 1) {
            for (z in minZ - 1..maxZ + 1) {
                for (y in minY - 1..maxY + 1) {
                    for (x in minX - 1..maxX + 1) {
                        val currentPoint = Point4D(x, y, z, w)
                        val nbAdjacents = nbAdjacentActivePoints(grid, currentPoint)

                        val currentPointValue = grid.getOrDefault(currentPoint, false)
                        if ((currentPointValue && nbAdjacents == 2 ) || nbAdjacents == 3) {
                            minX = min(currentPoint.x, minX)
                            minY = min(currentPoint.y, minY)
                            minZ = min(currentPoint.z, minZ)
                            minW = min(currentPoint.w, minW)

                            maxX = max(currentPoint.x, maxX)
                            maxY = max(currentPoint.y, maxY)
                            maxZ = max(currentPoint.z, minZ)
                            maxW = max(currentPoint.w, minW)

                            nextGrid[currentPoint] = true
                        } else {
                            nextGrid[currentPoint] = false
                        }
                    }
                }
            }
        }

        grid = nextGrid
    }

    return grid.values.filter { it }.count()
}


data class Point3D(val x: Int, val y: Int, val z: Int)
data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int)
