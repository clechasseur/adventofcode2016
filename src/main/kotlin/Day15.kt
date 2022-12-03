object Day15 {
    private val input = Sculpture(listOf(
        Disc(17, 1),
        Disc(7, 0),
        Disc(19, 2),
        Disc(5, 0),
        Disc(3, 0),
        Disc(13, 5)
    ), -1, 0)

    fun part1(): Int = generateSequence(input) { it.moveWithNewBall() }.first { ballPassesThrough(it) }.t

    fun part2(): Int = generateSequence(input.addDisc(Disc(11, 0))) {
        it.moveWithNewBall()
    }.first {
        ballPassesThrough(it)
    }.t

    private data class Disc(val numPos: Int, val curPos: Int) {
        fun move(): Disc = Disc(numPos, if (curPos == numPos - 1) 0 else curPos + 1)
    }

    private data class Sculpture(val discs: List<Disc>, val ballPos: Int, val t: Int) {
        val ballBounced: Boolean
            get() = ballPos in discs.indices && discs[ballPos].curPos != 0

        val ballPassedThrough: Boolean
            get() = ballPos == discs.size

        fun move(): Sculpture = Sculpture(discs.map { it.move() }, ballPos + 1, t + 1)
        fun moveWithNewBall(): Sculpture = Sculpture(discs.map { it.move() }, -1, t + 1)

        fun addDisc(disc: Disc): Sculpture = Sculpture(discs + disc, ballPos, t)
    }

    private fun ballPassesThrough(sculpture: Sculpture): Boolean = generateSequence(sculpture) {
        if (it.ballPassedThrough) {
            null
        } else {
            val next = it.move()
            if (next.ballBounced) null else next
        }
    }.last().ballPassedThrough
}
