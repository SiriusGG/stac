package com.nwawsoft.stac.model;

import java.io.*;

import static com.nwawsoft.stac.BuildData.*;

public class KeyBindingsFileHandler {
  public static void save(final String failedKey, final String successfulKey, final boolean remappingActive,
                          final String simulatedKey) {
    try {
      File d = new File(USER_HOME + "/" + DIRECTORY_NAME);
      if (!d.exists()) {
        if (!d.mkdir()) {
          throw new IOException();
        }
      }
      File f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + KEY_BINDINGS_FILE_FULL_NAME);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("FAILED_KEY=" + failedKey + "\n");
      bw.write("SUCCESSFUL_KEY=" + successfulKey + "\n");
      bw.write("REMAPPING_ACTIVE=" + Boolean.toString(remappingActive) + "\n");
      bw.write("SIMULATED_KEY=" + simulatedKey + "\n");
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String[] load() {
    String[] settings = new String[4];
    try {
      File f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + KEY_BINDINGS_FILE_FULL_NAME);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        if (currentLine.startsWith("FAILED_KEY")) {
          settings[0] = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        } else if (currentLine.startsWith("SUCCESSFUL_KEY")) {
          settings[1] = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        } else if (currentLine.startsWith("REMAPPING_ACTIVE")) {
          settings[2] = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        } else if (currentLine.startsWith("SIMULATED_KEY")) {
          settings[3] = currentLine.substring(currentLine.lastIndexOf("=") + 1);
        }
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return settings;
  }

  public static void guaranteeSettings() throws IOException {
    File d = new File(USER_HOME + "/" + DIRECTORY_NAME + "/");
    if (!d.exists()) {
      if (!d.mkdir()) {
        throw new IOException();
      }
    }
    File f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + KEY_BINDINGS_FILE_FULL_NAME);
    if (!f.exists()) {
      save("F7", "F8", false, "F4");
    }
  }
}
