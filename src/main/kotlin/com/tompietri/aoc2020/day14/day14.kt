package com.tompietri.aoc2020.day14

import java.math.BigInteger
import kotlin.math.pow

fun day14FirstSolution(input: List<String>): BigInteger {
    val instructions = inputToInstructions(input)

    var currentMask = ""
    val memory = mutableMapOf<Int, Long>()

    instructions.forEach {
        if(it.type == InstructionType.MASK) {
            currentMask = it.value
        } else {
            var numberAsBits = it.value.toUInt().toString(radix = 2).padStart(36, '0')

            currentMask.forEachIndexed { i, maskChar ->
                when(maskChar) {
                    '0' -> numberAsBits = numberAsBits.changeCharAt(i, '0')
                    '1' -> numberAsBits = numberAsBits.changeCharAt(i, '1')
                }
            }

            numberAsBits = numberAsBits.reversed()

            memory[it.addr] = bitsToLong(numberAsBits)
        }
    }

    var result = BigInteger("0")

    memory.values.forEach { result += it.toBigInteger() }

    return result
}

fun day14SecondSolution(input: List<String>): BigInteger {
    val instructions = inputToInstructions(input)

    var currentMask = ""
    val memory = mutableMapOf<Long, Int>()

    instructions.forEach {
        if(it.type == InstructionType.MASK) {
            currentMask = it.value
        } else {
            var addressAsBits = it.addr.toUInt().toString(radix = 2).padStart(36, '0')
            currentMask.forEachIndexed { i, maskChar ->
                if(maskChar == '1' || maskChar == 'X') {
                    addressAsBits = addressAsBits.changeCharAt(i, maskChar)
                }
            }
            addressAsBits = addressAsBits.reversed()

            val floatinBitsIndex = mutableListOf<Int>()
            addressAsBits.forEachIndexed { index, c -> if(c == 'X') floatinBitsIndex.add(index) }

            val addressesToSet = allValuesWithFloatingBits(floatinBitsIndex, addressAsBits)

            val numberToSet = it.value.toInt()

            addressesToSet.forEach { addr ->
                memory[addr] = numberToSet
            }
        }
    }

    var result = BigInteger("0")

    memory.values.forEach { result += it.toBigInteger() }

    return result
}

private fun allValuesWithFloatingBits(floatinBitsIndex: List<Int>, addressAsBits: String): List<Long> {
    return if (floatinBitsIndex.isNotEmpty()) {
        val firstIndex = floatinBitsIndex[0]
        val withZero = addressAsBits.changeCharAt(firstIndex, '0')
        val withOne = addressAsBits.changeCharAt(firstIndex, '1')
        allValuesWithFloatingBits(floatinBitsIndex.drop(1), withZero) + allValuesWithFloatingBits(floatinBitsIndex.drop(1), withOne)
    } else {
        listOf(bitsToLong(addressAsBits))
    }
}

private fun String.changeCharAt(i: Int, maskChar: Char) =
    this.substring(0, i) + maskChar + this.substring(i + 1)

fun inputToInstructions(input: List<String>) : List<Instruction> {
    val regex = Regex("(.*) = (.*)")
    val memoryRegex = Regex(".*\\[(.*)\\].*")
    return input.map {
        val (_, instruction, value)  = regex.find(it)!!.groupValues
        if(instruction.substring(0, 3) == "mem") {
            val (_, addr) = memoryRegex.find(instruction)!!.groupValues
            Instruction(type = InstructionType.MEM, value = value, addr = addr.toInt())
        } else {
            Instruction(type = InstructionType.MASK, value = value, addr = -1)
        }
    }
}

private fun bitsToLong(numberAsBits: String) = numberAsBits.foldIndexed(0L) { index, acc, bit ->
    if (bit == '0') {
        acc
    } else {
        acc + ((2.0).pow(index).toLong())
    }
}

data class Instruction(val type: InstructionType, val value: String, val addr: Int)

enum class InstructionType {
    MASK,
    MEM
}
