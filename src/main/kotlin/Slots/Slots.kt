package Slots
import Globals.*

class Slots() {

    var win: Double = 0.0
    val symbols: List<Char> = listOf(SPADE_SYMBOL, DIAMOND_SYMBOL, HEART_SYMBOL, CLOVER_SYMBOL)
    val lines = MutableList(3) { MutableList(3) { Symbol(symbols.random()) } }
    var winCheck: Boolean = false

    fun displayLines() {
        println("===============")
        for (line in lines) {
            println(line.joinToString(" - "))
        }
        println("===============")
    }

    fun resetLines() {
        lines.forEach { it.clear() }
        for (line in lines) {
            repeat(3) {
                line.add(Symbol(symbols.random()))
            }
        }
    }

    fun lineCheck() {
        for (line in lines) {
            line.forEach { it.isLastSpin = true }
            if (line[0] == line[1] && line[1] == line[2]) {
                line.forEach { it.hit() }
                bet *= 3
            }
        }
    }

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