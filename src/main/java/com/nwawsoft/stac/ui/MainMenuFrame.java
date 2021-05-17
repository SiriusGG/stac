package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {
  private final MainMenuController mmc;

  public MainMenuFrame() {
    super("STAC");
    mmc = new MainMenuController(this);
    init();
  }

  public void init() {
    mmc.prepareSettings();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 240;
    int frameHeight = 320;
    int modules = 8;
    setSize(frameWidth, frameHeight);
    mmc.center();
    Container cp = getContentPane();
    cp.setLayout(null);
    int buttonWidth = frameWidth-16;
    int buttonHeight = ((frameHeight/(modules-1))-10);
    JButton[] buttons = new JButton[modules];
    JButton buttonNew = new JButton("Create New Trick File");
    buttons[0] = buttonNew;
    JButton buttonEdit = new JButton("Edit Trick File");
    buttons[1] = buttonEdit;
    JButton buttonLoad = new JButton("Load Trick File");
    buttons[2] = buttonLoad;
    JButton buttonKeyBindings = new JButton("Key Bindings");
    buttons[3] = buttonKeyBindings;
    JButton buttonVisualizationSettings = new JButton("Visualization Settings");
    buttons[4] = buttonVisualizationSettings;
    JButton buttonBrowse = new JButton(mmc.getBrowseText());
    buttons[5] = buttonBrowse;
    JButton buttonAbout = new JButton("About");
    buttons[6] = buttonAbout;
    JButton buttonExit = new JButton("Exit");
    buttons[7] = buttonExit;
    buttonNew.setBounds(0, 0, buttonWidth, buttonHeight);
    buttonEdit.setBounds(0, buttonHeight, buttonWidth, buttonHeight);
    buttonLoad.setBounds(0, (buttonHeight)*2, buttonWidth, buttonHeight);
    buttonKeyBindings.setBounds(0, (buttonHeight)*3, buttonWidth, buttonHeight);
    buttonVisualizationSettings.setBounds(0, (buttonHeight)*4, buttonWidth, buttonHeight);
    buttonBrowse.setBounds(0, (buttonHeight)*5, buttonWidth, buttonHeight);
    buttonAbout.setBounds(0, (buttonHeight)*6, buttonWidth, buttonHeight);
    buttonExit.setBounds(0, (buttonHeight)*7, buttonWidth, buttonHeight);
    buttonNew.addActionListener(this::buttonNewActionPerformed);
    buttonEdit.addActionListener(this::buttonEditActionPerformed);
    buttonLoad.addActionListener(this::buttonLoadActionPerformed);
    buttonKeyBindings.addActionListener(this::buttonKeyBindingsActionPerformed);
    buttonVisualizationSettings.addActionListener(this::buttonVisualizationSettingsActionPerformed);
    buttonBrowse.addActionListener(this::buttonBrowseActionPerformed);
    buttonAbout.addActionListener(this::buttonAboutActionPerformed);
    buttonExit.addActionListener(this::buttonExitActionPerformed);
    for(JButton b : buttons) {
      b.setBackground(Color.WHITE);
      cp.add(b);
    }
    setResizable(false);
    setVisible(true);
  }
  
  private void buttonNewActionPerformed(final ActionEvent actionEvent) {
    mmc.newTrick();
  }
  
  private void buttonEditActionPerformed(final ActionEvent actionEvent) {
    mmc.editTrick();
  }

  private void buttonLoadActionPerformed(final ActionEvent actionEvent) {
    mmc.loadTrick();
  }

  private void buttonKeyBindingsActionPerformed(final ActionEvent actionEvent) {
    mmc.openKeyBindingsConfiguration();
  }
  
  private void buttonVisualizationSettingsActionPerformed(final ActionEvent actionEvent) {
    mmc.openVisualizationSettings();
  }
  
  private void buttonBrowseActionPerformed(final ActionEvent actionEvent) {
    mmc.browseDirectory();
  }

  private void buttonAboutActionPerformed(final ActionEvent actionEvent) {
    mmc.openAbout();
  }
  
  private void buttonExitActionPerformed(final ActionEvent actionEvent) {
    mmc.doClose();
  }
}
