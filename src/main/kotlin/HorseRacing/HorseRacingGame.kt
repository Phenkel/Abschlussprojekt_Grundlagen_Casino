package HorseRacing
import Globals.*

val BROWN = "\u001B[33m"
val horseRace: HorseRace = HorseRace()

fun main() {
    println("\n\n\n\n\n\n\n\n\n\n" +
            "                   ${BROWN};;\n" +
            "                 ,;;'\\\n" +
            "      __       ,;;' ' \\   ${RESET}#     #                             ######                                \n" +
            "    ${BROWN}/'  '${GREEN}\\'~~'~' \\${BROWN} /'\\.)  ${RESET}#     #  ####  #####   ####  ###### #     #   ##    ####  # #    #  ####  \n" +
            " ${BROWN},;(      ${GREEN})    /${BROWN}  |       ${RESET}#     # #    # #    # #      #      #     #  #  #  #    # # ##   # #    # \n" +
            "${BROWN},;' \\    ${GREEN}/-.,,(${BROWN}   )       ${RESET}####### #    # #    #  ####  #####  ######  #    # #      # # #  # #      \n" +
            "     ${BROWN}) /|      ) /|       ${RESET}#     # #    # #####       # #      #   #   ###### #      # #  # # #  ### \n" +
            "     ${BROWN}||(_\\     ||(_\\      ${RESET}#     # #    # #   #  #    # #      #    #  #    # #    # # #   ## #    # \n" +
            "     ${BROWN}(_\\       (_\\        ${RESET}#     #  ####  #    #  ####  ###### #     # #    #  ####  # #    #  ####  \n")
    horseRace.race()
}