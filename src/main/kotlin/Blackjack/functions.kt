package Blackjack

import Globals.*

// Globale Variablen zur Steuerung des Spielverlaufs
var insuranceCheck: Boolean = false
var splitCheck: Boolean = false
var standCheck: Boolean = false
var splitStandCheck: Boolean = false
var surrenderCheck: Boolean = false
var doubleDownCheck: Boolean = false
var newAtTableCheck: Boolean = false
var playerHandValue: Int = 0
var playerSplitHandValue: Int = 0
var dealerHandValue: Int = 0
var playerBurnedCheck: Boolean = false
var endGameCheck: Boolean = false
var tipCounter: Int = 0

// Erstellung des Dealer-Objekts und des Kartendecks
var dealer: Dealer = Dealer("Dealer")
var deck: Deck = Deck()

// Funktion zur Ausgabe des Startbanners
fun startMessage() {
    // Banner für den Spielstart
    println("${BLUE}┌─────────┐ ${RED}┌─────────┐ ${RESET}######                                                   #    # ${RED}┌─────────┐ ${BLUE}┌─────────┐\n" +
            "${BLUE}│ ${Rank.ACE.cardRank}       │ ${RED}│ ${Rank.JACK.cardRank}       │ ${RESET}#     # #        ##    ####  #    #      #   ##    ####  #   #  ${RED}│ ${Rank.JACK.cardRank}       │ ${BLUE}│ ${Rank.ACE.cardRank}       │\n" +
            "${BLUE}│         │ ${RED}│         │ ${RESET}#     # #       #  #  #    # #   #       #  #  #  #    # #  #   ${RED}│         │ ${BLUE}│         │\n" +
            "${BLUE}│    ${SPADE_SYMBOL}    │ ${RED}│    ${HEART_SYMBOL}    │ ${RESET}######  #      #    # #      ####        # #    # #      ###    ${RED}│    ${DIAMOND_SYMBOL}    │ ${BLUE}│    ${CLOVER_SYMBOL}    │\n" +
            "${BLUE}│         │ ${RED}│         │ ${RESET}#     # #      ###### #      #  #        # ###### #      #  #   ${RED}│         │ ${BLUE}│         │\n" +
            "${BLUE}│       ${Rank.ACE.cardRank} │ ${RED}│       ${Rank.JACK.cardRank} │ ${RESET}#     # #      #    # #    # #   #  #    # #    # #    # #   #  ${RED}│       ${Rank.JACK.cardRank} │ ${BLUE}│       ${Rank.ACE.cardRank} │\n" +
            "${BLUE}└─────────┘ ${RED}└─────────┘ ${RESET}######  ###### #    #  ####  #    #  ####  #    #  ####  #    # ${RED}└─────────┘ ${BLUE}└─────────┘\n${RESET}")
}

// Funktion zur Initialisierung des Spielstarts
fun gameStart(player: UserPlayer) {
    println("Startrunde:")
    player.start() // Startet die Startrunde für den Spieler
    repeat(30) {
        print(".")
        Thread.sleep(100)
    }
    println()
    dealer.start() // Startet die Startrunde für den Dealer
    if (tipCounter == 3) {
        println("Um sich für das ganze Trinkgeld zu bedanken, flüstert dir die Bedienung die andere Karte vom Dealer ins Ohr")
        if (dealer.hand.hand.first().rank.cardValue >= dealer.hand.hand.last().rank.cardValue) dealer.hand.hand.last().printCard()
        else dealer.hand.hand.first().printCard()
        tipCounter = 0
    }
    repeat(30) {
        print(".")
        Thread.sleep(100)
    }
    println()
}

