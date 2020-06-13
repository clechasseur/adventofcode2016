import org.clechasseur.adventofcode2016.math.generatePairSequence

object Day8 {
    private val input = """
        rect 1x1
        rotate row y=0 by 20
        rect 1x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 3
        rect 2x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 3
        rect 2x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 4
        rect 2x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 3
        rect 2x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 5
        rect 1x1
        rotate row y=0 by 2
        rect 1x1
        rotate row y=0 by 6
        rect 5x1
        rotate row y=0 by 2
        rect 1x3
        rotate row y=2 by 8
        rotate row y=0 by 8
        rotate column x=0 by 1
        rect 7x1
        rotate row y=2 by 24
        rotate row y=0 by 20
        rotate column x=5 by 1
        rotate column x=4 by 2
        rotate column x=2 by 2
        rotate column x=0 by 1
        rect 7x1
        rotate column x=34 by 2
        rotate column x=22 by 1
        rotate column x=15 by 1
        rotate row y=2 by 18
        rotate row y=0 by 12
        rotate column x=8 by 2
        rotate column x=7 by 1
        rotate column x=5 by 2
        rotate column x=2 by 1
        rotate column x=0 by 1
        rect 9x1
        rotate row y=3 by 28
        rotate row y=1 by 28
        rotate row y=0 by 20
        rotate column x=18 by 1
        rotate column x=15 by 1
        rotate column x=14 by 1
        rotate column x=13 by 1
        rotate column x=12 by 2
        rotate column x=10 by 3
        rotate column x=8 by 1
        rotate column x=7 by 2
        rotate column x=6 by 1
        rotate column x=5 by 1
        rotate column x=3 by 1
        rotate column x=2 by 2
        rotate column x=0 by 1
        rect 19x1
        rotate column x=34 by 2
        rotate column x=24 by 1
        rotate column x=23 by 1
        rotate column x=14 by 1
        rotate column x=9 by 2
        rotate column x=4 by 2
        rotate row y=3 by 5
        rotate row y=2 by 3
        rotate row y=1 by 7
        rotate row y=0 by 5
        rotate column x=0 by 2
        rect 3x2
        rotate column x=16 by 2
        rotate row y=3 by 27
        rotate row y=2 by 5
        rotate row y=0 by 20
        rotate column x=8 by 2
        rotate column x=7 by 1
        rotate column x=5 by 1
        rotate column x=3 by 3
        rotate column x=2 by 1
        rotate column x=1 by 2
        rotate column x=0 by 1
        rect 9x1
        rotate row y=4 by 42
        rotate row y=3 by 40
        rotate row y=1 by 30
        rotate row y=0 by 40
        rotate column x=37 by 2
        rotate column x=36 by 3
        rotate column x=35 by 1
        rotate column x=33 by 1
        rotate column x=32 by 1
        rotate column x=31 by 3
        rotate column x=30 by 1
        rotate column x=28 by 1
        rotate column x=27 by 1
        rotate column x=25 by 1
        rotate column x=23 by 3
        rotate column x=22 by 1
        rotate column x=21 by 1
        rotate column x=20 by 1
        rotate column x=18 by 1
        rotate column x=17 by 1
        rotate column x=16 by 3
        rotate column x=15 by 1
        rotate column x=13 by 1
        rotate column x=12 by 1
        rotate column x=11 by 2
        rotate column x=10 by 1
        rotate column x=8 by 1
        rotate column x=7 by 2
        rotate column x=5 by 1
        rotate column x=3 by 3
        rotate column x=2 by 1
        rotate column x=1 by 1
        rotate column x=0 by 1
        rect 39x1
        rotate column x=44 by 2
        rotate column x=42 by 2
        rotate column x=35 by 5
        rotate column x=34 by 2
        rotate column x=32 by 2
        rotate column x=29 by 2
        rotate column x=25 by 5
        rotate column x=24 by 2
        rotate column x=19 by 2
        rotate column x=15 by 4
        rotate column x=14 by 2
        rotate column x=12 by 3
        rotate column x=9 by 2
        rotate column x=5 by 5
        rotate column x=4 by 2
        rotate row y=5 by 5
        rotate row y=4 by 38
        rotate row y=3 by 10
        rotate row y=2 by 46
        rotate row y=1 by 10
        rotate column x=48 by 4
        rotate column x=47 by 3
        rotate column x=46 by 3
        rotate column x=45 by 1
        rotate column x=43 by 1
        rotate column x=37 by 5
        rotate column x=36 by 5
        rotate column x=35 by 4
        rotate column x=33 by 1
        rotate column x=32 by 5
        rotate column x=31 by 5
        rotate column x=28 by 5
        rotate column x=27 by 5
        rotate column x=26 by 3
        rotate column x=25 by 4
        rotate column x=23 by 1
        rotate column x=17 by 5
        rotate column x=16 by 5
        rotate column x=13 by 1
        rotate column x=12 by 5
        rotate column x=11 by 5
        rotate column x=3 by 1
        rotate column x=0 by 1
    """.trimIndent()

    private val rectRegex = """rect (\d+)x(\d+)""".toRegex()
    private val rotateRegex = """rotate ([a-z]+) [xy]=(\d+) by (\d+)""".toRegex()

    private val inputInstructions = input.lines().flatMap { it.toInstructions() }

    fun part1() = blankScreen().apply {
        inputInstructions.forEach { instruction ->
            instruction(this)
        }
    }.map { row ->
        row.count { it }
    }.sum()

    fun part2() = blankScreen().apply {
        inputInstructions.forEach { instruction ->
            instruction(this)
        }
    }.joinToString("\n") { row ->
        row.map { if (it) '#' else ' ' }.joinToString("")
    }

    private fun blankScreen() = MutableList(6) { MutableList(50) { false } }

    private interface Instruction {
        operator fun invoke(screen: MutableList<MutableList<Boolean>>)
    }

    private class Rect(val width: Int, val height: Int) : Instruction {
        override fun invoke(screen: MutableList<MutableList<Boolean>>) {
            generatePairSequence(0 until width, 0 until height).forEach { (x, y) ->
                screen[y][x] = true
            }
        }
    }

    private class ShiftRow(val y: Int) : Instruction {
        override fun invoke(screen: MutableList<MutableList<Boolean>>) {
            val newRow = listOf(screen[y][49]) + screen[y].subList(0, 49)
            screen[y] = newRow.toMutableList()
        }
    }

    private class ShiftColumn(val x: Int): Instruction {
        override fun invoke(screen: MutableList<MutableList<Boolean>>) {
            val new0 = screen[5][x]
            for (y in 5 downTo 1) {
                screen[y][x] = screen[y - 1][x]
            }
            screen[0][x] = new0
        }
    }

    private fun String.toInstructions(): List<Instruction> = when (val rectMatch = rectRegex.matchEntire(this)) {
        null -> {
            val rotateMatch = rotateRegex.matchEntire(this)!!
            val instructions = mutableListOf<Instruction>()
            if (rotateMatch.groupValues[1] == "row") {
                (0 until rotateMatch.groupValues[3].toInt()).forEach { _ ->
                    instructions.add(ShiftRow(rotateMatch.groupValues[2].toInt()))
                }
            } else {
                (0 until rotateMatch.groupValues[3].toInt()).forEach { _ ->
                    instructions.add(ShiftColumn(rotateMatch.groupValues[2].toInt()))
                }
            }
            instructions
        }
        else -> listOf(Rect(rectMatch.groupValues[1].toInt(), rectMatch.groupValues[2].toInt()))
    }
}
