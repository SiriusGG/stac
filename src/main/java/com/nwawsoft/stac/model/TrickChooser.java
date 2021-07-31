package com.nwawsoft.stac.model;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;

import static com.nwawsoft.stac.BuildData.*;

public class TrickChooser {

  public static Trick createTrickFromJFileChooser() {
    JFileChooser jfc = new JFileChooser();
    jfc.setCurrentDirectory(new File
        (USER_HOME + System.getProperty("file.separator") + DIRECTORY_NAME));
    FileFilter filter = new FileNameExtensionFilter
        ("STAC Trick File (." + TRICK_FILE_FORMAT + ")", TRICK_FILE_FORMAT);
    jfc.addChoosableFileFilter(filter);
    jfc.setFileFilter(filter);
    jfc.showOpenDialog(null);
    File trickFile = jfc.getSelectedFile();
    String trickFileStringNoEnding = null;
    if (trickFile != null && trickFile.exists()) {
      String trickFileString = trickFile.getName();
      if (trickFileString.endsWith("." + TRICK_FILE_FORMAT)) {
        trickFileStringNoEnding = trickFileString.substring(0, trickFileString.indexOf('.'));
      } else {
        trickFileStringNoEnding = trickFileString;
      }
    }
    if (trickFileStringNoEnding != null) {
      return TrickFileHandler.load(trickFileStringNoEnding);
    }
    return null;
  }
}
