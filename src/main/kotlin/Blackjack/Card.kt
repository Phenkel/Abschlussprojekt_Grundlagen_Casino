package Blackjack
import Globals.*

// Definition der Kartenfarben (Suit) für das Blackjackspiel
enum class Suit(val cardSuit: Char) {
    HEART(HEART_SYMBOL),      // Herzkarte (♥), die Variable '$RED$HEART_SYMBOL' ist ein Farbcode für die Ausgabe
    DIAMOND(DIAMOND_SYMBOL),  // Karokarte (♦), die Variable '$RED$DIAMOND_SYMBOL' ist ein Farbcode für die Ausgabe
    CLOVER(CLOVER_SYMBOL),   // Kreuzkarte (♣), die Variable '$BLUE$CLOVER_SYMBOL' ist ein Farbcode für die Ausgabe
    SPADE(SPADE_SYMBOL)      // Pik-Karte (♠), die Variable '$BLUE$SPADE_SYMBOL' ist ein Farbcode für die Ausgabe
}

// Definition der Kartenwerte (Rank) für das Blackjackspiel
enum class Rank(val cardValue: Int, val cardRank: String) {
    TWO(2, "2"),    // Karte mit Wert 2, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    THREE(3, "3"),  // Karte mit Wert 3, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    FOUR(4, "4"),   // Karte mit Wert 4, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    FIVE(5, "5"),   // Karte mit Wert 5, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    SIX(6, "6"),   // Karte mit Wert 6, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    SEVEN(7, "7"),// Karte mit Wert 7, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    EIGHT(8, "8"),  // Karte mit Wert 8, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    NINE(9, "9"),   // Karte mit Wert 9, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    TEN(10, "X"),   // Karte mit Wert 10, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    JACK(10, "B"),  // Buben-Karte mit Wert 10, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    QUEEN(10, "D"), // Damen-Karte mit Wert 10, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    KING(10, "K"), // Königs-Karte mit Wert 10, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
    ACE(11, "A")     // Ass-Karte mit Wert 11, 'germanRank' ist eine deutsche Bezeichnung für die Karte, '$RESET' ist ein Farbcode für die Ausgabe
}

// Datenklasse für eine einzelne Spielkarte
data class Card(val suit: Suit, val rank: Rank) {
    fun printCard() {
        if (suit == Suit.DIAMOND || suit == Suit.HEART) {
            println("${Globals.RED}┌─────────┐\n" +
                                  "│ ${rank.cardRank}       │\n" +
                                  "│         │\n" +
                                  "│    ${suit.cardSuit}    │\n" +
                                  "│         │\n" +
                                  "│       ${rank.cardRank} │\n" +
                                  "└─────────┘${Globals.RESET}")
        }
        else {
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