package aoc2022.java19.puzzles;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Puzzle3 extends Puzzle {
  List<String> contents;

  public Puzzle3(String input) {
    super(input);
    contents = Arrays.asList(input.split("\\n"));
  }

  @Override
  public int getDay() {
    return 3;
  }

  @Override
  public String getPart1Output() {
    int sum = contents.stream()
        .map(Puzzle3::getOverlappingItemFromRucksack)
        .map(Puzzle3::getItemPriority)
        .reduce(0, Integer::sum);
    return String.valueOf(sum);
  }

  @Override
  public String getPart2Output() {
    int sum = Lists.partition(contents, 3).stream()
        .map(Puzzle3::getOverlappingItemFromGroup)
        .map(Puzzle3::getItemPriority)
        .reduce(0, Integer::sum);
    return String.valueOf(sum);
  }

  private static Integer getOverlappingItemFromRucksack(String content) {
    /* Divide given contents to two compartments */
    int divider = content.length() / 2;
    String firstCompartment = content.substring(0, divider);
    String secondCompartment = content.substring(divider);

    return getIntersections(List.of(firstCompartment, secondCompartment), Set.of()).get(0);
  }

  private static Integer getOverlappingItemFromGroup(List<String> groupContents) {
    return getIntersections(groupContents, Set.of()).get(0);
  }

  /**
   * Get intersecting char values from given list of strings. Also accepts a list of intersections (used in recursion)
   */
  private static List<Integer> getIntersections(List<String> contents, Set<Integer> intersections) {
    if (contents.size() <= 1)
      return intersections.stream().toList();

    var currentItems = contents.get(0).chars().boxed().collect(Collectors.toSet());

    /* Check overlaps by comparing to next contents and intersections (if intersections have any values) */
    var overlaps = contents.get(1).chars().boxed()
        .filter(item -> intersections.isEmpty() || intersections.contains(item))
        .filter(item -> currentItems.contains(item))
        .collect(Collectors.toSet());
    
    if (contents.size() == 2)
      return overlaps.stream().toList();

    return getIntersections(contents.subList(1, contents.size()), overlaps);
  }

  /**
   * Determine item priority by checking ASCII table position and offsetting by puzzle scores
   */
  private static Integer getItemPriority(Integer item) {
    if (item >= 96 && item <= 122)
      return item % 96;
    if (item >= 65 && item <= 96)
      return item % 65 + 27;
    return 0;
  }
}
