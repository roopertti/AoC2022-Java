package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle2;

public class Puzzle2Test {
  public static final String validInput = """
    C Z
    C X
    C Y
    C Y
    B Y
    B Y
    C Z
    C Y
    A Y
    B Z
    B Z
    C Y
    B Z
    A Y
    C Y
    B Y
    C Y
    B Y
    B X
    A Y
    A Y
    C Y
    B X
    C X
    B Y
    A Y
    C Z
    C X
      """;
  
  public static final String invalidChars = """
    B O
      """;

  public static final String missingChars = """
    C Y
    B
      X
      """;

  @Test
  void getExpectedOutputPart1() {
    String expectedOutput = "147";
    Puzzle2 puzzle2 = new Puzzle2(validInput);
    assertEquals(expectedOutput, puzzle2.getPart1Output());
  }

  @Test
  void getExpectedOutputPart2() {
    String expectedOutput = "143";
    Puzzle2 puzzle2 = new Puzzle2(validInput);
    assertEquals(expectedOutput, puzzle2.getPart2Output());
  }

  @Test
  void invalidCharsPart1() {
    Puzzle2 puzzle2 = new Puzzle2(invalidChars);
    assertThrows(NullPointerException.class, () -> puzzle2.getPart1Output());
  }

  @Test
  void invalidCharsPart2() {
    Puzzle2 puzzle2 = new Puzzle2(invalidChars);
    assertThrows(NullPointerException.class, () -> puzzle2.getPart2Output());
  }

  @Test
  void missingCharsPart1() {
    Puzzle2 puzzle2 = new Puzzle2(missingChars);
    assertThrows(NullPointerException.class, () -> puzzle2.getPart1Output());
  }

  @Test
  void missingCharsPart2() {
    Puzzle2 puzzle2 = new Puzzle2(missingChars);
    assertThrows(StringIndexOutOfBoundsException.class, () -> puzzle2.getPart2Output());
  }
}
