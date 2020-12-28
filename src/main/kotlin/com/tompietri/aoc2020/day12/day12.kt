package com.tompietri.aoc2020.day12

import com.tompietri.aoc2020.day12.Direction.*
import kotlin.math.absoluteValue

fun day12FirstSolution(input: List<String>): Int {
    val movements = inputToMovements(input)

    val finalShip = movements.fold(Ship(0, 0, EAST)) { acc, movement ->
        acc.move(movement)
    }

    return finalShip.x.absoluteValue + finalShip.y.absoluteValue
}

private fun inputToMovements(input: List<String>): List<Movement> {
    val pattern = Regex("([A-Z])(\\d+)")
    return input.map {
        val (_, action, value) = pattern.find(it)!!.groupValues
        Movement(action, value.toInt())
    }
}

fun day12SecondSolution(input: List<String>): Int {
    val movements = inputToMovements(input)

    val finalShip = movements.fold(Ship2(0, 0, -1, 10)) { acc, movement ->
        acc.move(movement)
    }

    return finalShip.x.absoluteValue + finalShip.y.absoluteValue
}

data class Movement(val action: String, val value: Int)

data class Ship(val x: Int, val y: Int, val direction: Direction) {
    fun move(movement: Movement): Ship {
        return when (movement.action) {
            "N" -> this.copy(x = x + NORTH.xChange * movement.value, y = y + NORTH.yChange * movement.value)
            "S" -> this.copy(x = x + SOUTH.xChange * movement.value, y = y + SOUTH.yChange * movement.value)
            "E" -> this.copy(x = x + EAST.xChange * movement.value, y = y + EAST.yChange * movement.value)
            "W" -> this.copy(x = x + WEST.xChange * movement.value, y = y + WEST.yChange * movement.value)
            "F" -> this.copy(x = x + direction.xChange * movement.value, y = y + direction.yChange * movement.value)

            "L" -> this.copy(direction = Direction.directionAfterTurning(direction, -movement.value))
            "R" -> this.copy(direction = Direction.directionAfterTurning(direction, movement.value))

            else -> error("We should only have theses actions")
        }
    }
}

enum class Direction(val xChange: Int, val yChange: Int, val index: Int) {
    NORTH(-1, 0, 0),
    EAST(0, 1, 1),
    SOUTH(1, 0, 2),
    WEST(0, -1, 3);


    companion object {
        fun directionAfterTurning(direction: Direction, nbAngleChange: Int): Direction {
            val newIndex = ((direction.index + (nbAngleChange / 90)) + 4) % 4
            return values().find { it.index == newIndex }!!
        }
    }
}

data class Ship2(val x: Int, val y: Int, val waypointX: Int, val waypointY: Int) {
    fun move(movement: Movement): Ship2 {
        return when (movement.action) {
            "N" -> this.copy(
                waypointX = waypointX + NORTH.xChange * movement.value,
                waypointY = waypointY + NORTH.yChange * movement.value
            )
            "S" -> this.copy(
                waypointX = waypointX + SOUTH.xChange * movement.value,
                waypointY = waypointY + SOUTH.yChange * movement.value
            )
            "E" -> this.copy(
                waypointX = waypointX + EAST.xChange * movement.value,
                waypointY = waypointY + EAST.yChange * movement.value
            )
            "W" -> this.copy(
                waypointX = waypointX + WEST.xChange * movement.value,
                waypointY = waypointY + WEST.yChange * movement.value
            )

            "L" -> {
                val nbTurns = movement.value / 90
                var currentValue = this.copy()
                repeat(nbTurns) {
                    currentValue = currentValue
                        .copy(
                            waypointX = -currentValue.waypointY,
                            waypointY = currentValue.waypointX
                        )
                }
                currentValue
            }
            "R" -> {
                val nbTurns = movement.value / 90
                var currentValue = this.copy()
                repeat(nbTurns) {
                    currentValue = currentValue
                        .copy(
                            waypointX = currentValue.waypointY,
                            waypointY = -currentValue.waypointX
                        )
                }
                currentValue
            }
            "F" -> this.copy(x = x + waypointX * movement.value, y = y + waypointY * movement.value)

            else -> error("We should only have theses actions")
        }
    }
}
