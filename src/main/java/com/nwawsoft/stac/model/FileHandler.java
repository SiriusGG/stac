package com.nwawsoft.stac.model;

import java.io.*;

public class FileHandler {
  public FileHandler() {}

  public static void save(final Trick t, final String fileName) {
    try {
      File d = new File(System.getProperty("user.home") + "/.stac");
      if (!d.exists()) {
        if (!d.mkdir()) {
          throw new IOException();
        }
      }
      File f = new File(System.getProperty("user.home") + "/.stac/" + fileName + ".sacf");
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("VERSION=" + t.getVersion() + "\n");
      bw.write("NAME=" + t.getName() + "\n");
      bw.write("FILENAME=" + fileName + "\n");
      bw.write("ATTEMPTS=" + t.getAttempts() + "\n");
      bw.write("SUCCESSES_COUNT=" + t.getSuccesses() + "\n");
      bw.write("SUCCESSES_BACK_TO_BACK=" + t.getSuccessesBackToBack() + "\n");
      bw.write("SUCCESSES_HIGHSCORE=" + t.getSuccessesHighscore() + "\n");
      bw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Trick load(final String fileName) {
    Trick t = new Trick();
    try {
      File f = new File(System.getProperty("user.home") + "/.stac/" + fileName + ".sacf");
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        if (currentLine.startsWith("VERSION")) {
          t.setVersion(currentLine.substring(currentLine.lastIndexOf("=") + 1));
        } else if (currentLine.startsWith("NAME")) {
          t.setName(currentLine.substring(currentLine.lastIndexOf("=") + 1));
        } else if (currentLine.startsWith("FILENAME")) {
          t.setFileName(currentLine.substring(currentLine.lastIndexOf("=") + 1));
        } else {
          int count = Integer.parseInt(currentLine.substring(currentLine.lastIndexOf("=") + 1));
          if (currentLine.startsWith("ATTEMPTS")) {
            t.setAttempts(count);
          } else if (currentLine.startsWith("SUCCESSES_COUNT")) {
            t.setSuccesses(count);
          } else if (currentLine.startsWith("SUCCESSES_BACK_TO_BACK")) {
            t.setSuccessesBackToBack(count);
          } else if (currentLine.startsWith("SUCCESSES_HIGHSCORE")) {
            t.setSuccessesHighscore(count);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return t;
  }
}
