package com.tompietri.aoc2020.day10


import com.tompietri.utils.readMultipleNumberInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class day10Test {
    @Test
    fun `first solution should return 1766`() {
        var input = readMultipleNumberInput(2020, 10)
        assertThat(day10FirstSolution(input)).isEqualTo(2484)
    }

    @Test
    fun `second solution should return 16310`() {
        var input = readMultipleNumberInput(2020, 10)
        assertThat(day10SecondSolution(input)).isEqualTo(BigInteger("15790581481472"))
    }


    @Test
    fun `second solution with firsts dataset should return 8`() {
        val input = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
        assertThat(day10SecondSolution(input)).isEqualTo((8).toBigInteger())
    }
    @Test
    fun `second solution with second dataset should return 19208`() {
        val input = listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)
        assertThat(day10SecondSolution(input)).isEqualTo((19208).toBigInteger())
    }

}
