package com.tompietri.aoc2020.day20

import java.math.BigInteger

fun day20FirstSolution(input: List<String>): BigInteger {
    val grids = Grid.fromInput(input)

    val matches = grids.map { testedGrid ->
        val toto = grids.filter { otherGrid ->
            otherGrid.id != testedGrid.id
        }.filter { otherGrid ->

            val defaultMatch = testAllSides(testedGrid, otherGrid)
            val verticalFlipMatch = otherGrid.flipVertically().let { testAllSides(testedGrid, it) }
            val horizontalFlipMatch = otherGrid.flipHorizontal().let { testAllSides(testedGrid, it) }
            val horizontalAndVerticalFlipMatch =
                otherGrid.flipHorizontal().flipVertically().let { testAllSides(testedGrid, it) }

            defaultMatch || verticalFlipMatch || horizontalFlipMatch || horizontalAndVerticalFlipMatch
        }

        Pair(testedGrid, toto.size)
    }
    return matches.filter { it.second == 2 }.map { it.first.id.toBigInteger() }
        .reduce { acc, bigInteger -> acc * bigInteger }
}

private fun testAllSides(testedGrid: Grid, flippedVertically: Grid): Boolean {
    val rotatedOnce = flippedVertically.rotate()
    val rotatedTwice = rotatedOnce.rotate()
    val rotatedThrice = rotatedTwice.rotate()

    val testedGridLastColMatch = flippedVertically.firstCol() == testedGrid.lastCol()
            || rotatedOnce.firstCol() == testedGrid.lastCol()
            || rotatedTwice.firstCol() == testedGrid.lastCol()
            || rotatedThrice.firstCol() == testedGrid.lastCol()

    val testedGridFirstColMatch = flippedVertically.lastCol() == testedGrid.firstCol()
            || rotatedOnce.lastCol() == testedGrid.firstCol()
            || rotatedTwice.lastCol() == testedGrid.firstCol()
            || rotatedThrice.lastCol() == testedGrid.firstCol()

    val testedGridLastRowMatch = flippedVertically.firstRow() == testedGrid.lastRow()
            || rotatedOnce.firstRow() == testedGrid.lastRow()
            || rotatedTwice.firstRow() == testedGrid.lastRow()
            || rotatedThrice.firstRow() == testedGrid.lastRow()

    val testedGridFirstRowMatch = flippedVertically.lastRow() == testedGrid.firstRow()
            || rotatedOnce.lastRow() == testedGrid.firstRow()
            || rotatedTwice.lastRow() == testedGrid.firstRow()
            || rotatedThrice.lastRow() == testedGrid.firstRow()
    return testedGridLastColMatch || testedGridFirstColMatch || testedGridLastRowMatch || testedGridFirstRowMatch
}

fun day20SecondSolution(input: List<String>): Int {
    val solvedPuzzle = Grid(-1, solvePuzzle(input))

    val part2CorrectOrientation = solvedPuzzle.rotate()

//    part2CorrectOrientation.printGrid()

    val seaMonsterPattern = listOf(
        Regex("..................#."),
        Regex("#....##....##....###"),
        Regex(".#..#..#..#..#..#...")
    )

    val middlePattern = seaMonsterPattern[1]

    for (i in 1 until part2CorrectOrientation.lines.size - 1) {
        val currentLine = part2CorrectOrientation.lines[i]
        for (j in 0 until part2CorrectOrientation.lines.size - 20) {
            val testedPattern = currentLine.subList(j, j + 20).joinToString("")
            if (testedPattern.matches(middlePattern)) {
                val firstLineOfPattern = part2CorrectOrientation.lines[i - 1].subList(j, j + 20).joinToString("")
                val secondLineOfPattern = part2CorrectOrientation.lines[i + 1].subList(j, j + 20).joinToString("")
                if (firstLineOfPattern.matches(seaMonsterPattern[0]) && secondLineOfPattern.matches(seaMonsterPattern[2])) {
                    val gridWithoutSeaMonster = toto(part2CorrectOrientation, i, j)
                }
            }
        }
    }

    return 0
}

private fun toto(grid: Grid, i: Int, j: Int): Grid {
    TODO()
}



private fun solvePuzzle(input: List<String>): List<List<Char>> {
    val puzzle = mutableMapOf<Grid, Point2D>()
    val grids = Grid.fromInput(input).toMutableList()
    val firstGrid = grids.removeFirst()

    puzzle[firstGrid] = Point2D(0, 0)
    val alreadyFilledPoints = mutableSetOf(Point2D(0, 0))
    val gridStack = mutableListOf(firstGrid)
    while (gridStack.isNotEmpty()) {
        val nextGrid = gridStack.removeFirst()
        val nextGridCoordinates = puzzle[nextGrid] ?: error("Should allways be defined")
        Side.values().forEach { side ->
            val sideCoordinate = side.adjacentCoordinates(nextGridCoordinates)
            if (!alreadyFilledPoints.contains(sideCoordinate)) {
                findPieceAdjacentTo(nextGrid, side, grids)?.let { foundGrid ->
                    puzzle[foundGrid] = sideCoordinate
                    alreadyFilledPoints.add(sideCoordinate)
                    gridStack.add(foundGrid)
                    grids.removeIf { it.id == foundGrid.id }
                }
            }
        }
    }

    return puzzle.entries
        .map { it.key.withoutBorders() to it.value }
        .sortedBy { it.second }
        .groupBy { it.second.y }
        .toList().flatMap { (_, grid) ->
            val nbLines = grid.first().first.lines.indices
            val lines = mutableListOf<List<Char>>()
            for (i in nbLines) {
                lines.add(grid.flatMap { it.first.lines[i] })
            }

            lines.toList()
        }
}

