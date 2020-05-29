import org.clechasseur.adventofcode2016.Direction
import org.clechasseur.adventofcode2016.Pt
import org.clechasseur.adventofcode2016.manhattan
import org.clechasseur.adventofcode2016.move

object Day1 {
    private const val input = "R1, L3, R5, R5, R5, L4, R5, R1, R2, L1, L1, R5, R1, L3, L5, L2, R4, L1, R4, R5, L3, R5, L1, R3, L5, R1, L2, R1, L5, L1, R1, R4, R1, L1, L3, R3, R5, L3, R4, L4, R5, L5, L1, L2, R4, R3, R3, L185, R3, R4, L5, L4, R48, R1, R2, L1, R1, L4, L4, R77, R5, L2, R192, R2, R5, L4, L5, L3, R2, L4, R1, L5, R5, R4, R1, R2, L3, R4, R4, L2, L4, L3, R5, R4, L2, L1, L3, R1, R5, R5, R2, L5, L2, L3, L4, R2, R1, L4, L1, R1, R5, R3, R3, R4, L1, L4, R1, L2, R3, L3, L2, L1, L2, L2, L1, L2, R3, R1, L4, R1, L1, L4, R1, L2, L5, R3, L5, L2, L2, L3, R1, L4, R1, R1, R2, L1, L4, L4, R2, R2, R2, R2, R5, R1, L1, L4, L5, R2, R4, L3, L5, R2, R3, L4, L1, R2, R3, R5, L2, L3, R3, R1, R3"

    private val moves = input.split(", ").map { it.toMove() }

    fun part1(): Int {
        val me = Me()
        moves.forEach { me.apply(it) }
        return manhattan(me.pos, Pt.ZERO)
    }

    fun part2(): Int {
        val me = Me()
        val visited = mutableSetOf<Pt>()
        for (move in moves) {
            val pts = me.apply(move)
            pts.forEach {
                if (visited.contains(it)) {
                    return manhattan(it, Pt.ZERO)
                }
                visited.add(it)
            }
        }
        error("No location was visited twice")
    }

    private enum class TurnDirection {
        L, R
    }

    private data class Move(val turn: TurnDirection, val distance: Int)

    private fun String.toMove() = Move(TurnDirection.valueOf(this[0].toString()), substring(1).toInt())

    private class Me {
        var pos = Pt.ZERO
        var heading = Direction.UP

        fun apply(move: Move): List<Pt> {
            heading = if (move.turn == TurnDirection.L) {
                heading.left
            } else {
                heading.right
            }
            return (0 until move.distance).map { _ ->
                pos = pos.move(heading)
                pos
            }
        }
    }
}
