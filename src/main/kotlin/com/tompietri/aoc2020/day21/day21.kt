package com.tompietri.aoc2020.day21

fun day21FirstSolution(input: List<String>): Int {
    val foods = Food.fromInput(input)

    val ingredientsWithAllergens = mapFoodToAllergens(foods)
        .toAllergenWithPotentialIngredients()
        .flatMap { it.ingredients }
        .toSet()

    val nonAllergenIngredients = foods.flatMap { it.ingredients }.filterNot { ingredientsWithAllergens.contains(it) }.toSet()

    return foods.flatMap { it.ingredients }.filter { nonAllergenIngredients.contains(it) }.count()
}

fun day21SecondSolution(input: List<String>): String {
    return mapFoodToAllergens(Food.fromInput(input))
        .toAllergenWithPotentialIngredients()
        .toAllergensWithIngredient()
        .sortedBy { it.allergen }
        .joinToString(",") { it.ingredient }
}

private fun Map<String, MutableList<Food>>.toAllergenWithPotentialIngredients(): List<AllergenWithPotentialIngredients> {
    return this.map { (allergen, foods) ->
        var ingredientsWithPotentialAllergen = foods[0].ingredients
        foods.forEach { food ->
            ingredientsWithPotentialAllergen = ingredientsWithPotentialAllergen.filter { food.ingredients.contains(it) }
        }

        AllergenWithPotentialIngredients(allergen, ingredientsWithPotentialAllergen)
    }
}

private fun List<AllergenWithPotentialIngredients>.toAllergensWithIngredient(): MutableList<AllergenWithIngredient> {
    var ingredientsWithAllergens = this
    val allergensWithIngredient = mutableListOf<AllergenWithIngredient>()
    while (ingredientsWithAllergens.isNotEmpty()) {
        val allergenWithOnlyOnePossibleIngredient = ingredientsWithAllergens.find { it.ingredients.size == 1 }!!
        val foundAllergen = AllergenWithIngredient(allergenWithOnlyOnePossibleIngredient.allergen, allergenWithOnlyOnePossibleIngredient.ingredients[0])
        allergensWithIngredient.add(foundAllergen)

        ingredientsWithAllergens = ingredientsWithAllergens
            .filter { it != allergenWithOnlyOnePossibleIngredient }
            .map { it.copy(ingredients = it.ingredients.filter { ingredient -> ingredient != foundAllergen.ingredient }) }
    }

    return allergensWithIngredient
}

private fun mapFoodToAllergens(foods: List<Food>): MutableMap<String, MutableList<Food>> {
    val allergensByFood = mutableMapOf<String, MutableList<Food>>()
    foods.forEach {
        it.allergens.forEach { allergen ->
            val list = allergensByFood.getOrDefault(allergen, mutableListOf())
            list.add(it)
            allergensByFood[allergen] = list
        }
    }
    return allergensByFood
}

data class Food(val ingredients: List<String>, val allergens: List<String>) {
    companion object {
        private val regex = Regex("^(.*) \\(contains (.*)\\)$")
        fun fromInput(input: List<String>): List<Food> = input.map {
            val (_, ingredients, allergens) = regex.find(it)!!.groupValues
            Food(ingredients.split(" "), allergens.split(", "))
        }
    }
}

data class AllergenWithPotentialIngredients(val allergen: String, val ingredients: List<String>)
data class AllergenWithIngredient(val allergen: String, val ingredient: String)
