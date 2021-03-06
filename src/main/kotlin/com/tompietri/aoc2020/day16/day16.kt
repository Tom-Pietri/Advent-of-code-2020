package com.tompietri.aoc2020.day16

import java.math.BigInteger

fun day16FirstSolution(input: List<String>): Int {
    val formattedInput = FormattedInput.fromInputStrings(input)

    return formattedInput.nearbyTicketsValues.flatMap { ticket ->
        ticket.values.map {
            val rulesMatching = formattedInput.rules.filter { rule -> rule.range1.contains(it) || rule.range2.contains(it) }
            if(rulesMatching.isEmpty()) {
                it
            } else {
                0
            }
        }
    }.sum()
}

fun day16SecondSolution(input: List<String>): BigInteger {
    val formattedInput = FormattedInput.fromInputStrings(input)
    val inputWithValidTickets = formattedInput.copy(
        nearbyTicketsValues = formattedInput.nearbyTicketsValues.filter { ticket ->
            ticket.values.all {
                formattedInput.rules.any { rule -> rule.range1.contains(it) || rule.range2.contains(it) }
            }
        }
    )


    val rulesMatchingByNearbyTicket = inputWithValidTickets.nearbyTicketsValues.map { ticket ->
        ticket.values.map {
            RuleMatch(it, formattedInput.rules.filter { rule -> rule.range1.contains(it) || rule.range2.contains(it) })
        }
    }

    val matchByColumns = mutableMapOf<Int, List<RuleMatch>>()

    rulesMatchingByNearbyTicket.forEach {
        it.forEachIndexed { index, ruleMatch ->
            val list = matchByColumns.getOrPut(index) { listOf() }
            matchByColumns[index] = list + ruleMatch
        }
    }

    val rulesMatchingColumn = matchByColumns.map { (key, value) ->
        key to value.map { it.rules.map { rule -> rule.name } }
            .reduce { acc, list -> acc.filter { list.contains(it) } }
    }.toMap()

    val map = mutableMapOf<Int, String>()
    val allreadySeenRules = mutableSetOf<String>()

    while(allreadySeenRules.size < rulesMatchingColumn.keys.size) {
        val (row, rule) = rulesMatchingColumn.map { it.key to it.value.filterNot { ruleName -> allreadySeenRules.contains(ruleName) } }
            .filter { (_, rules) -> rules.size == 1 }.map { it.first to it.second.first() }
            .first()

        map[row] = rule
        allreadySeenRules.add(rule)
    }


    val columns = map.filter { (_, ruleName) -> ruleName.startsWith("departure") }.keys

    return columns.map { formattedInput.myTicketValues[it].toBigInteger() }.reduce(BigInteger::times)
}


data class FormattedInput(
    val rules: List<Rule>,
    val myTicketValues: List<Int>,
    val nearbyTicketsValues: List<NearbyTicket>
) {
    companion object {
        fun fromInputStrings(input: List<String>): FormattedInput {
            val splitedInput = input.joinToString(";").split(";;")
            val myTicketValues = splitedInput[1].split(";")[1].split(",").map { it.toInt() }

            val rules = splitedInput[0].split(";").map { Rule.fromRuleString(it) }
            rules.filter { it.range1.contains(1) || it.range2.contains(1) }

            val otherTicketsValues =
                splitedInput[2].split(";").drop(1).map { it.split(",").map { it.toInt() } }.map { NearbyTicket(it) }

            return FormattedInput(
                rules = rules,
                myTicketValues = myTicketValues,
                nearbyTicketsValues = otherTicketsValues
            )
        }
    }
}

data class NearbyTicket(val values: List<Int>)
data class Rule(val name: String, val range1: IntRange, val range2: IntRange) {
    companion object {
        private val ruleRegex = Regex("^(.*): (\\d+)-(\\d+) or (\\d+)-(\\d+)$")

        fun fromRuleString(stringedRule: String): Rule {
            val matches = ruleRegex.find(stringedRule)!!.groupValues
            return Rule(matches[1], matches[2].toInt()..matches[3].toInt(), matches[4].toInt()..matches[5].toInt())
        }
    }
}

data class RuleMatch(val testedValue: Int, val rules: List<Rule>)
data class Collumn(val index: Int, val rule: Rule)

