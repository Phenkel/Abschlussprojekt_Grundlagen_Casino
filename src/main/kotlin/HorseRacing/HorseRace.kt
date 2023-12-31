package HorseRacing
import Globals.*
import kotlin.random.Random

// Die Klasse, die die Pferderennen-Logik enthält.
class HorseRace {
    val horse: String = "\uD83D\uDC34" // Ein Unicode-Symbol für ein Pferd (Pferdeemoji)
    val horseSpeedList: MutableList<Long> = mutableListOf()

    fun fastestHorseColor(horseTime: Long,): String {
        horseSpeedList.add(horseTime)
        horseSpeedList.sort()
        return if (horseTime == horseSpeedList[0]) GREEN
        else RED
    }

    // Die Funktion, die das Pferderennen simuliert und den Gewinner ermittelt.
    fun race(): Int {
        // Start des Rennens für Pferd 1
        print("Pferd 1: ")
        var horseOneTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25, 150) // Zufällige Laufzeit des Pferdes
            Thread.sleep(horseRun) // Das Programm wird für die angegebene Zeit angehalten
            print(horse) // Das Pferdeemoji wird ausgegeben
            horseOneTime += horseRun // Die Gesamtzeit des Pferdes wird aktualisiert
        }
        println(" FINISH!")
        println("Zeit von Pferd 1: ${fastestHorseColor(horseOneTime)}${horseOneTime}ms${RESET}!")

        // Start des Rennens für Pferd 2
        print("Pferd 2: ")
        var horseTwoTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25, 150) // Zufällige Laufzeit des Pferdes
            Thread.sleep(horseRun) // Das Programm wird für die angegebene Zeit angehalten
            print(horse) // Das Pferdeemoji wird ausgegeben
            horseTwoTime += horseRun // Die Gesamtzeit des Pferdes wird aktualisiert
        }
        if (horseTwoTime == horseOneTime) horseTwoTime++
        println(" FINISH!")
        println("Zeit von Pferd 2: ${fastestHorseColor(horseTwoTime)}${horseTwoTime}ms${RESET}!")

        // Start des Rennens für Pferd 3
        print("Pferd 3: ")
        var horseThreeTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25, 150) // Zufällige Laufzeit des Pferdes
            Thread.sleep(horseRun) // Das Programm wird für die angegebene Zeit angehalten
            print(horse) // Das Pferdeemoji wird ausgegeben
            horseThreeTime += horseRun // Die Gesamtzeit des Pferdes wird aktualisiert
        }
        if (horseThreeTime == horseOneTime || horseThreeTime == horseTwoTime) horseThreeTime++
        println(" FINISH!")
        println("Zeit von Pferd 3: ${fastestHorseColor(horseThreeTime)}${horseThreeTime}ms${RESET}!")

        // Start des Rennens für Pferd 4
        print("Pferd 4: ")
        var horseFourTime: Long = 0
        repeat(43) {
            val horseRun: Long = Random.nextLong(25, 150) // Zufällige Laufzeit des Pferdes
            Thread.sleep(horseRun) // Das Programm wird für die angegebene Zeit angehalten
            print(horse) // Das Pferdeemoji wird ausgegeben
            horseFourTime += horseRun // Die Gesamtzeit des Pferdes wird aktualisiert
        }
        if (horseFourTime == horseOneTime || horseFourTime == horseTwoTime || horseFourTime == horseThreeTime) horseFourTime++
        println(" FINISH!")
        println("Zeit von Pferd 4: ${fastestHorseColor(horseFourTime)}${horseFourTime}ms${RESET}!")
        horseSpeedList.clear()

        // Gewinner wird ermittelt
        return if (horseOneTime < horseTwoTime && horseOneTime < horseThreeTime && horseOneTime < horseFourTime) {
            successMessage("Pferd 1 hat gewonnen!")
            1
        } else if (horseTwoTime < horseThreeTime && horseTwoTime < horseFourTime) {
            successMessage("Pferd 2 hat gewonnen!")
            2
        } else if (horseThreeTime < horseFourTime) {
            successMessage("Pferd 3 hat gewonnen!")
            3
        } else {
            successMessage("Pferd 4 hat gewonnen!")
            4
        }
    }
}