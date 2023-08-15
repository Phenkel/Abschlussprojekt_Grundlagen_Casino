package Blackjack

// Basisklasse für einen Spieler im Blackjack-Spiel.
open class BasicPlayer(var name: String, var hand: Hand = Hand()) {

    // Methode, um die aktuelle Hand des Spielers anzuzeigen.
    open fun handShow() {
        println("Die aktuelle Hand von $name:")
        println(hand) // Ruft die toString()-Methode der Hand auf, um die visuelle Darstellung der Hand auszugeben
        println("Der aktuelle Wert der Hand ist: ${hand.handValue(true)}") // Berechnet und gibt den Wert der Hand aus
    }
}