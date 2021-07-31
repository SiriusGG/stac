package com.nwawsoft.stac.controller.frame;

import com.nwawsoft.stac.controller.dialog.FileNameController;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.EditTrickFrame;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;

public class EditTrickController implements STACFrameController {
  private final Trick trick;
  private final ArrayList<VisualizationTupel> vts;
  private final String mode;
  private EditTrickFrame etf;

  public EditTrickController(final Trick trick, final String mode) {
    this.trick = trick;
    this.vts = VisualizationSettingsFileHandler.load(
        trick.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT).getVisualizationTupels();
    this.mode = mode;
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
      TrickControlPanelController tcpc = new TrickControlPanelController(trick);
      tcpc.fullCreate();
      etf.dispose();
    } else {
      FileNameController fnc = new FileNameController(this);
      fnc.createDialog();
    }
  }

  public void doCancel() {
    if (mode.equals("main menu")) {
      MainMenuController mmc = new MainMenuController();
      mmc.fullCreate();
    } else if (mode.equals("trick control panel")) {
      TrickControlPanelController tcpc = new TrickControlPanelController(trick);
      tcpc.fullCreate();
    }
    etf.dispose();
  }

  @Override
  public JFrame getFrame() {
    return etf;
  }

  @Override
  public void centerFrame() {
    ComponentFunctions.center(etf);
  }

  @Override
  public void createFrame() {
    etf = new EditTrickFrame(this);
  }

  @Override
  public void fullCreate() {
    createFrame();
    centerFrame();
  }

  public WindowListener handleClosing() {
    return new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (mode.equals("main menu")) {
          MainMenuController mmc = new MainMenuController();
          mmc.createFrame();
          mmc.centerFrame();
        } else if (mode.equals("trick control panel")) {
          TrickControlPanelController tcpc = new TrickControlPanelController(trick);
          tcpc.createFrame();
          tcpc.centerFrame();
          tcpc.handleOnClose();
          tcpc.createVisualization();
          tcpc.addNativeHook();
        }
        etf.dispose();
      }
    };
  }
}
