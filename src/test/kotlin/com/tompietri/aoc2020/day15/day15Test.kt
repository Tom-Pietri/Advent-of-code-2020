package com.tompietri.aoc2020.day15


import com.tompietri.aoc2020.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class day15Test {

    @Test
    fun `first solution test data should return 436`() {
        var input = "0,3,6"
        assertThat(day15FirstSolution(input)).isEqualTo(436)
    }


    @Test
    fun `first solution should return 13727901897109`() {
        var input = readInputWithSingleLine(15)
        assertThat(day15FirstSolution(input)).isEqualTo(376)
    }

    @Test
    fun `second solution test data should return 175594`() {
        var input = "0,3,6"
        assertThat(day15SecondSolution(input)).isEqualTo(175594)
    }

    @Test
    fun `second solution should return 323780`() {
        var input = readInputWithSingleLine(15)
        assertThat(day15SecondSolution(input)).isEqualTo(323780)
    }

}
