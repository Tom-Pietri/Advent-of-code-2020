package com.tompietri.aoc2021.day6

import com.tompietri.utils.readInputWithSingleLine
import com.tompietri.utils.readMultipleNumberInputSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day6Test {
    @Test
    fun `First solution - My input should return 6687`() {
        val input = readMultipleNumberInputSingleLine(2021, 6)
        assertThat(day6FirstSolution(input)).isEqualTo(BigInteger("389726"))
    }

    @Test
    fun `Second solution - My input should return 19851`() {
        val input = readMultipleNumberInputSingleLine(2021, 6)
        assertThat(day6SecondSolution(input)).isEqualTo(BigInteger("1743335992042"))
    }

}