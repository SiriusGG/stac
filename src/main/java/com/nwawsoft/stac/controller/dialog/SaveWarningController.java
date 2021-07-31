package com.nwawsoft.stac.controller.dialog;

import com.nwawsoft.stac.controller.frame.*;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.SaveWarningDialog;
import com.nwawsoft.stac.controller.TrickChooser;
import com.nwawsoft.util.html.HTMLTagger;

import javax.swing.*;

import static com.nwawsoft.stac.BuildData.*;

public class SaveWarningController implements STACDialogController {
  private final STACFrameController calledBy;
  private final String caller;
  private final String mode;
  private SaveWarningDialog swd;

  public SaveWarningController(final STACFrameController calledBy, final String caller, final String destination) {
    this.calledBy = calledBy;
    this.caller = caller;
    mode = caller + ", " + destination;
  }

  @Override
  public JDialog getDialog() {
    return swd;
  }

  @Override
  public void createDialog() {
    swd = new SaveWarningDialog(this);
  }

  public STACFrameController getCalledBy() {
    return calledBy;
  }

  public JLabel createSaveQuestion1Label() {
    JLabel labelSaveQuestion1 = new JLabel();
    if (caller.equals("controlpanel") || caller.equals("visualization")) {
      labelSaveQuestion1 = new JLabel(HTMLTagger.toHTML(
          "There have been unsaved attempts on this trick."),
          SwingConstants.CENTER);
    }
    if (caller.equals("visualization settings") || caller.equals("trick visualization settings")) {
      labelSaveQuestion1 = new JLabel(HTMLTagger.toHTML(
          "There have been unsaved changes on this visualization file."),
          SwingConstants.CENTER);
    }
    return labelSaveQuestion1;
  }

  public JLabel createSaveQuestion2Label() {
    JLabel labelSaveQuestion2 = new JLabel();
    if (caller.equals("controlpanel") || caller.equals("visualization")) {
      labelSaveQuestion2 = new JLabel(HTMLTagger.toHTML(
          "Save trick file before closing?"), SwingConstants.CENTER);
    }
    if (caller.equals("visualization settings") || caller.equals("trick visualization settings")) {
      labelSaveQuestion2 = new JLabel(HTMLTagger.toHTML(
          "Save visualization settings file before closing?"), SwingConstants.CENTER);
    }
    return labelSaveQuestion2;
  }

  public void doYes() {
    switch (mode) {
      case "controlpanel, close":
        if (calledBy instanceof TrickControlPanelController) {
          TrickFileHandler.save(((TrickControlPanelController) calledBy).getTrick());
          System.exit(0);
        }
        break;
      case "controlpanel, menu":
        if (calledBy instanceof TrickControlPanelController) {
          TrickFileHandler.save(((TrickControlPanelController) calledBy).getTrick());
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          MainMenuController mmc = new MainMenuController();
          mmc.fullCreate();
        }
        break;
      case "controlpanel, visualization settings":
        if (calledBy instanceof TrickControlPanelController) {
          TrickFileHandler.save(((TrickControlPanelController) calledBy).getTrick());
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          VisualizationSettingsController vsc = new VisualizationSettingsController(
              (TrickControlPanelController) calledBy);
          vsc.fullCreate();
          calledBy.getFrame().dispose();
        }
        break;
      case "controlpanel, edit trick":
        if (calledBy instanceof TrickControlPanelController) {
          TrickFileHandler.save(((TrickControlPanelController) calledBy).getTrick());
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          EditTrickController etc = new EditTrickController(
              ((TrickControlPanelController) calledBy).getTrick(), "trick control panel");
          etc.fullCreate();
          calledBy.getFrame().dispose();
        }
        break;
      case "controlpanel, switch trick":
        if (calledBy instanceof TrickControlPanelController) {
          TrickFileHandler.save(((TrickControlPanelController) calledBy).getTrick());
          Trick t = TrickChooser.createTrickFromJFileChooser();
          if (t != null) {
            TrickControlPanelController tcpc = new TrickControlPanelController(t);
            tcpc.fullCreate();
          }
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          calledBy.getFrame().dispose();
        }
        break;
      case "visualization, close":
        if (calledBy instanceof TrickVisualizationController) {
          TrickFileHandler.save(((TrickVisualizationController) calledBy).getControlPanelController().getTrick());
          System.exit(0);
        }
        break;
      case "visualization settings, close":
        if (calledBy instanceof VisualizationSettingsController) {
          VisualizationSettingsFileHandler.save(
              ((VisualizationSettingsController) calledBy).getVisualizationSettings(),
              VISUALIZATION_FILE_FULL_NAME);
          MainMenuController mmc = new MainMenuController();
          mmc.fullCreate();
          calledBy.getFrame().dispose();
        }
        break;
      case "trick visualization settings, close":
        if (calledBy instanceof VisualizationSettingsController) {
          String fileName = ((VisualizationSettingsController) calledBy).getTrick().getFileName();
          VisualizationSettingsFileHandler.save(
              ((VisualizationSettingsController) calledBy).getVisualizationSettings(),
              fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
          TrickControlPanelController tcpc = new TrickControlPanelController(
              ((VisualizationSettingsController) calledBy).getTrick());
          tcpc.fullCreate();
        }
        break;
    }
  }

  public void doNo() {
    switch (mode) {
      case "controlpanel, close":
      case "visualization, close":
        System.exit(0);
        break;
      case "controlpanel, menu":
        if (calledBy instanceof TrickControlPanelController) {
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          calledBy.getFrame().dispose();
          MainMenuController mmc = new MainMenuController();
          mmc.fullCreate();
        }
        break;
      case "controlpanel, visualization settings":
        if (calledBy instanceof TrickControlPanelController) {
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          VisualizationSettingsController vsc = new VisualizationSettingsController(
              (TrickControlPanelController) calledBy);
          vsc.fullCreate();
          calledBy.getFrame().dispose();
        }
        break;
      case "controlpanel, edit trick":
        if (calledBy instanceof TrickControlPanelController) {
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          ((TrickControlPanelController) calledBy).reloadTrick();
          EditTrickController etc = new EditTrickController(
              ((TrickControlPanelController) calledBy).getTrick(), "trick control panel");
          etc.fullCreate();
          calledBy.getFrame().dispose();
        }
        break;
      case "controlpanel, switch trick":
        if (calledBy instanceof TrickControlPanelController) {
          ((TrickControlPanelController) calledBy).reloadTrick();
          Trick t = TrickChooser.createTrickFromJFileChooser();
          if (t != null) {
            TrickControlPanelController tcpc = new TrickControlPanelController(t);
            tcpc.fullCreate();
          }
          ((TrickControlPanelController) calledBy).getVisualizationController().getFrame().dispose();
          calledBy.getFrame().dispose();
        }
        break;
      case "visualization settings, close":
        MainMenuController mmc = new MainMenuController();
        mmc.fullCreate();
        calledBy.getFrame().dispose();
        break;
      case "trick visualization settings, close":
        TrickControlPanelController tcpc = new TrickControlPanelController(
            ((TrickControlPanelController) calledBy).getTrick());
        tcpc.fullCreate();
        calledBy.getFrame().dispose();
        break;
    }
  }
}
