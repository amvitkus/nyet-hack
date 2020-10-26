open class Room(val name: String) {
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\n" + "Danger level: $dangerLevel\n +" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "Nothing to see here..."

}

open class TownSquare : Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "DONG"

    final override fun load() = "The villagers look at you as you enter.\n${ringBell()}"

    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}