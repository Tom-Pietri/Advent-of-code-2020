package com.tompietri.aoc2020.day18

import java.lang.Integer.min
import java.math.BigInteger
import kotlin.math.exp

fun day18FirstSolution(input: List<String>): BigInteger {
    return input.map { it.replace(" ", "") }.map { computeExpression(it) }.reduce { acc, bigInteger -> acc + bigInteger }
}

fun day18SecondSolution(input: List<String>): BigInteger {
    return input.map { it.replace(" ", "") }
        .map { computeExpression2(it) }
        .map { BigInteger(it) }
        .reduce { acc, bigInteger -> acc + bigInteger }
}

fun computeExpression(expression: String): BigInteger {
    val groups = groupByFirstParenthese(expression)

    var total : BigInteger? = null
    var lastSign : Char? = null
    for (i in 0 until groups.size) {
        var currentGroup = groups[i]
        val nextSign = currentGroup.last()
        if (nextSign == '*' || nextSign == '+') {
            currentGroup = currentGroup.dropLast(1)
        }

        if (currentGroup.indexOf("(") == -1) {
            if(currentGroup.toIntOrNull() != null) {
                total = when (lastSign) {
                    null -> currentGroup.toBigInteger()
                    '+' -> (total ?: BigInteger.ZERO) + currentGroup.toBigInteger()
                    else -> (total ?: BigInteger.ONE) * currentGroup.toBigInteger()
                }
            } else {
                val regex = Regex("^(\\d*)([+*])(.*)")
                var (_, firstExpression, symbol, secondExpression) = regex.find(currentGroup)!!.groupValues
                total = when (lastSign) {
                    null -> firstExpression.toBigInteger()
                    '+' -> (total ?: BigInteger.ZERO) + firstExpression.toBigInteger()
                    else -> (total ?: BigInteger.ONE) * firstExpression.toBigInteger()
                }
                var shouldContinue = true
                while (shouldContinue) {
                    val groupValues = regex.find(secondExpression)?.groupValues
                    if (groupValues != null) {
                        if (symbol == "*") {
                            total = (total ?: BigInteger.ONE) * groupValues[1].toBigInteger()
                        } else {
                            total = (total ?: BigInteger.ZERO) + groupValues[1].toBigInteger()
                        }
                        symbol = groupValues[2]
                        secondExpression = groupValues[3]
                    } else {
                        shouldContinue = false
                        if (symbol == "*") {
                            total = (total ?: BigInteger.ONE) * secondExpression.toBigInteger()
                        } else {
                            total = (total ?: BigInteger.ZERO) + secondExpression.toBigInteger()
                        }
                    }
                }
            }
        } else {

            val currentGroupValue = computeExpression(currentGroup.drop(1).dropLast(1))

            total = when (lastSign) {
                null -> currentGroupValue
                '+' -> (total ?: BigInteger.ZERO) + currentGroupValue
                else -> (total ?: BigInteger.ONE) * currentGroupValue
            }
        }

        lastSign = nextSign
    }

    return total!!
}

fun computeExpression2(expression: String): String {
    val groups = groupByFirstParenthese(expression)

    val noParenthesesExpression = groups.map {
        var currentGroup = it
        var sign : String? = null
        if (currentGroup.last() == '+' || currentGroup.last() == '*') {
            sign = currentGroup.last().toString()
            currentGroup = currentGroup.dropLast(1)
        }

        if (currentGroup.indexOf("(") == -1) {
            it
        } else {
            computeExpression2(currentGroup.drop(1).dropLast(1)) + (sign ?: "")
        }
    }.joinToString("")

    return computeNoParenthesesExpression(noParenthesesExpression)
}

private fun computeNoParenthesesExpression(expression: String): String {
    return expression.split("*")
        .map { onlySums ->
            onlySums.split("+")
                .map { it.toBigInteger() }
                .reduce { sum, nextNumber -> sum + nextNumber }
        }.reduce { acc, nextNumber -> acc * nextNumber }.toString()
}

private fun groupByFirstParenthese(currentExpression: String) : MutableList<String> {
    val groups = mutableListOf<String>()
    var currentExpression1 = currentExpression
    while (currentExpression1.isNotEmpty()) {
        val indexOfFirstOpenParenthesis = currentExpression1.indexOf("(")
        if (indexOfFirstOpenParenthesis == -1) {
            groups.add(currentExpression1)
            currentExpression1 = ""
        } else {
            if (indexOfFirstOpenParenthesis > 0) {
                groups.add(currentExpression1.substring(0, indexOfFirstOpenParenthesis))
                currentExpression1 = currentExpression1.substring(indexOfFirstOpenParenthesis)
            }

            var openCount = 1
            var closeCount = 0
            var index = 1
            while (openCount > closeCount) {
                if (currentExpression1[index] == '(') {
                    openCount++
                } else if (currentExpression1[index] == ')') {
                    closeCount++
                }
                index++
            }

            groups.add(currentExpression1.substring(0, min(index + 1, currentExpression1.length)))
            currentExpression1 = currentExpression1.substring(min(index + 1, currentExpression1.length))
        }
    }

    return groups
}

