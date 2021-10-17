package com.tompietri.aoc2020.day5


import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class day5Test {

    @ParameterizedTest
    @MethodSource("rowTestData")
    fun `Get row should match the expected row`(boardingPass: String, expectedRow: Int) {
        assertThat(getRow(boardingPass)).isEqualTo(expectedRow)
    }


    @ParameterizedTest
    @MethodSource("collumnTestData")
    fun `Get collumn should match the expected row`(boardingPass: String, expectedCollumn: Int) {
        assertThat(getColumn(boardingPass)).isEqualTo(expectedCollumn)
    }

    @Test
    fun `first solution should return ???`() {
        var input = readInputWithMultipleLines(2020, 5)
        assertThat(day5FirstSolution(input)).isEqualTo(989)
    }

    @Test
    fun `second solution should return ???`() {
    var input = readInputWithMultipleLines(2020, 5)
        assertThat(day5SecondSolution(input)).isEqualTo(548)
    }

    companion object {
        @JvmStatic
        fun rowTestData() = listOf(
            Arguments.of("FBFBBFFRLR", 44),
            Arguments.of("BFFFBBFRRR", 70),
            Arguments.of("FFFBBBFRRR", 14),
            Arguments.of("BBFFBBFRLL", 102),
        )

        @JvmStatic
        fun collumnTestData() = listOf(
            Arguments.of("FBFBBFFRLR", 5),
            Arguments.of("BFFFBBFRRR", 7),
            Arguments.of("FFFBBBFRRR", 7),
            Arguments.of("BBFFBBFRLL", 4),
        )
    }
}
