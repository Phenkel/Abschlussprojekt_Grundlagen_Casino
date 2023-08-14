package HorseRacing
import Globals.*

import kotlin.random.Random

class HorseRace {
    val horse: String = "\uD83D\uDC34"

    fun race(): Int {
        print("Pferd 1: ")
        var horseOneTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25,150)
            Thread.sleep(horseRun)
            print(horse)
            horseOneTime += horseRun
        }
        println("\nZeit von Pferd 1: ${GREEN}${horseOneTime}ms${RESET}!")
        print("Pferd 2: ")
        var horseTwoTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25,150)
            Thread.sleep(horseRun)
            print(horse)
            horseTwoTime += horseRun
        }
        println("\nZeit von Pferd 2: ${GREEN}${horseTwoTime}ms${RESET}!")
        print("Pferd 3: ")
        var horseThreeTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25,150)
            Thread.sleep(horseRun)
            print(horse)
            horseThreeTime += horseRun
        }
        println("\nZeit von Pferd 3: ${GREEN}${horseThreeTime}ms${RESET}!")
        print("Pferd 4: ")
        var horseFourTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25,150)
            Thread.sleep(horseRun)
            print(horse)
            horseFourTime += horseRun
        }
        println("\nZeit von Pferd 4: ${GREEN}${horseFourTime}ms${RESET}!")
        if (horseOneTime < horseTwoTime && horseOneTime < horseThreeTime && horseOneTime < horseFourTime) {
            successMessage("Pferd 1 hat gewonnen!")
            return 1
        }
        else if (horseTwoTime < horseThreeTime && horseTwoTime < horseFourTime) {
            successMessage("Pferd 2 hat gewonnen!")
            return 2
        }
        else if (horseThreeTime < horseFourTime) {
            successMessage("Pferd 3 hat gewonnen!")
            return 3
        }
        else {
            successMessage("Pferd 4 hat gewonnen!")
            return 4
        }
    }
}