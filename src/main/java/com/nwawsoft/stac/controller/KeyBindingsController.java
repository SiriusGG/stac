package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;

public class KeyBindingsController {
  private final KeyBindingsFrame kbf;

  public KeyBindingsController(final KeyBindingsFrame kbf) {
    this.kbf = kbf;
  }

  public void center() {
    ComponentFunctions.center(kbf);
  }

  public void setAvailableKeys(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful) {
    AvailableKeys ak = new AvailableKeys();
    for (String currentKeyString : ak.getKeys()) {
      comboBoxFailed.addItem(currentKeyString);
    }
    for (String currentKeyString : ak.getKeys()) {
      comboBoxSuccessful.addItem(currentKeyString);
    }
  }

  public void loadSelectedItems(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful) {
    String[] settings = KeyBindingsFileHandler.load();
    comboBoxFailed.setSelectedItem(settings[0]);
    comboBoxSuccessful.setSelectedItem(settings[1]);
  }

  public void doSave(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful) {
    KeyBindingsFileHandler.save((String) comboBoxFailed.getSelectedItem(),
        (String) comboBoxSuccessful.getSelectedItem());
    CounterKeyListenerSingleton.getCounterKeyListener().reset();
    new MainMenuFrame();
    kbf.dispose();
  }

  public void doCancel() {
    new MainMenuFrame();
    kbf.dispose();
  }
}
