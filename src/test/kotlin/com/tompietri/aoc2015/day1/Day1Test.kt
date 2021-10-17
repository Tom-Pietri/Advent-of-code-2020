package com.tompietri.aoc2015.day1

import com.tompietri.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `First solution - My input should return 74`() {
        val input = readInputWithSingleLine(2020, 1)
        assertThat(day1FirstSolution(input)).isEqualTo(74)
    }

    @Test
    fun `Second solution - My input should return ???`() {
        val input = readInputWithSingleLine(2020, 1)
        assertThat(day1SecondSolution(input)).isEqualTo(1795)
    }

}
