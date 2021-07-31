package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.stac.model.TrickFileHandler;

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
    int result = jfc.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
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
    }
    return null;
  }
}
