package aoc2022.java19.puzzles;

public class Puzzle6 extends Puzzle {
  public Puzzle6(String input) {
    super(input);
  }

  @Override
  public int getDay() {
    return 6;
  }

  @Override
  public String getPart1Output() {
    int marker = processDataStream(getInput(), 4, 4);
    return String.valueOf(marker);
  }

  @Override
  public String getPart2Output() {
    int marker = processDataStream(getInput(), 14, 14);
    return String.valueOf(marker);
  }

  private static int processDataStream(String remainingStream, int currentMarker, int bufferLength) {
    boolean doesBufferContainDistinctValues = remainingStream
        .substring(0, bufferLength).chars().boxed().distinct()
        .count() == bufferLength;

    return doesBufferContainDistinctValues ? currentMarker
        : processDataStream(remainingStream.substring(1), currentMarker + 1, bufferLength);
  }
}
