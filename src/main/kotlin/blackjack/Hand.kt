package blackjack

import globals.BLUE
import globals.RED
import globals.RESET

// Klasse, die eine Hand im Blackjack-Spiel repräsentiert
open class Hand() {
    // Eine Liste von Karten, die die Hand enthält
    val hand: MutableList<Card> = mutableListOf()

    // Methode zum Hinzufügen einer Karte zur Hand
    fun cardAdd(){
        hand.add(deck.cardDraw()) // 'deck' scheint eine Referenz auf das Kartendeck zu sein, und 'cardDraw()' zieht eine Karte aus dem Deck und fügt sie zur Hand hinzu
    }

    // Methode zum Berechnen des Wertes der Hand
    open fun handValue(): Int {
        var handValue: Int = 0  // Variable, um den aktuellen Wert der Hand zu speichern
        var aces: Int = 0       // Variable, um die Anzahl der Asse in der Hand zu zählen (Ass kann 1 oder 11 wert sein)

        // Durchlaufe jede Karte in der Hand
        for (card in hand) {
            when (card.rank) {
                Rank.ACE -> {  // Wenn die Karte ein Ass ist
                    handValue += card.rank.cardValue  // Füge den Wert des Asses zum aktuellen Handwert hinzu (normalerweise 11)
                    aces++  // Erhöhe die Anzahl der Asse in der Hand
                }
                else -> handValue += card.rank.cardValue  // Für andere Karten, füge ihren Wert zum aktuellen Handwert hinzu
            }
        }

        // Wenn der Handwert größer als 21 ist und Asse in der Hand vorhanden sind
        // Dann zähle ein Ass als 1 statt 11, um den Handwert zu reduzieren (um ein Bust zu verhindern)
        while (handValue > 21 && aces > 0) {
            aces--  // Reduziere die Anzahl der verbleibenden Asse
            handValue -= 10  // Ziehe 10 vom Handwert ab (da ein Ass von 11 auf 1 reduziert wird)
        }

        // Gib den berechneten Handwert zurück
        return handValue
    }

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
            line1 += color + "┌─────────┐" + RESET + " "
            line2 += color + "│${card.rank.cardRank}        │" + RESET + " "
            line3 += color + "│         │" + RESET +  " "
            line4 += color + "│    ${card.suit.cardSuit}    │" + RESET + " "
            line5 += color + "│         │" + RESET + " "
            line6 += color + "│        ${card.rank.cardRank}│" + RESET + " "
            line7 += color + "└─────────┘" + RESET + " "
        }
        line1 += "\n"
        line2 += "\n"
        line3 += "\n"
        line4 += "\n"
        line5 += "\n"
        line6 += "\n"
        line7 += "\n"
        return line1 + line2 + line3 + line4 + line5 + line6 + line7
    }
}