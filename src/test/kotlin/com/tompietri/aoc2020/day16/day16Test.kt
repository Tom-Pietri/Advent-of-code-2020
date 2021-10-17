package com.tompietri.aoc2020.day16


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class day16Test {

    @Test
    fun `first solution test data should return 71`() {
        val input = listOf(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50",
            "",
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12",)
        assertThat(day16FirstSolution(input)).isEqualTo(71)
    }


    @Test
    fun `first solution should return 13727901897109`() {
        var input = readInputWithMultipleLines(2020, 16)
        assertThat(day16FirstSolution(input)).isEqualTo(376)
    }

    @Test
    fun `second solution should return 323780`() {
        var input = readInputWithMultipleLines(2020, 16)
        assertThat(day16SecondSolution(input)).isEqualTo(BigInteger("650080463519"))
    }

}
