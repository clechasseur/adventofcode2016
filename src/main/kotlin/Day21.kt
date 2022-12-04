import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day21 {
    private val input = """
        swap position 7 with position 1
        swap letter e with letter d
        swap position 7 with position 6
        move position 4 to position 0
        move position 1 to position 4
        move position 6 to position 5
        rotate right 1 step
        swap letter e with letter b
        reverse positions 3 through 7
        swap position 2 with position 6
        reverse positions 2 through 4
        reverse positions 1 through 4
        reverse positions 5 through 7
        rotate left 2 steps
        swap letter g with letter f
        rotate based on position of letter a
        swap letter b with letter h
        swap position 0 with position 3
        move position 4 to position 7
        rotate based on position of letter g
        swap letter f with letter e
        move position 1 to position 5
        swap letter d with letter e
        move position 5 to position 2
        move position 6 to position 5
        rotate right 6 steps
        rotate left 4 steps
        reverse positions 0 through 3
        swap letter g with letter c
        swap letter f with letter e
        reverse positions 6 through 7
        move position 6 to position 1
        rotate left 2 steps
        rotate left 5 steps
        swap position 3 with position 6
        reverse positions 1 through 5
        rotate right 6 steps
        swap letter a with letter b
        reverse positions 3 through 4
        rotate based on position of letter f
        swap position 2 with position 6
        reverse positions 5 through 6
        swap letter h with letter e
        reverse positions 0 through 4
        rotate based on position of letter g
        rotate based on position of letter d
        rotate based on position of letter b
        swap position 5 with position 1
        rotate based on position of letter f
        move position 1 to position 5
        rotate right 0 steps
        rotate based on position of letter e
        move position 0 to position 1
        swap position 7 with position 2
        rotate left 3 steps
        reverse positions 0 through 1
        rotate right 7 steps
        rotate right 5 steps
        swap position 2 with position 0
        swap letter g with letter a
        rotate left 0 steps
        rotate based on position of letter f
        swap position 5 with position 1
        rotate right 0 steps
        rotate left 5 steps
        swap letter e with letter a
        swap position 5 with position 4
        reverse positions 2 through 5
        swap letter e with letter a
        swap position 3 with position 7
        reverse positions 0 through 2
        swap letter a with letter b
        swap position 7 with position 1
        move position 1 to position 6
        rotate right 1 step
        reverse positions 2 through 6
        rotate based on position of letter b
        move position 1 to position 0
        swap position 7 with position 3
        move position 6 to position 5
        rotate right 4 steps
        reverse positions 2 through 7
        reverse positions 3 through 4
        reverse positions 4 through 5
        rotate based on position of letter f
        reverse positions 0 through 5
        reverse positions 3 through 4
        move position 1 to position 2
        rotate left 4 steps
        swap position 7 with position 6
        rotate right 1 step
        move position 5 to position 2
        rotate right 5 steps
        swap position 7 with position 4
        swap letter a with letter e
        rotate based on position of letter e
        swap position 7 with position 1
        swap position 7 with position 3
        move position 7 to position 1
        swap position 7 with position 4
    """.trimIndent()

    private val swapPositionRegex = """^swap position (\d+) with position (\d+)$""".toRegex()
    private val swapLettersRegex = """^swap letter (\w) with letter (\w)$""".toRegex()
    private val shiftRegex = """^rotate (left|right) (\d+) steps?$""".toRegex()
    private val shiftBasedOnRegex = """^rotate based on position of letter (\w)$""".toRegex()
    private val reverseRegex = """^reverse positions (\d+) through (\d+)$""".toRegex()
    private val moveRegex = """^move position (\d+) to position (\d+)$""".toRegex()

    fun part1(): String {
        val operations = input.lines().map { it.toOperation() }
        var password = "abcdefgh"
        for (operation in operations) {
            password = operation(password)
        }
        return password
    }

    fun part2(): String {
        val operations = input.lines().map { it.toOperation() }
        var password = "abcdefgh"
        val forward = mutableListOf(password)
        for (operation in operations) {
            password = operation(password)
            forward.add(password)
        }
        val backward = forward.reversed()

        val reverseOperations = input.lines().reversed().map { it.toOperation().reversed() }
        var i = 0
        for (operation in reverseOperations) {
            val oldPassword = password
            password = operation(password)
            i++
            if (password != backward[i]) {
                error("Operation [$operation] did not properly reverse $oldPassword: expected ${backward[i]}, got $password")
            }
        }
        return password
    }

    private interface Operation {
        operator fun invoke(input: String): String
        fun reversed(): Operation
    }

    private class SwapPositions(a: Int, b: Int) : Operation {
        private val x = min(a, b)
        private val y = max(a, b)

        override fun invoke(input: String): String =
            "${input.substring(0, x)}${input[y]}${input.substring(x + 1, y)}${input[x]}${input.substring(y + 1)}"

        override fun reversed(): Operation = this

        override fun toString(): String = "swap position $x with position $y"
    }

    private class SwapLetters(val x: Char, val y: Char) : Operation {
        override fun invoke(input: String): String = SwapPositions(input.indexOf(x), input.indexOf(y))(input)

        override fun reversed(): Operation = this

        override fun toString(): String = "swap letter $x with letter $y"
    }

    private class Shift(val steps: Int) : Operation {
        override fun invoke(input: String): String {
            val startPos = if (steps >= 0) {
                steps % input.length
            } else {
                var moduloSteps = steps
                while (abs(moduloSteps) > input.length) {
                    moduloSteps += input.length
                }
                input.length + moduloSteps
            }
            return "${input.substring(startPos)}${input.substring(0, startPos)}"
        }

        override fun reversed(): Operation = Shift(-steps)

        override fun toString(): String = "rotate ${if (steps < 0) "right" else "left"} $steps steps"
    }

    private class ShiftBasedOn(val x: Char) : Operation {
        override fun invoke(input: String): String {
            val xPos = input.indexOf(x)
            return Shift(-(1 + xPos + if (xPos >= 4) 1 else 0))(input)
        }

        override fun reversed(): Operation = ShiftReversedBasedOn(x)

        override fun toString(): String = "rotate based on position of letter $x"
    }

    private class ShiftReversedBasedOn(val x: Char) : Operation {
        override fun invoke(input: String): String {
            return ShiftBasedOn(x)(input.reversed()).reversed()
        }

        override fun reversed(): Operation {
            error("You probably didn't mean to reverse this")
        }

        override fun toString(): String = "reverse-rotate based on position of letter $x"
    }

    private class Reverse(a: Int, b: Int) : Operation {
        private val x = min(a, b)
        private val y = max(a, b)

        override fun invoke(input: String): String =
            "${input.substring(0, x)}${input.substring(x..y).reversed()}${input.substring(y + 1)}"

        override fun reversed(): Operation = this

        override fun toString(): String = "reverse positions $x through $y"
    }

    private class Move(val x: Int, val y: Int) : Operation {
        override fun invoke(input: String): String {
            val c = input[x]
            val without = "${input.substring(0, x)}${input.substring(x + 1)}"
            return if (y < without.length) {
                "${without.substring(0, y)}$c${without.substring(y)}"
            } else {
                without + c
            }
        }

        override fun reversed(): Operation = Move(y, x)

        override fun toString(): String = "move position $x to position $y"
    }

    private fun String.toOperation(): Operation {
        var match = swapPositionRegex.matchEntire(this)
        if (match != null) {
            return SwapPositions(match.groupValues[1].toInt(), match.groupValues[2].toInt())
        }

        match = swapLettersRegex.matchEntire(this)
        if (match != null) {
            return SwapLetters(match.groupValues[1].single(), match.groupValues[2].single())
        }

        match = shiftRegex.matchEntire(this)
        if (match != null) {
            var steps = match.groupValues[2].toInt()
            if (match.groupValues[1] == "right") {
                steps = -steps
            }
            return Shift(steps)
        }

        match = shiftBasedOnRegex.matchEntire(this)
        if (match != null) {
            return ShiftBasedOn(match.groupValues[1].single())
        }

        match = reverseRegex.matchEntire(this)
        if (match != null) {
            return Reverse(match.groupValues[1].toInt(), match.groupValues[2].toInt())
        }

        match = moveRegex.matchEntire(this)
        if (match != null) {
            return Move(match.groupValues[1].toInt(), match.groupValues[2].toInt())
        }

        error("Wrong operation: $this")
    }
}
