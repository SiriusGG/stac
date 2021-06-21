package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.KeyBindingsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KeyBindingsFrame extends JFrame {
  private JComboBox<String> comboBoxFailed;
  private JComboBox<String> comboBoxSuccessful;
  private JCheckBox checkBoxMultiMapping;
  private JComboBox<String> comboBoxFrom;
  private JComboBox<String> comboBoxTo;
  private final KeyBindingsController kbc;

  public KeyBindingsFrame() {
    super("Key Bindings");
    this.kbc = new KeyBindingsController(this);
    init();
  }

  public void init() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 280;
    setSize(frameWidth, frameHeight);
    kbc.center();
    Container cp = getContentPane();
    cp.setLayout(null);

    JLabel labelFailedKey = new JLabel("Key for failed attempts: ");
    comboBoxFailed = new JComboBox<>();
    JLabel labelSuccessfulKey = new JLabel("Key for successful attempts: ");
    comboBoxSuccessful = new JComboBox<>();
    checkBoxMultiMapping = new JCheckBox("Activate multi mapping");
    JLabel labelFrom = new JLabel("From: ");
    comboBoxFrom = new JComboBox<>();
    JLabel labelTo = new JLabel("To : ");
    comboBoxTo = new JComboBox<>();

    kbc.setAvailableKeys(comboBoxFailed);
    kbc.setAvailableKeys(comboBoxSuccessful);
    kbc.setAvailableKeys(comboBoxFrom);
    kbc.setAvailableKeys(comboBoxTo);

    JButton buttonSave = new JButton("Save");
    JButton buttonCancel = new JButton("Cancel");

    int headerSpacer = 30;
    int column_1_x = 10;
    int column_2_x = 180;
    int row_1_y = 10;
    int row_2_y = 50;
    int row_3_y = 90;
    int row_4_y = 120;
    int row_5_y = 160;
    int defaultElementHeight = 30;
    int leftWidth = 200;
    int rightWidth = 100;
    int buttonWidth = 120;
    int leftButtonX = 20;
    int rightButtonX = 150;
    int checkBoxSize = 20;
    int bottomSpacer = 20;
    int textWidth = 200;

    labelFailedKey.setBounds(column_1_x, row_1_y, leftWidth, defaultElementHeight);
    comboBoxFailed.setBounds(column_2_x, row_1_y, rightWidth, defaultElementHeight);
    labelSuccessfulKey.setBounds(column_1_x, row_2_y, leftWidth, defaultElementHeight);
    comboBoxSuccessful.setBounds(column_2_x, row_2_y, rightWidth, defaultElementHeight);
    checkBoxMultiMapping.setBounds(column_1_x, row_3_y, checkBoxSize + textWidth, checkBoxSize);
    labelFrom.setBounds(column_1_x, row_4_y, leftWidth, defaultElementHeight);
    comboBoxFrom.setBounds(column_2_x, row_4_y, rightWidth, defaultElementHeight);
    labelTo.setBounds(column_1_x, row_5_y, leftWidth, defaultElementHeight);
    comboBoxTo.setBounds(column_2_x, row_5_y, rightWidth, defaultElementHeight);
    buttonSave.setBounds(leftButtonX, frameHeight - headerSpacer - defaultElementHeight - bottomSpacer,
        buttonWidth, defaultElementHeight);
    buttonCancel.setBounds(rightButtonX, frameHeight - headerSpacer - defaultElementHeight - bottomSpacer,
        buttonWidth, defaultElementHeight);

    buttonSave.addActionListener(this::buttonSaveActionPerformed);
    buttonCancel.addActionListener(this::buttonCancelActionPerformed);

    kbc.loadPrimaryKeys(comboBoxFailed, comboBoxSuccessful);
    kbc.loadMultiMappingKeys(comboBoxFrom, comboBoxTo);

    cp.add(labelFailedKey);
    cp.add(comboBoxFailed);
    cp.add(labelSuccessfulKey);
    cp.add(comboBoxSuccessful);
    cp.add(checkBoxMultiMapping);
    cp.add(labelFrom);
    cp.add(comboBoxFrom);
    cp.add(labelTo);
    cp.add(comboBoxTo);
    cp.add(buttonSave);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  private void buttonSaveActionPerformed(final ActionEvent actionEvent) {
    kbc.doSave(comboBoxFailed, comboBoxSuccessful, checkBoxMultiMapping, comboBoxFrom, comboBoxTo);
  }
  
  private void buttonCancelActionPerformed(final ActionEvent actionEvent) {
    kbc.doCancel();
  }
}
