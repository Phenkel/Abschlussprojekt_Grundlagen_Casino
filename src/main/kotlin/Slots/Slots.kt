package Slots
import Globals.*

// Die Klasse, die das Slot-Spiel repräsentiert.
class Slots() {

    // Eigenschaften des Slot-Spiels.
    val symbols: List<Char> = listOf(SPADE_SYMBOL, DIAMOND_SYMBOL, HEART_SYMBOL, CLOVER_SYMBOL) // Liste der Slot-Symbole.
    val lines = MutableList(3) { MutableList(3) { Symbol(symbols.random()) } } // 3x3 Gitter für die Slot-Linien.
    var winCheck: Boolean = false // Gibt an, ob eine Gewinnkombination erzielt wurde.

    // Funktion, um die aktuellen Slot-Linien anzuzeigen.
    fun displayLines() {
        var counter: Int = 1
        println("┌─────────────────┐" + "    |")
        for (line in lines) {
            if (counter == 2) println("| " + line.joinToString(" | ") + " |" + "────┘") // Jede Zeile wird als Zeichenkette mit Trennzeichen angezeigt.
            else if (counter == 1) println("| " + line.joinToString(" | ") + " |" + "    |") // Jede Zeile wird als Zeichenkette mit Trennzeichen angezeigt.
            else println("| " + line.joinToString(" | ") + " |") // Jede Zeile wird als Zeichenkette mit Trennzeichen angezeigt.
            counter ++
        }
        println("${RESET}└─────────────────┘")
    }

    // Funktion, um die Slot-Linien zurückzusetzen und neue Symbole zuzuweisen.
    fun resetLines() {
        lines.forEach { it.clear() } // Alle Symbole in den Linien löschen.
        for (line in lines) {
            repeat(3) {
                line.add(Symbol(symbols.random())) // Neue zufällige Symbole den Linien zuweisen.
            }
        }
    }

    // Funktion, um die Slot-Linien auf Gewinnkombinationen zu überprüfen.
    fun lineCheck() {
        for (line in lines) {
            line.forEach { it.isLastSpin = true } // Alle Symbole in der Linie als im letzten Spin markieren.
            if (line[0].symbol == line[1].symbol && line[1].symbol == line[2].symbol) {
                line.forEach { it.hit() } // Alle Symbole in der Linie als getroffen markieren.
                bet *= 3 // Den Einsatz erhöhen, da eine Gewinnkombination erzielt wurde.
                winCheck = true
            }
        }
    }

    // Funktion, um diagonale Gewinnkombinationen zu überprüfen.
    fun crossCheck() {
        if (lines[0][0].symbol == lines[1][1].symbol && lines[1][1].symbol == lines[2][2].symbol) {
            lines[0][0].hit()
            lines[1][1].hit()
            lines[2][2].hit()
            bet *= 3
            winCheck = true
        }
        if (lines[0][2].symbol == lines[1][1].symbol && lines[1][1].symbol == lines[2][0].symbol) {
            lines[0][2].hit()
            lines[1][1].hit()
            lines[2][0].hit()
            bet *= 3
            winCheck = true
        }
    }
}