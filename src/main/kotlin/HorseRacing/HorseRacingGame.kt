package HorseRacing
import Globals.*

val BROWN = "\u001B[33m"
val horseRace: HorseRace = HorseRace()
var newAtHorseRacing: Boolean = false
var leaveTrack: Boolean = false

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

fun main() {
    do {
        if (!newAtHorseRacing) {
            horseRacingBanner()
            newAtHorseRacing = true
            Thread.sleep(1000)
            println("Willkommen beim Pferderennen $name!")
        } else {
            print("Bereit für die nächste Runde? Ja oder Nein: ")
            do {
                val userInputEndGame = readln()
                if (userInputEndGame != "Ja" && userInputEndGame != "Nein") {
                    errorMessage("Ungültige Eingabe!")
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
        if (leaveTrack) break
        println("Ihr aktuelles Guthaben: $balance€")
        do {
            try {
                print("Geben Sie Ihren Wetteinsatz an: ")
                bet = readln().toDouble()
                if (bet > balance) {
                    bet = 0.0
                    errorMessage("Guthaben $balance nicht ausreichend!")
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe! Format 0.00")
                bet = 0.0
            }
        } while (bet == 0.0)
        balance -= bet
        println("Auf welches Pferd möchten sie wetten?")
        var userInputHorse: Int = 0
        do {
            print("1, 2, 3 oder 4: ")
            try {
                userInputHorse = readln().toInt()
                if (userInputHorse < 1 || userInputHorse > 4) {
                    errorMessage("Ungültige Eingabe!")
                    userInputHorse = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                userInputHorse = 0
            }
        } while (userInputHorse == 0)
        println("Viel Erfolg!")
        Thread.sleep(1000)
        println()
        val winningHorse = horseRace.race()
        Thread.sleep(1000)
        println()
        if (userInputHorse == winningHorse) {
            successMessage("Herzlichen Glückwunsch! Du hast ${bet * 4}€ gewonnen!")
            balance += bet * 4
        } else errorMessage("Verloren! Viel Glück beim nächsten mal.")
        if (balance == 0.0) {
            errorMessage("Keine Chips mehr zur Verfügung! Kaufen Sie erst neue Chips.")
            Thread.sleep(1000)
            println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
        }
        if (balance > 0) Thread.sleep(1000)
    } while (balance > 0.0)
}