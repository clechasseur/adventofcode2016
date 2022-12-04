import org.clechasseur.adventofcode2016.Day20Data

object Day20 {
    private val input = Day20Data.input

    fun part1(): Long {
        val blockList = getBlockList()
        return (0L..4294967295L).first { ip ->
            blockList.none { it.contains(ip) }
        }
    }

    fun part2(): Long {
        val blockList = getBlockList()
        val blockSet = mutableSetOf<Long>()
        blockList.forEach { block -> block.forEach { blockSet.add(it) } }
        return 4294967296L - blockSet.size.toLong()
    }

    private fun getBlockList(): List<LongRange> = input.lines().map {
        val (from, to) = it.split('-')
        (from.toLong())..(to.toLong())
    }
}
