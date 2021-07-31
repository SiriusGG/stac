package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.MainMenuFrame;
import com.nwawsoft.stac.util.TrickChooser;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static com.nwawsoft.stac.BuildData.DIRECTORY_NAME;

public class MainMenuController implements STACFrameController {
  private MainMenuFrame mmf;

  public MainMenuController() {}

  @Override
  public void createFrame() {
    mmf = new MainMenuFrame(this);
  }

  @Override
  public void fullCreate() {
    createFrame();
    centerFrame();
  }

  public void prepareSettings() {
    try {
      KeyBindingsFileHandler.guaranteeSettings();
      VisualizationSettingsFileHandler.guaranteeSettings();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getBrowseText() {
    return "Browse " + BuildData.DIRECTORY_NAME + " directory";
  }

  public void newTrick() {
    CreateTrickController ctc = new CreateTrickController();
    ctc.fullCreate();
    mmf.dispose();
  }

  public void editTrick() {
    Trick t = TrickChooser.createTrickFromJFileChooser();
    if (t != null) {
      EditTrickController etc = new EditTrickController(t, "main menu");
      etc.fullCreate();
      mmf.dispose();
    }
  }

  public void loadTrick() {
    Trick t = TrickChooser.createTrickFromJFileChooser();
    if (t != null) {
      TrickControlPanelController tcpc = new TrickControlPanelController(t);
      tcpc.fullCreate();
      mmf.dispose();
    }
  }

  public void openKeyBindingsConfiguration() {
    KeyBindingsController kbc = new KeyBindingsController();
    kbc.fullCreate();
    mmf.dispose();
  }

  public void openVisualizationSettings() {
    VisualizationSettingsController vsc = new VisualizationSettingsController();
    vsc.fullCreate();
    mmf.dispose();
  }

  public void browseDirectory() {
    try {
      File d = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME);
      Desktop.getDesktop().open(d);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openAbout() {
    AboutController ac = new AboutController();
    ac.fullCreate();
  }

  public void doClose() {
    System.exit(0);
  }

  @Override
  public JFrame getFrame() {
    return mmf;
  }

  @Override
  public void centerFrame() {
    ComponentFunctions.center(mmf);
  }
}
