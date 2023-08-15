package Slots
import Globals.*

// Globale Variablen für das Slots-Modul.
var newAtSlotsCheck: Boolean = false // Gibt an, ob der Spieler neu im Slot-Spiel ist.
var playAgainUserInput: String = "" // Gibt die Eingabe des Spielers für eine weitere Runde an.

// Funktion, um den Slotmaschinen-Banner auszugeben.
fun banner() {
    println("${GREEN}  #####                                ${BLUE} #####                                ${RED} #####                             \n" +
            "${GREEN} #     # #       ####  #####  ####     ${BLUE}#     # #       ####  #####  ####     ${RED}#     # #       ####  #####  ####  \n" +
            "${GREEN} #       #      #    #   #   #         ${BLUE}#       #      #    #   #   #         ${RED}#       #      #    #   #   #      \n" +
            "${GREEN}  #####  #      #    #   #    ####     ${BLUE} #####  #      #    #   #    ####     ${RED} #####  #      #    #   #    ####  \n" +
            "${GREEN}       # #      #    #   #        #    ${BLUE}      # #      #    #   #        #    ${RED}      # #      #    #   #        # \n" +
            "${GREEN} #     # #      #    #   #   #    #    ${BLUE}#     # #      #    #   #   #    #    ${RED}#     # #      #    #   #   #    # \n" +
            "${GREEN}  #####  ######  ####    #    ####     ${BLUE} #####  ######  ####    #    ####     ${RED} #####  ######  ####    #    ####  \n${RESET}")
}

// Funktion, die den gesamten Ablauf des Slot-Spiels steuert.
fun slotsGame() {
    println("\n\n\n\n\n\n\n\n\n\n\n")
    var slots: Slots = Slots()
    do {
        // Überprüfen, ob der Spieler neu im Slot-Spiel ist.
        if (!newAtSlotsCheck) {
            banner()
            newAtSlotsCheck = true
            successMessage("Willkommen an der Slotmaschine $name!")
        } else {
            // Abfrage, ob der Spieler eine weitere Runde spielen möchte.
            do {
                print("Möchten Sie noch eine Runde spielen? Ja oder Nein: ")
                playAgainUserInput = readln()
                if (playAgainUserInput != "Ja" && playAgainUserInput != "Nein") {
                    errorMessage("Ungültige Eingabe!")
                    playAgainUserInput = ""
                }
            } while (playAgainUserInput == "")
        }

        // Wenn der Spieler nicht mehr spielen möchte.
        if (playAgainUserInput == "Nein") {
            successMessage("Sie werden zurück ins Casino geleitet!")
            Thread.sleep(1000)
            println("\n\n\n")
            break
        }

        // Aktuelles Guthaben anzeigen und Wetteinsatz setzen.
        println("Ihr aktuelles Guthaben: $balance€")
        do {
            print("Wie viel € möchten Sie setzen?: ")
            try {
                bet = readln().toDouble()
                if (bet > balance) {
                    errorMessage("Sie haben nur noch $balance€ zur Verfügung!")
                    bet = 0.0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                bet = 0.0
            }
        } while (bet == 0.0)

        balance -= bet // Wetteinsatz vom Guthaben abziehen.
        Thread.sleep(1000)
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
        print("Drücken Sie Enter, um an den Slots zu drehen!")
        readln()

        // Animation des Slot-Spins.
        repeat(4) {
            repeat(15) {
                Thread.sleep(150)
                print(".")
            }
            println()
            slots.displayLines()
            slots.resetLines()
        }

        // Ergebnis des Slot-Spins überprüfen und anzeigen.
        repeat(15) {
            Thread.sleep(150)
            print(".")
        }
        println()
        slots.lineCheck()
        slots.crossCheck()
        slots.displayLines()
        slots.resetLines()

        // Gewinn oder Verlust anzeigen und Guthaben aktualisieren.
        if (slots.winCheck) {
            successMessage("Gesamtgewinn $bet€! Herzlichen Glückwunsch.")
            balance += bet
            slots.winCheck = false
            Thread.sleep(500)
        } else {
            errorMessage("Verloren! Viel Glück beim nächsten Mal.")
            Thread.sleep(500)
        }

        // Überprüfen, ob das Guthaben aufgebraucht ist.
        if (balance == 0.0) {
            errorMessage("Sie müssen erst neue Chips erwerben, um weiterspielen zu können!")
        }

        Thread.sleep(1000)
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n")
    } while (balance > 0)
}