package com.tompietri.aoc2020.day23

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day23Test {

    val testData = "389125467"

    @Test
    fun `first solution test data 10 rounds should return 92658374`() {
        assertThat(day23FirstSolution(testData, 10)).isEqualTo("92658374")
    }

    @Test
    fun `first solution test data 100 rounds should return 67384529`() {
        assertThat(day23FirstSolution(testData, 100)).isEqualTo("67384529")
    }

    @Test
    fun `first solution should return 92658374`() {
        val input = "394618527"
        assertThat(day23FirstSolution(input, 100)).isEqualTo("92658374")
    }

    @Test
    fun `second solution test data should return 149245887792`() {
        val input = "54321"
        assertThat(day23SecondSolution(testData, 10000000)).isEqualTo(BigInteger("149245887792"))
    }

    @Test
    fun `second solution should return 2472`() {
        val input = "394618527"
        assertThat(day23SecondSolution(input, 10000000)).isEqualTo(BigInteger("565615814504"))
    }

}
