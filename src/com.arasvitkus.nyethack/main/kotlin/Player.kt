class Player {
    var name = "caljax"
    get() = field.capitalize()
    private set(value) {
        field = value.trim() // remove any leading and trailing spaces from passed value
    }

    val isBlessed = true
    private val isImmortal = false
    var healthPoints = 77

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
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

    fun castFireball(numFireballs: Int = 2) =
            println("A glass of Fireball springs into existence. (x$numFireballs)")
}