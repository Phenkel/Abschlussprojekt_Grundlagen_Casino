package HorseRacing
import Globals.*

// Eine zusätzliche Farbkonstante für die Konsolenausgabe.
val BROWN = "\u001B[33m"

// Instanz der HorseRace-Klasse erstellen.
val horseRace: HorseRace = HorseRace()

// Globale Variablen für das Pferderennen-Modul.
var newAtHorseRacing: Boolean = false // Wird verwendet, um zu überprüfen, ob der Spieler neu im Pferderennen ist.
var leaveTrack: Boolean = false // Wird verwendet, um zu überprüfen, ob der Spieler die Rennbahn verlassen möchte.

// Funktion, um den Pferderennen-Banner auszugeben.
fun horseRacingBanner() {
    println("\n\n\n\n\n\n\n\n\n\n" +
            "                   ${BROWN};;\n" +
            "                 ,;;'\\\n" +
            "      __       ,;;' ' \\   ${RESET}#     #                             ######                                \n" +
            "    ${BROWN}/'  '${GREEN}\\'~~'~' \\${BROWN} /'\\.)  ${RESET}#     #  ####  #####   ####  ###### #     #   ##    ####  # #    #  ####  \n" +
            " ${BROWN},;(      ${GREEN})    /${BROWN}  |       ${RESET}#     # #    # #    # #      #      #     #  #  #  #    # # ##   # #    # \n" +
            "${BROWN},;' \\    ${GREEN}/-.,,(${BROWN}   )       ${RESET}####### #    # #    #  ####  #####  ######  #    # #      # # #  # #      \n" +
            "     ${BROWN}) /|      ) /|       ${RESET}#     # #    # #####       # #      #   #   ###### #      # #  # # #  ### \n" +
            "     ${BROWN}||(_\\     ||(_\\      ${RESET}#     # #    # #   #  #    # #      #    #  #    # #    # # #   ## #    # \n" +
            "     ${BROWN}(_\\       (_\\        ${RESET}#     #  ####  #    #  ####  ###### #     # #    #  ####  # #    #  ####  \n")
}

// Funktion, die den gesamten Ablauf des Pferderennens steuert.
fun horseRacing() {
    do {
        // Überprüfen, ob der Spieler neu im Pferderennen ist.
        if (!newAtHorseRacing) {
            horseRacingBanner()
            newAtHorseRacing = true
            Thread.sleep(1000)
            successMessage("Willkommen beim Pferderennen $name!")
        } else {
            // Spieler fragen, ob er für die nächste Runde bereit ist.
            print("Bereit für die nächste Runde? Ja oder Nein: ")
            do {
                val userInputEndGame = readln()
                if (userInputEndGame != "Ja" && userInputEndGame != "Nein") {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    print("Bitte wählen Sie Ja oder Nein: ")
                } else if (userInputEndGame == "Nein") {
                    successMessage("Sie werden zurück in das Casino geleitet!")
                    leaveTrack = true
                    newAtHorseRacing = false
                    Thread.sleep(1000)
                    println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
                } else {
                    successMessage("Dann machen Sie sich bereit, es geht los!")
                    Thread.sleep(1000)
                    println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
                }
            } while (userInputEndGame != "Ja" && userInputEndGame != "Nein")
        }
        if (leaveTrack) {
            leaveTrack = false
            break
        }

        // Wettinformationen vom Spieler erhalten.
        println("Ihr aktuelles Guthaben: $balance€")
        do {
            try {
                print("Geben Sie Ihren Wetteinsatz an: ")
                bet = readln().toDouble()
                if (bet > balance) {
                    bet = 0.0
                    errorMessage("Guthaben $balance nicht ausreichend!")
                    wrongUserInput()
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe! Format 0.00")
                wrongUserInput()
                bet = 0.0
            }
        } while (bet == 0.0)

        balance -= bet // Wetteinsatz vom Guthaben abziehen
        println("Auf welches Pferd möchten Sie wetten?")
        var userInputHorse: Int = 0
        do {
            print("1, 2, 3 oder 4: ")
            try {
                userInputHorse = readln().toInt()
                if (userInputHorse < 1 || userInputHorse > 4) {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    userInputHorse = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputHorse = 0
            }
        } while (userInputHorse == 0)

        successMessage("Viel Erfolg!")
        Thread.sleep(1000)
        println()

        // Pferderennen durchführen und Gewinner ermitteln.
        val winningHorse = horseRace.race()
        Thread.sleep(1000)
        println()

        // Ergebnis anzeigen und Guthaben aktualisieren.
        if (userInputHorse == winningHorse) {
            successMessage("Herzlichen Glückwunsch! Du hast ${bet * 4}€ gewonnen!")
            balance += bet * 4
        } else {
            errorMessage("Verloren! Viel Glück beim nächsten Mal.")
        }

        // Prüfen, ob das Guthaben aufgebraucht ist.
        if (balance == 0.0) {
            errorMessage("Keine Chips mehr verfügbar! Kaufen Sie erst neue Chips.")
            Thread.sleep(1000)
            println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
        } else {
            drink()
            pickPocket()
            println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
        }

        // Eine Pause einlegen, wenn das Guthaben noch vorhanden ist.
        if (balance > 0) Thread.sleep(1000)
    } while (balance > 0.0)
}