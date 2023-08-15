package Slots
import Globals.*

// Die Klasse, die ein Slot-Symbol repräsentiert.
class Symbol(val symbol: Char) {

    // Eigenschaften eines Symbols.
    var isHit: Boolean = false // Gibt an, ob das Symbol getroffen wurde.
    var isLastSpin: Boolean = false // Gibt an, ob das Symbol im letzten Spin war.

    // Funktion, um das Symbol als getroffen zu markieren.
    fun hit() {
        isHit = true
    }

    // Die toString()-Methode, die das Symbol als Zeichenkette zurückgibt.
    override fun toString(): String {
        // Je nach Zustand des Symbols die entsprechende Farbe wählen.
        return if (!isLastSpin) "$BLUE $symbol $RESET" // Standardfarbe für ein Symbol.
        else if (isHit) "$GREEN $symbol $RESET" // Grüne Farbe, wenn das Symbol getroffen wurde.
        else "$RED $symbol $RESET" // Rote Farbe, wenn das Symbol nicht getroffen wurde.
    }
}