package com.tompietri.aoc2020.day15

fun day15FirstSolution(input: String) = solutionWithNbTurn(input, 2020)
fun day15SecondSolution(input: String)= solutionWithNbTurn(input, 30000000)

fun solutionWithNbTurn(input: String, nbTurn: Int): Int {
    val initialNumbers = input.split(",").map { it.toInt() }

    val spokenNumbers = initialNumbers
        .mapIndexed { index, i -> i to SpokenNumber(index + 1, null) }
        .toMap().toMutableMap()

    var numberToSay = initialNumbers.last()

    for (turnNumber in initialNumbers.size + 1..nbTurn) {
        numberToSay = spokenNumbers[numberToSay]?.let {
            it.secondLastTimeSaid?.let { secondLastTimeSaid -> it.lastTimeSaid - secondLastTimeSaid }
        } ?: 0

        spokenNumbers[numberToSay] = spokenNumbers[numberToSay]?.let {
            it.copy(lastTimeSaid = turnNumber, secondLastTimeSaid = it.lastTimeSaid)
        } ?: SpokenNumber(turnNumber, null)
    }

    return numberToSay
}

data class SpokenNumber(val lastTimeSaid: Int, val secondLastTimeSaid: Int?)
