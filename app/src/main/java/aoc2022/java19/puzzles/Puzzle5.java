package aoc2022.java19.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

record Movement(int amount, int from, int to) {
  public static Movement of(String movementRow) {
    var parts = movementRow.split(" ");
    return new Movement(
        Integer.valueOf(parts[1]),
        Integer.valueOf(parts[3]) - 1,
        Integer.valueOf(parts[5]) - 1);
  }

  public void applyMovements(List<Stack<Character>> stacks) {
    for (int i = 0; i < amount; i++) {
      var crate = stacks.get(from).pop();
      stacks.get(to).add(crate);
    }
  }

  public void applyBatchMovement(List<Stack<Character>> stacks) {
    List<Character> crateBatch = new ArrayList<>();

    for (int i = 0; i < amount; i++) {
      crateBatch.add(stacks.get(from).pop());
    }

    for (int i = crateBatch.size() - 1; i >= 0; i--) {
      stacks.get(to).add(crateBatch.get(i));
    }
  }
}

public class Puzzle5 extends Puzzle {
  private final Pattern CRATE_PATTERN = Pattern.compile("[A-Z]");

  private List<Stack<Character>> stacks;
  private List<Movement> movements;

  public Puzzle5(String input) {
    super(input);
    var parts = input.split("\\n{2}");

    /* Initialize stack list and create list of Movements */
    stacks = new ArrayList<>();
    movements = Arrays.asList(parts[1].split("\\n")).stream().map(Movement::of).toList();

    /*
     * Split stack rows by newline, get position row separately to check crate
     * positions
     */
    var stackRows = Arrays.asList(parts[0].split("\\n"));
    var positionRow = stackRows.get(stackRows.size() - 1);

    /* Go through stack rows in reverse order */
    for (int i = stackRows.size() - 2; i >= 0; i--) {
      var currentRow = stackRows.get(i);
      var matcher = CRATE_PATTERN.matcher(currentRow); // Locate crates with A-Z pattern

      while (matcher.find()) {
        /* Here we are just sorting crates to correct stacks (by position) */
        var crate = matcher.group().charAt(0);
        var position = Character.getNumericValue(positionRow.charAt(matcher.start())) - 1;

        if (position < stacks.size() && stacks.get(position) != null) {
          stacks.get(position).add(crate);
        } else {
          var newStack = new Stack<Character>();
          newStack.add(crate);
          stacks.add(newStack);
        }
      }
    }
  }

  @Override
  public int getDay() {
    return 5;
  }

  @Override
  public String getPart1Output() {
    var stacksCopy = stacks.stream().map(Puzzle5::copyStack).toList();
    movements.forEach(movement -> movement.applyMovements(stacksCopy));
    var topCrates = stacksCopy.stream().map(Stack::pop).map(String::valueOf).collect(Collectors.joining());
    return topCrates;
  }

  @Override
  public String getPart2Output() {
    var stacksCopy = stacks.stream().map(Puzzle5::copyStack).toList();
    movements.forEach(movement -> movement.applyBatchMovement(stacksCopy));
    var topCrates = stacksCopy.stream().map(Stack::pop).map(String::valueOf).collect(Collectors.joining());
    return topCrates;
  }

  private static Stack<Character> copyStack(Stack<Character> stack) {
    return (Stack<Character>) stack.clone();
  }
}
