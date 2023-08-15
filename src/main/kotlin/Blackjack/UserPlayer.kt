package Blackjack

import Globals.*
import kotlin.math.round

// Klasse, die den Benutzerspieler im Blackjack-Spiel repräsentiert und von BasicPlayer erbt.
class UserPlayer(name: String, hand: Hand = Hand()) : BasicPlayer(name, hand) {

    var splitHand: Hand = Hand() // Die Hand für den Fall, dass der Spieler seine Hand splittet

    // Methode, um das Spiel für den Benutzerspieler zu starten.
    fun start() {
        println("$name erhält zwei Karten!")
        hand.cardAdd()
        hand.cardAdd()
        handShow()
    }

    // Methode, um eine Karte zu ziehen (Hit).
    fun hit() {
        println("$name - HIT\n" +
                "Eine neue Karte wird verteilt!")
        hand.cardAdd()
        handShow()
        playerHandValue = hand.handValue(true) // Aktualisieren des Wertes der Hand für den Benutzerspieler
    }

    // Methode, um keine weiteren Karten zu ziehen (Stand).
    fun stand(): Boolean {
        println("$name - STAND")
        println("Aktueller Wert: ${hand.handValue(true)}")
        playerHandValue = hand.handValue(true) // Aktualisieren des Wertes der Hand für den Benutzerspieler
        return true
    }

    // Methode, um die Hand zu splitten.
    fun split(): Boolean {
        println("$name - SPLIT\n" +
                "Die Hand wurde gesplittet!")
        splitHand.hand.add(hand.hand.removeAt(0)) // Eine Karte wird zur gesplitteten Hand hinzugefügt
        balance -= bet // Der Betrag der Wette wird für die gesplitte Hand von dem Guthaben abgezogen
        playerHandValue = hand.handValue(true) // Aktualisieren des Wertes der Hand für den Benutzerspieler
        playerSplitHandValue = splitHand.handValue(true) // Aktualisieren des Wertes der gesplitteten Hand
        return true
    }

    // Methode, um die gesplittete Hand anzuzeigen.
    fun splitHandShow() {
        println("Die aktuelle Hand von $name:")
        println(splitHand)
        println("Der aktuelle Wert der Hand ist: ${splitHand.handValue(true)}")
    }

    // Methode, um eine Karte für die gesplittete Hand zu ziehen (Hit).
    fun splitHit() {
        println("$name - HIT\n" +
                "Eine neue Karte wird verteilt!")
        splitHand.cardAdd()
        splitHandShow()
        playerSplitHandValue = splitHand.handValue(true) // Aktualisieren des Wertes der gesplitteten Hand
    }

    // Methode, um keine weiteren Karten für die gesplittete Hand zu ziehen (Stand).
    fun splitStand(): Boolean {
        println("$name - STAND")
        println("Aktueller Wert: ${splitHand.handValue(true)}")
        playerSplitHandValue = splitHand.handValue(true) // Aktualisieren des Wertes der gesplitteten Hand
        return true
    }

    // Methode für die Aufgabe (Surrender).
    fun surrender(): Boolean {
        println("$name - SURRENDER\n" +
                "Der halbe Einsatz wurde erstattet.")
        balance += bet / 2 // Der halbe Einsatz wird zur Balance hinzugefügt
        balance = round(balance * 100) / 100 // Runden auf 2 Dezimalstellen
        return true
    }

    // Methode für die Versicherung (Insurance).
    fun insurance(): Boolean {
        println("$name - INSURANCE\n" +
                "Die Wette auf den Dealer für $bet wurde gesetzt!")
        balance -= bet // Der Betrag der Versicherung wird von der Balance abgezogen
        return true
    }

    // Methode für das Verdoppeln (Double Down).
    fun doubleDown(): Boolean {
        println("$name - DOUBLE DOWN\n" +
                "Der Einsatz wurde verdoppelt und eine weitere Karte wird verteilt!")
        balance -= bet // Der ursprüngliche Einsatz wird von der Balance abgezogen
        bet *= 2 // Der Einsatz wird verdoppelt
        hand.cardAdd() // Eine weitere Karte wird gezogen
        handShow()
        playerHandValue = hand.handValue(true) // Aktualisieren des Wertes der Hand für den Benutzerspieler
        return true
    }
}