package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle8;

public class Puzzle8Test {
  @Test
  void getExpectedOutputPart1() {
    String input = """
      30373
      25512
      65332
      33549
      35390
      """;
    String expectedOutput = "21";
    Puzzle8 puzzle8 = new Puzzle8(input);

    assertEquals(expectedOutput, puzzle8.getPart1Output());
  }

  @Test
  void getExpectedOutputPart2() {
    String input = """
      30373
      25512
      65332
      33549
      35390
      """;
    String expectedOutput = "8";
    Puzzle8 puzzle8 = new Puzzle8(input);

    assertEquals(expectedOutput, puzzle8.getPart2Output());
  }
}
