package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.controller.TrickChooserController;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

import static com.nwawsoft.stac.BuildData.DIRECTORY_NAME;

public class MainMenuFrame extends JFrame {

  public MainMenuFrame() {
    super("STAC");
    init();
  }

  public MainMenuFrame(final SaveWarningDialog calledBy) {
    super("STAC");
    if (calledBy.getCalledBy() instanceof TrickControlPanelFrame) {
      ((TrickControlPanelFrame)calledBy.getCalledBy()).getVisualization().dispose();
      calledBy.getCalledBy().dispose();
      calledBy.dispose();
      init();
    }
  }

  public MainMenuFrame(final TrickControlPanelFrame calledBy) {
    super("STAC");
    calledBy.getVisualization().dispose();
    calledBy.dispose();
    init();
  }

  public MainMenuFrame(final KeyBindingFrame calledBy) {
    super("STAC");
    calledBy.dispose();
    init();
  }

  public void init() {
    try {
      KeyBindingsFileHandler.guaranteeSettings();
      VisualizationSettingsFileHandler.guaranteeSettings();
    } catch (IOException e) {
      e.printStackTrace();
    }
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 240;
    int frameHeight = 320;
    int modules = 8;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
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
    JButton buttonBrowse = new JButton("Browse " + BuildData.DIRECTORY_NAME + " directory");
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
    new CreateTrickFrame(this);
    dispose();
  }
  
  private void buttonEditActionPerformed(final ActionEvent actionEvent) {
    Trick t = TrickChooserController.createTrickFromJFileChooser();
    if (t != null) {
      new EditTrickFrame(this, t);
      dispose();
    }
  }

  private void buttonLoadActionPerformed(final ActionEvent actionEvent) {
    Trick t = TrickChooserController.createTrickFromJFileChooser();
    if (t != null) {
      new TrickControlPanelFrame(this, t);
      dispose();
    }
  }

  private void buttonKeyBindingsActionPerformed(final ActionEvent actionEvent) {
    new KeyBindingFrame(this);
    dispose();
  }
  
  private void buttonVisualizationSettingsActionPerformed(ActionEvent actionEvent) {
    new VisualizationSettingsFrame();
    dispose();
  }
  
  private void buttonBrowseActionPerformed(ActionEvent actionEvent) {
    try {
      File d = new File(System.getProperty("user.home") + "/" + DIRECTORY_NAME);
      Desktop.getDesktop().open(d);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void buttonAboutActionPerformed(final ActionEvent actionEvent) {
    new AboutFrame();
  }
  
  private void buttonExitActionPerformed(ActionEvent actionEvent) {
    System.exit(0);
  }
}
