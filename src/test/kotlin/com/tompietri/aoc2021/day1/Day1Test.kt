package com.tompietri.aoc2021.day1

import com.tompietri.utils.readMultipleNumberInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `First solution - My input should return 1215`() {
        val input = readMultipleNumberInput(2021, 1)
        assertThat(day1FirstSolution(input)).isEqualTo(1215)
    }

    @Test
    fun `Second solution - My input should return 1150`() {
        val input = readMultipleNumberInput(2021, 1)
        assertThat(day1SecondSolution(input)).isEqualTo(1150)
    }

}
