package com.tompietri.aoc2020.day2

import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `first solution should return ???`() {
        var input = readInputWithMultipleLines(2)
        Assertions.assertThat(day2FirstSolution(input)).isEqualTo(493)
    }

    @Test
    fun `second solution should return ???`() {
        var input = readInputWithMultipleLines(2)
        Assertions.assertThat(day2SecondSolution(input)).isEqualTo(593)
    }
}
