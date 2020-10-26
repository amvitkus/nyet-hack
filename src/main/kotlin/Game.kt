import kotlin.system.exitProcess

fun main(args: Array<String>) {

    //val player = Player("Caljax")
    //player.castFireball()

    //var currentRoom: Room = TownSquare()

    // Aura
    //val auraColor = player.auraColor()
    //println(auraColor)

    // Health points
    //val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    Game.play()

    //Aura
    //player.auraColor()

}

/*private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
    println("(Aura: $auraColor) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}*/

object Game {
   private val player = Player("Caljax")
    private var currentRoom: Room = TownSquare()
    var whileIsTrue = true

    private var worldMap = listOf(
            listOf(currentRoom, Room("Tavern"), Room("Back Room")),
            listOf(Room("Long Corridor"), Room("Generic Room")))


    init {
        println("Welcome, adventurer...")
        player.castFireball()
    }

    fun play() {
        while (whileIsTrue) {
            //Play NyetHack
            println(currentRoom.description())
            println(currentRoom.load())

            //Player status
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "fight" -> fight()
            "move" -> move(argument)
            "quit", "exit" -> {whileIsTrue = false; "GOODBYE"}
            else -> commandNotFound()
        }

        private fun commandNotFound() = "Unknown command issued."
    }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }

        "Combat complete."
    } ?: "There's nothing here to fight"

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(player)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing! <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    private fun move(directionInput: String) =
            try {
                val direction = Direction.valueOf(directionInput.toUpperCase())
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if (!newPosition.isInBounds) {
                    throw IllegalStateException("$direction is out of bounds.")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
            } catch (e: Exception) {
                "Invalid direction: $directionInput"
            }
}