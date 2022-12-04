package aoc2022.java19.puzzles;

import java.util.Arrays;
import java.util.List;

record SectionRange(int start, int end) {
  public static SectionRange of(String sectionRangeStr) {
    var range = sectionRangeStr.split("-");
    return new SectionRange(Integer.valueOf(range[0]), Integer.valueOf(range[1]));
  }

  public boolean containsOrIsContainedBy(SectionRange other) {
    return (start <= other.start && end >= other.end) || (other.start <= start && other.end >= end);
  }

  public boolean containsSection(int sectionNumber) {
    return start <= sectionNumber && end >= sectionNumber;
  }

  public boolean overlapsOrIsOverlappedBy(SectionRange other) {
    return this.containsSection(other.start)
        || this.containsSection(other.end)
        || other.containsSection(start)
        || other.containsSection(end);
  }
}

public class Puzzle4 extends Puzzle {
  private List<List<SectionRange>> sectionRangePairs;

  public Puzzle4(String input) {
    super(input);
    sectionRangePairs = splitToList(input, "\\n").stream()
        .map(sectionPairStr -> splitToList(sectionPairStr, ",").stream().map(SectionRange::of).toList())
        .toList();
  }

  @Override
  public int getDay() {
    return 4;
  }

  @Override
  public String getPart1Output() {
    var count = sectionRangePairs.stream().filter(Puzzle4::areSectionsContained).count();
    return String.valueOf(count);
  }

  @Override
  public String getPart2Output() {
    var count = sectionRangePairs.stream().filter(Puzzle4::areSectionsOverlapping).count();
    return String.valueOf(count);
  }

  private static List<String> splitToList(String str, String delimiter) {
    return Arrays.asList(str.split(delimiter));
  }

  private static boolean areSectionsContained(List<SectionRange> sections) {
    return sections.get(0).containsOrIsContainedBy(sections.get(1));
  }

  private static boolean areSectionsOverlapping(List<SectionRange> sections) {
    return sections.get(0).overlapsOrIsOverlappedBy(sections.get(1));
  }
}
