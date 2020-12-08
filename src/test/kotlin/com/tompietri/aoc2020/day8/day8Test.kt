package com.tompietri.aoc2020.day8


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class day8Test {

    @Test
    fun `first solution should return 1766`() {
        var input = readInputWithMultipleLines(8)
        assertThat(day8FirstSolution(input)).isEqualTo(1766)
    }

    @Test
    fun `second solution should return 1639`() {
        var input = readInputWithMultipleLines(8)
        assertThat(day8SecondSolution(input)).isEqualTo(1639)
    }

}
