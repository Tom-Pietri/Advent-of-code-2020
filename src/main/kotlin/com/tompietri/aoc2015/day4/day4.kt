package com.tompietri.aoc2015.day4

import java.math.BigInteger
import java.security.MessageDigest

fun day4FirstSolution(input: String): Int {
    return findMd5hashWithNbZeroes(input, 5)
}

fun day4SecondSolution(input: String): Int {
    return findMd5hashWithNbZeroes(input, 6)
}

private fun findMd5hashWithNbZeroes(input: String, nbZeroes: Int): Int {
    val zeroes = "".padEnd(nbZeroes, '0')
    var i = 1
    while (true) {
        if (md5(input + i.toString()).startsWith(zeroes)) {
            return i
        }
        i++
    }
}

fun md5(input: String): String {
    return BigInteger(1, md5MessageDigest.digest(input.toByteArray()))
        .toString(16).padStart(32, '0')
}

private val md5MessageDigest = MessageDigest.getInstance("MD5")