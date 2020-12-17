package com.tompietri.aoc2020.day16


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import com.tompietri.aoc2020.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        var input = readInputWithMultipleLines(16)
        assertThat(day16FirstSolution(input)).isEqualTo(376)
    }

    @Test
    fun `second solution test data should return `() {
        var input = listOf(
            "class: 0-1 or 4-19",
            "row: 0-5 or 8-19",
            "seat: 0-13 or 16-19",
            "",
            "your ticket:",
            "11,12,13",
            "",
            "nearby tickets:",
            "3,9,18",
            "15,1,5",
            "5,14,9",
        )
        assertThat(day16SecondSolution(input)).isEqualTo(323780)
    }

    @Test
    fun `second solution should return 323780`() {
        var input = readInputWithMultipleLines(16)
        assertThat(day16SecondSolution(input)).isEqualTo(323780)
    }

}
