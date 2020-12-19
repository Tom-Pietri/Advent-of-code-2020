package com.tompietri.aoc2020.day19

fun day19FirstSolution(input: List<String>): Int {
    val (commaSeparatedRules, commaSeparatedMessages) = input.joinToString(",").split(",,")

    val regex = buildRuleMap(commaSeparatedRules).let { rulesById ->
        Regex(rulesById[0]!!.toRegexString(rulesById, 0))
    }

    return commaSeparatedMessages.split(",")
        .filter { it matches regex }
        .count()
}

fun day19SecondSolution(input: List<String>): Int {
    val (commaSeparatedRules, commaSeparatedMessages) = input.joinToString(",").split(",,")

    val regex = buildRuleMap(commaSeparatedRules).toMutableMap().let { rulesById ->
        rulesById[8] = OrRule(id = 8, SimpleRule(listOf(42)), SimpleRule(listOf(42, 8)))
        rulesById[11] = OrRule(id = 8, SimpleRule(listOf(42, 31)), SimpleRule(listOf(42, 11, 31)))

        Regex(rulesById[0]!!.toRegexString(rulesById, 0))
    }

    return commaSeparatedMessages.split(",")
        .filter { it matches regex }
        .count()
}

private fun buildRuleMap(commaSeparatedRules: String): Map<Int, Rule> {
    return commaSeparatedRules.split(",").map {
        val (id, value) = it.split(":")

        id.toInt() to when {
            LetterRule.format matches value -> LetterRule.fromValue(value)
            SimpleRule.format matches value -> SimpleRule.fromValue(value)
            OrRule.format matches value -> OrRule.fromValue(value, id.toInt())
            else -> error("We should always match at least one")
        }
    }.toMap()
}

private fun String.stringedIdsToList() = this.split(" ").filter(String::isNotEmpty).map(String::toInt)

interface Rule {
    fun toRegexString(ruleMap: Map<Int, Rule>, nbIterations: Int): String
}

data class LetterRule(val letter: String) : Rule {
    override fun toRegexString(ruleMap: Map<Int, Rule>, nbIterations: Int) = letter

    companion object {
        fun fromValue(value: String) = LetterRule(letter = value.filter { it != '"' }.trim())
        val format = Regex("^ \"(.)\"$")
    }
}

data class OrRule(val id: Int, val firstRule: SimpleRule, val secondRule: SimpleRule) : Rule {
    override fun toRegexString(ruleMap: Map<Int, Rule>, nbIterations: Int): String {
        val firstGroupRegex = firstRule.toRegexString(ruleMap, nbIterations + 1)

        // Special case for part 2, nbIteration to 96 as it appears to be the longest message to match in my input
        return if (nbIterations > 96 && (id == 8 || id == 11)) {
            firstGroupRegex
        } else {
            val secondGroupRegex = secondRule.toRegexString(ruleMap, nbIterations + 1)
            "($firstGroupRegex|$secondGroupRegex)"
        }
    }

    companion object {
        fun fromValue(value: String, id: Int): OrRule {
            val (_, firstGroupIdsStringed, _, secondGroupIdsStringed) = format.find(value)!!.groupValues
            return OrRule(
                id = id,
                firstRule = SimpleRule(firstGroupIdsStringed.stringedIdsToList()),
                secondRule = SimpleRule(secondGroupIdsStringed.stringedIdsToList())
            )
        }
        val format = Regex("^ ((\\d* )*)\\|(( \\d*)*)\$")
    }
}

data class SimpleRule(val rules: List<Int>) : Rule {
    override fun toRegexString(ruleMap: Map<Int, Rule>, nbIterations: Int) =
        rules.joinToString("") { ruleMap[it]!!.toRegexString(ruleMap, nbIterations + 1) }

    companion object {
        fun fromValue(value: String) = SimpleRule(value.stringedIdsToList())
        val format = Regex("^( \\d*)*$")
    }
}
