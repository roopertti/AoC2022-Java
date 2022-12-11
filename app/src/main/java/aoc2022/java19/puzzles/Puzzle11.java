package aoc2022.java19.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record Monkey(int id, String operation, int divisibleBy, int nextTarget, int fallbackTarget) {
  public static Monkey of(int id, String operation, int divisibleBy, int nextTarget, int fallbackTarget) {
    return new Monkey(id, operation, divisibleBy, nextTarget, fallbackTarget);
  }

  public Long getWorryLevelFor(Long item) {
    var parts = operation.split(" ");
    Long other = parts[2].equals("old") ? item : Long.valueOf(parts[2]);

    switch (parts[1]) {
      case "*":
        return item * other;
      case "+":
        return item + other;
      default:
        return item;
    }
  }
}

public class Puzzle11 extends Puzzle {
  List<List<Long>> startingItems;
  List<Monkey> monkeys;

  public Puzzle11(String input) {
    super(input);
    List<String> monkeysRaw = Arrays.asList(input.split("\\n\\n"));

    startingItems = new ArrayList<>();
    monkeys = new ArrayList<>();

    for (String monkeyRaw : monkeysRaw) {
      var parts = monkeyRaw.split("\\n");
      var id = Character.getNumericValue(parts[0].charAt(7));
      List<Long> items = Arrays
          .stream(parts[1].substring("  Starting items: ".length(), parts[1].length()).split(", "))
          .map(item -> Long.valueOf(item)).toList();
      var operation = parts[2].substring("  Operation: new = ".length(), parts[2].length());
      var divisibleBy = Integer.valueOf(parts[3].substring("  Test: divisible by ".length(), parts[3].length()));
      var nextTarget = Integer
          .valueOf(parts[4].substring("    If true: throw to monkey ".length(), parts[4].length()));
      var fallbackTarget = Integer
          .valueOf(parts[5].substring("    If false: throw to monkey ".length(), parts[5].length()));

      startingItems.add(items);
      monkeys.add(Monkey.of(id, operation, divisibleBy, nextTarget, fallbackTarget));
    }
  }

  @Override
  public int getDay() {
    return 11;
  }

  @Override
  public String getPart1Output() {
    var maxValues = getInspectionsAfterThrowingRounds(20, 3).values().stream().sorted(Comparator.reverseOrder())
        .limit(2)
        .reduce((a, b) -> a * b).orElse(0l);
    return String.valueOf(maxValues);
  }

  @Override
  public String getPart2Output() {
    var maxValues = getInspectionsAfterThrowingRounds(10000, null).values().stream().sorted(Comparator.reverseOrder())
        .limit(2)
        .reduce((a, b) -> a * b).orElse(0l);
    return String.valueOf(maxValues);
  }

  public Map<Integer, Long> getInspectionsAfterThrowingRounds(int amountOfRounds, Integer divideBy) {
    Map<Integer, Long> inspectionsByMonkeyId = monkeys.stream().collect(Collectors.toMap(Monkey::id, __ -> 0l));
    Map<Integer, LinkedList<Long>> itemsByMonkeyId = IntStream.range(0, startingItems.size())
        .boxed()
        .collect(Collectors.toMap(Function.identity(), i -> new LinkedList<>(startingItems.get(i))));

    while (amountOfRounds > 0) {
      for (Monkey thrower : this.monkeys) {
        var remainingItems = itemsByMonkeyId.get(thrower.id());

        while (!remainingItems.isEmpty()) {
          inspectionsByMonkeyId.put(thrower.id(), inspectionsByMonkeyId.get(thrower.id()) + 1);

          // thanks to reddit for this one
          int magicModulo = monkeys.stream().mapToInt(Monkey::divisibleBy).reduce(1, (a, b) -> a * b);
          
          Long item = thrower.getWorryLevelFor(remainingItems.removeFirst());
          Long inspectedItem = divideBy != null ? item / 3 : item % magicModulo;

          if (inspectedItem % thrower.divisibleBy() == 0)
            itemsByMonkeyId.get(thrower.nextTarget()).add(inspectedItem);
          else
            itemsByMonkeyId.get(thrower.fallbackTarget()).add(inspectedItem);
        }

      }
      amountOfRounds--;
    }

    return inspectionsByMonkeyId;
  }
}
