package com.tompietri.aoc2021.day3

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.pow

class Day3Test {
    @Test
    fun `First solution - My input should return 1813801`() {
        val input = readInputWithMultipleLines(2021, 3)
        assertThat(day3FirstSolution(input)).isEqualTo(2640986)
    }

    @Test
    fun `Second solution - My input should return 1960569556`() {
        val input = readInputWithMultipleLines(2021, 3)
        assertThat(day3SecondSolution(input)).isEqualTo(6822109)
    }

}