package com.tompietri.aoc2021.day4

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.pow

class Day4Test {
    @Test
    fun `First solution - My input should return 1813801`() {
        val input = readInputWithMultipleLines(2021, 4)
        assertThat(day4FirstSolution(input)).isEqualTo(10374)
    }

    @Test
    fun `Second solution - My input should return 1960569556`() {
        val input = readInputWithMultipleLines(2021, 4)
        assertThat(day4SecondSolution(input)).isEqualTo(24742)
    }

}