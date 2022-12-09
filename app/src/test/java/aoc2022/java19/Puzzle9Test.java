package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle9;

public class Puzzle9Test {
  @Test
  void getExpectedOutputPart1() {
    String input = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
        """;
    String expectedOutput = "13";
    Puzzle9 puzzle9 = new Puzzle9(input);

    assertEquals(expectedOutput, puzzle9.getPart1Output());
  }

  @Test
  void getExpectedOutputPart2() {
    String input = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
        """;
    String expectedOutput = "36";
    Puzzle9 puzzle9 = new Puzzle9(input);

    assertEquals(expectedOutput, puzzle9.getPart2Output());
  }
}
