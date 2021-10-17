package com.tompietri.aoc2020.day4


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class day4Test {
    @Test
    fun `first solution should return ???`() {
        var input = readInputWithMultipleLines(2020, 4)
        Assertions.assertThat(day4FirstSolution(input)).isEqualTo(204)
    }

    @Test
    fun `second solution should return ???`() {
    var input = readInputWithMultipleLines(2020, 4)
        Assertions.assertThat(day4SecondSolution(input)).isEqualTo(179)
    }
}
