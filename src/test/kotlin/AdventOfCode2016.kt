import kotlin.test.Ignore
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

    class Day5Puzzles {
        @Test
        @Ignore
        fun `day 5, part 1`() {
            assertEquals("2414bc77", Day5.part1())
        }

        @Test
        @Ignore
        fun `day 5, part 2`() {
            assertEquals("437e60fc", Day5.part2())
        }
    }

    class Day6Puzzles {
        @Test
        fun `day 6, part 1`() {
            assertEquals("afwlyyyq", Day6.part1())
        }

        @Test
        fun `day 6, part 2`() {
            assertEquals("bhkzekao", Day6.part2())
        }
    }

    class Day7Puzzles {
        @Test
        fun `day 7, part 1`() {
            assertEquals(105, Day7.part1())
        }

        @Test
        fun `day 7, part 2`() {
            assertEquals(258, Day7.part2())
        }
    }

    class Day8Puzzles {
        companion object {
            private const val expected =
                    " ##  #### ###  #  # ###  #### ###    ## ###   ### \n" +
                    "#  # #    #  # #  # #  #    # #  #    # #  # #    \n" +
                    "#  # ###  ###  #  # #  #   #  ###     # #  # #    \n" +
                    "#### #    #  # #  # ###   #   #  #    # ###   ##  \n" +
                    "#  # #    #  # #  # #    #    #  # #  # #       # \n" +
                    "#  # #    ###   ##  #    #### ###   ##  #    ###  "
        }

        @Test
        fun `day 8, part 1`() {
            assertEquals(123, Day8.part1())
        }

        @Test
        fun `day 8, part 2`() {
            assertEquals(expected, Day8.part2())
        }
    }

    class Day9Puzzles {
        @Test
        fun `day 9, part 1`() {
            assertEquals(107_035L, Day9.part1())
        }

        @Test
        fun `day 9, part 2`() {
            assertEquals(11_451_628_995L, Day9.part2())
        }
    }

    class Day10Puzzles {
        @Test
        fun `day 10, part 1`() {
            assertEquals(147, Day10.part1())
        }

        @Test
        fun `day 10, part 2`() {
            assertEquals(55_637, Day10.part2())
        }
    }

    class Day11Puzzles {
        @Test
        fun `day 11, part 1`() {
            assertEquals(31, Day11.part1())
        }

        @Test
        @Ignore("Took about 4 minutes to run on my machine")
        fun `day 11, part 2`() {
            assertEquals(55, Day11.part2())
        }
    }

    class Day12Puzzles {
        @Test
        fun `day 12, part 1`() {
            assertEquals(318083, Day12.part1())
        }

        @Test
        fun `day 12, part 2`() {
            assertEquals(9227737, Day12.part2())
        }
    }

    class Day13Puzzles {
        @Test
        fun `day 13, part 1`() {
            assertEquals(82, Day13.part1())
        }

        @Test
        fun `day 13, part 2`() {
            assertEquals(138, Day13.part2())
        }
    }

    class Day14Puzzles {
        @Test
        fun `day 14, part 1`() {
            assertEquals(23890, Day14.part1())
        }

        @Test
        fun `day 14, part 2`() {
            assertEquals(22696, Day14.part2())
        }
    }

    class Day15Puzzles {
        @Test
        fun `day 15, part 1`() {
            assertEquals(317371, Day15.part1())
        }

        @Test
        fun `day 15, part 2`() {
            assertEquals(2080951, Day15.part2())
        }
    }

    class Day16Puzzles {
        @Test
        fun `day 16, part 1`() {
            assertEquals("10010101010011101", Day16.part1())
        }

        @Test
        fun `day 16, part 2`() {
            assertEquals("01100111101101111", Day16.part2())
        }
    }

    class Day17Puzzles {
        @Test
        fun `day 17, part 1`() {
            assertEquals("RDURRDDLRD", Day17.part1())
        }

        @Test
        fun `day 17, part 2`() {
            assertEquals(526, Day17.part2())
        }
    }

    class Day18Puzzles {
        @Test
        fun `day 18, part 1`() {
            assertEquals(1956, Day18.part1())
        }

        @Test
        fun `day 18, part 2`() {
            assertEquals(19995121, Day18.part2())
        }
    }

    class Day19Puzzles {
        @Test
        @Ignore("Too slow")
        fun `day 19, part 1`() {
            assertEquals(0, Day19.part1())
        }
    }
}
