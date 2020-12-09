package com.tompietri.aoc2020.day9


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class day9Test {

    @Test
    fun `first solution with test data should return 127`() {

        var input = listOf(
            "35",
            "20",
            "15",
            "25",
            "47",
            "40",
            "62",
            "55",
            "65",
            "95",
            "102",
            "117",
            "150",
            "182",
            "127",
            "219",
            "299",
            "277",
            "309",
            "576"
        )
        assertThat(day9FirstSolution(input, 5)).isEqualTo(BigInteger("127"))
    }

    @Test
    fun `first solution should return 1766`() {
        var input = readInputWithMultipleLines(9)
        assertThat(day9FirstSolution(input)).isEqualTo(BigInteger("15353384"))
    }

    @Test
    fun `second solution should return 1639`() {
        var input = readInputWithMultipleLines(9)
        assertThat(day9SecondSolution(input)).isEqualTo(BigInteger("2466556"))
    }

}
