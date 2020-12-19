package com.tompietri.aoc2020.day19


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun `First solution test data should return 2`() {
        val input = listOf(
            "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb"
        )
        assertThat(day19FirstSolution(input)).isEqualTo(2)
    }

    @Test
    fun `first solution should return 353`() {
        val input = readInputWithMultipleLines(19)
        assertThat(day19FirstSolution(input)).isEqualTo(208)
    }

    @Test
    fun `second solution should return 2472`() {
        val input = readInputWithMultipleLines(19)
        assertThat(day19SecondSolution(input)).isEqualTo(316)
    }

}
