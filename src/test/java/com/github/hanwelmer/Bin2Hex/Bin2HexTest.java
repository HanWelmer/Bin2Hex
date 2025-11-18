package com.github.hanwelmer.Bin2Hex;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class Bin2HexTest {

  /**
   * Test usage output if no parameters are passed.
   */
  @Test
  public void testUsage() {
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);

    // Do the test, with System.out redirected to our ps PrintStream.
    String[] args = { "", "" };
    Bin2Hex.main(args);

    // Put things back
    System.out.flush();
    System.setOut(old);

    // Test what happened
    // assertTrue(baos.size() == 0);
    assertTrue(baos.toString().startsWith("Usage: java -jar Bin2Hex.jar filename.ext"));
  }
}
