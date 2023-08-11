package globals

fun successMessage(text: String) {
    println(GREEN + text + RESET)
}

fun errorMessage(text: String) {
    println(RED + text + RESET)
}