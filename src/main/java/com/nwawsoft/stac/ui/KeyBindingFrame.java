package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.AvailableKeys;
import com.nwawsoft.stac.model.CounterKeyListenerSingleton;
import com.nwawsoft.stac.model.KeyBindingsFileHandler;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KeyBindingFrame extends JFrame {
  private final JComboBox<String> comboBoxFailed;
  private final JComboBox<String> comboBoxSuccessful;

  public KeyBindingFrame(final JFrame calledBy) {
    super("Key Bindings");
    calledBy.dispose();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 170;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    JLabel labelFailedKey = new JLabel("Key for failed attempts: ");
    JLabel labelSuccessfulKey = new JLabel("Key for successful attempts: ");

    comboBoxFailed = new JComboBox<>();
    comboBoxSuccessful = new JComboBox<>();

    AvailableKeys ak = new AvailableKeys();
    for (String currentKeyString : ak.getKeys()) {
      comboBoxFailed.addItem(currentKeyString);
    }
    for (String currentKeyString : ak.getKeys()) {
      comboBoxSuccessful.addItem(currentKeyString);
    }

    JButton buttonCancel = new JButton("Cancel");
    JButton buttonSave = new JButton("Save");

    int left_x = 10;
    int upper_y = 10;
    int right_x = 180;
    int medium_y = 50;
    int lower_y = 90;
    int height = 30;
    int leftWidth = 200;
    int rightWidth = 100;
    int buttonWidth = 120;
    int leftButtonX = 20;
    int rightButtonX = 150;

    labelFailedKey.setBounds(left_x, upper_y, leftWidth, height);
    comboBoxFailed.setBounds(right_x, upper_y, rightWidth, height);
    labelSuccessfulKey.setBounds(left_x, medium_y, leftWidth, height);
    comboBoxSuccessful.setBounds(right_x, medium_y, rightWidth, height);
    buttonCancel.setBounds(leftButtonX, lower_y, buttonWidth, height);
    buttonSave.setBounds(rightButtonX, lower_y, buttonWidth, height);

    buttonCancel.addActionListener(this::buttonCancelActionPerformed);
    buttonSave.addActionListener(this::buttonSaveActionPerformed);

    String[] settings = KeyBindingsFileHandler.load();
    comboBoxFailed.setSelectedItem(settings[0]);
    comboBoxSuccessful.setSelectedItem(settings[1]);

    cp.add(labelFailedKey);
    cp.add(labelSuccessfulKey);
    cp.add(comboBoxFailed);
    cp.add(comboBoxSuccessful);
    cp.add(buttonCancel);
    cp.add(buttonSave);

    setResizable(false);
    setVisible(true);
  }

  private void buttonCancelActionPerformed(final ActionEvent actionEvent) {
    new MainMenuFrame(this);
  }

  private void buttonSaveActionPerformed(final ActionEvent actionEvent) {
    KeyBindingsFileHandler.save((String)comboBoxFailed.getSelectedItem(), (String)comboBoxSuccessful.getSelectedItem());
    CounterKeyListenerSingleton.getCounterKeyListener().reset();
    new MainMenuFrame(this);
  }
}
