package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle4;

public class Puzzle4Test {
  @Test
  void getCorrectAmountOfContainedPairs() {
    String input = """
        12-54,12-68
        3-71,3-3
        71-72,35-71
        """;
    String expectedOutput = "2";
    Puzzle4 puzzle4 = new Puzzle4(input);

    assertEquals(puzzle4.getPart1Output(), expectedOutput);
  }

  @Test
  void getCorrectAmountOfOverlappingPairs() {
    String input = """
        12-54,12-68
        3-71,3-3
        1-3,6-7
        """;
    String expectedOutput = "2";
    Puzzle4 puzzle4 = new Puzzle4(input);

    assertEquals(puzzle4.getPart1Output(), expectedOutput);
  }
}
