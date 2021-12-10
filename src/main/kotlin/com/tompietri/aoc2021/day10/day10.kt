package com.tompietri.aoc2021.day10

fun day10FirstSolution(input: List<String>) = input.sumOf { computeErrorValue(it) }

fun day10SecondSolution(input: List<String>): Long {
    val sortedScores = input.filter { computeErrorValue(it) == 0 }
        .map { finishString(it) }
        .map { computeAutocompleteScore(it) }
        .sorted()

    return sortedScores[sortedScores.size / 2]
}

val autocompleteValue = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
fun computeAutocompleteScore(autocompleteChar: List<Char>): Long {
    return autocompleteChar.map { autocompleteValue[it]!! }
        .fold(0L) { acc, it -> acc * 5 + it }
}

val errorValue = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
fun computeErrorValue(s: String): Int {
    val nextClosing = mutableListOf<Char>()

    s.forEach {
        when (it) {
            '(' -> nextClosing.add(')')
            '{' -> nextClosing.add('}')
            '[' -> nextClosing.add(']')
            '<' -> nextClosing.add('>')
            else -> {
                val removeLast = nextClosing.removeLast()
                if (removeLast != it) {
                    return errorValue[it]!!
                }
            }
        }
    }

    return 0
}

fun finishString(s: String): List<Char> {
    val nextClosing = mutableListOf<Char>()

    s.forEach {
        when (it) {
            '(' -> nextClosing.add(')')
            '{' -> nextClosing.add('}')
            '[' -> nextClosing.add(']')
            '<' -> nextClosing.add('>')
            else -> nextClosing.removeLast()
        }
    }

    return nextClosing.reversed()
}