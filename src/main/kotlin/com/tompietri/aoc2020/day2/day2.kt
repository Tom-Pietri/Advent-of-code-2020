package com.tompietri.aoc2020.day2

fun day2FirstSolution(input: List<String>) : Int {
    return transformInputToPasswordWithPolicies(input).filter {
        val count = it.password.filter { letter ->  letter == it.policy.letter }.length
        count >= it.policy.minCount && count <= it.policy.maxCount
    }.size
}

fun day2SecondSolution(input: List<String>) : Int {
    return transformInputToPasswordWithPolicies(input).filter {
        val firstLetterMatch = it.password[it.policy.minCount - 1] == it.policy.letter
        val secondPositionMatch = it.password[it.policy.maxCount - 1] == it.policy.letter
        firstLetterMatch.xor(secondPositionMatch)
    }.size
}

fun transformInputToPasswordWithPolicies(input: List<String>): List<PasswordWithPolicy> {
    val regex = Regex("(\\d*)-(\\d*) (.): (.*)")
    return input.map {
        val regexMatches = regex.find(it)!!.groupValues
        PasswordWithPolicy(
                Policy(
                        minCount = regexMatches[1].toInt(),
                        maxCount = regexMatches[2].toInt(),
                        letter = regexMatches[3][0]
                ), password = regexMatches[4]
        )
    }
}

data class PasswordWithPolicy(val policy: Policy, val password: String)
data class Policy(val minCount: Int, val maxCount: Int, val letter: Char)
