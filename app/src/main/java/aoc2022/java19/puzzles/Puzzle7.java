package aoc2022.java19.puzzles;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Puzzle7 extends Puzzle {
  private Map<String, Integer> directoriesBySizes;

  public Puzzle7(String input) {
    super(input);
    LinkedList<String> terminalOutput = new LinkedList<String>(Arrays.asList(input.split("\\n")));
    directoriesBySizes = getDirectorySizesByName(terminalOutput, new HashMap<>(), new LinkedList<>(),
        new HashSet<>());
  }

  @Override
  public int getDay() {
    return 7;
  }

  @Override
  public String getPart1Output() {
    int maxTotal = 100000;
    int sum = directoriesBySizes.values().stream()
        .filter(value -> value <= maxTotal)
        .mapToInt(v -> v)
        .sum();
    return String.valueOf(sum);
  }

  @Override
  public String getPart2Output() {
    int minimumSpace = 70000000 - 30000000;
    int usedSpace = directoriesBySizes.get("/");
    int smallestDirSize = directoriesBySizes.values().stream()
        .filter(size -> minimumSpace >= (usedSpace - size))
        .mapToInt(v -> v)
        .min()
        .orElse(0);
    return String.valueOf(smallestDirSize);
  }

  private Map<String, Integer> getDirectorySizesByName(LinkedList<String> remainingOutput,
      Map<String, Integer> directorySizes, Deque<String> currentPath, Set<String> countedFiles) {
    /* When output has been processed, return map of directory sizes */
    if (remainingOutput.isEmpty()) {
      return directorySizes;
    }

    String currentCmd = remainingOutput.removeFirst();

    /* On cd, either remove from currentPath or add dir to it */
    if (currentCmd.indexOf("$ cd") == 0) {
      if (currentCmd.indexOf("$ cd ..") == 0) {
        currentPath.removeLast();
      } else {
        String directoryName = currentCmd.split(" ")[2];
        currentPath.add(directoryName);
      }
    }

    /* On ls, it is time to calculate sizes for dirs in current path */
    if (currentCmd.indexOf("$ ls") == 0) {
      int fileSizeSum = 0;

      // Pop output rows until the next row after current one is a command
      while (!remainingOutput.isEmpty() && remainingOutput.getFirst().charAt(0) != '$') {
        String fileOrDir = remainingOutput.removeFirst();

        // Just ignoring dirs for now, only file sizes matter
        if (fileOrDir.indexOf("dir") != 0) {
          String[] parts = fileOrDir.split(" ");
          String filePath = stringifyPath(currentPath, parts[1]);

          // Whenever files have been counted, "blacklist" them in countedFiles
          if (!countedFiles.contains(filePath)) {
            fileSizeSum += Integer.valueOf(parts[0]);
            countedFiles.add(filePath);
          }
        }
      }

      /* Time to increase sizes for each dir on current path (starting from root) */
      String dirPath = "";
      var pathIterator = currentPath.iterator();

      while (pathIterator.hasNext()) {
        dirPath += pathIterator.next();
        if (directorySizes.containsKey(dirPath))
          directorySizes.put(dirPath, directorySizes.get(dirPath) + fileSizeSum);
        else
          directorySizes.put(dirPath, fileSizeSum);
      }
    }

    return getDirectorySizesByName(remainingOutput, directorySizes, currentPath, countedFiles);
  }

  private static String stringifyPath(Deque<String> path, String fileName) {
    var pathStr = "";
    var iterator = path.iterator();

    while (iterator.hasNext()) {
      pathStr += iterator.next();
    }

    return pathStr + fileName;
  }
}
