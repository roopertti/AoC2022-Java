package aoc2022.java19.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

record Point(int x, int y) {
  public static Point of(int x, int y) {
    return new Point(x, y);
  }

  public static Point origo() {
    return new Point(0, 0);
  }

  public Point getNextMovePointAccordingTo(Point target) {
    int nextX = x;
    int nextY = y;

    if (target.x > x)
      nextX++;
    else if (target.x < x)
      nextX--;

    if (target.y > y)
      nextY++;
    else if (target.y < y)
      nextY--;

    return Point.of(nextX, nextY);
  }

  public boolean isAdjacentTo(Point other) {
    int horizontalDistance = Math.abs(x - other.x);
    int verticalDistance = Math.abs(y - other.y);

    return (horizontalDistance == 0 || horizontalDistance == 1) && (verticalDistance == 0 || verticalDistance == 1);
  }
}

public class Puzzle9 extends Puzzle {
  private List<String> movements;

  public Puzzle9(String input) {
    super(input);
    this.movements = Arrays.stream(input.split("\\n")).toList();
  }

  @Override
  public int getDay() {
    return 9;
  }

  @Override
  public String getPart1Output() {
    Set<Point> discoveredPoints = getPointsVisitedByTail(this.movements, 2);
    return String.valueOf(discoveredPoints.size());
  }

  @Override
  public String getPart2Output() {
    Set<Point> discoveredPoints = getPointsVisitedByTail(this.movements, 10);
    return String.valueOf(discoveredPoints.size());
  }

  private Set<Point> getPointsVisitedByTail(List<String> movements, int amountOfKnots) {
    Set<Point> discoveredPoints = new HashSet<>();
    LinkedList<String> movementQueue = new LinkedList<>(movements);
    List<Point> nodes = IntStream.range(0, amountOfKnots).boxed().map(__ -> Point.origo()).toList();
    Point target = Point.origo();

    while (true) {
      var head = nodes.get(0);
      var tail = nodes.get(nodes.size() - 1);
      if (!discoveredPoints.contains(tail)) {
        discoveredPoints.add(tail);
      }

      // Base case, target is reached by head and movementQueue is empty
      if (target.equals(head) && movementQueue.isEmpty()) {
        break;
      }

      // If target is reached, it is time to parse next movement command and set new
      // target
      if (target.equals(head)) {
        var parts = movementQueue.removeFirst().split(" ");
        int nextX = head.x();
        int nextY = head.y();

        switch (parts[0]) {
          case "L":
            nextX -= Integer.valueOf(parts[1]);
            break;
          case "U":
            nextY -= Integer.valueOf(parts[1]);
            break;
          case "R":
            nextX += Integer.valueOf(parts[1]);
            break;
          case "D":
            nextY += Integer.valueOf(parts[1]);
            break;
          default:
            break;
        }

        target = Point.of(nextX, nextY);
        continue;
      }

      // We'll end up here if target is not reached, so update positions
      nodes = updateNodes(nodes, target);
    }

    return discoveredPoints;
  }

  private List<Point> updateNodes(List<Point> nodes, Point target) {
    List<Point> updatedNodes = new ArrayList<>();
    Point lastPoint = nodes.get(0).getNextMovePointAccordingTo(target);
    updatedNodes.add(lastPoint);

    for (Point node : nodes.subList(1, nodes.size())) {
      Point nextPoint = node.isAdjacentTo(lastPoint) ? node : node.getNextMovePointAccordingTo(lastPoint);
      updatedNodes.add(nextPoint);
      lastPoint = nextPoint;
    }

    return updatedNodes;
  }
}
