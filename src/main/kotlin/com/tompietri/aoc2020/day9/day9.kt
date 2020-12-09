package com.tompietri.aoc2020.day9

import java.math.BigInteger

fun day9FirstSolution(input: List<String>, preambleSize: Int = 25): BigInteger {
    val inputAsNumbers = input.map { it.toBigInteger() }

    for (indexOfTestedNumber in preambleSize..inputAsNumbers.size) {
        var foundMatching = false
        for(i in (indexOfTestedNumber - preambleSize)..indexOfTestedNumber - 2) {
            for (j in (i + 1) until indexOfTestedNumber) {
                foundMatching = (inputAsNumbers[i] + inputAsNumbers[j]) == inputAsNumbers[indexOfTestedNumber]
                if(foundMatching) {
                    break
                }
            }
            if(foundMatching) {
                break
            }
        }

        if(!foundMatching) {
            return inputAsNumbers[indexOfTestedNumber]
        }
    }

    return BigInteger("-1")
}

fun day9SecondSolution(input: List<String>): BigInteger {

    val numberToFind = day9FirstSolution(input)
    val inputAsNumbers = input.map { it.toBigInteger() }

    for(i in inputAsNumbers.indices) {
        var currentCount = BigInteger("0")
        val usedNumbers = mutableSetOf<BigInteger>()
        for(j in i..inputAsNumbers.size) {
            currentCount += inputAsNumbers[j]
            usedNumbers.add(inputAsNumbers[j])

            if(currentCount == numberToFind) {
                return usedNumbers.minOrNull()!! + usedNumbers.maxOrNull()!!
            } else if(currentCount > numberToFind) {
                break
            }
        }
    }

    return BigInteger("-1")
}
