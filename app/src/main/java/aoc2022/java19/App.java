/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc2022.java19;

import java.util.List;

import aoc2022.java19.puzzles.Puzzle;
import aoc2022.java19.puzzles.Puzzle1;
import aoc2022.java19.puzzles.Puzzle2;
import aoc2022.java19.puzzles.Puzzle3;
import aoc2022.java19.puzzles.Puzzle4;
import aoc2022.java19.puzzles.Puzzle5;
import aoc2022.java19.puzzles.Puzzle6;
import aoc2022.java19.puzzles.Puzzle7;

public class App {
    /* Very cool banner */
    private static final String BANNER = """
            AOC 2022 Java edition
            * *       * * *** *
             *       *         **
                 *     *     *
            *        ** *
                * **
              *              *
                           *
                       *  *     *
             *                 *


                            * *

                    """;



    public static void main(String[] args) {
        System.out.println(BANNER); // Print cool banner

        try {
            /* List puzzles here with dedicated inputs */
            List<Puzzle> puzzles = List.of(
                new Puzzle1(InputReader.read("1.txt")),
                new Puzzle2(InputReader.read("2.txt")),
                new Puzzle3(InputReader.read("3.txt")),
                new Puzzle4(InputReader.read("4.txt")),
                new Puzzle5(InputReader.read("5.txt")),
                new Puzzle6(InputReader.read("6.txt")),
                new Puzzle7(InputReader.read("7.txt"))
            );

            /* Produce outputs for each puzzle */
            puzzles.stream().map(Puzzle::produceOutputs).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
