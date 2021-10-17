package com.tompietri.aoc2020.day8


import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class day8Test {

    @Test
    fun `first solution should return 1766`() {
        var input = readInputWithMultipleLines(2020, 8)
        assertThat(day8FirstSolution(input)).isEqualTo(1766)
    }

    @Test
    fun `second solution should return 1639`() {
        var input = readInputWithMultipleLines(2020, 8)
        assertThat(day8SecondSolution(input)).isEqualTo(1639)
    }

}
