package com.tompietri.aoc2020.day22

fun day22FirstSolution(input: List<String>): Int {
    var (player1Deck, player2Deck) = Deck.fromInput(input)

    while (player1Deck.cards.isNotEmpty() && player2Deck.cards.isNotEmpty()) {
        val player1Card = player1Deck.cards.first()
        val player2Card = player2Deck.cards.first()
        if (player1Card > player2Card) {
            player2Deck = player2Deck.copy(cards = player2Deck.cards.drop(1))
            player1Deck = player1Deck.copy(cards = player1Deck.cards.drop(1) + listOf(player1Card, player2Card))
        } else {
            player1Deck = player1Deck.copy(cards = player1Deck.cards.drop(1))
            player2Deck = player2Deck.copy(cards = player2Deck.cards.drop(1) + listOf(player2Card, player1Card))
        }
    }

    return if (player1Deck.cards.isEmpty()) player2Deck.score() else player1Deck.score()
}

fun day22SecondSolution(input: List<String>): Int {
    val (player1Deck, player2Deck) = Deck.fromInput(input)

    val result = playRecursiveGame(player1Deck, player2Deck)

    return if (result.first != null) {
        result.first!!.score()
    } else {
        result.second!!.score()
    }
}

fun playRecursiveGame(player1Deck: Deck, player2Deck: Deck): Pair<Deck?, Deck?> {
    var deck1 = player1Deck
    var deck2 = player2Deck

    val previousRounds = mutableSetOf<Pair<Deck, Deck>>()
    while (deck1.cards.isNotEmpty() && deck2.cards.isNotEmpty()) {

        val currentRoundState = Pair(deck1, deck2)
        if (previousRounds.contains(currentRoundState)) {
            return Pair(deck1, null)
        }
        previousRounds.add(currentRoundState)

        val player1Card = deck1.cards.first()
        val player2Card = deck2.cards.first()

        deck1 = deck1.copy(cards = deck1.cards.drop(1))
        deck2 = deck2.copy(cards = deck2.cards.drop(1))

        if (deck1.canPlayRecursive(player1Card) && deck2.canPlayRecursive(player2Card)) {
            val subGameResult = playRecursiveGame(
                deck1.copy(cards = deck1.cards.take(player1Card)),
                deck2.copy(cards = deck2.cards.take(player2Card))
            )

            if (subGameResult.first != null) {
                deck1 =
                    deck1.copy(cards = deck1.cards + listOf(player1Card, player2Card))
            } else {
                deck2 =
                    deck2.copy(cards = deck2.cards + listOf(player2Card, player1Card))
            }
        } else {
            if (player1Card > player2Card) {
                deck1 =
                    deck1.copy(cards = deck1.cards + listOf(player1Card, player2Card))
            } else {
                deck2 =
                    deck2.copy(cards = deck2.cards + listOf(player2Card, player1Card))
            }
        }
    }

    return if (deck1.cards.isEmpty()) {
        Pair(null, deck2)
    } else {
        Pair(deck1, null)
    }
}

data class Deck(val cards: List<Int>) {
    fun score() = cards.reversed().mapIndexed() { index, i -> i * (index + 1) }.sum()

    fun canPlayRecursive(value: Int) = cards.count() >= value

    companion object {
        fun fromInput(input: List<String>): List<Deck> {
            return input.joinToString(";")
                .split(";;").map { deck ->
                    val cards = deck.split(";").drop(1).map { it.toInt() }
                    Deck(cards)
                }
        }
    }
}
