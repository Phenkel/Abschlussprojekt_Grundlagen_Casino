package Blackjack
import Globals.*

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

var dealer: Dealer = Dealer("Dealer")
var deck: Deck = Deck()

// Funktion zum Anzeigen von Start- und Endmeldungen mit farbigen Symbolen
fun startMessage() {
    println("${BLUE}┌─────────┐ ${RED}┌─────────┐ ${RESET}######                                                   #    # ${RED}┌─────────┐ ${BLUE}┌─────────┐\n" +
            "${BLUE}│ ${Rank.ACE.cardRank}       │ ${RED}│ ${Rank.JACK.cardRank}       │ ${RESET}#     # #        ##    ####  #    #      #   ##    ####  #   #  ${RED}│ ${Rank.JACK.cardRank}       │ ${BLUE}│ ${Rank.ACE.cardRank}       │\n" +
            "${BLUE}│         │ ${RED}│         │ ${RESET}#     # #       #  #  #    # #   #       #  #  #  #    # #  #   ${RED}│         │ ${BLUE}│         │\n" +
            "${BLUE}│    ${SPADE_SYMBOL}    │ ${RED}│    ${HEART_SYMBOL}    │ ${RESET}######  #      #    # #      ####        # #    # #      ###    ${RED}│    ${DIAMOND_SYMBOL}    │ ${BLUE}│    ${CLOVER_SYMBOL}    │\n" +
            "${BLUE}│         │ ${RED}│         │ ${RESET}#     # #      ###### #      #  #        # ###### #      #  #   ${RED}│         │ ${BLUE}│         │\n" +
            "${BLUE}│       ${Rank.ACE.cardRank} │ ${RED}│       ${Rank.JACK.cardRank} │ ${RESET}#     # #      #    # #    # #   #  #    # #    # #    # #   #  ${RED}│       ${Rank.JACK.cardRank} │ ${BLUE}│       ${Rank.ACE.cardRank} │\n" +
            "${BLUE}└─────────┘ ${RED}└─────────┘ ${RESET}######  ###### #    #  ####  #    #  ####  #    #  ####  #    # ${RED}└─────────┘ ${BLUE}└─────────┘\n${RESET}")
}

