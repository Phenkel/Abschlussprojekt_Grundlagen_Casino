package Blackjack

import Globals.*

// Klasse, die den Dealer im Blackjack-Spiel repräsentiert und von BasicPlayer erbt.
class Dealer(name: String, hand: Hand = Hand()) : BasicPlayer(name, hand) {

    // Überschreiben der Methode handShow() aus der Basisklasse.
    override fun handShow() {
        println("Die aktuelle Hand von $name:")
        println(hand) // Ruft die toString()-Methode der Hand auf, um die visuelle Darstellung der Hand auszugeben
        println("Der aktuelle Wert der Hand ist: ${hand.handValue(false)}") // Berechnet und gibt den Wert der Hand aus (ohne Ass-Einstellung)
    }

    // Methode, um das Spiel für den Dealer zu starten.
    fun start() {
        hand.cardAdd()
        hand.cardAdd()
        if (hand.hand.first().rank.cardValue >= hand.hand.last().rank.cardValue) {
            println("Die erste Karte des Dealers:")
            hand.hand.first().printCard()
        } else {
            println("Die erste Karte des Dealers:")
            hand.hand.last().printCard()
        }
    }

    // Methode, die den Zug des Dealers durchführt.
    fun dealerTurn() {
        handShow() // Zeigt die aktuelle Hand des Dealers
        while (hand.handValue(false) < 17 ) { // Der Dealer zieht bis seine Hand einen Wert von mindestens 17 hat
            println("$name zieht eine weitere Karte!")
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            hand.cardAdd() // Eine weitere Karte wird gezogen
            handShow() // Zeigt die aktualisierte Hand des Dealers
        }
        println("Dealerrunde beendet")
        dealerHandValue = hand.handValue(false) // Setzt den Wert der Dealerhand (ohne Ass-Einstellung)
    }
}