package com.tompietri.aoc2020.day25

import com.tompietri.utils.readMultipleNumberInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day25Test {

    @Test
    fun `first solution test data should return 14897079`() {
        val input = listOf(5764801, 17807724)
        assertThat(day25FirstSolution(input)).isEqualTo(14897079)
    }

    @Test
    fun `first solution should return 92658374`() {
        val input = readMultipleNumberInput(2020, 25)
        assertThat(day25FirstSolution(input)).isEqualTo(11707042)
    }

    @Test
    fun `second solution should return 2472`() {
        val input = readMultipleNumberInput(2020, 25)
        assertThat(day25SecondSolution(input)).isEqualTo(BigInteger("565615814504"))
    }

}
