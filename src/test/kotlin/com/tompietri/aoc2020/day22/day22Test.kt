package com.tompietri.aoc2020.day22

import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun `first solution should return 35818`() {
        val input = readInputWithMultipleLines(2020, 22)
        assertThat(day22FirstSolution(input)).isEqualTo(35818)
    }

    @Test
    fun `second solution loop data should return ???`() {

        val input = listOf(
            "Player 1:",
            "43",
            "19",
            "",
            "Player 2:",
            "2",
            "29",
            "14",
        )
        assertThat(day22SecondSolution(input)).isEqualTo(105)
    }

    @Test
    fun `second solution test data should return 291`() {

        val input = listOf(
            "Player 1:",
            "9",
            "2",
            "6",
            "3",
            "1",
            "",
            "Player 2:",
            "5",
            "8",
            "4",
            "7",
            "10",
        )
        assertThat(day22SecondSolution(input)).isEqualTo(291)
    }

    @Test
    fun `second solution should return 34771`() {
        val input = readInputWithMultipleLines(2020, 22)
        assertThat(day22SecondSolution(input)).isEqualTo(34771)
    }
}
