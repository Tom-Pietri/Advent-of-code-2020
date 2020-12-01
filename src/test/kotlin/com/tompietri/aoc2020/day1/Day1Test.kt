package com.tompietri.aoc2020.day1

import com.tompietri.aoc2020.utils.readMultipleNumberInput
import day1FirstSolution
import day1SecondSolution
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `First solution - My input should return ???`() {
        var input = readMultipleNumberInput(1)
        assertThat(day1FirstSolution(input)).isEqualTo(181044)
    }

    @Test
    fun `Second solution - My input should return ???`() {
        var input = readMultipleNumberInput(1)
        assertThat(day1SecondSolution(input)).isEqualTo(82660352)
    }

}
