package com.tompietri.aoc2020.day21

import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day21Test {

    private val testData = listOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)"
    )

    @Test
    fun `first solution test data should return 5`() {
        assertThat(day21FirstSolution(testData)).isEqualTo(5)
    }

    @Test
    fun `first solution should return 2307`() {
        val input = readInputWithMultipleLines(21)
        assertThat(day21FirstSolution(input)).isEqualTo(2307)
    }

    @Test
    fun `second solution test data should return 'mxmxvkd,sqjhc,fvjkl'`() {
        assertThat(day21SecondSolution(testData)).isEqualTo("mxmxvkd,sqjhc,fvjkl")
    }

    @Test
    fun `second solution should return 2472`() {
        val input = readInputWithMultipleLines(21)
        assertThat(day21SecondSolution(input)).isEqualTo("cljf,frtfg,vvfjj,qmrps,hvnkk,qnvx,cpxmpc,qsjszn")
    }

}
