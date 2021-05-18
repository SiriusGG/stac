package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;

import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.TRICK_VISUALIZATION_FILE_FORMAT;
import static com.nwawsoft.stac.BuildData.VERSION;

public class EditTrickController {
  private final EditTrickFrame etf;
  private final JFrame calledBy;
  private final Trick trick;
  private final ArrayList<VisualizationTupel> vts;

  public EditTrickController(final EditTrickFrame etf, final JFrame calledBy, final Trick trick) {
    this.etf = etf;
    this.calledBy = calledBy;
    this.trick = trick;
    this.vts = VisualizationSettingsFileHandler.load(
        trick.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT).getVisualizationTupels();
  }


  public void center() {
    ComponentFunctions.center(etf);
  }

  public String getLabelNameText() {
    return vts.get(VisualizationTupelListFunctions.getTupelByMetric(vts, Metric.TRICK_NAME).getIndex()).getName();
  }

  public String getFieldNameText() {
    return trick.getName();
  }

  public String getFieldFileNameText() {
    return trick.getFileName();
  }

  public String getLabelAttemptsText() {
    return vts.get(VisualizationTupelListFunctions.getTupelByMetric(vts, Metric.ATTEMPTS).getIndex()).getName();
  }

  public String getFieldAttemptsText() {
    return "" + trick.getAttempts();
  }

  public String getLabelSuccessesText() {
    return vts.get(VisualizationTupelListFunctions.getTupelByMetric(vts, Metric.SUCCESSES).getIndex()).getName();
  }

  public String getFieldSuccessesText() {
    return "" + trick.getSuccesses();
  }

  public String getLabelSuccessesBackToBackText() {
    return vts.get(VisualizationTupelListFunctions.getTupelByMetric(vts, Metric.SUCCESSES_BACK_TO_BACK).getIndex()).getName();
  }

  public String getFieldSuccessesBackToBackText() {
    return "" + trick.getSuccessesBackToBack();
  }

  public String getLabelSuccessesHighscoreText() {
    return vts.get(VisualizationTupelListFunctions.getTupelByMetric(vts, Metric.SUCCESSES_HIGHSCORE).getIndex()).getName();
  }

  public String getFieldSuccessesHighscoreText() {
    return "" + trick.getSuccessesHighscore();
  }

  public void editTrick() {
    String trimmedFile = TrickFileHandler.trimmedFileString(etf.getFileNameFieldContent());
    if (!trimmedFile.equals("")) {
      trick.setVersion(VERSION);
      trick.setName(etf.getNameFieldContent());
      trick.setFileName(trimmedFile);
      trick.setAttempts(Integer.parseInt(etf.getAttemptsFieldContent()));
      trick.setSuccesses(Integer.parseInt(etf.getSuccessesFieldContent()));
      trick.setSuccessesBackToBack(Integer.parseInt(etf.getSuccessesBackToBackFieldContent()));
      trick.setSuccessesHighscore(Integer.parseInt(etf.getSuccessesHighscoreFieldContent()));
      TrickFileHandler.save(trick);
      new TrickControlPanelFrame(etf, trick);
    } else {
      new FileNameDialog(etf);
    }
  }

  public void doCancel() {
    if (calledBy instanceof MainMenuFrame) {
      new MainMenuFrame();
    } else if (calledBy instanceof TrickControlPanelFrame) {
      new TrickControlPanelFrame(etf, trick);
    }
    etf.dispose();
  }
}
