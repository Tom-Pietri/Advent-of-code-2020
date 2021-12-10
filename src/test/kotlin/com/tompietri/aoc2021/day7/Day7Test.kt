package com.tompietri.aoc2021.day7

import com.tompietri.utils.readMultipleNumberInputSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Test {
    @Test
    fun `First solution - My input should return 357353`() {
        val input = readMultipleNumberInputSingleLine(2021, 7)
        assertThat(day7FirstSolution(input)).isEqualTo(357353)
    }

    @Test
    fun `Second solution - My input should return 104822130`() {
        val input = readMultipleNumberInputSingleLine(2021, 7)
        assertThat(day7SecondSolution(input)).isEqualTo(104822130)
    }

}