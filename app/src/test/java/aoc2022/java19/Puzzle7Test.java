package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle7;

public class Puzzle7Test {
  @Test
  void getExpectedOutputPart1() {
    String input = """
      $ cd /
      $ ls
      dir a
      14848514 b.txt
      8504156 c.dat
      dir d
      $ cd a
      $ ls
      dir e
      29116 f
      2557 g
      62596 h.lst
      $ cd e
      $ ls
      584 i
      $ cd ..
      $ cd ..
      $ cd d
      $ ls
      4060174 j
      8033020 d.log
      5626152 d.ext
      7214296 k
      """;
    String expectedOutput = "95437";
    Puzzle7 puzzle7 = new Puzzle7(input);

    assertEquals(expectedOutput, puzzle7.getPart1Output());
  }

  @Test
  void getExpectedOutputPart2() {
    String input = """
      $ cd /
      $ ls
      dir a
      14848514 b.txt
      8504156 c.dat
      dir d
      $ cd a
      $ ls
      dir e
      29116 f
      2557 g
      62596 h.lst
      $ cd e
      $ ls
      584 i
      $ cd ..
      $ cd ..
      $ cd d
      $ ls
      4060174 j
      8033020 d.log
      5626152 d.ext
      7214296 k
      """;
    String expectedOutput = "24933642";
    Puzzle7 puzzle7 = new Puzzle7(input);

    assertEquals(expectedOutput, puzzle7.getPart2Output());
  }
}
