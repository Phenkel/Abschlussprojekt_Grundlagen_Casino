import Globals.*
import Blackjack.*

fun loadingScreen(color1: String, color2: String, color3: String) {
    println("\n\n\n")
    print("$color1 #####                                      $color2#    #                                 $color3 #####                                \n" +
            "$color1#       #   # #    # #####   ##   #    #    $color2#   #   ####  ##### #      # #    #    $color3#     #   ##    ####  # #    #  ####  \n" +
            "$color1#        # #  ##   #   #    #  #   #  #     $color2#  #   #    #   #   #      # ##   #    $color3#        #  #  #      # ##   # #    # \n" +
            "$color1 #####    #   # #  #   #   #    #   ##      $color2###    #    #   #   #      # # #  #    $color3#       #    #  ####  # # #  # #    # \n" +
            "$color1      #   #   #  # #   #   ######   ##      $color2#  #   #    #   #   #      # #  # #    $color3#       ######      # # #  # # #    # \n" +
            "$color1#     #   #   #   ##   #   #    #  #  #     $color2#   #  #    #   #   #      # #   ##    $color3#     # #    # #    # # #   ## #    # \n" +
            "$color1 #####    #   #    #   #   #    # #    #    $color2#    #  ####    #   ###### # #    #    $color3 #####  #    #  ####  # #    #  ####$RESET\n\n\n\n\n")
}

fun loadingScreenAnimation() {
    repeat(3) {
        loadingScreen(RED, GREEN, BLUE)
        Thread.sleep(1000)
        loadingScreen(BLUE, RED, GREEN)
        Thread.sleep(1000)
        loadingScreen(GREEN, BLUE, RED)
        Thread.sleep(1000)
    }
    println("\n\n\n\n\n\n\n")
}

fun main() {
    loadingScreenAnimation()
    blackjack()
}