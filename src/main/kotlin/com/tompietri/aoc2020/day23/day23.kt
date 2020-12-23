package com.tompietri.aoc2020.day23

import java.math.BigInteger

fun day23FirstSolution(input: String, nbMoves: Int): String {

    var cups = input.toList().map { it.toString() }.map { it.toInt() }

    val cupMap = mutableMapOf<Int, Int>()
    cups.forEachIndexed { i, value ->
        cupMap[value] = cups[(i + 1) % cups.size]
    }

    var currentCup = cups.first()
    for(i in 0 until nbMoves) {
        val firstCup = cupMap[currentCup]!!
        val secondCup = cupMap[firstCup]!!
        val thirdCup = cupMap[secondCup]!!
        val maxCupValue = cupMap.keys.maxOrNull() ?: error("Should allways have max")

        var destinationIndex = currentCup - 1
        if (destinationIndex < 1) {
            destinationIndex = maxCupValue
        }
        while (destinationIndex == firstCup || destinationIndex == secondCup || destinationIndex == thirdCup) {
            destinationIndex = destinationIndex - 1
            if (destinationIndex < 1) {
                destinationIndex = maxCupValue
            }
        }

        val cupAfterThird = cupMap[destinationIndex]!!
        cupMap[destinationIndex] = firstCup
        cupMap[currentCup] = cupMap[thirdCup]!!
        cupMap[thirdCup] = cupAfterThird
        currentCup = cupMap[currentCup]!!
    }

    var nextValue = cupMap[1]
    val strBuilder = StringBuilder()
    while (nextValue != 1) {
        strBuilder.append(nextValue)
        nextValue = cupMap[nextValue]!!
    }


    return strBuilder.toString()
}

fun day23SecondSolution(input: String, nbMoves : Int): BigInteger {

    var initializationCups = input.toList().map { it.toString() }.map { it.toInt() }.toMutableList()
    val maxCup = initializationCups.maxOrNull() ?: error("Should allways have a max")
    for(i in 1 .. (1000000 - initializationCups.size)) {
        initializationCups.add(maxCup + i)
    }

    var cups = initializationCups.toList()

    val cupMap = mutableMapOf<Int, Int>()
    cups.forEachIndexed { i, value ->
        cupMap[value] = cups[(i + 1) % cups.size]
    }

    var currentCup = cups.first()
    val maxCupValue = cupMap.keys.maxOrNull() ?: error("Should allways have max")

    for(i in 0 until nbMoves) {
        val firstCup = cupMap[currentCup]!!
        val secondCup = cupMap[firstCup]!!
        val thirdCup = cupMap[secondCup]!!

        var destinationIndex = currentCup - 1
        if (destinationIndex < 1) {
            destinationIndex = maxCupValue
        }
        while (destinationIndex == firstCup || destinationIndex == secondCup || destinationIndex == thirdCup) {
            destinationIndex = destinationIndex - 1
            if (destinationIndex < 1) {
                destinationIndex = maxCupValue
            }
        }

        val cupAfterThird = cupMap[destinationIndex]!!
        cupMap[destinationIndex] = firstCup
        cupMap[currentCup] = cupMap[thirdCup]!!
        cupMap[thirdCup] = cupAfterThird
        currentCup = cupMap[currentCup]!!
    }


    return cupMap[1]!!.toBigInteger() * cupMap[cupMap[1]!!]!!.toBigInteger()
}



private fun Set<Int>.toStringedCup() : String {
    val indexOf1 = this.indexOf(1)

    val strBuilder = StringBuilder()
    for(i in this.indices) {
        val valueToAdd = this.elementAt((i + indexOf1) % this.size)
        if(valueToAdd != 1) {
            strBuilder.append(valueToAdd)
        }
    }
    return strBuilder.toString()
}

data class Cup(val value: Int, var next: Cup)
