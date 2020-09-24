fun main(args: Array<String>) {
    val name = "Caljax"
    val isBlessed = true
    val isImmortal = false

    var healthPoints = 77

    // Aura
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)
    //println(auraColor)

    // Health points
    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    //Player status
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)

    //Cast fireball
    castFireball()
}

private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
    println("(Aura: $auraColor) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
     if (isBlessed && healthPoints > 50 || isImmortal)
    "GREEN" else "NONE"

private fun castFireball(numFireballs: Int = 2) =
    println("An orb of fire sparks into existence (x$numFireballs)")

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
        when (healthPoints) {
        100 -> "is in perfect health!"
        in 85..99 -> "has a few scratches."
        in 75..84 -> if (isBlessed) {
            "has some injures, but prayer is healing quite quickly!"
        } else {
            "has some injuries."
        }
        in 15..74 -> "has significant injuries."
        else -> "is in the worst condition."
    }