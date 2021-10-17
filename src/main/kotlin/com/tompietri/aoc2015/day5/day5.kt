package com.tompietri.aoc2015.day5

fun day5FirstSolution(input: List<String>): Int {
    return input.count { it.isNice() }
}

fun day5SecondSolution(input: List<String>): Int {
    return input.count { it.isNice2() }
}

private val stringToNotContain = listOf("ab", "cd", "pq", "xy")
private val vowels = setOf('a', 'e', 'i', 'o', 'u')
fun String.isNice(): Boolean {
    if (stringToNotContain.any { this.contains(it) })
        return false

    val nbVowels = this.count { vowels.contains(it) }
    var inARow = false
    for (i in 0 until this.length - 1) {
        if (this[i] == this[i + 1]) {
            inARow = true
            break
        }
    }

    return inARow && nbVowels >= 3
}

fun String.isNice2(): Boolean {
    var pair = false
    var repeat = false
    for (i in 0 until this.length - 2) {
        if(this[i] == this[i + 2]) {
            repeat = true
            break
        }
    }

    for(i in 0 until this.length - 1) {
        if(this.substring(i+2).contains(this.substring(i, i + 2))) {
            pair = true
            break
        }
    }

    return pair && repeat;
}