package com.tompietri.aoc2020.day7


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class day7Test {

    @Test
    fun `first solution should return 142`() {
        var input = readInputWithMultipleLines(7)
        assertThat(day7FirstSolution(input)).isEqualTo(142)
    }

    @Test
    fun `second solution should return 10219`() {
        var input = readInputWithMultipleLines(7)
        assertThat(day7SecondSolution(input)).isEqualTo(10219)
    }


    @Test
    fun `second solution first data set should be 32`() {
        val input = listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
        )
        assertThat(day7SecondSolution(input)).isEqualTo(32)
    }

    @Test
    fun `second solution second data set 126`() {
        val input = listOf(
            "shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags."
        )
        assertThat(day7SecondSolution(input)).isEqualTo(126)
    }
}
