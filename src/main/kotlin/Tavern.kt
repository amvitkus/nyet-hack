const val TAVERN_NAME = "Bomboe's Place"

fun main() {
    placeOrder("shandy,Dragon's Breath,5.91");
    //placeOrder("elixer,Shirley's Temple,4.20")
}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiouAEIOU]")) {
            when (it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                "A" -> "4"
                "E" -> "3"
                "I" -> "1"
                "O" -> "0"
                "U" -> "|_|"
                else -> it.value
            }
        }

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Boromir speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(',')
    val message = "Boromir buys a $name ($type) for $price."
    println(message)

    val phrase = if (name == "Dragon's Breath") {
        "Boromir exclaims ${toDragonSpeak("Ah, fantastic $name!")}"
    } else {
        "Boromir says: Thanks for the $name."
    }
    println(phrase)
}