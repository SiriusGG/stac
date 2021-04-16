package com.nwawsoft.stac.model;

import java.io.*;

import static com.nwawsoft.stac.BuildData.*;

public class TrickFileHandler {
  public static void save(final Trick t) {
    try {
      File d = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME);
      if (!d.exists()) {
        if (!d.mkdir()) {
          throw new IOException();
        }
      }
      File f = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME + "/" + t.getFileName() + "." +
          TRICK_FILE_FORMAT);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("VERSION=" + VERSION + "\n");
      bw.write("NAME=" + t.getName() + "\n");
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
    String version = null;
    String name = null;
    int attempts = 0;
    int successes = 0;
    int successesBackToBack = 0;
    int successesHighscore = 0;
    try {
      File f = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME + "/" + fileName + "." + TRICK_FILE_FORMAT);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        if (currentLine.startsWith("VERSION")) {
          version = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        } else if (currentLine.startsWith("NAME")) {
          name = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        } else {
          int count = Integer.parseInt(currentLine.substring(currentLine.lastIndexOf("=") + 1));
          if (currentLine.startsWith("ATTEMPTS")) {
            attempts = count;
          } else if (currentLine.startsWith("SUCCESSES_COUNT")) {
            successes = count;
          } else if (currentLine.startsWith("SUCCESSES_BACK_TO_BACK")) {
            successesBackToBack = count;
          } else if (currentLine.startsWith("SUCCESSES_HIGHSCORE")) {
            successesHighscore = count;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Trick(version, name, fileName, attempts, successes, successesBackToBack, successesHighscore);
  }

  public static String trimmedFileString(final String temporaryFileName) {
    return temporaryFileName.replaceAll("[^a-zA-Z0-9\\-_]", "");
  }
}
