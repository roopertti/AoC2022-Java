package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Example;

public class ExampleTest {
  @Test
  void getCoolExampleOutput() {
    String input = """
        123
        456
        678
        """;
        
    String expectedOutput = """
       ~ DAY 999 OUTPUT ~

      Part 1:
      > 3

      Part 2:
      > 123

        """;

    Example example = new Example(input);

    assertEquals(expectedOutput, example.produceOutputs());
  }

  @Test
  void handleEmptyInputCorrectly() {
    Example example = new Example("");
    assertEquals(example.getPart1Output(), "0");
    assertEquals(example.getPart2Output(), "File was empty!");
  }
}
