object Day19 {
    private const val input = 3004953

    fun part1(): Int = generateSequence(State((1..input).toList())) {
        it.next()
    }.dropWhile {
        it.elves.size > 1
    }.first().elves.single()

    private data class State(val elves: List<Int>) {
        fun next(): State {
            var copyLast = false
            val nextElves = elves.asSequence().chunked(2).mapNotNull {
                copyLast = it.size == 1
                if (copyLast) null else it[0]
            }.toList()
            return State(if (copyLast) listOf(elves.last()) + nextElves else nextElves)
        }
    }
}
