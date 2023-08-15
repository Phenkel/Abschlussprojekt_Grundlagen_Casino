package Blackjack
import Globals.*

// Aufzählung für die verschiedenen Kartenfarben (Suit) im Blackjack-Spiel.
enum class Suit(val cardSuit: Char) {
    HEART(HEART_SYMBOL),    // Herz
    DIAMOND(DIAMOND_SYMBOL), // Karo
    CLOVER(CLOVER_SYMBOL),   // Kreuz
    SPADE(SPADE_SYMBOL)      // Pik
}

// Aufzählung für die verschiedenen Kartenwerte (Rank) und Ränge im Blackjack-Spiel.
enum class Rank(val cardValue: Int, val cardRank: String) {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "X"),   // 10
    JACK(10, "B"),  // Bube
    QUEEN(10, "D"), // Dame
    KING(10, "K"),  // König
    ACE(11, "A")    // Ass
}

// Datenklasse, die eine Karte im Blackjack-Spiel repräsentiert.
data class Card(val suit: Suit, val rank: Rank) {

    // Funktion, um die visuelle Darstellung der Karte auszugeben.
    fun printCard() {
        if (suit == Suit.DIAMOND || suit == Suit.HEART) {
            println("${Globals.RED}┌─────────┐\n" +
                    "│ ${rank.cardRank}       │\n" +
                    "│         │\n" +
                    "│    ${suit.cardSuit}    │\n" +
                    "│         │\n" +
                    "│       ${rank.cardRank} │\n" +
                    "└─────────┘${Globals.RESET}")
        } else {
            println("${Globals.BLUE}┌─────────┐\n" +
                    "│ ${rank.cardRank}       │\n" +
                    "│         │\n" +
                    "│    ${suit.cardSuit}    │\n" +
                    "│         │\n" +
                    "│       ${rank.cardRank} │\n" +
                    "└─────────┘${Globals.RESET}")
        }
    }
}