package aoc2022.java19;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class InputReaderTest {
  @Test
  void readFileThatExists() throws IOException {
    String output = InputReader.read("test.txt");
    assertEquals(output, "test", "should read file and have expected content");
  }

  @Test
  void readFileThatDoesNotExist() throws IOException {
    assertThrows(IllegalArgumentException.class, () -> InputReader.read("notfound.txt"));
  }
}
