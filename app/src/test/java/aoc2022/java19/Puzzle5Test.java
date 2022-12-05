package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle5;

public class Puzzle5Test {
  @Test
  void getExpectedTopCratesPart1() {
    String input = """
            [D]
        [N] [C]
        [Z] [M] [P]
         1   2   3

        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """;

    String expectedOutput = "CMZ";

    Puzzle5 puzzle5 = new Puzzle5(input);

    assertEquals(expectedOutput, puzzle5.getPart1Output());
  }

  @Test
  void getExpectedTopCratesPart2() {
    String input = """
            [D]
        [N] [C]
        [Z] [M] [P]
         1   2   3

        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """;

    String expectedOutput = "MCD";

    Puzzle5 puzzle5 = new Puzzle5(input);

    assertEquals(expectedOutput, puzzle5.getPart2Output());
  }
}
