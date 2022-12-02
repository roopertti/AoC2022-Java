package aoc2022.java19.puzzles;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Puzzle2 extends Puzzle {
  private static final Map<Character, Integer> selectionScores = Map.of('Z', 3, 'Y', 2, 'X', 1);
  
  private static final Map<String, Integer> matchScoresPart1 = Map.of(
      "A X", 3,
      "A Y", 6,
      "A Z", 0,
      "B X", 0,
      "B Y", 3,
      "B Z", 6,
      "C X", 6,
      "C Y", 0,
      "C Z", 3);

  private static final Map<String, Character> selectionMapPart2 = Map.of(
    "A X", 'Z',
    "A Y", 'X',
    "A Z", 'Y',
    "B X", 'X',
    "B Y", 'Y',
    "B Z", 'Z',
    "C X", 'Y',
    "C Y", 'Z',
    "C Z", 'X'
  );
  private static final Map<Character, Integer> matchScoresPart2 = Map.of(
    'X', 0,
    'Y', 3,
    'Z', 6
  );

  private List<String> matches;

  public Puzzle2(String input) {
    super(input);
    this.matches = Arrays.stream(input.split("\\n")).toList();
  }

  @Override
  public int getDay() {
    return 2;
  }

  @Override
  public String getPart1Output() {
    return String.valueOf(this.matches.stream().map(Puzzle2::determineScorePart1).mapToInt(v -> v).sum());
  }

  @Override
  public String getPart2Output() {
    return String.valueOf(this.matches.stream().map(Puzzle2::determineScorePart2).mapToInt(v -> v).sum());
  }

  private static int determineScorePart1(String match) {
    int matchScore = matchScoresPart1.get(match);
    int selectionScore = selectionScores.get(match.charAt(2));
    return matchScore + selectionScore;
  }

  private static int determineScorePart2(String match) {
    int matchScore = matchScoresPart2.get(match.charAt(2));
    char selection = selectionMapPart2.get(match);
    int selectionScore = selectionScores.get(selection);
    return matchScore + selectionScore;
  }
}
