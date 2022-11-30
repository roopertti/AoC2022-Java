package aoc2022.java19.puzzles;

/**
 * This Puzzle class should be extended by each puzzle class
 */
public abstract class Puzzle {
  /**
   * Input string for puzzle, might contain multiple line breaks etc
   */
  private final String input;

  public Puzzle(String input) {
    this.input = input;
  }

  public String getInput() {
    return this.input;
  }

  /**
   * Each puzzle provides a day number from 1 - 25
   * @return The day number of the puzzle
   */
  public abstract int getDay();

  /**
   * Provides clean output for part 1 of the puzzle
   * @return Solution output for part 1
   */
  public abstract String getPart1Output();

  /**
   * Provides clean output for part 2 of the puzzle
   * @return Solution output for part 2
   */
  public abstract String getPart2Output();

  /**
   * Produces "pretty" output for both parts
   * @return
   */
  public String produceOutputs() {
    return """
         ~ DAY %s OUTPUT ~

        Part 1:
        > %s

        Part 2:
        > %s

        """.formatted(getDay(), getPart1Output(), getPart2Output());
  }
}
