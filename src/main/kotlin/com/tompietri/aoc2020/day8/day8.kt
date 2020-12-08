package com.tompietri.aoc2020.day8

fun day8FirstSolution(input: List<String>): Int {
    val operations = inputToOperations(input);
    return runOperations(operations).acc
}

fun day8SecondSolution(input: List<String>): Int {
    val operations = inputToOperations(input).toMutableList()

    for (i in 0..operations.count()) {
        val currentOperation = operations[i]
        if(currentOperation.code == "nop") {
            operations[i] = currentOperation.copy(code = "jmp")
        } else if(currentOperation.code == "jmp") {
            operations[i] = currentOperation.copy(code = "nop")
        } else {
            continue
        }

        val result = runOperations(operations)
        if(result.resultCode == ResultCode.FINISHED) {
            return result.acc
        }

        operations[i] = currentOperation
    }

    return -1
}

fun runOperations(operations: List<Operation>): OperationsResult {
    var pos = 0
    var acc = 0
    val visitedPostions = mutableSetOf<Int>()
    while (!visitedPostions.contains(pos)) {
        visitedPostions.add(pos)
        when(operations[pos].code) {
            "nop" -> pos++
            "acc" -> {
                acc += operations[pos].value
                pos++
            }
            "jmp" -> pos += operations[pos].value
        }

        if(pos >= operations.count()) {
            return OperationsResult(acc, ResultCode.FINISHED)
        }
    }

    return OperationsResult(acc, ResultCode.LOOPED)
}


fun inputToOperations(input: List<String>): List<Operation> {
    val regex = Regex("(\\D{3}) (.)(\\d*)")
    return input.map {
        val (_, code, symbol, value) = regex.matchEntire(it)!!.groupValues
        Operation(code, if(symbol == "+") value.toInt() else -value.toInt())
    }
}

data class Operation(val code: String, val value: Int)

data class OperationsResult(val acc: Int, val resultCode: ResultCode)

enum class ResultCode {
    FINISHED,
    LOOPED
}
