package com.tompietri.aoc2020.day6


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class day6Test {

    @Test
    fun `first solution should return ???`() {
        var input = readInputWithMultipleLines(6)
        assertThat(day6FirstSolution(input)).isEqualTo(6530)
    }

    @Test
    fun `second solution should return ???`() {
        var input = readInputWithMultipleLines(6)
        assertThat(day6SecondSolution(input)).isEqualTo(3323)
    }
}
