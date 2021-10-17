package com.tompietri.aoc2020.day14


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class day14Test {

    @Test
    fun `first solution test data return 165`() {
        var input = listOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
        )
        assertThat(day14FirstSolution(input)).isEqualTo(BigInteger("165"))
    }

    @Test
    fun `first solution should return 13727901897109`() {
        var input = readInputWithMultipleLines(2020, 14)
        assertThat(day14FirstSolution(input)).isEqualTo(BigInteger("13727901897109"))
    }

    @Test
    fun `second solution test data return 208`() {
        var input = listOf(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
        )
        assertThat(day14SecondSolution(input)).isEqualTo(BigInteger("208"))
    }


    @Test
    fun `second solution should return 16314`() {
        var input = readInputWithMultipleLines(2020, 14)
        assertThat(day14SecondSolution(input)).isEqualTo(BigInteger("5579916171823"))
    }

}
