package com.tompietri.aoc2021.day9

import com.tompietri.utils.readInputWithMultipleLines
import com.tompietri.utils.readMultipleNumberInputSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun `First solution - My input should return 357353`() {
        val input = readInputWithMultipleLines(2021, 9)
        assertThat(day9FirstSolution(input)).isEqualTo(425)
    }

    @Test
    fun `Second solution - My input should return 104822130`() {
        val input = readInputWithMultipleLines(2021, 9)
        assertThat(day9SecondSolution(input)).isEqualTo(1135260)
    }

}