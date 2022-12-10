package aoc2022.java19.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Puzzle10 extends Puzzle {
  private int signalStrenghtSum = 0;
  private List<Character> pixels = new ArrayList<>();

  public Puzzle10(String input) {
    super(input);
    var instructions = Arrays.asList(input.split("\\n"));
    runInstructions(instructions);
  }

  @Override
  public int getDay() {
    return 10;
  }

  @Override
  public String getPart1Output() {
    return String.valueOf(signalStrenghtSum);
  }

  @Override
  public String getPart2Output() {
    return renderScreenAsString();
  }

  private void runInstructions(List<String> instructions) {
    LinkedList<String> remainingInstructions = new LinkedList<>(instructions);
    int amountOfCyclesRan = 0;
    String pendingInstruction = null;
    int xRegister = 1;

    while (true) {
      if (remainingInstructions.isEmpty() && pendingInstruction == null) {
        break;
      }

      amountOfCyclesRan++;
      int spriteCenter = xRegister + 1;
      int cyclePosition = amountOfCyclesRan % 40;

      if (spriteCenter == cyclePosition || Math.abs(cyclePosition - spriteCenter) == 1) {
        pixels.add('#');
      } else {
        pixels.add('.');
      }

      if ((amountOfCyclesRan == 20 || (amountOfCyclesRan - 20) % 40 == 0) && amountOfCyclesRan <= 220)
        signalStrenghtSum += amountOfCyclesRan * xRegister;

      if (pendingInstruction != null) {
        var parts = pendingInstruction.split(" ");
        xRegister += Integer.valueOf(parts[1]);
        pendingInstruction = null;
        continue;
      }

      String nextInstruction = remainingInstructions.removeFirst();

      if (nextInstruction.indexOf("noop") == 0) {
        continue;
      }

      if (nextInstruction.indexOf("addx") == 0) {
        pendingInstruction = nextInstruction;
      }
    }
  }

  private String renderScreenAsString() {
    String output = "";
    int position = 1;

    for (Character pixel : pixels) {
      output += pixel;
      if (position % 40 == 0)
        output += "\n";
      position++;
    }

    return output;
  }
}
