fun main(args: Array<String>) {

    val player = Player("Caljax")
    player.castFireball()

    var currentRoom: Room = TownSquare()
    println(currentRoom.description())
    println(currentRoom.load())

    // Aura
    val auraColor = player.auraColor()
    //println(auraColor)

    // Health points
    //val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    //Player status
    //printPlayerStatus(player)

    //Aura
    player.auraColor()

}

private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
    println("(Aura: $auraColor) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}