fun findPieceAdjacentTo(testedGrid: Grid, side: Side, otherGrids: List<Grid>): Grid? {
    otherGrids.forEach { otherGrid ->
        val matchingGrid = toto(testedGrid, side, otherGrid)
            ?: toto(testedGrid, side, otherGrid.flipHorizontal())
            ?: toto(testedGrid, side, otherGrid.flipVertically())
            ?: toto(testedGrid, side, otherGrid.flipVertically().flipHorizontal())
        if (matchingGrid != null) {
            return matchingGrid
        }
    }

    return null
}

fun toto(testedGrid: Grid, side: Side, otherGrid : Grid): Grid? {
    val rotatedOnce = otherGrid.rotate()
    val rotatedTwice = rotatedOnce.rotate()
    val rotatedThrice = rotatedTwice.rotate()

    if (side.borderMatch(testedGrid, otherGrid)) return otherGrid
    if (side.borderMatch(testedGrid, rotatedOnce)) return rotatedOnce
    if (side.borderMatch(testedGrid, rotatedTwice)) return rotatedTwice
    if (side.borderMatch(testedGrid, rotatedThrice)) return rotatedThrice

    return null
}


data class Grid(val id: Int, val lines: List<List<Char>>) {

    companion object {

        val idRegex = Regex("^Tile (\\d*):$")

        fun fromInput(input: List<String>): List<Grid> {
            val singleLineGrids = input.joinToString(";").split(";;")
            return singleLineGrids.map { singleLineGrid ->
                val tata = singleLineGrid.split(";")

                val (_, id) = idRegex.find(tata[0])!!.groupValues
                val lines = tata.drop(1)
                Grid(id.toInt(), lines.map { it.toCharArray().toList() })
            }
        }
    }

    fun printGrid() {
        this.lines.forEach { line ->
            line.forEach { cell -> print(cell) }
            println()
        }
    }

    fun firstRow(): String = lines.first().toString()
    fun lastRow(): String = lines.last().toString()

    fun firstCol(): String {
        val builder = StringBuilder()
        for (i in lines.indices) {
            builder.append(lines[i][0])
        }
        return builder.toString()
    }

    fun lastCol(): String {
        val builder = StringBuilder()
        for (i in lines.indices) {
            builder.append(lines[i][lines.size - 1])
        }
        return builder.toString()
    }

    fun rotate(): Grid {
        val newLines = lines.map { it.toMutableList() }.toMutableList()

        lines.forEachIndexed { colIndex, s ->
            s.forEachIndexed { rowIndex, c ->
                newLines[rowIndex][lines.size - colIndex - 1] = lines[colIndex][rowIndex]
            }
        }

        return this.copy(lines = newLines.map { it.toList() }.toList())
    }

    fun flipVertically(): Grid = this.copy(lines = lines.reversed())
    fun flipHorizontal(): Grid = this.copy(lines = lines.map { it.reversed() })

    fun withoutBorders() = this.copy(
        lines = this.lines.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
    )

}

data class Point2D(val x: Int, val y: Int) : Comparable<Point2D> {
    override fun compareTo(other: Point2D): Int {
        if(this.y == other.y) {
            if (this.x < other.x) {
                return -1
            } else {
                return 1
            }
        } else if (this.y < other.y) {
            return -1
        } else {
            return 1
        }
    }
}

enum class Side {
    FIRST_COL {
        override fun adjacentCoordinates(point2D: Point2D) = point2D.copy(x = point2D.x - 1)
        override fun borderMatch(origin: Grid, target: Grid) = origin.firstCol() == target.lastCol()
    },
    LAST_COL {
        override fun adjacentCoordinates(point2D: Point2D) = point2D.copy(x = point2D.x + 1)
        override fun borderMatch(origin: Grid, target: Grid) = origin.lastCol() == target.firstCol()
    },
    FIRST_ROW {
        override fun adjacentCoordinates(point2D: Point2D) = point2D.copy(y = point2D.y - 1)
        override fun borderMatch(origin: Grid, target: Grid) = origin.firstRow() == target.lastRow()
    },
    LAST_ROW {
        override fun adjacentCoordinates(point2D: Point2D) = point2D.copy(y = point2D.y + 1)
        override fun borderMatch(origin: Grid, target: Grid) = origin.lastRow() == target.lastRow()
    };

    abstract fun adjacentCoordinates(point2D: Point2D): Point2D
    abstract fun borderMatch(origin: Grid, target : Grid) : Boolean
}
