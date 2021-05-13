package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;

import static com.nwawsoft.stac.BuildData.VERSION;

public class EditTrickController {
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
}
