package com.tompietri.aoc2020.day13


import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class day13Test {

    @Test
    fun `first solution test data return 295`() {
        var input = listOf(
            "939",
            "7,13,x,x,59,x,31,19"
        )
        assertThat(day13FirstSolution(input)).isEqualTo(295)
    }


    @Test
    fun `first solution should return 1766`() {
        var input = readInputWithMultipleLines(2020, 13)
        assertThat(day13FirstSolution(input)).isEqualTo(8413)
    }

    @Test
    fun `second solution test data should return 9`() {
        var input = listOf(
            "939",
            "17,x,13,19"
        )
        assertThat(day13SecondSolution(input)).isEqualTo(BigInteger("3417"))
    }    @Test

    fun `second solution test data should return 1068781`() {
        var input = listOf(
            "939",
            "17,x,13,19"
        )
        assertThat(day13SecondSolution(input)).isEqualTo(BigInteger("3417"))
    }

    @Test
    fun `second solution should return 16312`() {
        var input = readInputWithMultipleLines(2020, 13)
        assertThat(day13SecondSolution(input)).isEqualTo(20113)
    }

}
