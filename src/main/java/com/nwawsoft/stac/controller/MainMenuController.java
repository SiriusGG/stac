package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import java.awt.*;
import java.io.*;

import static com.nwawsoft.stac.BuildData.DIRECTORY_NAME;

public class MainMenuController {
  private final MainMenuFrame mmf;

  public MainMenuController(final MainMenuFrame mmf) {
    this.mmf = mmf;
  }

  public void prepareSettings() {
    try {
      KeyBindingsFileHandler.guaranteeSettings();
      VisualizationSettingsFileHandler.guaranteeSettings();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void center() {
    ComponentFunctions.center(mmf);
  }

  public String getBrowseText() {
    return "Browse " + BuildData.DIRECTORY_NAME + " directory";
  }

  public void newTrick() {
    new CreateTrickFrame();
    mmf.dispose();
  }

  public void editTrick() {
    Trick t = TrickChooserController.createTrickFromJFileChooser();
    if (t != null) {
      new EditTrickFrame(mmf, t);
      mmf.dispose();
    }
  }

  public void loadTrick() {
    Trick t = TrickChooserController.createTrickFromJFileChooser();
    if (t != null) {
      new TrickControlPanelFrame(mmf, t);
      mmf.dispose();
    }
  }

  public void openKeyBindingsConfiguration() {
    new KeyBindingsFrame();
    mmf.dispose();
  }

  public void openVisualizationSettings() {
    new VisualizationSettingsFrame();
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
    new AboutFrame();
  }

  public void doClose() {
    System.exit(0);
  }
}
