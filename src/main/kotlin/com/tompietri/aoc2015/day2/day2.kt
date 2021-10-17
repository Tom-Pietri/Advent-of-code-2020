package com.tompietri.aoc2015.day2

import kotlin.math.min

fun day2FirstSolution(input: List<String>) = input.map(String::toGift).sumOf { it.wrappingPaperNeed() }

fun day2SecondSolution(input: List<String>) = input.map(String::toGift).sumOf { it.wrappingRibonNeed() }

fun String.toGift(): Gift {
    val (l, w, h) = this.split("x").map(String::toInt)
    return Gift(l, w, h)
}

data class Gift(val length: Int, val width: Int, val height: Int) {
    fun wrappingPaperNeed(): Int {
        val first = 2 * length * width
        val second = 2 * width * height
        val third = 2 * height * length
        return first + second + third + (min(min(first, second), third) / 2)
    }

    fun wrappingRibonNeed(): Int {
        return when (min(min(length, width), height)) {
            length -> (length * 2) + min(width, height) * 2
            width -> (width * 2) + min(length, height) * 2
            height -> (height * 2) + min(length, width) * 2
            else -> error("Impossible")
        } + length * width * height
    }
}