import java.io.File

class Player(_name: String, override var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean) : Fightable {
    var name = _name
    get() = "${field.capitalize()} of $hometown"
    private set(value) {
        field = value.trim() // remove any leading and trailing spaces from passed value
    }

    //Lazy init
    val hometown by lazy { selectHometown() }
    var currentPosition = Coordinate(0, 0)

    //Initialization block, set up variables or values. Perform validation
    init {
        require(healthPoints > 0, { "healthpoints must be greater than zero."})
        require(name.isNotBlank(), { "Player must have a name."})
    }

    constructor(name: String) : this(name, isBlessed = true, isImmortal = false) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    private fun selectHometown() = File("data/towns.txt")
            .readText()
            .split("\n")
            .shuffled()
            .first()

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

    override val diceCount = 3

    override val diceSides = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}