package slots
import globals.*


class Symbol(val symbol: Char) {

    var isHit: Boolean = false

    fun hit() {
        isHit = true
    }

    override fun toString(): String {
        return if (isHit) "$GREEN $symbol $RESET"
        else "$RED $symbol $RESET"
    }
}