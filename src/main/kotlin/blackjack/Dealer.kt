package blackjack

// Klasse, die den Dealer im Blackjack-Spiel repräsentiert
class Dealer(name: String) : BasicPlayer(name) {

    // Überschreibt die Methode handValue() der Basisklasse, um den Wert der Hand des Dealers zu berechnen
    fun handValue(): Int {
        var handvalue: Int = 0
        for (card in hand.hand) {
            handvalue += card.rank.cardValue // Addiert den Wert jeder Karte in der Hand des Dealers zum Gesamtwert
        }
        return handvalue // Gibt den berechneten Handwert zurück
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
        while (handValue() < 17 ) {
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
        dealerHandValue = handValue() // Setzt den Wert der Hand des Dealers für das Spielende fest
    }
}