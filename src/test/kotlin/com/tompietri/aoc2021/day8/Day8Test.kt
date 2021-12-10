package com.tompietri.aoc2021.day8

import com.tompietri.utils.readInputWithMultipleLines
import com.tompietri.utils.readMultipleNumberInputSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Test {
    @Test
    fun `First solution - My input should return 357353`() {
        val input = readInputWithMultipleLines(2021, 8)
        assertThat(day8FirstSolution(input)).isEqualTo(452)
    }

    @Test
    fun `Second solution - My input should return 104822130`() {
        val input = readInputWithMultipleLines(2021, 8)
        assertThat(day8SecondSolution(input)).isEqualTo(1096964)
    }

}