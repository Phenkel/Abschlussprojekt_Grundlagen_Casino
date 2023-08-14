import Globals.*
import Blackjack.*
import HorseRacing.*
import Slots.*
import kotlin.math.round


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

fun loadingScreenAnimation() {
    repeat(3) {
        loadingScreen(RED, GREEN, BLUE)
        Thread.sleep(1000)
        loadingScreen(BLUE, RED, GREEN)
        Thread.sleep(1000)
        loadingScreen(GREEN, BLUE, RED)
        Thread.sleep(1000)
    }
    println("\n\n\n\n\n\n\n\n\n\n")
}

fun buyNewChips() {
    var userInputMoney: Double
    do {
        print("Für wieviel € möchten sie Chips erwerben: ")
        try {
            userInputMoney = readln().toDouble()
        } catch (e: Exception) {
            userInputMoney = 0.0
        }
        userInputMoney = round(userInputMoney * 100) / 100
    } while (userInputMoney == 0.0)
    balance += userInputMoney
    successMessage("Ihr Kauf von Chips im Wert von $userInputMoney€ war erfolgreich! Ihr neuer Kontostand: $balance€\n" +
            "Viel Erfolg beim Spielen!")
}

fun main() {
    var newAtCasino: Boolean = false
    do {
        if (!newAtCasino) {
            loadingScreenAnimation()
            print("Willkommen im Syntax Kotlin Casino! Wie ist ihr Name: ")
            name = readln()
            newAtCasino = true
        } else {
            loadingScreen(RED, GREEN, BLUE)
            Thread.sleep(1000)
            println("\n\n\n\n\n")
            successMessage("Willkommen zurück in der Casino Lobby!")
        }
        var userInputMenu: Int = 0
        if (balance > 0) {
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
                        userInputMenu = 0
                    }
                } catch (e: Exception) {
                    errorMessage("Ungültige Eingabe!")
                }
            } while (userInputMenu == 0)
        } else {
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
                        userInputMenu = 0
                    }
                } catch (e: Exception) {
                    errorMessage("Ungültige Eingabe!")
                }
            } while (userInputMenu == 0)
        }
        when (userInputMenu) {
            1 -> println()
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