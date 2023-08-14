package Blackjack

// Eine Klasse, die einen einfachen Spieler im Blackjack-Spiel repräsentiert
open class BasicPlayer(var name: String, var hand: Hand = Hand()) {

    // Methode zum Anzeigen der aktuellen Hand des Spielers
    open fun handShow() {
        println("Die aktuelle Hand von $name:")
        println(hand)
        println("Der aktuelle Wert der Hand ist: ${hand.handValue(true)}")
    }
}