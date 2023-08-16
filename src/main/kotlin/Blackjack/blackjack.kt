package Blackjack
import Globals.*

// Funktion für den Hauptspielablauf
fun blackjack() {
    println("\n\n\n\n\n\n\n\n\n\n")  // Leere Zeilen für klare Ausgabe

    // Erstellen eines Benutzerspielers
    val userPlayer: UserPlayer = UserPlayer(name)

    do {
        // Startnachricht anzeigen, wenn der Spieler neu am Tisch ist
        if (!newAtTableCheck) {
            startMessage()
            newAtTableCheck = true
        }
        // Oder nachfragen, ob der Spieler für die nächste Runde bereit ist
        else {
            print("Bereit für die nächste Runde? Ja oder Nein: ")
            do {
                val userInputEndGame = readln()
                if (userInputEndGame != "Ja" && userInputEndGame != "Nein") {
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
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

        // Das Spiel beenden, wenn der Spieler das möchte
        if (endGameCheck) {
            endGameCheck = false
            break
        }

        println("Ihr aktuelles Guthaben: $balance€")

        // Wetteinsatz festlegen
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
        balance -= bet
        repeat(30) {
            print(".")
            Thread.sleep(100)
        }
        println()

        // Wenn der Kartenstapel fast leer ist, Kartenstapel wechseln
        if (deck.deck.size <= 156) deck.deckChange()

        // Spielstart: Karten austeilen
        gameStart(userPlayer)

        // Spielerrunde: Entscheidungen treffen
        playerTurn(userPlayer)

        // Wenn der Spieler nicht aufgegeben hat und nicht überkauft ist, folgt die Dealerrunde
        if (!surrenderCheck && !playerBurnedCheck) {
            dealer.dealerTurn()

            // Spielende: Ergebnis auswerten
            gameEnd(userPlayer)
        } else if (surrenderCheck) {
            errorMessage("Aufgegeben! Viel Glück beim nächsten Mal.")
        } else {
            errorMessage("Verloren! Viel Glück beim nächsten Mal.")
        }

        // Trinkgeld geben, wenn das Guthaben positiv ist
        if (balance > 0) {
            print("\nMöchten Sie der Bedienung Trinkgeld geben? Ja oder Nein: ")
            var userInputTip: String = ""
            do {
                userInputTip = readln()
                if (userInputTip != "Ja" && userInputTip != "Nein") {
                    userInputTip = ""
                    errorMessage("Ungültige Eingabe!")
                    wrongUserInput()
                    print("Wählen Sie Ja oder Nein: ")
                }
            } while (userInputTip == "")

            if (userInputTip == "Ja") {
                var userInputTipAmount: Double = 0.0
                println("Ihr aktuelles Guthaben: $balance€")
                do {
                    print("Wie viel möchten Sie als Trinkgeld geben: ")
                    try {
                        userInputTipAmount = readln().toDouble()
                        if (userInputTipAmount > balance) {
                            errorMessage("Guthaben $balance€ nicht ausreichend!")
                            wrongUserInput()
                            userInputTipAmount = 0.0
                        }
                    } catch (e: Exception) {
                        errorMessage("Ungültige Eingabe!")
                        wrongUserInput()
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

        // Wenn das Guthaben noch positiv ist, wird die Spielsituation zurückgesetzt
        if (balance > 0) {
            resetGlobals(userPlayer)
        }

        // Wenn das Guthaben aufgebraucht ist, das Spiel beenden
        if (balance == 0.0) {
            errorMessage("Keine Chips mehr zur Verfügung! Kaufen Sie erst neue Chips.")
            Thread.sleep(1000)
        } else {
            drink()
            pickPocket()
            professionalLovemaking()
            Thread.sleep(1000)
            println("\n\n\n\n\n\n\n\n\n\n\n\n\n")
        }
    } while (balance > 0)
}