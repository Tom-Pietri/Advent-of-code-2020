package com.tompietri.aoc2020.day11


fun day11FirstSolution(input: List<String>): Int {
    var currentGrid = input.map { it.toCharArray().toList() }
    val nextGrid = currentGrid.map { it.toMutableList() }

    while (true) {
        for (i in currentGrid.indices) for (j in currentGrid[0].indices) {
            val seat = currentGrid[i][j]
            val nbOfOccupiedAdjacentSeat = countAdjacentOccupiedSeats(i, j, currentGrid)

            if (seat == 'L' && nbOfOccupiedAdjacentSeat == 0) {
                nextGrid[i][j] = '#'
            } else if (seat == '#' && nbOfOccupiedAdjacentSeat >= 4) {
                nextGrid[i][j] = 'L'
            }
        }

        if(currentGrid == nextGrid) {
            return currentGrid.flatMap { it.filter { seat -> seat == '#' } }.size
        }

        currentGrid = nextGrid.map { it.toList() }
    }
}

fun day11SecondSolution(input: List<String>): Int {
    var currentGrid = input.map { it.toCharArray().toList() }
    val nextGrid = currentGrid.map { it.toMutableList() }

    while (true) {
        for (i in currentGrid.indices) for (j in currentGrid[0].indices) {
            val seat = currentGrid[i][j]
            val nbOfOccupiedVisibleSeat = countVisibleOccupiedSeats(i, j, currentGrid)

            if (seat == 'L' && nbOfOccupiedVisibleSeat == 0) {
                nextGrid[i][j] = '#'
            } else if (seat == '#' && nbOfOccupiedVisibleSeat >= 5) {
                nextGrid[i][j] = 'L'
            }
        }

        if(currentGrid == nextGrid) {
            return currentGrid.flatMap { it.filter { seat -> seat == '#' } }.size
        }

        currentGrid = nextGrid.map { it.toList() }
    }
}


fun countAdjacentOccupiedSeats(i: Int, j: Int, grid: List<List<Char>>) : Int {
    var nbOfOccupiedAdjacentSeat = 0
    for (iOffset in -1..1) for (jOffset in -1..1) {
        if ((iOffset != 0 || jOffset != 0) && (i + iOffset in grid.indices) && (j + jOffset in grid[i].indices) && grid[i + iOffset][j + jOffset] == '#') {
            nbOfOccupiedAdjacentSeat++
        }
    }

    return nbOfOccupiedAdjacentSeat
}


fun countVisibleOccupiedSeats(i: Int, j: Int, grid: List<List<Char>>) : Int {
    var nbOfOccupiedAdjacentSeat = 0
    for (iOffsetDirection in -1..1) for (jOffsetDirection in -1..1) {
        if(iOffsetDirection == 0 && jOffsetDirection == 0) {
            continue
        }

        var iOffset = iOffsetDirection
        var jOffset = jOffsetDirection

        while (true) {
            if(i + iOffset !in grid.indices || j + jOffset !in grid[i].indices) {
                break
            }

            if(grid[i + iOffset][j + jOffset] == '#') {
                nbOfOccupiedAdjacentSeat++
                break
            } else if(grid[i + iOffset][j + jOffset] == 'L') {
                break
            }

            iOffset += iOffsetDirection
            jOffset += jOffsetDirection
        }
    }

    return nbOfOccupiedAdjacentSeat
}
