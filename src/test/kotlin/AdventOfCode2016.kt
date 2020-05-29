import kotlin.test.Test
import kotlin.test.assertEquals

class AdventOfCode2016 {
    class Day1Puzzles {
        @Test
        fun `day 1, part 1`() {
            assertEquals(298, Day1.part1())
        }

        @Test
        fun `day 1, part 2`() {
            assertEquals(158, Day1.part2())
        }
    }

    class Day2Puzzles {
        @Test
        fun `day 2, part 1`() {
            assertEquals("56855", Day2.part1())
        }

        @Test
        fun `day 2, part 2`() {
            assertEquals("B3C27", Day2.part2())
        }
    }
}
