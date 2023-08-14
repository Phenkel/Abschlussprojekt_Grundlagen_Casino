package Blackjack
import Globals.*

// Klasse, die den Dealer im Blackjack-Spiel repräsentiert
class Dealer(name: String, hand: Hand = Hand()) : BasicPlayer(name, hand) {

    // Überschreibt die Methode handValue() der Basisklasse, um den Wert der Hand des Dealers zu berechnen
    override fun handShow() {
        println("Die aktuelle Hand von $name:")
        println(hand)
        println("Der aktuelle Wert der Hand ist: ${hand.handValue(false)}")
    }

    // Methode zum Starten der Dealer-Runde
    fun start() {
        hand.cardAdd() // Zieht eine Karte zur Hand des Dealers
        hand.cardAdd() // Zieht eine weitere Karte zur Hand des Dealers
        // Zeigt nur eine der beiden Karten des Dealers an, um die zweite verdeckt zu lassen
        if (hand.hand.first().rank.cardValue >= hand.hand.last().rank.cardValue) {
            println("Die erste Karte des Dealers:")
            hand.hand.first().printCard()
        } else {
            println("Die erste Karte des Dealers:")
            hand.hand.last().printCard()
        }
    }

    // Methode, die die Spielzüge des Dealers steuert
    fun dealerTurn() {
        handShow() // Zeigt die Hand des Dealers an
        // Der Dealer zieht Karten, solange der Wert der Hand kleiner als 17 ist
        while (hand.handValue(false) < 17 ) {
            println("$name zieht eine weitere Karte!")
            // Eine kurze Verzögerung, um das "Ziehen" der Karte zu simulieren
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            hand.cardAdd() // Zieht eine weitere Karte zur Hand des Dealers
            handShow() // Zeigt die aktualisierte Hand des Dealers an
        }
        println("Dealerrunde beendet")
        dealerHandValue = hand.handValue(false) // Setzt den Wert der Hand des Dealers für das Spielende fest
    }
}