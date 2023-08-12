package Slots
import Globals.*

var newAtSlotsCheck: Boolean = false
var playAgainUserInput: String = ""

fun startAndEndMessage(text: String) {
    println("$RED$SPADE_SYMBOL$GREEN$DIAMOND_SYMBOL$RED$HEART_SYMBOL$GREEN$CLOVER_SYMBOL${RESET} $text $RED$CLOVER_SYMBOL$GREEN$HEART_SYMBOL$RED$DIAMOND_SYMBOL$GREEN$SPADE_SYMBOL$RESET")
}

fun slotsGame(): Double {
    var slots: Slots = Slots()
    do {
        if (!newAtSlotsCheck) {
            startAndEndMessage("Willkommen bei den Slots $name!")
            newAtSlotsCheck = true
        } else {
            do {
                print("Möchten sie noch eine Runde spielen? Ja oder Nein: ")
                playAgainUserInput = readln()
                if (playAgainUserInput != "Ja" && playAgainUserInput != "Nein") {
                    errorMessage("Ungültige Eingabe!")
                    playAgainUserInput = ""
                }
            } while (playAgainUserInput == "")
        }
        if (playAgainUserInput == "Nein") {
            startAndEndMessage("Sie werden zurück ins Casino geleitet!")
            repeat(15) {
                Thread.sleep(150)
                print(".")
            }
            println("\n\n\n")
            break
        }
        do {
            print("Wieviel € möchten sie setzen?: ")
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
        print("Drücken sie Enter um an den Slots zu drehen!")
        readln()
        repeat(4) {
            repeat(15) {
                Thread.sleep(150)
                print(".")
            }
            println()
            slots.displayLines()
            slots.resetLines()
        }
        repeat(15) {
            Thread.sleep(150)
            print(".")
        }
        println()
        slots.lineCheck()
        slots.crossCheck()
        slots.displayLines()
        slots.resetLines()
        if (slots.winCheck) {
            successMessage("Gesamtgewinn $bet€! Herzlichen Glückwunsch.")
            balance += bet
            slots.winCheck = false
        } else errorMessage("Verloren! Viel Glück beim nächsten mal.")
    } while (balance > 0)
    if (balance == 0.0) errorMessage("Sie müssen erst neue Chips erwerben um weiterspielen zu können!")
    newAtSlotsCheck = false
    repeat(15) {
        Thread.sleep(150)
        print(".")
    }
    println("\n\n\n")
    return balance
}