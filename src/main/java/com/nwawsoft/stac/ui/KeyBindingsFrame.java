package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.KeyBindingsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KeyBindingsFrame extends JFrame {
  private JComboBox<String> comboBoxFailed;
  private JComboBox<String> comboBoxSuccessful;
  private final KeyBindingsController kbc;

  public KeyBindingsFrame() {
    super("Key Bindings");
    this.kbc = new KeyBindingsController(this);
    init();
  }

  public void init() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 170;
    setSize(frameWidth, frameHeight);
    kbc.center();
    Container cp = getContentPane();
    cp.setLayout(null);

    JLabel labelFailedKey = new JLabel("Key for failed attempts: ");
    JLabel labelSuccessfulKey = new JLabel("Key for successful attempts: ");

    comboBoxFailed = new JComboBox<>();
    comboBoxSuccessful = new JComboBox<>();

    kbc.setAvailableKeys(comboBoxFailed, comboBoxSuccessful);

    JButton buttonSave = new JButton("Save");
    JButton buttonCancel = new JButton("Cancel");

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
    buttonSave.setBounds(leftButtonX, lower_y, buttonWidth, height);
    buttonCancel.setBounds(rightButtonX, lower_y, buttonWidth, height);

    buttonSave.addActionListener(this::buttonSaveActionPerformed);
    buttonCancel.addActionListener(this::buttonCancelActionPerformed);

    kbc.loadSelectedItems(comboBoxFailed, comboBoxSuccessful);

    cp.add(labelFailedKey);
    cp.add(labelSuccessfulKey);
    cp.add(comboBoxFailed);
    cp.add(comboBoxSuccessful);
    cp.add(buttonSave);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  private void buttonSaveActionPerformed(final ActionEvent actionEvent) {
    kbc.doSave(comboBoxFailed, comboBoxSuccessful);
  }
  
  private void buttonCancelActionPerformed(final ActionEvent actionEvent) {
    kbc.doCancel();
  }
}