// Funktion zur Durchführung des Spielzugs für den Spieler
fun playerTurn(player: UserPlayer) {
    println("Spielerrunde:")
    player.handShow() // Zeigt die Hand des Spielers
    // Menü zur Auswahl der Aktionen
    println("1 - HIT")
    println("2 - STAND")
    println("3 - SURRENDER")
    if (balance >= bet) println("4 - INSURANCE")
    if (balance >= bet) println("5 - DOUBLE DOWN")
    if (player.hand.hand[0].rank == player.hand.hand[1].rank) println("6 - SPLIT")
    var userInputPlayerMenu: Int = 0
    do {
        print("Treffen sie ihre Auswahl: ")
        try {
            userInputPlayerMenu = readln().toInt()
            // Überprüfung der Auswahl basierend auf den Spielregeln und dem Spielerstatus
            if ((player.hand.hand[0].rank != player.hand.hand[1].rank && userInputPlayerMenu == 6) || (balance < bet && (userInputPlayerMenu == 5 || userInputPlayerMenu == 4))) {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputPlayerMenu = 0
            } else if (userInputPlayerMenu < 0 || userInputPlayerMenu > 6) {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputPlayerMenu = 0
            }
        } catch (e: Exception) {
            errorMessage("Ungültige Eingabe!")
            wrongUserInput()
            userInputPlayerMenu = 0
        }
    } while (userInputPlayerMenu == 0)
    repeat(30) {
        print(".")
        Thread.sleep(100)
    }
    println()
    // Abhängig von der Auswahl des Spielers werden die entsprechenden Aktionen durchgeführt
    when (userInputPlayerMenu) {
        1 -> player.hit()
        2 -> standCheck = player.stand()
        3 -> surrenderCheck = player.surrender()
        4 -> insuranceCheck = player.insurance()
        5 -> doubleDownCheck = player.doubleDown()
        6 -> splitCheck = player.split()
    }
    if (standCheck || doubleDownCheck) println("Spielerrunde beendet.")
    else if (surrenderCheck) println("Runde beendet.")
    // Behandlung des Splits
    else if (splitCheck) {
        // Durchführung der Aktionen für die erste Hand
        do {
            player.handShow()
            println("1 - HIT")
            println("2 - STAND")
            print("Treffen sie ihre Auswahl: ")
            try {
                userInputPlayerMenu = readln().toInt()
                if (userInputPlayerMenu != 1 && userInputPlayerMenu != 2) {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    userInputPlayerMenu = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputPlayerMenu = 0
            }
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            // Durchführung der Aktion basierend auf der Spielerwahl
            when (userInputPlayerMenu) {
                1 -> player.hit()
                2 -> standCheck = player.stand()
            }
            if (standCheck) println("Jetzt kommt die Splithand.")
            if (playerHandValue > 21) errorMessage("BURNED!")
        } while (!standCheck && playerHandValue <= 21)
        // Durchführung der Aktionen für die zweite Hand
        do {
            player.splitHandShow()
            println("1 - HIT")
            println("2 - STAND")
            print("Treffen sie ihre Auswahl: ")
            try {
                userInputPlayerMenu = readln().toInt()
                if (userInputPlayerMenu != 1 && userInputPlayerMenu != 2) {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    userInputPlayerMenu = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputPlayerMenu = 0
            }
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            // Durchführung der Aktion basierend auf der Spielerwahl
            when (userInputPlayerMenu) {
                1 -> player.splitHit()
                2 -> splitStandCheck = player.splitStand()
            }
            if (splitStandCheck) println("Spielerrunde beendet.")
            if (playerSplitHandValue > 21 && playerHandValue <= 21) errorMessage("BURNED!")
        } while (!splitStandCheck && playerSplitHandValue <= 21)
    } else {
        // Durchführung der Aktionen für den normalen Spielzug
        while (!standCheck && playerHandValue <= 21) {
            println("1 - HIT")
            println("2 - STAND")
            print("Treffen sie ihre Auswahl: ")
            try {
                userInputPlayerMenu = readln().toInt()
                if (userInputPlayerMenu != 1 && userInputPlayerMenu != 2) {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    userInputPlayerMenu = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                wrongUserInput()
                userInputPlayerMenu = 0
            }
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            // Durchführung der Aktion basierend auf der Spielerwahl
            when (userInputPlayerMenu) {
                1 -> player.hit()
                2 -> standCheck = player.stand()
            }
            if (standCheck) println("Spielerrunde beendet.")
        }
    }
    // Behandlung von Burned-Händen
    if ((!splitCheck && playerHandValue > 21) || (splitCheck && (playerHandValue > 21 && playerSplitHandValue > 21))) {
        errorMessage("BURNED!")
        playerBurnedCheck = true
    }
    repeat(30) {
        print(".")
        Thread.sleep(100)
    }
    println()
}

// Funktion zur Durchführung des Spielendes
fun gameEnd(player: UserPlayer) {
    println()
    // Überprüfung auf Versicherung
    if (insuranceCheck) {
        if ((playerHandValue != dealerHandValue) && !(playerHandValue > 21 && dealerHandValue > 21)) {
            successMessage("Einsatz ${bet * 2}€ geht zurück an den Spieler!")
            balance += bet * 2
        } else {
            errorMessage("Unentschieden! Halber Einsatz $bet€ geht zurück an den Spieler!")
            balance += bet
        }
    }
    // Überprüfung auf Split
    else if (splitCheck) {
        // Auswertung der ersten Hand
        if ((playerHandValue > dealerHandValue && playerHandValue <= 21) || (playerHandValue <= 21 && dealerHandValue > 21)) {
            successMessage("Erste Hand: Gewinn ${bet * 2}€")
            balance += bet * 2
        } else if (playerHandValue == dealerHandValue && playerHandValue <= 21) {
            successMessage("Unentschieden! ${bet}€ geht zurück an den Spieler")
            balance += bet
        } else errorMessage("1. Hand verloren!")
        // Auswertung der zweiten Hand
        if ((playerSplitHandValue > dealerHandValue && playerSplitHandValue <= 21) || (playerSplitHandValue <= 21 && dealerHandValue > 21)) {
            successMessage("Zweite Hand: Gewinn ${bet * 2}€")
            balance += bet * 2
        } else if (playerSplitHandValue == dealerHandValue && playerSplitHandValue <= 21) {
            successMessage("Unentschieden! ${bet}€ geht zurück an den Spieler")
            balance += bet
        } else errorMessage("2. Hand verloren!")
        // Auswertung der Gesamtsituation
        if (((playerHandValue > dealerHandValue && playerHandValue <= 21) || (playerHandValue <= 21 && dealerHandValue > 21)) && ((playerSplitHandValue > dealerHandValue && playerSplitHandValue <= 21) || (playerSplitHandValue <= 21 && dealerHandValue > 21))) {
            successMessage("Gewinn beider Hände: ${bet * 4}€")
        } else if (((playerHandValue > dealerHandValue && playerHandValue <= 21)) || (playerSplitHandValue > dealerHandValue && playerSplitHandValue <= 21)) {
            successMessage("Nur eine Hand gewonnen! Viel Glück beim nächsten Mal.")
        } else if ((playerHandValue == dealerHandValue && playerHandValue <= 21) && (playerSplitHandValue == dealerHandValue && playerSplitHandValue <= 21)) {
            successMessage("Beide Hände unentschieden! Viel Glück beim nächsten Mal")
        } else errorMessage("Runde verloren! Viel Glück beim nächsten Mal.")
    } else {
        // Auswertung des normalen Spielzugs
        if ((playerHandValue <= 21 && playerHandValue > dealerHandValue) || (playerHandValue <= 21 && dealerHandValue > 21)) {
            successMessage("Gewinn ${bet * 2}€")
            balance += bet * 2
        } else if (playerHandValue == dealerHandValue && playerHandValue <= 21) {
            successMessage("Unentschieden! ${bet}€ geht zurück an den Spieler")
            balance += bet
        } else errorMessage("Runde verloren! Viel Glück beim nächsten Mal.")
    }
}

// Funktion zum Zurücksetzen der globalen Variablen nach einer Runde
fun resetGlobals(player: UserPlayer) {
    player.hand.hand.clear()
    player.splitHand.hand.clear()
    dealer.hand.hand.clear()
    insuranceCheck = false
    splitCheck = false
    standCheck = false
    splitStandCheck = false
    surrenderCheck = false
    doubleDownCheck = false
    playerBurnedCheck = false
}