// Funktion, um eine neue Spielrunde zu starten
fun gameStart(player: UserPlayer) {
    println("Startrunde:")
    player.start() // Startet die Spielerrunde für den Benutzer
    repeat(30) {
        print(".")
        Thread.sleep(100)
    }
    println()
    dealer.start() // Startet die Dealer-Runde
    if (tipCounter == 3) {
        println("Um sich für das ganze Trinkgeld zu bedanken, flüstert dir die Bedienung die andere Karte vom Dealer ins Ohr")
        // Zeigt die verdeckte Karte des Dealers
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

// Funktion für die Spielerrunde
fun playerTurn(player: UserPlayer) {
    println("Spielerrunde:")
    player.handShow() // Zeigt die Hand des Spielers an
    println("1 - HIT")
    println("2 - STAND")
    println("3 - SURRENDER")
    if (balance >= bet) println("4 - INSURANCE")
    if (balance >= bet) println("5 - DOUBLE DOWN")
    // Prüft, ob eine Split-Option für den Spieler verfügbar ist
    if (player.hand.hand[0].rank == player.hand.hand[1].rank) println("6 - SPLIT")
    var userInputPlayerMenu: Int = 0
    // Schleife zur Benutzereingabe für die Aktionen des Spielers
    do {
        print("Treffen sie ihre Auswahl: ")
        try {
            userInputPlayerMenu = readln().toInt()
            // Überprüft die Gültigkeit der Benutzereingabe basierend auf den verfügbaren Optionen
            if ((player.hand.hand[0].rank != player.hand.hand[1].rank && userInputPlayerMenu == 6) || (balance < bet && (userInputPlayerMenu == 5 || userInputPlayerMenu == 4))) {
                errorMessage("Ungültige Eingabe!")
                userInputPlayerMenu = 0
            } else if (userInputPlayerMenu < 0 || userInputPlayerMenu > 6) {
                errorMessage("Ungültige Eingabe!")
                userInputPlayerMenu = 0
            }
        } catch (e: Exception) {
            errorMessage("Ungültige Eingabe!")
            userInputPlayerMenu = 0
        }
    } while (userInputPlayerMenu == 0)
    repeat(30) {
        print(".")
        Thread.sleep(100)
    }
    println()
    // Abhängig von der Benutzereingabe werden die entsprechenden Aktionen für den Spieler ausgeführt
    when (userInputPlayerMenu) {
        1 -> {
            player.hit()
            playerHandValue = player.hand.handValue()
        }
        2 -> {
            standCheck = player.stand()
            playerHandValue = player.hand.handValue()
        }
        3 -> surrenderCheck = player.surrender()
        4 -> insuranceCheck = player.insurance()
        5 -> doubleDownCheck = player.doubleDown()
        6 -> splitCheck = player.split()
    }
    // Überprüft, ob die Spielerrunde beendet ist oder weitere Aktionen erforderlich sind
    if (standCheck || doubleDownCheck) println("Spielerrunde beendet.")
    else if (surrenderCheck) println("Runde beendet.")
    // Wenn der Spieler seine Hand gesplittet hat
    else if (splitCheck) {
        do {
            player.handShow() // Zeigt die Hand des Spielers an
            println("1 - HIT")
            println("2 - STAND")
            print("Treffen sie ihre Auswahl: ")
            try {
                userInputPlayerMenu = readln().toInt()
                // Überprüft die Gültigkeit der Benutzereingabe für die gesplittete Hand
                if (userInputPlayerMenu != 1 && userInputPlayerMenu != 2) {
                    errorMessage("Ungültige Eingabe!")
                    userInputPlayerMenu = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                userInputPlayerMenu = 0
            }
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            // Entsprechend der Benutzereingabe werden die Aktionen für die gesplittete Hand ausgeführt
            when (userInputPlayerMenu) {
                1 -> player.hit()
                2 -> standCheck = player.stand()
            }
            // Überprüft, ob die Spielerhand beendet ist oder weitere Aktionen erforderlich sind
            if (standCheck) println("Jetzt kommt die Splithand.")
            playerHandValue = player.hand.handValue()
            // Überprüft, ob die Spielerhand "burned" ist (über 21)
            if (playerHandValue > 21) errorMessage("BURNED!")
        } while (!standCheck && playerHandValue <= 21)
        // Wenn die Spielerhand beendet ist, beginnt die Runde für die gesplittete Hand
        do {
            player.splitHandShow() // Zeigt die Hand der gesplitteten Hand an
            println("1 - HIT")
            println("2 - STAND")
            print("Treffen sie ihre Auswahl: ")
            try {
                userInputPlayerMenu = readln().toInt()
                // Überprüft die Gültigkeit der Benutzereingabe für die gesplittete Hand
                if (userInputPlayerMenu != 1 && userInputPlayerMenu != 2) {
                    errorMessage("Ungültige Eingabe!")
                    userInputPlayerMenu = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                userInputPlayerMenu = 0
            }
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            // Entsprechend der Benutzereingabe werden die Aktionen für die gesplittete Hand ausgeführt
            when (userInputPlayerMenu) {
                1 -> player.splitHit()
                2 -> splitStandCheck = player.splitStand()
            }
            // Überprüft, ob die gesplittete Hand beendet ist oder weitere Aktionen erforderlich sind
            if (splitStandCheck) println("Spielerrunde beendet.")
            playerSplitHandValue = player.splitHand.handValue()
            // Überprüft, ob die gesplittete Hand "burned" ist (über 21)
            if (playerSplitHandValue > 21 && playerHandValue <= 21) errorMessage("BURNED!")
        } while (!splitStandCheck && playerSplitHandValue <= 21)
    }
    // Wenn keine gesplittete Hand vorhanden ist
    else {
        while (!standCheck && playerHandValue <= 21) {
            println("1 - HIT")
            println("2 - STAND")
            print("Treffen sie ihre Auswahl: ")
            try {
                userInputPlayerMenu = readln().toInt()
                // Überprüft die Gültigkeit der Benutzereingabe für die Hand des Spielers
                if (userInputPlayerMenu != 1 && userInputPlayerMenu != 2) {
                    errorMessage("Ungültige Eingabe!")
                    userInputPlayerMenu = 0
                }
            } catch (e: Exception) {
                errorMessage("Ungültige Eingabe!")
                userInputPlayerMenu = 0
            }
            repeat(30) {
                print(".")
                Thread.sleep(100)
            }
            println()
            // Entsprechend der Benutzereingabe werden die Aktionen für die Hand des Spielers ausgeführt
            when (userInputPlayerMenu) {
                1 -> player.hit()
                2 -> standCheck = player.stand()
            }
            // Überprüft, ob die Spielerhand beendet ist oder weitere Aktionen erforderlich sind
            if (standCheck) println("Spielerrunde beendet.")
            playerHandValue = player.hand.handValue()
        }
    }
    // Überprüft, ob die Spielerhand oder die gesplittete Hand "burned" ist (über 21)
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

// Funktion für das Ende des Spiels und die Auswertung des Ergebnisses
fun gameEnd(player: UserPlayer) {
    println()
    // Überprüft, ob eine Versicherung abgeschlossen wurde
    if (insuranceCheck) {
        // Überprüft, ob der Spieler die Versicherung gewinnt oder verliert
        if (playerHandValue <= 21 && playerHandValue != dealerHandValue) {
            successMessage("Einsatz ${bet * 2}€ geht zurück an den Spieler!")
            balance += bet * 2
        } else errorMessage("Runde verloren! Viel Glück beim nächsten Mal.")
    }
    // Überprüft, ob eine Hand gesplittet wurde
    else if (splitCheck) {
        // Auswertung der Ergebnisse für die beiden gesplitteten Hände
        if ((playerHandValue > dealerHandValue && playerHandValue <= 21) || (playerHandValue <= 21 && dealerHandValue > 21)) {
            successMessage("Erste Hand: Gewinn ${bet * 2}€")
            balance += bet * 2
        } else if (playerHandValue == dealerHandValue && playerHandValue <= 21) {
            successMessage("Unentschieden! ${bet}€ geht zurück an den Spieler")
            balance += bet
        } else errorMessage("1. Hand verloren!")
        if ((playerSplitHandValue > dealerHandValue && playerSplitHandValue <= 21) || (playerSplitHandValue <= 21 && dealerHandValue > 21)) {
            successMessage("Zweite Hand: Gewinn ${bet * 2}€")
            balance += bet * 2
        } else if (playerSplitHandValue == dealerHandValue && playerSplitHandValue <= 21) {
            successMessage("Unentschieden! ${bet}€ geht zurück an den Spieler")
            balance += bet
        } else errorMessage("2. Hand verloren!")
        // Auswertung der Gesamtergebnisse für beide gesplitteten Hände
        if (((playerHandValue > dealerHandValue && playerHandValue <= 21) || (playerHandValue <= 21 && dealerHandValue > 21)) && ((playerSplitHandValue > dealerHandValue && playerSplitHandValue <= 21) || (playerSplitHandValue <= 21 && dealerHandValue > 21))) {
            successMessage("Gewinn beider Hände: ${bet * 4}€")
        } else if (((playerHandValue > dealerHandValue && playerHandValue <= 21)) || (playerSplitHandValue > dealerHandValue && playerSplitHandValue <= 21)) {
            successMessage("Nur eine Hand gewonnen! Viel Glück beim nächsten Mal.")
        } else if ((playerHandValue == dealerHandValue && playerHandValue <= 21) && (playerSplitHandValue == dealerHandValue && playerSplitHandValue <= 21)) {
            successMessage("Beide Hände unentschieden! Viel Glück beim nächsten Mal")
        } else errorMessage("Runde verloren! Viel Glück beim nächsten Mal.")
    }
    // Wenn keine Hand gesplittet wurde
    else {
        // Auswertung des Ergebnisses für die Hand des Spielers
        if ((playerHandValue <= 21 && playerHandValue > dealerHandValue) || (playerHandValue <= 21 && dealerHandValue > 21)) {
            successMessage("Gewinn ${bet * 2}€")
            balance += bet * 2
        } else if (playerHandValue == dealerHandValue && playerHandValue <= 21) {
            successMessage("Unentschieden! ${bet}€ geht zurück an den Spieler")
            balance += bet
        } else errorMessage("Runde verloren! Viel Glück beim nächsten Mal.")
    }
}

// Funktion zum Zurücksetzen aller globalen Variablen für eine neue Spielrunde
fun resetGlobals(player: UserPlayer) {
    player.hand.hand.clear() // Leert die Hand des Spielers
    player.splitHand.hand.clear() // Leert die gesplittete Hand des Spielers
    dealer.hand.hand.clear() // Leert die Hand des Dealers
    insuranceCheck = false // Setzt die Versicherung-Flag auf false zurück
    splitCheck = false // Setzt die Split-Flag auf false zurück
    standCheck = false // Setzt die Stand-Flag auf false zurück
    splitStandCheck = false // Setzt die Split-Stand-Flag auf false zurück
    surrenderCheck = false // Setzt die Surrender-Flag auf false zurück
    doubleDownCheck = false // Setzt die Double-Down-Flag auf false zurück
    playerBurnedCheck = false // Setzt die Burned-Flag auf false zurück
}