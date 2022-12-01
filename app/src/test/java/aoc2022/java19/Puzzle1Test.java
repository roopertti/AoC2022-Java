package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle1;

public class Puzzle1Test {
  public static final String validInput = """
      1000
      2000

      5000
      6000

      3500

      7000
      500

      6000
      900
      400
        """;

  @Test
  void getExpectedSuccessfulOutputPart1() {
    String expectedOutput = "11000";

    Puzzle1 puzzle1 = new Puzzle1(validInput);

    assertEquals(expectedOutput, puzzle1.getPart1Output());
  }

  @Test
  void getExpectedSuccessfulOutputPart2() {
    String expectedOutput = "25800";

    Puzzle1 puzzle1 = new Puzzle1(validInput);

    assertEquals(expectedOutput, puzzle1.getPart2Output());
  }

  @Test
  void getExceptionForExtraEmptyRows() {
    String input = """
        1000
        2000

        5000
        6000




        """;

    assertThrows(NumberFormatException.class, () -> new Puzzle1(input));
  }

  @Test
  void getExceptionForBadValues() {
    String input = """
        asd
        fghf
        refer
        """;

    assertThrows(NumberFormatException.class, () -> new Puzzle1(input));
  }
}
