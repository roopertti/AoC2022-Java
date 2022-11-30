package aoc2022.java19;

import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * Little utility class for reading puzzle inputs from filesystem
 */
public class InputReader {
  public static String read(String fileName) throws IOException {
    return Resources.toString(Resources.getResource(fileName), Charsets.UTF_8);
  }
}
