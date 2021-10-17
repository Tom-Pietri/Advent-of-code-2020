package com.tompietri.aoc2015.day2

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `first solution should return 1586300`() {
        val input = readInputWithMultipleLines(2015, 2)
        Assertions.assertThat(day2FirstSolution(input)).isEqualTo(1586300)
    }

    @Test
    fun `second solution should return 3737498`() {
        val input = readInputWithMultipleLines(2015, 2)
        Assertions.assertThat(day2SecondSolution(input)).isEqualTo(3737498)
    }
}
