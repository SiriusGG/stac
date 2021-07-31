package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.KeyBindingsFrame;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.IOException;

public class KeyBindingsController implements STACFrameController {
  private KeyBindingsFrame kbf;
  private String[] settings;

  public KeyBindingsController() {
    init();
  }

  public void init() {
    try {
      KeyBindingsFileHandler.guaranteeSettings();
    } catch (IOException e) {
      e.printStackTrace();
    }
    settings = KeyBindingsFileHandler.load();
  }

  public void setAvailableKeys(final JComboBox<String> comboBox) {
    for (String currentKeyString : KeyHandler.getKeys()) {
      comboBox.addItem(currentKeyString);
    }
  }

  public void loadPrimaryKeys(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful) {
    comboBoxFailed.setSelectedItem(settings[0]);
    comboBoxSuccessful.setSelectedItem(settings[1]);
  }

  public void loadRemappingKey(final JCheckBox checkBoxActive, final JComboBox<String> comboBoxSimulated) {
    String simulatedKey = settings[3];
    checkBoxActive.setSelected(Boolean.parseBoolean(settings[2]));
    comboBoxSimulated.setSelectedItem(simulatedKey);
  }

  public void setRemappingStatus(final JCheckBox checkBoxActive, final JComboBox<String> comboBoxSimulated) {
    comboBoxSimulated.setEnabled(checkBoxActive.isSelected());
  }

  public void addRemappingItemListener(final JCheckBox checkBoxActive, final JComboBox<String> comboBoxSimulated) {
    checkBoxActive.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        setRemappingStatus(checkBoxActive, comboBoxSimulated);
      } else if (e.getStateChange() == ItemEvent.DESELECTED) {
        setRemappingStatus(checkBoxActive, comboBoxSimulated);
      }
    });
  }

  public void doSave(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful,
                     final JCheckBox checkBoxRemappingStatus, final JComboBox<String> comboBoxSimulated) {
    KeyBindingsFileHandler.save(
        (String) comboBoxFailed.getSelectedItem(),
        (String) comboBoxSuccessful.getSelectedItem(),
        checkBoxRemappingStatus.isSelected(),
        (String) comboBoxSimulated.getSelectedItem());
    CounterKeyListenerSingleton.getCounterKeyListener().reset();
    MainMenuController mmc = new MainMenuController();
    mmc.fullCreate();
    kbf.dispose();
  }

  public void doCancel() {
    MainMenuController mmc = new MainMenuController();
    mmc.fullCreate();
    kbf.dispose();
  }

  @Override
  public JFrame getFrame() {
    return kbf;
  }

  @Override
  public void centerFrame() {
    ComponentFunctions.center(kbf);
  }

  @Override
  public void createFrame() {
    kbf = new KeyBindingsFrame(this);
  }

  @Override
  public void fullCreate() {
    createFrame();
    centerFrame();
  }
}
