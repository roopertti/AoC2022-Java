package aoc2022.java19.puzzles;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Puzzle1 extends Puzzle {
  private static final String GROUP_SEPARATOR = "\\n{2}";
  private List<Integer> calories;

  @Override
  public int getDay() {
    return 1;
  }

  public Puzzle1(String input) {
    super(input);
    List<String> groups = Arrays.asList(input.split(GROUP_SEPARATOR));
    this.calories = groups.stream().map(str -> str.lines().mapToInt(Integer::valueOf).reduce(0, Integer::sum)).toList();
  }

  @Override
  public String getPart1Output() {
    int max = this.calories.stream().mapToInt(v -> v).max().orElse(0);
    return String.valueOf(max);
  }

  @Override
  public String getPart2Output() {
    int sumOfTopThree = this.calories.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(v -> v).sum();
    return String.valueOf(sumOfTopThree);
  }
}
