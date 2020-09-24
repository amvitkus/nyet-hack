import kotlin.math.roundToInt
const val TAVERN_NAME = "Bomboe's Place"

var playerGold = 10
var playerSilver = 90
var dragonBreathGallons = 5.0
const val PINT = 0.125
var dragonBreathPints = dragonBreathGallons / PINT
var dragonBreathOrders = 0

fun main() {
    for (i in 1..10) {
        println("##### Order Number: $i #####")
        placeOrder("shandy,Dragon's Breath,5.91");
        //placeOrder("elixer,Shirley's Temple,4.20")
    }
}

fun performPurchase(price: Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    if (remainingBalance > 0) {
        println("Remaining balance: ${"%.2f".format(remainingBalance)}")

        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
        displayBalance()
    } else {
        println("Not enough gold to make a purchase.")
    }
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
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

private fun displayDragonBreathPintsLeft() {
    println("Dragon's Breath pints left: ${dragonBreathPints.toInt()}")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Boromir speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(',')
    val message = "Boromir buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "Boromir exclaims ${toDragonSpeak("Ah, fantastic $name!")}"
    } else {
        "Boromir says: Thanks for the $name."
    }
    println(phrase)

    if (name == "Dragon's Breath") {
        dragonBreathOrders++
        dragonBreathPints--
        if (dragonBreathOrders >= 8) displayDragonBreathPintsLeft()
    }
}