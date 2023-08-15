package Globals

import kotlin.system.exitProcess

// Hier werden verschiedene Unicode-Symbole für die Kartensymbole definiert.
val SPADE_SYMBOL = '\u2660' // Symbol für Pik
val DIAMOND_SYMBOL = '\u2666' // Symbol für Karo
val HEART_SYMBOL = '\u2665' // Symbol für Herz
val CLOVER_SYMBOL = '\u2663' // Symbol für Kreuz

// Hier werden ANSI-Farbcodes für die Konsolenausgabe definiert.
val RESET = "\u001B[0m" // Setzt die Farbe zurück
val BLUE = "\u001B[34m" // Blaue Farbe
val RED = "\u001B[31m" // Rote Farbe
val GREEN = "\u001B[32m" // Grüne Farbe

// Globale Variablen für den Spielerstatus im Casino.
var balance: Double = 10000.0 // Der Kontostand des Spielers
var bet: Double = 5000.0 // Der Einsatz des Spielers
var name: String = "" // Der Name des Spielers

var leaveCasino: Boolean = false // Ein Kontrollwert, um festzustellen, ob der Spieler das Casino verlassen möchte

// Variable um die Anzahl der falschen Usereingaben zu speichern
var idiotCounter: Int = 0
// Funktion um den User auf eine falsche Eingabe "hinzuweisen" und das Programm bei ständiger Falscheingabe zu beenden
fun wrongUserInput() {
    idiotCounter++
    when (idiotCounter) {
        in 1..5 -> println("Falsche Eingabe, hm? Bist du sicher, dass du hierher gehörst?")
        in 6..10 -> println("Ohne Scheiß, du versuchst mich zu testen, oder?")
        in 11..15 -> println("Das wird langsam peinlich, oder? Machst du das extra?")
        in 16..20 -> println("Ok, jetzt reicht's. Du tust wirklich alles, um mich zu nerven, oder?")
        else -> {
            println("Du hast den Idiotenmodus überschritten. Ich werde jetzt einfach auflegen.")
            exitProcess(0)
        }
    }
}

// Eine Funktion, um Erfolgsmeldungen in grüner Farbe auszugeben.
fun successMessage(text: String) {
    println(GREEN + text + RESET)
}

// Eine Funktion, um Fehlermeldungen in roter Farbe auszugeben.
fun errorMessage(text: String) {
    println(RED + text + RESET)
}