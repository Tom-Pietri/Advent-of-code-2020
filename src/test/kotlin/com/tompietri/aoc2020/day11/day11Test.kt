package com.tompietri.aoc2020.day11


import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class day11Test {
    @Test
    fun `first solution should return 1766`() {
        var input = readInputWithMultipleLines(2020, 11)
        assertThat(day11FirstSolution(input)).isEqualTo(2251)
    }

    @Test
    fun `second solution should return 16311`() {
        var input = readInputWithMultipleLines(2020, 11)
        assertThat(day11SecondSolution(input)).isEqualTo(2019)
    }

}
