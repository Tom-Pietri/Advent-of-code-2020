package com.tompietri.aoc2020.day6

fun day6FirstSolution(input: List<String>): Int {
    val groups = inputToGroups(input)
    return groups.map {
        it.answers.fold(setOf<Char>()) { acc, answer ->
            acc + answer.toCharArray().toSet()
        }.size
    }.sum()
}

fun day6SecondSolution(input: List<String>): Int {
    val groups = inputToGroups(input)
    return groups.map {
        val remainingAnswers = it.answers[0].toCharArray().toSet()
        it.answers.map { answer -> answer.toCharArray().toSet() }
            .reduce { acc, answer ->
                acc.filter { char -> answer.contains(char) }.toSet()
            }.count()
    }.sum()
}


fun inputToGroups(input: List<String>): List<Group> {
    var answers = mutableListOf<String>()
    val groups = mutableListOf<Group>()
    input.forEach {
        if(it.isEmpty()) {
            groups.add(Group(answers))
            answers = mutableListOf()
        } else {
            answers.add(it)
        }
    }

    groups.add(Group(answers))

    return groups.toList()
}

data class Group(val answers: List<String>)
