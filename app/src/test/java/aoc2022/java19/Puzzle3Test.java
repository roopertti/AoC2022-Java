package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle3;

public class Puzzle3Test {
  @Test
  void getSuccessfulInputPart1() {
    String lowercaseContent = "asdjgrej";
    String uppercaseContent = "ASDJGREJ";
    String expectedLowercaseOutput = "10";
    String expectedUppercaseOutput = "36";

    var puzzle = new Puzzle3(lowercaseContent);
    var puzzle2 = new Puzzle3(uppercaseContent);

    assertEquals(expectedLowercaseOutput, puzzle.getPart1Output());
    assertEquals(expectedUppercaseOutput, puzzle2.getPart1Output());
  }

  @Test
  void getSuccesfulInputPart2() {
    String testInput = """
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
        """;
    String expectedOutput = "52";
    Puzzle3 puzzle3 = new Puzzle3(testInput);

    assertEquals(expectedOutput, puzzle3.getPart2Output());
  }
}
