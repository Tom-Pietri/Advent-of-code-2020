package com.tompietri.aoc2015.day3

import com.tompietri.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `first solution should return 1586300`() {
        val input = readInputWithSingleLine(2015, 3)
        Assertions.assertThat(day3FirstSolution(input)).isEqualTo(2081)
    }

    @Test
    fun `second solution should return 3737498`() {
        val input = readInputWithSingleLine(2015, 3)
        Assertions.assertThat(day3SecondSolution(input)).isEqualTo(2341)
    }
}
