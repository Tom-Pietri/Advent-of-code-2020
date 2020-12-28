package com.tompietri.aoc2020.day18


import com.tompietri.aoc2020.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigInteger

class Day18Test {

    @ParameterizedTest
    @MethodSource("firstPartData")
    fun `First solution test data`(expression: String, expected: BigInteger) {
        val input = listOf(expression)
        assertThat(day18FirstSolution(input)).isEqualTo(expected)
    }

    @Test
    fun `first solution should return 2743012121210`() {
        val input = readInputWithMultipleLines(18)
        assertThat(day18FirstSolution(input)).isEqualTo(BigInteger("2743012121210"))
    }

    @ParameterizedTest
    @MethodSource("secondPartData")
    fun `second solution test data`(expression: String, expected: BigInteger) {
        val input = listOf(expression)
        assertThat(day18SecondSolution(input)).isEqualTo(expected)
    }


    @Test
    fun `second solution should return 65658760783597`() {
        val input = readInputWithMultipleLines(18)
        assertThat(day18SecondSolution(input)).isEqualTo(BigInteger("65658760783597"))
    }

    companion object {
        @JvmStatic
        fun firstPartData() = listOf(
            Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", BigInteger("51")),
            Arguments.of("2 * 3 + (4 * 5)", BigInteger("26")),
            Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", BigInteger("437")),
            Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", BigInteger("12240")),
            Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", BigInteger("13632")),
        )


        @JvmStatic
        fun secondPartData() = listOf(
            Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", BigInteger("51")),
            Arguments.of("2 * 3 + (4 * 5)", BigInteger("46")),
            Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", BigInteger("1445")),
            Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", BigInteger("669060")),
            Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", BigInteger("23340")),
        )
    }
}
