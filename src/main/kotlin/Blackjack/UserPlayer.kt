package Blackjack
import Globals.*

// Klasse, die einen Spieler im Blackjack-Spiel repräsentiert, der vom Benutzer gesteuert wird
class UserPlayer(name: String, hand: Hand = Hand()) : BasicPlayer(name, hand) {
    // Ein weiteres Hand-Objekt, das die Hand nach einem Split speichert
    var splitHand: Hand = Hand()

    // Methode zum Starten des Spiels für den Benutzer
    fun start() {
        println("$name erhält zwei Karten!")
        hand.cardAdd() // Zieht eine Karte zur Hand des Spielers
        hand.cardAdd() // Zieht eine weitere Karte zur Hand des Spielers
        handShow() // Zeigt die aktuelle Hand des Spielers an
    }

    // Methode, um eine Karte zu ziehen (Hit)
    fun hit() {
        println("$name - HIT\n" +
                "Eine neue Karte wird verteilt!")
        hand.cardAdd() // Zieht eine Karte zur Hand des Spielers
        handShow() // Zeigt die aktualisierte Hand des Spielers an
        // Setzt den Wert der Spielerhand auf den aktuellen Wert
        playerHandValue = hand.handValue()
    }

    // Methode, um keine weitere Karte zu ziehen (Stand)
    fun stand(): Boolean {
        println("$name - STAND")
        println("Aktueller Wert: ${hand.handValue()}")
        return true // Gibt 'true' zurück, um anzuzeigen, dass der Spieler steht
    }

    // Methode zum Teilen der Hand (Split)
    fun split(): Boolean {
        println("$name - SPLIT\n" +
                "Die Hand wurde gesplittet!")
        splitHand.hand.add(hand.hand.removeAt(0)) // Entfernt eine Karte von der ursprünglichen Hand und fügt sie zur gesplitteten Hand hinzu
        balance - bet // Reduziert den Einsatz für die gesplittete Hand vom Guthaben des Spielers
        return true // Gibt 'true' zurück, um anzuzeigen, dass der Spieler die Hand gesplittet hat
    }

    // Methode zum Anzeigen der gesplitteten Hand
    fun splitHandShow() {
        println("Die aktuelle Hand von $name:")
        println(splitHand)
        println("Der aktuelle Wert der Hand ist: ${splitHand.handValue()}")
    }

    // Methode, um eine Karte für die gesplittete Hand zu ziehen (Split Hit)
    fun splitHit() {
        println("$name - HIT\n" +
                "Eine neue Karte wird verteilt!")
        splitHand.cardAdd() // Zieht eine Karte zur gesplitteten Hand
        splitHandShow() // Zeigt die aktualisierte gesplittete Hand des Spielers an
        // Setzt den Wert der gesplitteten Hand auf den aktuellen Wert
        playerSplitHandValue = splitHand.handValue()
    }

    // Methode, um keine weitere Karte für die gesplittete Hand zu ziehen (Split Stand)
    fun splitStand(): Boolean {
        println("$name - STAND")
        println("Aktueller Wert: ${splitHand.handValue()}")
        return true // Gibt 'true' zurück, um anzuzeigen, dass der Spieler für die gesplittete Hand steht
    }

    // Methode, um aufzugeben (Surrender)
    fun surrender(): Boolean {
        println("$name - SURRENDER\n" +
                "Der halbe Einsatz wurde erstattet.")
        balance += bet / 2 // Erhöht das Guthaben des Spielers um die Hälfte des aktuellen Einsatzes
        return true // Gibt 'true' zurück, um anzuzeigen, dass der Spieler aufgegeben hat
    }

    // Methode, um eine Versicherung abzuschließen (Insurance)
    fun insurance(): Boolean {
        println("$name - INSURANCE\n" +
                "Die Wette auf den Dealer für $bet wurde gesetzt!")
        balance -= bet // Reduziert das Guthaben des Spielers um den aktuellen Einsatz für die Versicherung
        return true // Gibt 'true' zurück, um anzuzeigen, dass der Spieler eine Versicherung abgeschlossen hat
    }

    // Methode, um den Einsatz zu verdoppeln (Double Down)
    fun doubleDown(): Boolean {
        println("$name - DOUBLE DOWN\n" +
                "Der Einsatz wurde verdoppelt und eine weitere Karte wird verteilt!")
        balance -= bet // Reduziert das Guthaben des Spielers um den aktuellen Einsatz
        bet *= 2 // Verdoppelt den aktuellen Einsatz
        hand.cardAdd() // Zieht eine Karte zur Hand des Spielers
        handShow() // Zeigt die aktualisierte Hand des Spielers an
        // Setzt den Wert der Spielerhand auf den aktuellen Wert
        playerHandValue = hand.handValue()
        return true // Gibt 'true' zurück, um anzuzeigen, dass der Spieler den Einsatz verdoppelt hat
    }
}