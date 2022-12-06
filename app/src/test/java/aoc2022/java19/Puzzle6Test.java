package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aoc2022.java19.puzzles.Puzzle6;

public class Puzzle6Test {
  @Test
  void getSuccessfulOutputPart1() {
    String input1 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
    String output1 = "5";
    String input2 = "nppdvjthqldpwncqszvftbrmjlhg";
    String output2 = "6";
    String input3 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
    String output3 = "10";
    String input4 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
    String output4 = "11";
  
    Puzzle6 puzzle6_1 = new Puzzle6(input1);
    Puzzle6 puzzle6_2 = new Puzzle6(input2);
    Puzzle6 puzzle6_3 = new Puzzle6(input3);
    Puzzle6 puzzle6_4 = new Puzzle6(input4);

    assertEquals(puzzle6_1.getPart1Output(), output1);
    assertEquals(puzzle6_2.getPart1Output(), output2);
    assertEquals(puzzle6_3.getPart1Output(), output3);
    assertEquals(puzzle6_4.getPart1Output(), output4);
  }

  @Test
  void getSuccessfulOutputPart2() {
    String input1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
    String input2 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
    String input3 = "nppdvjthqldpwncqszvftbrmjlhg";
    String input4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
    String input5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

    String output1 = "19";
    String output2 = "23";
    String output3 = "23";
    String output4 = "29";
    String output5 = "26";

    Puzzle6 puzzle6_1 = new Puzzle6(input1);
    Puzzle6 puzzle6_2 = new Puzzle6(input2);
    Puzzle6 puzzle6_3 = new Puzzle6(input3);
    Puzzle6 puzzle6_4 = new Puzzle6(input4);
    Puzzle6 puzzle6_5 = new Puzzle6(input5);

    assertEquals(puzzle6_1.getPart2Output(), output1);
    assertEquals(puzzle6_2.getPart2Output(), output2);
    assertEquals(puzzle6_3.getPart2Output(), output3);
    assertEquals(puzzle6_4.getPart2Output(), output4);
    assertEquals(puzzle6_5.getPart2Output(), output5);
  }
}
