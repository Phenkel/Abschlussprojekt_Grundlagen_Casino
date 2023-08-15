package Blackjack

import Globals.*

// Klasse, die eine Hand im Blackjack-Spiel repräsentiert.
class Hand() {
    val hand: MutableList<Card> = mutableListOf() // Liste, um die Karten in der Hand zu speichern

    // Methode, um eine Karte zur Hand hinzuzufügen.
    fun cardAdd() {
        hand.add(deck.cardDraw())
    }

    // Methode, um den Wert der Hand zu berechnen.
    fun handValue(acesToOne: Boolean): Int {
        var handValue: Int = 0
        if (acesToOne) {
            var aces: Int = 0

            for (card in hand) {
                when (card.rank) {
                    Rank.ACE -> {
                        handValue += card.rank.cardValue
                        aces++
                    }
                    else -> handValue += card.rank.cardValue
                }
            }

            while (handValue > 21 && aces > 0) {
                aces--
                handValue -= 10
            }

        } else {
            for (card in hand) {
                handValue += card.rank.cardValue
            }
        }
        return handValue
    }

    // Überschreiben der toString()-Methode, um die visuelle Darstellung der Hand auszugeben.
    override fun toString(): String {
        var line1 = ""
        var line2 = ""
        var line3 = ""
        var line4 = ""
        var line5 = ""
        var line6 = ""
        var line7 = ""
        for (card in hand) {
            var color = if (card.suit == Suit.DIAMOND || card.suit == Suit.HEART) RED else BLUE
            line1 += color + "┌─────────┐" + " "
            line2 += color + "│ ${card.rank.cardRank}       │" + " "
            line3 += color + "│         │" + " "
            line4 += color + "│    ${card.suit.cardSuit}    │" + " "
            line5 += color + "│         │" + " "
            line6 += color + "│       ${card.rank.cardRank} │" + " "
            line7 += color + "└─────────┘" + " "
        }
        line1 += "\n"
        line2 += "\n"
        line3 += "\n"
        line4 += "\n"
        line5 += "\n"
        line6 += "\n"
        line7 += RESET // Zurücksetzen der Farbe auf die Standardfarbe
        return line1 + line2 + line3 + line4 + line5 + line6 + line7 // Die visuelle Darstellung der Hand wird zusammengesetzt
    }
}