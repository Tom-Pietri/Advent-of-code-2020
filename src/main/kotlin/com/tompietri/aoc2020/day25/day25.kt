package com.tompietri.aoc2020.day25

import java.math.BigInteger

fun day25FirstSolution(input: List<Int>): Int {
    val (cardPK, doorPK) = input

    val cardLastFoundSecret = findLoopSizeWithSubject7(cardPK)
    val doorlastFoundSecret = findLoopSizeWithSubject7(doorPK)

    var result = 1
    repeat(cardLastFoundSecret.loopSize) {
        result = (result.toBigInteger() * doorlastFoundSecret.publicKey.toBigInteger() % BigInteger("20201227")).toInt()
    }

    return result
}

fun day25SecondSolution(input: List<Int>): Int {
    return 0
}

fun findLoopSizeWithSubject7(publicKey: Int) : Secret {
    var loopSize = 1
    val testedSubject = 7
    var value = 1
    while (true) {
        value = (value * testedSubject) % 20201227
        if(value == publicKey) {
            return Secret(publicKey, testedSubject, loopSize)
        }

        loopSize++
    }
}

data class Secret(val publicKey : Int, val subject: Int, val loopSize: Int)
