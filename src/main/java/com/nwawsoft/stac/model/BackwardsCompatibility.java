package com.nwawsoft.stac.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static com.nwawsoft.stac.BuildData.*;

public class BackwardsCompatibility {
  /**
   * Converts old .staf (Siri's Trick Attempt [Counter] File) files and .sacf (Siri's [Trick] Attempt Counter File)
   * files to the current trick file format .stact (Siri's Trick Attempt Counter Trick File).
   */
  public static void convertTrickFiles_1_3_0() {
    File d = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME + "/");
    if (d.exists() && d.isDirectory()) {
      for (final File f : Objects.requireNonNull(d.listFiles())) {
        if (f.getName().endsWith(".staf")) {
          renameTrickFiles_1_3_0(f, ".staf");
        } else if (f.getName().endsWith(".sacf")) {
          renameTrickFiles_1_3_0(f, ".sacf");
        }
      }
    }
  }
  
  /**
   * Changes the file ending of a specified file to the current trick file ending if the file was in the specified
   * format.
   *
   * @param f any file within the .stac directory.
   * @param format any file format (so far only .staf makes sense).
   */
  private static void renameTrickFiles_1_3_0(final File f, final String format) {
    if (f.getName().endsWith(format)) {
      String oldPath = f.getAbsolutePath();
      String newPath = oldPath.replace(format, TRICK_FILE_FORMAT);
      File newFile = new File(newPath);
      if (f.renameTo(newFile)) {
        System.out.println("Renamed " + oldPath + " to " + newPath);
      } else {
        System.err.println("Failed at renaming " + oldPath + " to " + newPath);
      }
    }
  }

  /**
   * Changes the name of the "settings.ini" file to the name specified in com.nwawsoft.stac.BuildData.java.
   */
  public static void convertKeyBindingsFile_1_3_0() {
    File d = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME + "/");
    if (d.exists() && d.isDirectory()) {
      for (final File f : Objects.requireNonNull(d.listFiles())) {
        if (f.getName().endsWith("settings.ini")) {
          String oldPath = f.getAbsolutePath();
          String newPath = oldPath.replace("settings.ini", KEY_BINDINGS_FILE_FULL_NAME);
          File newFile = new File(newPath);
          if (f.renameTo(newFile)) {
            System.out.println("Renamed " + oldPath + " to " + newPath);
          } else {
            System.err.println("Failed at renaming " + oldPath + " to " + newPath);
          }
        }
      }
    }
  }

  public static void addKeyRemapping_2_1_0() {
    boolean old = true;
    try {
      File f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + KEY_BINDINGS_FILE_FULL_NAME);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        if (currentLine.startsWith("REMAPPING_ACTIVE")) {
          old = false;
        }
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (old) {
      String[] settings = new String[2];
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
          }
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      KeyBindingsFileHandler.save(settings[0], settings[1], false, "F4");
    }
  }
}
