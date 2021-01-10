package com.tompietri.aoc2020.day13

import java.math.BigInteger

fun day13FirstSolution(input: List<String>): Int {
    val earliestDepartTime = input[0].toInt()
    val busToTake = input[1]
        .split(",")
        .filter { it != "x" }
        .map { it.toInt() }
        .map { Pair(it, it - (earliestDepartTime % it)) }
        .minByOrNull { it.second } ?: error("Should allways have a bus")

    return busToTake.first * busToTake.second
}

fun day13SecondSolution(input: List<String>): BigInteger {
    val busToTake = input[1]
        .split(",")
        .mapIndexed { index, s ->
            if(s == "x")
                null
            else {
                BusWithPosition(s.toBigInteger(), index.toBigInteger())
            }
        }.filterNotNull()

    var lcm = lcm(busToTake[0].id, busToTake[1].id + busToTake[1].position)
//    var lcm_ = lcm(busToTake[1].id, busToTake[2].id + (busToTake[2].position - busToTake[1].position))
    var lcm2 = lcm(lcm + (busToTake[2].position - busToTake[1].position), busToTake[2].id)
//    var lcm3 = lcm(lcm2, busToTake[3].id + (busToTake[3].position - busToTake[2].position))
//    var lcm4 = lcm(lcm3, busToTake[4].id + (busToTake[4].position - busToTake[3].position))


    return lcm2
}

fun day13BruteForce(input: List<String>): Int {
    val busToTake = input[1]
        .split(",")
        .mapIndexed { index, s ->
            if(s == "x")
                null
            else {
                BusWithPosition(s.toBigInteger(), index.toBigInteger())
            }
        }.filterNotNull()

    val first = busToTake.first()
    var startCounter = first.id
    first.id

    return 0
}


fun lcm(first: BigInteger, second: BigInteger): BigInteger {
    var i = BigInteger.ONE
    var gcd = BigInteger.ONE
    while (i <= first && i <= second) {
        if (first.mod(i) == BigInteger.ZERO && second % i == BigInteger.ZERO)
            gcd = i
        i++
    }

    val lcm = first * second / gcd
    return lcm
}

data class BusWithPosition(val id: BigInteger, val position: BigInteger)
