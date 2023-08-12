package Blackjack
import Globals.*

fun blackjack() {
    val userPlayer: UserPlayer = UserPlayer(name)
    // Die Schleife läuft solange, bis das Guthaben des Spielers auf 0 fällt
    do {
        // Begrüßungsnachricht, nur einmal beim ersten Mal am Tisch
        if (!newAtTableCheck) {
            startMessage()
            newAtTableCheck = true
        }
        // Nachfragen, ob der Spieler eine weitere Runde spielen möchte oder das Spiel beenden möchte
        else {
            print("Bereit für die nächste Runde? Ja oder Nein: ")
            do {
                val userInputEndGame = readln()
                if (userInputEndGame != "Ja" && userInputEndGame != "Nein") {
                    errorMessage("Ungültige Eingabe!")
                    print("Bitte wählen Sie Ja oder Nein: ")
                } else if (userInputEndGame == "Nein") {
                    successMessage("Sie werden zurück in das Casino geleitet!")
                    endGameCheck = true
                    newAtTableCheck = false
                } else {
                    successMessage("Dann machen Sie sich bereit, es geht los!")
                }
            } while (userInputEndGame != "Ja" && userInputEndGame != "Nein")
        }
        // Wenn der Spieler das Spiel beenden möchte, wird die Schleife beendet
        if (endGameCheck) break

        // Guthaben des Spielers anzeigen und Wetten platzieren
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
        // Reduziere das Guthaben des Spielers um den Wetteinsatz
        balance -= bet
        repeat(30) {
            print(".")
            Thread.sleep(100)
        }
        println()

        // Wenn der Kartendeck fast aufgebraucht ist, wird ein neues Kartendeck erstellt und gemischt
        if (deck.deck.size <= 156) deck.deckChange()

        // Beginne die Spielrunde
        gameStart(userPlayer)
        playerTurn(userPlayer)

        // Wenn der Spieler nicht aufgegeben hat und nicht "burned" ist, führt der Dealer seine Runde aus
        if (!surrenderCheck && !playerBurnedCheck) {
            dealer.dealerTurn()
            dealerHandValue = dealer.handValue()
            // Bewertung der Ergebnisse der Runde
            gameEnd(userPlayer)
        } else if (surrenderCheck) {
            errorMessage("Aufgegeben! Viel Glück beim nächsten Mal.")
        } else {
            errorMessage("Verloren! Viel Glück beim nächsten Mal.")
        }

        // Abfrage, ob der Spieler der Bedienung Trinkgeld geben möchte
        if (balance > 0) {
            print("\nMöchten Sie der Bedienung Trinkgeld geben? Ja oder Nein: ")
            var userInputTip: String = ""
            do {
                userInputTip = readln()
                if (userInputTip != "Ja" && userInputTip != "Nein") {
                    userInputTip = ""
                    print("Wählen Sie Ja oder Nein: ")
                }
            } while (userInputTip == "")

            // Wenn der Spieler Trinkgeld gibt, wird der Betrag vom Guthaben abgezogen
            if (userInputTip == "Ja") {
                var userInputTipAmount: Double = 0.0
                println("Ihr aktuelles Guthaben: $balance€")
                do {
                    print("Wie viel möchten Sie als Trinkgeld geben: ")
                    try {
                        userInputTipAmount = readln().toDouble()
                        if (userInputTipAmount > balance) {
                            errorMessage("Guthaben $balance€ nicht ausreichend!")
                            userInputTipAmount = 0.0
                        }
                    } catch (e: Exception) {
                        errorMessage("Ungültige Eingabe!")
                        userInputTipAmount = 0.0
                    }
                } while (userInputTipAmount == 0.0)
                balance -= userInputTipAmount
                successMessage("Die Bedienung bedankt sich für das Trinkgeld!")
                tipCounter++
            } else {
                errorMessage("Sehr unhöflich, kein Trinkgeld zu geben!")
            }
        }

        if (balance > 0) {
            resetGlobals(userPlayer)
            Thread.sleep(1000)
            println("\n\n\n\n\n\n\n\n\n\n\n\n\n")
        }

        // Wenn das Guthaben des Spielers auf 0 fällt, wird das Spiel beendet
        if (balance == 0.0) errorMessage("Keine Chips mehr zur Verfügung! Kaufen Sie erst neue Chips.")
    } while (balance > 0)
}