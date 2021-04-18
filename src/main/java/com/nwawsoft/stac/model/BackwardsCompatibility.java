package com.nwawsoft.stac.model;

import java.io.File;
import java.util.Objects;

import static com.nwawsoft.stac.BuildData.*;

public class BackwardsCompatibility {
  /**
   * Converts old .staf (Siri's Trick Attempt [Counter] File) files and .sacf (Siri's [Trick] Attempt Counter File)
   * files to the current trick file format .stact (Siri's Trick Attempt Counter Trick File).
   */
  public static void convertTrickFiles() {
    File d = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME + "/");
    if (d.exists() && d.isDirectory()) {
      for (final File f : Objects.requireNonNull(d.listFiles())) {
        if (f.getName().endsWith(".staf")) {
          renameTrickFiles(f, ".staf");
        } else if (f.getName().endsWith(".sacf")) {
          renameTrickFiles(f, ".sacf");
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
  private static void renameTrickFiles(final File f, final String format) {
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
  
  public static void convertKeyBindingsFile() {
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
}
