package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class KeyBindingsController {
  private final KeyBindingsFrame kbf;
  /**
   * Settings:
   * settings[0]: "failed attempt" key string representation
   * settings[1]: "successful attempt" key string representation
   * settings[2]: multi mapping status string representation (true or false)
   * settings[3]: KeyMapping string representation
   */
  private final String[] settings;

  public KeyBindingsController(final KeyBindingsFrame kbf) {
    this.kbf = kbf;
    settings = KeyBindingsFileHandler.load();
  }

  public void center() {
    ComponentFunctions.center(kbf);
  }

  public void setAvailableKeys(final JComboBox<String> comboBox) {
    AvailableKeys ak = new AvailableKeys();
    for (String currentKeyString : ak.getKeys()) {
      comboBox.addItem(currentKeyString);
    }
  }

  public void loadPrimaryKeys(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful) {
    comboBoxFailed.setSelectedItem(settings[0]);
    comboBoxSuccessful.setSelectedItem(settings[1]);
  }

  public void loadMultiMappingKeys(final JCheckBox checkBoxActive, final JComboBox<String> comboBoxFrom,
                                   final JComboBox<String> comboBoxTo) {
    KeyMapping mapping = new KeyMapping(settings[3]);
    checkBoxActive.setSelected(Boolean.parseBoolean(settings[2]));
    comboBoxFrom.setSelectedItem(mapping.getKeyFrom());
    comboBoxTo.setSelectedItem(mapping.getKeyTo());
  }

  public void setMultiMappingStatus(final JCheckBox checkBoxActive, final JComboBox<String> comboBoxFrom,
                                    final JComboBox<String> comboBoxTo) {
    boolean status = checkBoxActive.isSelected();
    comboBoxFrom.setEnabled(status);
    comboBoxTo.setEnabled(status);
  }

  public void addMultiMappingItemListener(final JCheckBox checkBoxActive, final JComboBox<String> comboBoxFrom,
                                          final JComboBox<String> comboBoxTo) {
    checkBoxActive.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        setMultiMappingStatus(checkBoxActive, comboBoxFrom, comboBoxTo);
      } else if (e.getStateChange() == ItemEvent.DESELECTED) {
        setMultiMappingStatus(checkBoxActive, comboBoxFrom, comboBoxTo);
      }
    });
  }

  public void doSave(final JComboBox<String> comboBoxFailed, final JComboBox<String> comboBoxSuccessful,
                     final JCheckBox checkBoxMultiMappingStatus, final JComboBox<String> comboBoxFrom,
                     final JComboBox<String> comboBoxTo) {
    KeyBindingsFileHandler.save(
        (String) comboBoxFailed.getSelectedItem(),
        (String) comboBoxSuccessful.getSelectedItem(),
        checkBoxMultiMappingStatus.isSelected(),
        new KeyMapping((String) comboBoxFrom.getSelectedItem(), (String) comboBoxTo.getSelectedItem())
    );
    CounterKeyListenerSingleton.getCounterKeyListener().reset();
    new MainMenuFrame();
    kbf.dispose();
  }

  public void doCancel() {
    new MainMenuFrame();
    kbf.dispose();
  }
}
