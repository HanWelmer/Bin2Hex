/*
Copyright © 2024 Han Welmer.

This file is part of the Bin2Hex utility.

Bin2Hex is free software: you can redistribute it and/or modify it under 
the terms of the GNU General Public License as published by the Free Software 
Foundation, either version 3 of the License, or (at your option) any later 
version.

Bin2Hex is distributed in the hope that it will be useful, but WITHOUT 
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with 
Bin2Hex. If not, see <https://www.gnu.org/licenses/>.
*/

package com.github.hanwelmer.Bin2Hex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main method for the Bin2hEX UTILITY. See usage() for a functional
 * description.
 * 
 * @param args
 *          the argument list.
 */
public class Bin2Hex {
  public static void main(String[] args) {
    // Options and arguments.
    String fileName = null;

    // process command line options and arguments.
    if (args.length == 1) {
      fileName = args[args.length - 1];
      // do the main work.
      bin2Hex(fileName);
    } else {
      usage();
    }
  }

  private static void usage() {
    System.out.println("Usage: java -jar Bin2Hex.jar filename.ext");
    System.out.println(" where filename.ext is file to be converted to Intel Hex.");
    System.out.println(" Output file has same filename but extension .hex");
    System.exit(1);
  }

  private static void bin2Hex(String fileName) {

    File inputFile = new File(fileName);
    File outputFile = new File(setExtension(fileName));
    BufferedWriter bufferedWriter = null;
    try (FileInputStream fis = new FileInputStream(inputFile)) {
      bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
      int nextValue = 0;
      int address = 0;
      int checkSum = 0;
      byte maxRecordSize = 16;
      ArrayList<Byte> record = new ArrayList(maxRecordSize);
      Byte nextByte = null;
      while ((nextValue = fis.read()) != -1) {
        nextByte = (byte) nextValue;
        record.add(nextByte);
        checkSum += nextByte;
        if (record.size() == maxRecordSize) {
          writeRecord(bufferedWriter, record, address, checkSum);
          address += record.size();
          record.clear();
          checkSum = 0;
        }
      }
      if (record.size() > 0) {
        writeRecord(bufferedWriter, record, address, checkSum);
      }
      bufferedWriter.write(String.format(":%02X%04X%02X%02X\n", 0x00, 0x0000, 0x01, 0xFF));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      if (bufferedWriter != null) {
        try {
          bufferedWriter.close();
        } catch (IOException e) {
          System.out.println("Error closing outputfile:");
          e.printStackTrace();
        }
      }
    }
  }

  private static void writeRecord(BufferedWriter bufferedWriter, ArrayList<Byte> record, int address, int checkSum)
      throws IOException {
    bufferedWriter.write(String.format(":%02X%04X00", record.size(), address));
    for (byte oneByte : record) {
      bufferedWriter.write(String.format("%02X", oneByte));
    }
    ;
    checkSum = checkSum + record.size() + ((byte) (address >> 8)) + ((byte) (address % 256));
    bufferedWriter.write(String.format("%02X\n", (byte) (0 - checkSum)));
  }

  private static String setExtension(String fileName) {
    // TODO replace any extension by bin.
    // TODO add extension .hex if extension is absent.
    return fileName.replace(".bin", ".hex");
  }

}
