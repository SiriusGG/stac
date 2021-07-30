package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.TrickControlPanelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TrickControlPanelFrame extends JFrame {
  private static final String FAILED_PREFIX = "Key for failed attempt: ";
  private static final String SUCCESSFUL_PREFIX = "Key for successful attempt: ";

  private final TrickControlPanelController tcpc;

  public TrickControlPanelFrame(final TrickControlPanelController tcpc) {
    super("Control Panel");
    this.tcpc = tcpc;
    init();
  }

  private void init() {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    Container cp = getContentPane();
    cp.setLayout(null);

    int buttonHeight = 30;
    JLabel labelFailedKey = new JLabel();
    JLabel labelFailedKeyValue = new JLabel();
    JLabel labelSuccessfulKey = new JLabel();
    JLabel labelSuccessfulKeyValue = new JLabel();
    labelFailedKey.setBounds(10, 10, 170, 20);
    labelFailedKeyValue.setBounds(180, 10, 30, 20);
    labelSuccessfulKey.setBounds(10, 40, 170, 20);
    labelSuccessfulKeyValue.setBounds(180, 40, 30, 20);
    labelFailedKey.setText(FAILED_PREFIX);
    labelFailedKeyValue.setText(tcpc.loadFailedKeyText());
    labelSuccessfulKey.setText(SUCCESSFUL_PREFIX);
    labelSuccessfulKeyValue.setText(tcpc.loadSuccessfulKeyText());
    JButton buttonMenu = new JButton("Back to Menu");
    buttonMenu.setBounds(10, 70, 140, buttonHeight);
    buttonMenu.addActionListener(this::buttonMenuActionPerformed);
    JButton buttonSave = new JButton("Save");
    buttonSave.setBounds(160, 70, 80, buttonHeight);
    buttonSave.addActionListener(this::buttonSaveActionPerformed);
    JButton buttonManualFail = new JButton("Manual Fail");
    buttonManualFail.setBounds(10, 110, 100, buttonHeight);
    buttonManualFail.addActionListener(this::buttonManualFailActionPerformed);
    JButton buttonManualSuccess = new JButton("Manual Success");
    buttonManualSuccess.setBounds(120, 110, 140, buttonHeight);
    buttonManualSuccess.addActionListener(this::buttonManualSuccessActionPerformed);
    JButton buttonReset = new JButton("Reset from file");
    buttonReset.setBounds(10, 150, 140, buttonHeight);
    buttonReset.addActionListener(this::buttonResetActionPerformed);
    JButton buttonVisualizationSettings = new JButton("Visualization Settings");
    buttonVisualizationSettings.setBounds(10, 190, 170, buttonHeight);
    buttonVisualizationSettings.addActionListener(this::buttonVisualizationSettingsActionPerformed);
    cp.add(labelFailedKey);
    cp.add(labelFailedKeyValue);
    cp.add(labelSuccessfulKey);
    cp.add(labelSuccessfulKeyValue);
    cp.add(buttonMenu);
    cp.add(buttonSave);
    cp.add(buttonManualFail);
    cp.add(buttonManualSuccess);
    cp.add(buttonReset);
    cp.add(buttonVisualizationSettings);

    setResizable(false);
    setVisible(true);
  }

  private void buttonSaveActionPerformed(final ActionEvent actionEvent) {
    tcpc.doSave();
  }

  private void buttonMenuActionPerformed(final ActionEvent actionEvent) {
    tcpc.goToMenu();
  }

  private void buttonManualFailActionPerformed(final ActionEvent actionEvent) {
    tcpc.doManualFail();
  }

  private void buttonManualSuccessActionPerformed(final ActionEvent actionEvent) {
    tcpc.doManualSuccess();
  }

  private void buttonResetActionPerformed(final ActionEvent actionEvent) {
    tcpc.doReset();
  }

  private void buttonVisualizationSettingsActionPerformed(final ActionEvent actionEvent) {
    tcpc.openVisualizationSettings();
  }
}
