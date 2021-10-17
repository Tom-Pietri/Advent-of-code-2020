package com.tompietri.aoc2015.day4

import com.tompietri.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun `first solution should return 1586300`() {
        val input = readInputWithSingleLine(2015, 4)
        Assertions.assertThat(day4FirstSolution(input)).isEqualTo(282749)
    }

    @Test
    fun `second solution should return 3737498`() {
        val input = readInputWithSingleLine(2015, 4)
        Assertions.assertThat(day4SecondSolution(input)).isEqualTo(9962624)
    }
}
