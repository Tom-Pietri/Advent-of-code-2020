package com.tompietri.aoc2020.day12


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import com.tompietri.aoc2020.utils.readMultipleNumberInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day12Test {
    @Test
    fun `Ship turning from EAST`() {
        val turningAndExpected = listOf(
            Pair(Movement("R", 90), Direction.SOUTH),
            Pair(Movement("R", 180), Direction.WEST),
            Pair(Movement("R", 270), Direction.NORTH),
            Pair(Movement("L", 90), Direction.NORTH),
            Pair(Movement("L", 180), Direction.WEST),
            Pair(Movement("L", 270), Direction.SOUTH),
        )
        val originalShip = Ship(x = 0, y = 0, direction = Direction.EAST)
        turningAndExpected.forEach {
            assertThat(originalShip.move(it.first).direction).isEqualTo(it.second)
        }
    }

    @Test
    fun `Ship turning from WEST`() {
        val turningAndExpected = listOf(
            Pair(Movement("R", 90), Direction.NORTH),
            Pair(Movement("R", 180), Direction.EAST),
            Pair(Movement("R", 270), Direction.SOUTH),
            Pair(Movement("L", 90), Direction.SOUTH),
            Pair(Movement("L", 180), Direction.EAST),
            Pair(Movement("L", 270), Direction.NORTH),
        )
        val originalShip = Ship(x = 0, y = 0, direction = Direction.WEST)
        turningAndExpected.forEach {
            assertThat(originalShip.move(it.first).direction).isEqualTo(it.second)
        }
    }

    @Test
    fun `first solution data set should return 1766`() {
        assertThat(day12FirstSolution(testInput)).isEqualTo(25)
    }

    @Test
    fun `first solution should return 1766`() {
        val input = readInputWithMultipleLines(12)
        assertThat(day12FirstSolution(input)).isEqualTo(845)
    }

    @Test
    fun `second solution data set return 286`() {
        assertThat(day12SecondSolution(testInput)).isEqualTo(286)
    }

    @Test
    fun `second solution should return 27016`() {
        val input = readInputWithMultipleLines(12)
        assertThat(day12SecondSolution(input)).isEqualTo(27016)
    }

    companion object {
        val testInput = listOf(
            "F10",
            "N3",
            "F7",
            "R90",
            "F11"
        )
    }

}
