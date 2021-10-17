package com.tompietri.aoc2015.day1

fun day1FirstSolution(input: String) = input.count { it == '(' } - input.count { it == ')' }

fun day1SecondSolution(input: String): Int {
    var floor = 0
    input.forEachIndexed { index, it ->
        if (it == '(') floor++ else floor--
        if (floor == -1) return index + 1
    }
    return -1
}
