package Globals

import kotlin.math.round
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
    print(RED)
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
    print(RESET)
}

// Eine Funktion um beklaut zu werden (zufällige Chance)
fun pickPocket(){
    val pickPocketChance: Int = (1..(100 - alcoholCounter)).random()
    if (pickPocketChance <= 2) {
        val stolenMoney: Double = round((balance * 0.1) * 100) / 100
        balance -= stolenMoney
        errorMessage("Ein Taschendieb hat die gerade $stolenMoney€ geklaut! Doof gelaufen aber du hättest es ja eh nur verzockt.")
    }
}

// Variable um den Alkoholkonsum zu speichern
var alcoholCounter: Int = 0

// Funktion um zu trinken
fun drink() {
    val alcoholicDrinks: List<String> = listOf("Bier", "Wein", "Schnaps")
    val nonAlcohlicDrinks: List<String> = listOf("Cola", "Wasser", "Fanta")
    var userInputDrink: String = ""
    do {
        print("Möchten sie etwas trinken? Ja oder Nein: ")
        userInputDrink = readln()
        if (userInputDrink != "Ja" && userInputDrink != "Nein") {
            errorMessage("Ungültige Eingabe")
            wrongUserInput()
            userInputDrink = ""
        }
    } while (userInputDrink == "")
    if (userInputDrink == "Ja") {
        println("Hier ist die Auswahl unserer Getränke: ")
        println("Alkohol: ${alcoholicDrinks.joinToString(", ")}")
        println("Softgetränke: ${nonAlcohlicDrinks.joinToString(", ")}")
        print("Was möchten sie trinken: ")
        userInputDrink = readln()
        if (alcoholicDrinks.contains(userInputDrink)) {
            println("Ein $userInputDrink für $name!")
            alcoholCounter++
        } else if (nonAlcohlicDrinks.contains(userInputDrink)) {
            println("Ein $userInputDrink für $name!")
            alcoholCounter--
        } else if (userInputDrink == "Radler") {
            errorMessage("So etwas wird hier nicht geduldet!")
            exitProcess(0)
        } else {
            errorMessage("WOW! Zu blöd um ein Getränk zu bestellen!")
            wrongUserInput()
        }
    }
}

// Funktion um professionellen Beischlaf mit (M/W/D) zu ermöglichen (zufällige Chance) -> Für die realistische Vegas Erfahrung
fun professionalLovemaking() {
    val intimateServicesSpecialistChance: Int = if (balance >= 10000.0) (1..50).random() else (1..100).random()
    val intimateServicesSpecialist: String = listOf<String>("Beischlaffachkraft(M)", "Beischlaffachkraft(W)", "Beischlaffachkraft(D)").random()
    if (intimateServicesSpecialistChance <= 2) {
        println("Eine $intimateServicesSpecialist nähert sich und fragt dich ob du für 100€ schöne 15 Minuten haben möchtest.")
        var userInputIntimateServicesSpecialist: String = ""
        do {
            print("Nimmst du das Angebot an? Ja oder Nein: ")
            userInputIntimateServicesSpecialist = readln()
            if (userInputIntimateServicesSpecialist != "Ja" && userInputIntimateServicesSpecialist != "Nein") {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputIntimateServicesSpecialist = ""
            } else if (userInputIntimateServicesSpecialist == "Ja" && balance < 100.0) {
                errorMessage("Dein Geld von $balance€ reicht nicht aus für $intimateServicesSpecialist!")
            }
        } while (userInputIntimateServicesSpecialist == "")
        if (userInputIntimateServicesSpecialist == "Nein") successMessage("Richtige Entscheidung! Professioneller Beischlaf ist hier verboten!")
        else if (balance >= 100.0) {
            balance -= 100.0
            println("Du gehst mit $intimateServicesSpecialist auf dein Zimmer!")
            Thread.sleep(1000)
            val policeChance: Int = (1..2).random()
            if (policeChance == 1) {
                var counter: Int = 1
                while (counter < 15) {
                    if (counter == 1) println("$counter Minute um!")
                    else println("$counter Minuten um!")
                    Thread.sleep(100)
                    counter++
                }
                successMessage("Du hattest schöne 15 Minuten!")
            } else {
                errorMessage("$intimateServicesSpecialist ist ein Undercover-Cop!")
                if (balance >= 5000) {
                    successMessage("Du hast erfolgreich ein Bestechungsgeld von 5000€ gezahlt!")
                    balance -= 5000.0
                } else {
                    errorMessage("Du hattest nicht genug Geld um den Cop zu bestechen und kommst ins Gefängnis!")
                    exitProcess(0)
                }
            }
        } else println("Heute gibt es keine $intimateServicesSpecialist für dich!")
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