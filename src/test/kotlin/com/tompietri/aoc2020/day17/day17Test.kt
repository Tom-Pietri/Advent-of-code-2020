package com.tompietri.aoc2020.day17


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import com.tompietri.aoc2020.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class day17Test {

    @Test
    fun `first solution tes data should return 112`() {
        val input = listOf(
            ".#.",
            "..#",
            "###"
        )
        assertThat(day17FirstSolution(input)).isEqualTo(112)
    }

    @Test
    fun `first solution should return 353`() {
        val input = readInputWithMultipleLines(17)
        assertThat(day17FirstSolution(input)).isEqualTo(353)
    }

    @Test
    fun `second solution should return 2472`() {
        val input = readInputWithMultipleLines(17)
        assertThat(day17SecondSolution(input)).isEqualTo(2472)
    }

}
