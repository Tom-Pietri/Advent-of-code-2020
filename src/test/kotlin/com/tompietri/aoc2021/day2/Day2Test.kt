package com.tompietri.aoc2021.day2

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Test {
    @Test
    fun `First solution - My input should return 1813801`() {
        val input = readInputWithMultipleLines(2021, 2)
        assertThat(day2FirstSolution(input)).isEqualTo(1813801)
    }

    @Test
    fun `Second solution - My input should return 1960569556`() {
        val input = readInputWithMultipleLines(2021, 2)
        assertThat(day2SecondSolution(input)).isEqualTo(1960569556)
    }

}
