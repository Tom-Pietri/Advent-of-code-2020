package com.tompietri.aoc2021.day10

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun `First solution - My input should return 357353`() {
        val input = readInputWithMultipleLines(2021, 10)
        assertThat(day10FirstSolution(input)).isEqualTo(268845)
    }

    @Test
    fun `Second solution - My input should return 104822130`() {
        val input = readInputWithMultipleLines(2021, 10)
        assertThat(day10SecondSolution(input)).isEqualTo(4038824534L)
    }

}