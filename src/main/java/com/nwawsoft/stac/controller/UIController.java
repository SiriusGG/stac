package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.stac.ui.CreateTrickFrame;
import com.nwawsoft.stac.ui.EditTrickFrame;
import com.nwawsoft.stac.ui.FileNameDialog;
import com.nwawsoft.stac.ui.TrickControlPanelFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static com.nwawsoft.stac.BuildData.*;

public class UIController {
  public static void addTrick(final String name, final String file, final CreateTrickFrame ctf) {
    if (!file.equals("")) {
      Trick t = new Trick(VERSION, name, file, 0, 0, 0, 0);
      TrickFileHandler.save(t);
      new TrickControlPanelFrame(ctf, t);
    } else {
      new FileNameDialog(ctf);
    }
  }
  
  public static void editTrick(final EditTrickFrame etf, final String file, final Trick t) {
    if (!file.equals("")) {
      t.setVersion(VERSION);
      t.setName(etf.getNameFieldContent());
      t.setFileName(TrickFileHandler.trimmedFileString(etf.getFileNameFieldContent()));
      t.setAttempts(Integer.parseInt(etf.getAttemptsFieldContent()));
      t.setSuccesses(Integer.parseInt(etf.getSuccessesFieldContent()));
      t.setSuccessesBackToBack(Integer.parseInt(etf.getSuccessesBackToBackFieldContent()));
      t.setSuccessesHighscore(Integer.parseInt(etf.getSuccessesHighscoreFieldContent()));
      TrickFileHandler.save(t);
      new TrickControlPanelFrame(etf, t);
    } else {
      new FileNameDialog(etf);
    }
  }
  
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
