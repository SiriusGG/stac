package com.nwawsoft.stac.model;

import com.nwawsoft.stac.BuildData;

import java.io.*;

public class KeyBindingsFileHandler {
  public static void save(final String failedKey, final String successfulKey) {
    try {
      File d = new File(System.getProperty("user.home") + "/.stac");
      if (!d.exists()) {
        if (!d.mkdir()) {
          throw new IOException();
        }
      }
      File f = new File(System.getProperty("user.home") + "/.stac/" + BuildData.KEY_BINDINGS_FILE_NAME);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("FAILED_KEY=" + failedKey + "\n");
      bw.write("SUCCESSFUL_KEY=" + successfulKey + "\n");
      bw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String[] load() {
    String[] settings = new String[2];
    try {
      File f = new File(System.getProperty("user.home") + "/.stac/" + BuildData.KEY_BINDINGS_FILE_NAME);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        if (currentLine.startsWith("FAILED_KEY")) {
          settings[0] = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        } else if (currentLine.startsWith("SUCCESSFUL_KEY")) {
          settings[1] = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return settings;
  }

  public static void guaranteeSettings() throws IOException {
    File d = new File(System.getProperty("user.home") + "/.stac/");
    if (!d.exists()) {
      if (!d.mkdir()) {
        throw new IOException();
      }
    }
    File f = new File(System.getProperty("user.home") + "/.stac/" + BuildData.KEY_BINDINGS_FILE_NAME);
    if (!f.exists()) {
      save("F7", "F8");
    }
  }
}
