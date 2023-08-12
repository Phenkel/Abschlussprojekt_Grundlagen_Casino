package Slots
import Globals.*


class Symbol(val symbol: Char) {

    var isHit: Boolean = false
    var isLastSpin: Boolean = false

    fun hit() {
        isHit = true
    }

    override fun toString(): String {
        return if (!isLastSpin) "$BLUE $symbol $RESET"
        else if (isHit) "$GREEN $symbol $RESET"
        else "$RED $symbol $RESET"
    }
}