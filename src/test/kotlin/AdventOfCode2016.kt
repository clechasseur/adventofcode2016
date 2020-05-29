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

    class Day3Puzzles {
        @Test
        fun `day 3, part 1`() {
            assertEquals(993, Day3.part1())
        }

        @Test
        fun `day 3, part 2`() {
            assertEquals(1849, Day3.part2())
        }
    }

    class Day4Puzzles {
        @Test
        fun `day 4, part 1`() {
            assertEquals(137896, Day4.part1())
        }

        @Test
        fun `day 4, part 2`() {
            assertEquals(0, Day4.part2())
        }
    }
}
