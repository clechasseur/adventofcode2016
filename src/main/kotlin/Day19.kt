object Day19 {
    private const val input = 3004953

    fun part1(): Int = generateSequence(State((1..input).map { Elf(it, 1) }, 1)) {
        it.next()
    }.dropWhile {
        it.elves.size > 1
    }.first().elves.first().pos

    private data class Elf(val pos: Int, val presents: Int)

    private data class State(val elves: List<Elf>, val turn: Int) {
        fun next(): State {
            val curIdx = elves.indexOfFirst { it.pos == turn }
            val nextIdx = if (curIdx == elves.indices.last) 0 else curIdx + 1
            val nextTurn = elves[if (nextIdx == elves.indices.last) 0 else nextIdx + 1].pos
            val newElf = Elf(elves[curIdx].pos, elves[curIdx].presents + elves[nextIdx].presents)
            val newElves = if (nextIdx == 0) {
                elves.subList(1, elves.indices.last) + newElf
            } else {
                elves.subList(0, curIdx) + newElf + elves.subList(nextIdx + 1, elves.size)
            }
            return State(newElves, nextTurn)
        }
    }
}
