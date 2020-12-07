package com.tompietri.aoc2020.day7

private const val myBagColor = "shiny gold"

fun day7FirstSolution(input: List<String>): Int {
    val bagMap = inputToBagMap(input)

    val bagsThatCanContain = mutableSetOf<String>()
    val bagsThatCanContainsShinyGold = bagMap.values.filter { bag -> bag.canContain.any { it.color == myBagColor } }.toMutableList()

    while (bagsThatCanContainsShinyGold.isNotEmpty()) {
        val currentBag = bagsThatCanContainsShinyGold.removeAt(0)
        bagsThatCanContain.add(currentBag.color)
        bagsThatCanContainsShinyGold.addAll(currentBag.canBeContainedIn.map { color -> bagMap[color]!! })
    }

    return bagsThatCanContain.size
}

fun day7SecondSolution(input: List<String>): Int {
    val bagMap = inputToBagMap(input)
    val initialBag = bagMap[myBagColor] ?: error("My bag should always be in the list")

    return countBagsInsideBag(bagMap, initialBag) - 1
}

fun countBagsInsideBag(bagMap : Map<String, Bag>, currentBag: Bag): Int {
    return currentBag.canContain.map {
        it.count * countBagsInsideBag(bagMap,bagMap[it.color] ?: error("bagMap should contain all the colors we require"))
    }.sum() + 1
}

fun inputToBagMap(input: List<String>): Map<String, Bag> {
    val bagsMap = mutableMapOf<String, Bag>()

    input.forEach { bagDefinition ->
        val (containingBagColor, canContainStringed) = bagDefinition.split("contain").map { it.trim() }
        val bagCapacities = bagCapacitiesFromContainString(canContainStringed)

        val newBag = Bag(color = containingBagColor.dropLast(5), canBeContainedIn = mutableSetOf(), canContain = bagCapacities)

        bagsMap.getOrDefault(newBag.color, newBag).let {
            bagsMap[newBag.color] = newBag.copy(canBeContainedIn = it.canBeContainedIn)
        }

        bagCapacities.forEach { bagCapacity ->
            val bagFromCapacity =
                Bag(color = bagCapacity.color, canBeContainedIn = setOf(newBag.color), canContain = emptySet())
            bagsMap.getOrDefault(bagFromCapacity.color, bagFromCapacity).let {
                bagsMap[bagFromCapacity.color] = it.copy(canBeContainedIn = it.canBeContainedIn + bagFromCapacity.canBeContainedIn)
            }
        }
    }

    return bagsMap
}

private fun bagCapacitiesFromContainString(canContainStringed: String): Set<BagCapacity> {
    if (canContainStringed == "no other bags.") return emptySet()

    val capacityRegex = Regex("([0-9]) (.*) bag")
    return canContainStringed.split(",")
        .map { capacity ->
            val (_, count, color) = capacityRegex.find(capacity)?.groupValues
                ?: error("'$capacity' does not match regex : ${capacityRegex.pattern}")
            BagCapacity(color = color, count = count.toInt())
        }.toSet()
}

data class Bag(val color: String, val canBeContainedIn: Set<String>, val canContain: Set<BagCapacity>)

data class BagCapacity(val color: String, val count: Int)
