package aoc2022.java19.puzzles;

/**
 * Just a little example implementation of a puzzle class
 */
public class Example extends Puzzle {

  public Example(String input) {
    super(input);
  }

  @Override
  public int getDay() {
    return 999;
  }

  /**
   * Just provide count of lines in string
   */
  @Override
  public String getPart1Output() {
    return "%s".formatted(getInput().lines().count());
  }

  /**
   * Output first line from file
   */
  @Override
  public String getPart2Output() {
    return getInput().lines().findFirst().orElse("File was empty!");
  }
}
