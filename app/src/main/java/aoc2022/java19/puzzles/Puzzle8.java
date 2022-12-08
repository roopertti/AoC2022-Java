package aoc2022.java19.puzzles;

import java.util.ArrayList;
import java.util.List;

record TreeVisibilityDetails(int x, int y, int scenicScore, boolean isVisibleFromOutside) {
  public static TreeVisibilityDetails of(int x, int y, int scenicScore, boolean isVisibleFromOutside) {
    return new TreeVisibilityDetails(x, y, scenicScore, isVisibleFromOutside);
  }
}

public class Puzzle8 extends Puzzle {
  private int[][] forest;
  private int forestWidth;
  private int forestHeight;
  private List<TreeVisibilityDetails> treeDetails;

  public Puzzle8(String input) {
    super(input);
    String[] lines = input.split("\\n");
    forestHeight = lines[0].length();
    forestWidth = lines.length;
    forest = createTreeMatrix(lines);
    treeDetails = createTreeDetails();
  }

  @Override
  public int getDay() {
    return 8;
  }

  @Override
  public String getPart1Output() {
    var amountOfTreesVisibleOutside = treeDetails.stream()
        .filter(TreeVisibilityDetails::isVisibleFromOutside)
        .count();
    return String.valueOf(amountOfTreesVisibleOutside);
  }

  @Override
  public String getPart2Output() {
    var highestScenicScore = treeDetails.stream()
        .map(TreeVisibilityDetails::scenicScore)
        .mapToInt(v -> v)
        .max()
        .orElse(0);
    return String.valueOf(highestScenicScore);
  }

  private int[][] createTreeMatrix(String[] lines) {
    int[][] treeMatrix = new int[forestHeight][forestWidth];
    int x = 0;
    int y = 0;
    for (String row : lines) {
      for (int tree : row.chars().toArray()) {
        treeMatrix[y][x] = Character.getNumericValue(tree);
        x++;
      }
      y++;
      x = 0;
    }

    return treeMatrix;
  }

  private List<TreeVisibilityDetails> createTreeDetails() {
    List<TreeVisibilityDetails> details = new ArrayList<>();
    int x = 0;
    int y = 0;
    while (y < forestHeight) {
      while (x < forestWidth) {
        details.add(calculateTreeVisibilityDetailsForPoint(x, y));
        x++;
      }
      y++;
      x = 0;
    }

    return details;
  }

  private TreeVisibilityDetails calculateTreeVisibilityDetailsForPoint(int x, int y) {
    // Border trees can only have score of 0 because of multiplication and are
    // always visible from outside
    if (x == 0 || x == forestWidth - 1 || y == 0 || y == forestHeight - 1)
      return TreeVisibilityDetails.of(x, y, 0, true);

    int currentTreeHeight = forest[y][x];

    int leftCursor = 0;
    int topCursor = 0;
    int rightCursor = 0;
    int bottomCursor = 0;

    boolean isVisibleOutside = false;
    boolean isLeftMaxReached = false;
    boolean isTopMaxReached = false;
    boolean isRightMaxReached = false;
    boolean isBottomMaxReached = false;

    while (!isLeftMaxReached || !isTopMaxReached || !isRightMaxReached || !isBottomMaxReached) {
      if (!isLeftMaxReached) {
        leftCursor++;
        boolean isCursorAtEnd = x - leftCursor == 0;
        boolean isNextTreeBlocking = forest[y][x - leftCursor] >= currentTreeHeight;
        isLeftMaxReached = isCursorAtEnd || isNextTreeBlocking;
        isVisibleOutside = isVisibleOutside || (isCursorAtEnd && !isNextTreeBlocking);
      }

      if (!isTopMaxReached) {
        topCursor++;
        boolean isCursorAtEnd = y - topCursor == 0;
        boolean isNextTreeBlocking = forest[y - topCursor][x] >= currentTreeHeight;
        isTopMaxReached = isCursorAtEnd || isNextTreeBlocking;
        isVisibleOutside = isVisibleOutside || isCursorAtEnd && !isNextTreeBlocking;
      }

      if (!isRightMaxReached) {
        rightCursor++;
        boolean isCursorAtEnd = (x + rightCursor) == (forestWidth - 1);
        boolean isNextTreeBlocking = forest[y][x + rightCursor] >= currentTreeHeight;
        isRightMaxReached = isCursorAtEnd || isNextTreeBlocking;
        isVisibleOutside = isVisibleOutside || isCursorAtEnd && !isNextTreeBlocking;
      }

      if (!isBottomMaxReached) {
        bottomCursor++;
        boolean isCursorAtEnd = (y + bottomCursor) == (forestHeight - 1);
        boolean isNextTreeBlocking = forest[y + bottomCursor][x] >= currentTreeHeight;
        isBottomMaxReached = isCursorAtEnd || isNextTreeBlocking;
        isVisibleOutside = isVisibleOutside || isCursorAtEnd && !isNextTreeBlocking;
      }
    }

    int scenicScore = leftCursor * topCursor * rightCursor * bottomCursor;

    return TreeVisibilityDetails.of(x, y, scenicScore, isVisibleOutside);
  }
}
