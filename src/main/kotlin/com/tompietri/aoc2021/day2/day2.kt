package com.tompietri.aoc2021.day2

fun day2FirstSolution(input: List<String>): Int {
    val position = input.map { it.split(" ") }
        .map { Command(it[0], it[1].toInt()) }
        .fold(Position(0, 0)) { acc, action -> acc.move(action) }
    return position.depth * position.horizontal
}
data class Position(val depth : Int, val horizontal : Int) {
    fun move(action: Command): Position {
        val change = action.getChange()
        return this.copy(
            depth = depth + change.depth,
            horizontal = horizontal + change.horizontal
        )
    }
}

data class Command(val direction: String, val size: Int) {
    fun getChange() : Position {
        return when(direction) {
            "forward" -> Position(0, size)
            "down" -> Position(size, 0)
            "up" -> Position(-size, 0)
            else -> error("")
        }
    }
}

fun day2SecondSolution(input: List<String>): Int {
    val position = input.map { it.split(" ") }
        .map { Command2(it[0], it[1].toInt()) }
        .fold(Position2(0, 0, 0)) { acc, action -> acc.move(action) }
    return position.depth * position.horizontal
}

data class Position2(val depth : Int, val horizontal : Int, val aim: Int) {
    fun move(action: Command2): Position2 {
        val change = action.getChange()
        return this.copy(
            depth = depth + (change.depth * aim),
            horizontal = horizontal + change.horizontal,
            aim= aim + change.aim
        )
    }
}

data class Command2(val direction: String, val size: Int) {
    fun getChange() : Position2 {
        return when(direction) {
            "forward" -> Position2(size, size, 0)
            "down" -> Position2(0, 0, size)
            "up" -> Position2(0, 0, -size)
            else -> error("")
        }
    }
}