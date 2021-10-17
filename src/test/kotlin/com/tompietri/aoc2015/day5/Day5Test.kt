package com.tompietri.aoc2015.day5

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `first solution should return 255`() {
        val input = readInputWithMultipleLines(2015, 5)
        Assertions.assertThat(day5FirstSolution(input)).isEqualTo(255)
    }

    @Test
    fun `second solution should return 55`() {
        val input = readInputWithMultipleLines(2015, 5)
        Assertions.assertThat(day5SecondSolution(input)).isEqualTo(55)
    }
}
