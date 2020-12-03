package com.tompietri.aoc2020.day3


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class day3Test {
    @Test
    fun `first solution should return ???`() {
        var input = readInputWithMultipleLines(3)
        Assertions.assertThat(day3FirstSolution(input)).isEqualTo(207)
    }

    @Test
    fun `second solution should return ???`() {
        var input = readInputWithMultipleLines(3)
        Assertions.assertThat(day3SecondSolution(input)).isEqualTo(BigInteger("2655892800"))
    }
}
