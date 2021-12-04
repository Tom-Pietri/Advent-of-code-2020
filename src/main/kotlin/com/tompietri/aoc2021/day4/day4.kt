package com.tompietri.aoc2021.day4

fun day4FirstSolution(input: List<String>): Int {
    val drawnNumbers = inputToDrawnNumbers(input)
    val bingos = inputToBingos(input)

    drawnNumbers.forEach {  drawn ->
        bingos.forEach { bingo ->
            bingo.checkDrawnNumber(drawn)
            if(bingo.hasWon())
                return bingo.calculateScore(drawn)
        }
    }
    error("A board should have won")
}

fun day4SecondSolution(input: List<String>): Int {
    val drawnNumbers = inputToDrawnNumbers(input)
    val bingos = inputToBingos(input).toMutableList()

    drawnNumbers.forEach { drawn ->
        bingos.forEach { grid ->
            grid.checkDrawnNumber(drawn)
        }

        if(bingos.size > 1) {
            bingos.removeIf { it.hasWon() }
        } else {
            if(bingos.first().hasWon()) {
                return bingos.first().calculateScore(drawn)
            }
        }
    }
    error("should have ended")
}

private fun inputToDrawnNumbers(input: List<String>) = input[0].split(",").map { it.toInt() }

private fun inputToBingos(input: List<String>): List<BingoGrid> {
    return input.asSequence()
        .drop(1).chunked(6)
        .map { it.drop(1) }
        .map { it.map(::lineToInts) }
        .map { BingoGrid(it) }
        .toList()
}

private fun lineToInts(line: String) = line.split(" ")
    .filter(String::isNotEmpty).map(String::toInt)

data class BingoGrid(val lines: List<List<Int>>) {
    private val matches = MutableList(lines.size) {
        MutableList(lines.size) { false }
    }

    fun hasWon(): Boolean {
        for (i in matches.indices) {
            val lineWon = matches[i].all { it }
            val columnWon = matches.map { it[i] }.all { it }
            if(lineWon || columnWon) {
                return true
            }
        }

        return false
    }

    fun checkDrawnNumber(drawn: Int) {
        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, value ->
                if (value == drawn) {
                    matches[i][j] = true
                }
            }
        }
    }

    fun calculateScore(drawn: Int): Int {
        return lines.mapIndexed { i, line ->
            line.mapIndexed { j, value ->
                if(matches[i][j]) {
                    0
                } else {
                    value
                }
            }
        }.flatten().sum() * drawn
    }
}