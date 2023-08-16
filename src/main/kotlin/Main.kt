import Globals.*
import Blackjack.*
import HorseRacing.*
import Slots.*
import kotlin.math.round

// Eine Nachricht, die angezeigt wird, wenn der Spieler das Casino verlässt
fun leaveMessage() {
    println("\n\n\n\n\n\n\n\n\n\n")
    println(" ######                                 ${GREEN}#######                  ${BLUE}###                                ${RED}######                                     \n" +
            " ${RESET}#     #   ##   #    # #    # ######    ${GREEN}#       #    # #####     ${BLUE} #  #    # #####  ###### #    #    ${RED}#     # ######  ####  #    #  ####  #    # \n" +
            " ${RESET}#     #  #  #  ##   # #   #  #         ${GREEN}#              #    #    ${BLUE} #  #    # #    # #      ##   #    ${RED}#     # #      #      #    # #    # #    # \n" +
            " ${RESET}#     # #    # # #  # ####   #####     ${GREEN}#####   #    # #    #    ${BLUE} #  ###### #    # #####  # #  #    ${RED}######  #####   ####  #    # #      ###### \n" +
            " ${RESET}#     # ###### #  # # #  #   #         ${GREEN}#       #    # #####     ${BLUE} #  #    # #####  #      #  # #    ${RED}#     # #           # #    # #      #    # \n" +
            " ${RESET}#     # #    # #   ## #   #  #         ${GREEN}#       #    # #   #     ${BLUE} #  #    # #   #  #      #   ##    ${RED}#     # #      #    # #    # #    # #    # \n" +
            " ${RESET}######  #    # #    # #    # ######    ${GREEN}#        ####  #    #    ${BLUE}### #    # #    # ###### #    #    ${RED}######  ######  ####   ####   ####  #    # ")
}

// Eine Ladescreen-Nachricht
fun loadingScreen(color1: String, color2: String, color3: String) {
    println("\n\n\n")
    print(  "$color1 #####                                      $color2#    #                                 $color3 #####                                \n" +
            "$color1#       #   # #    # #####   ##   #    #    $color2#   #   ####  ##### #      # #    #    $color3#     #   ##    ####  # #    #  ####  \n" +
            "$color1#        # #  ##   #   #    #  #   #  #     $color2#  #   #    #   #   #      # ##   #    $color3#        #  #  #      # ##   # #    # \n" +
            "$color1 #####    #   # #  #   #   #    #   ##      $color2###    #    #   #   #      # # #  #    $color3#       #    #  ####  # # #  # #    # \n" +
            "$color1      #   #   #  # #   #   ######   ##      $color2#  #   #    #   #   #      # #  # #    $color3#       ######      # # #  # # #    # \n" +
            "$color1#     #   #   #   ##   #   #    #  #  #     $color2#   #  #    #   #   #      # #   ##    $color3#     # #    # #    # # #   ## #    # \n" +
            "$color1 #####    #   #    #   #   #    # #    #    $color2#    #  ####    #   ###### # #    #    $color3 #####  #    #  ####  # #    #  ####$RESET\n\n\n\n\n")
}

// Animation des Ladescreens
fun loadingScreenAnimation() {
    repeat(2) {
        loadingScreen(RED, GREEN, BLUE)
        Thread.sleep(1000)
        loadingScreen(BLUE, RED, GREEN)
        Thread.sleep(1000)
        loadingScreen(GREEN, BLUE, RED)
        Thread.sleep(1000)
    }
    println("\n\n\n\n\n\n\n\n\n\n")
}

// Funktion zum Erwerb neuer Casinochips
fun buyNewChips() {
    var userInputMoney: Double
    do {
        print("Für wieviel € möchten sie Chips erwerben: ")
        userInputMoney = try {
            readln().toDouble()
        } catch (e: Exception) {
            errorMessage("Ungültige Eingabe!")
            wrongUserInput()
            0.0
        }
        userInputMoney = round(userInputMoney * 100) / 100
    } while (userInputMoney == 0.0)
    balance += userInputMoney
    successMessage("Ihr Kauf von Chips im Wert von $userInputMoney€ war erfolgreich! Ihr neuer Kontostand: $balance€\n" +
            "Viel Erfolg beim Spielen!")
    Thread.sleep(1000)
}

// Die Hauptfunktion des Programms
fun main() {
    var newAtCasino: Boolean = false
    do {
        if (!newAtCasino) {
            // Willkommensanimation und Name des Spielers abfragen
            loadingScreenAnimation()
            print("Willkommen im Syntax Kotlin Casino! Wie ist ihr Name: ")
            name = readln()
            newAtCasino = true
        } else {
            // Ladescreen und Nachricht für den Rückkehr des Spielers
            loadingScreen(RED, GREEN, BLUE)
            Thread.sleep(1000)
            println("\n\n\n\n\n")
            successMessage("Willkommen zurück in der Casino Lobby!")
        }

        var userInputMenu: Int = 0

        if (balance > 0) {
            // Menü für den Spieler anzeigen, wenn Guthaben vorhanden ist
            do {
                println("Ihr Guthaben beträgt: $balance€\n" +
                        "Ihre Möglichkeiten:\n" +
                        "${BLUE}1 - Neue Casinochips erwerben\n" +
                        "${GREEN}2 - Blackjack\n" +
                        "${GREEN}3 - Pferderennen\n" +
                        "${GREEN}4 - Slots\n" +
                        "${RED}5 - Casino verlassen${RESET}")
                try {
                    print("Wählen sie aus: ")
                    userInputMenu = readln().toInt()
                    if (userInputMenu <1 || userInputMenu > 5) {
                        errorMessage("Ungültige Eingabe!")
                        wrongUserInput()
                        userInputMenu = 0
                    }
                } catch (e: Exception) {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    userInputMenu = 0
                }
            } while (userInputMenu == 0)
        } else {
            // Menü für den Spieler anzeigen, wenn kein Guthaben vorhanden ist
            do {
                println("Ihr Guthaben beträgt: $balance€\n" +
                        "Ihre Möglichkeiten:\n" +
                        "${BLUE}1 - Neue Casinochips erwerben\n" +
                        "${RED}5 - Casino verlassen${RESET}")
                try {
                    print("Wählen sie aus: ")
                    userInputMenu = readln().toInt()
                    if (userInputMenu != 1 && userInputMenu != 5) {
                        errorMessage("Ungültige Eingabe!")
                        wrongUserInput()
                        userInputMenu = 0
                    }
                } catch (e: Exception) {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    userInputMenu = 0
                }
            } while (userInputMenu == 0)
        }

        when (userInputMenu) {
            1 -> buyNewChips()
            2 -> blackjack()
            3 -> horseRacing()
            4 -> slotsGame()
            5 -> leaveCasino = true
        }

        if (leaveCasino) {
            leaveMessage()
        }
    } while (!leaveCasino)
}
