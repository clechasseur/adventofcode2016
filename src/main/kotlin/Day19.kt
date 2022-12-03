object Day19 {
    private const val input = 3004953

    fun part1(): Int = generateSequence(State((1..input).toList())) {
        it.next()
    }.dropWhile {
        it.elves.size > 1
    }.first().elves.single()

    fun part2(): Int {
        val circle = Circle((1..input).toList())
        while (circle.size > 1) {
            circle.removeOne()
        }
        return circle.head!!.value
    }

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

    private class Node<T>(val value: T) {
        var next: Node<T>? = null
        var prev: Node<T>? = null

        fun skip(n: Int): Node<T> {
            require(next != null) { "Cannot skip uninitialized nodes" }
            var node = this
            var i = n
            while (i-- > 0) {
                node = node.next!!
            }
            return node
        }
    }

    private class Circle<T>(values: Collection<T>) {
        var head: Node<T>? = null
            private set

        var size: Int
            private set

        init {
            var prev: Node<T>? = null
            for (value in values) {
                val node = Node(value)
                if (head == null) {
                    head = node
                }
                node.prev = prev
                prev?.next = node
                prev = node
            }
            prev?.next = head
            head?.prev = prev
            size = values.size
        }

        fun removeOne() {
            require(size > 1) { "Circle is complete" }
            val node = head!!.skip(size / 2)
            node.prev!!.next = node.next
            node.next!!.prev = node.prev
            size--
            head = head!!.next
        }
    }
}
