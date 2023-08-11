package horseRacing

import kotlin.random.Random

class HorseRace {
    val horse: String = "\uD83D\uDC34"

    fun race() {
        print("Pferd 1: ")
        var horseOneTime: Long = 0
        repeat(20) {
            val horseRun: Long = Random.nextLong(50,250)
            Thread.sleep(horseRun)
            print(horse)
            horseOneTime += horseRun
        }
        println()
        print("Pferd 2: ")
        var horseTwoTime: Long = 0
        repeat(20) {
            val horseRun: Long = Random.nextLong(50,250)
            Thread.sleep(horseRun)
            print(horse)
            horseTwoTime += horseRun
        }
        println()
        print("Pferd 3: ")
        var horseThreeTime: Long = 0
        repeat(20) {
            val horseRun: Long = Random.nextLong(50,250)
            Thread.sleep(horseRun)
            print(horse)
            horseThreeTime += horseRun
        }
        println()
        print("Pferd 4: ")
        var horseFourTime: Long = 0
        repeat(20) {
            val horseRun: Long = Random.nextLong(50,250)
            Thread.sleep(horseRun)
            print(horse)
            horseFourTime += horseRun
        }
        println()
    }
}