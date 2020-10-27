//import kotlin.math.roundToInt
import java.io.File

const val TAVERN_NAME = "Bomboe's Place"

//var playerGold = 10
//var playerSilver = 90
val patronList = mutableListOf("Eli", "Mordoc", "Sophie", "Boris", "Eomer")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins", "Yeltsin", "Cutler")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")

val patronGold = mutableMapOf<String, Double>()

var dragonBreathGallons = 5.0
const val PINT = 0.125
var dragonBreathPints = dragonBreathGallons / PINT
var dragonBreathOrders = 0
var madePurchase = true

fun main() {

    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.random(), menuList.random())
        orderCount++
    }

    displayPatronBalances()

    println(patronGold)

    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yeah, they're seated by the soup kettle.")
    } else {
        println("The tavern master says: No, they left hours ago.")
    }

    //Commented out code is for chapter challenges.
    //for (i in 1..3) {
        //println("##### Order Number: $i #####")
        //placeOrder("shandy,Dragon's Breath,5.91");
        //placeOrder("elixer,Shirley's Temple,4.20")
    //}
}

/*fun performPurchase(price: Double){
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
        getMadePurchase = false;
        println("Not enough gold to make a purchase.")
    }
}*/

/*private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}*/

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
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

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("Ah, fantastic $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    if (madePurchase){
    println(phrase)
    } else {
        println("GOODBYE")
    }

    if (name == "Dragon's Breath" && madePurchase) {
        dragonBreathOrders++
        dragonBreathPints--
        if (dragonBreathOrders >= 12) displayDragonBreathPintsLeft()
    }
}