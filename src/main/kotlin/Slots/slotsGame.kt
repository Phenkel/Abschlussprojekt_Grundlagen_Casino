package Slots
import Globals.*

var newAtSlotsCheck: Boolean = false
var playAgainUserInput: String = ""

fun banner() {
    println("${GREEN}  #####                                ${BLUE} #####                                ${RED} #####                             \n" +
            "${GREEN} #     # #       ####  #####  ####     ${BLUE}#     # #       ####  #####  ####     ${RED}#     # #       ####  #####  ####  \n" +
            "${GREEN} #       #      #    #   #   #         ${BLUE}#       #      #    #   #   #         ${RED}#       #      #    #   #   #      \n" +
            "${GREEN}  #####  #      #    #   #    ####     ${BLUE} #####  #      #    #   #    ####     ${RED} #####  #      #    #   #    ####  \n" +
            "${GREEN}       # #      #    #   #        #    ${BLUE}      # #      #    #   #        #    ${RED}      # #      #    #   #        # \n" +
            "${GREEN} #     # #      #    #   #   #    #    ${BLUE}#     # #      #    #   #   #    #    ${RED}#     # #      #    #   #   #    # \n" +
            "${GREEN}  #####  ######  ####    #    ####     ${BLUE} #####  ######  ####    #    ####     ${RED} #####  ######  ####    #    ####  \n${RESET}")
}

fun slotsGame(){
    println("\n\n\n\n\n\n\n\n\n\n\n")
    var slots: Slots = Slots()
    do {
        if (!newAtSlotsCheck) {
            banner()
            newAtSlotsCheck = true
            successMessage("Willkommen an der Slotmaschine $name!")
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
            successMessage("Sie werden zurück ins Casino geleitet!")
            Thread.sleep(1000)
            println("\n\n\n")
            break
        }
        println("Ihr aktuelles Guthaben: $balance€")
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
        balance -= bet
        Thread.sleep(1000)
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
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
            Thread.sleep(500)
        } else {
            errorMessage("Verloren! Viel Glück beim nächsten mal.")
            Thread.sleep(500)
        }
        if (balance == 0.0) errorMessage("Sie müssen erst neue Chips erwerben um weiterspielen zu können!")
        Thread.sleep(1000)
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n")
    } while (balance > 0)
}