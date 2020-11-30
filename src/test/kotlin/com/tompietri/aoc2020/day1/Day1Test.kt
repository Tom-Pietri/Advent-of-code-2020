package com.tompietri.aoc2020.day1

import com.tompietri.aoc2020.utils.getInputFileForDay
import com.tompietri.aoc2020.utils.readInputWithSingleLine
import day1FirstSolution
import day1SecondSolution
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `First solution - My input should return ???`() {
        var input = readInputWithSingleLine(1)
        assertThat(day1FirstSolution()).isEqualTo("???")
    }

    @Test
    fun `Second solution - My input should return ???`() {
        assertThat(day1SecondSolution()).isEqualTo("???")
    }

}
