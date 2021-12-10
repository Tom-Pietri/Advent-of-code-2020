package com.tompietri.aoc2021.day5

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.pow

class Day5Test {
    @Test
    fun `First solution - My input should return 6687`() {
        val input = readInputWithMultipleLines(2021, 5)
        assertThat(day5FirstSolution(input)).isEqualTo(6687)
    }

    @Test
    fun `Second solution - My input should return 19851`() {
        val input = readInputWithMultipleLines(2021, 5)
        assertThat(day5SecondSolution(input)).isEqualTo(19851)
    }

}