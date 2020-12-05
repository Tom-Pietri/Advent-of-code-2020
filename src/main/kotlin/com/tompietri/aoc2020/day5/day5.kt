package com.tompietri.aoc2020.day5

fun day5FirstSolution(input: List<String>) = input.map(String::toBoardingPass).maxOf { it.id }

fun day5SecondSolution(input: List<String>): Int  {
    val seatsById = input.map(String::toBoardingPass).map { it.id to it }.toMap()
    return (1..126).flatMap { row -> (0..7).map { col -> row * 8 + col } }
        .find { seatsById[it] == null && seatsById[it - 1] != null && seatsById[it + 1] != null } ?: error("no seat found")
}

fun getRow(it: String): Int = findPostionInRange(positionInstructions = it.dropLast(3), max = 127, lowerChar =  'F')
fun getColumn(it: String): Int = findPostionInRange(positionInstructions = it.takeLast(3), max = 7, lowerChar = 'L')

private fun findPostionInRange(positionInstructions: String, min: Int = 0, max: Int, lowerChar: Char): Int {
    return positionInstructions.fold(min..max) { acc, letter ->
        val nextChange = (acc.last - acc.first + 1) / 2

        if (letter == lowerChar) acc.first..acc.last - nextChange
        else acc.first + nextChange..acc.last
    }.last
}


fun String.toBoardingPass(): BoardingPass = BoardingPass(Seat(getRow(this), getColumn(this)))

data class BoardingPass(val seat: Seat) {
    val id = seat.row * 8 + seat.column
}
data class Seat(val row: Int, val column: Int)


