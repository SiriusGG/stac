package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.controller.UIController;
import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.stac.model.KeyBindingsFileHandler;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

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
    } catch (IOException e) {
      e.printStackTrace();
    }
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 240;
    int frameHeight = 240;
    int modules = 5;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);
    int buttonWidth = frameWidth-10;
    int buttonHeight = ((frameHeight/modules)-10);
    JButton[] buttons = new JButton[modules];
    JButton buttonNew = new JButton("Create New Trick File");
    buttons[0] = buttonNew;
    JButton buttonEdit = new JButton("Edit Trick File");
    buttons[1] = buttonEdit;
    JButton buttonLoad = new JButton("Load Trick File");
    buttons[2] = buttonLoad;
    JButton buttonSettings = new JButton("Key Bindings");
    buttons[3] = buttonSettings;
    JButton buttonAbout = new JButton("About");
    buttons[4] = buttonAbout;
    buttonNew.setBounds(0, 0, buttonWidth, buttonHeight);
    buttonEdit.setBounds(0, buttonHeight, buttonWidth, buttonHeight);
    buttonLoad.setBounds(0, (buttonHeight)*2, buttonWidth, buttonHeight);
    buttonSettings.setBounds(0, (buttonHeight)*3, buttonWidth, buttonHeight);
    buttonAbout.setBounds(0, (buttonHeight)*4, buttonWidth, buttonHeight);
    buttonNew.addActionListener(this::buttonNewActionPerformed);
    buttonEdit.addActionListener(this::buttonEditActionPerformed);
    buttonLoad.addActionListener(this::buttonLoadActionPerformed);
    buttonSettings.addActionListener(this::buttonSettingsActionPerformed);
    buttonAbout.addActionListener(this::buttonAboutActionPerformed);
    for(JButton b : buttons) {
      b.setBackground(Color.WHITE);
      cp.add(b);
    }
    setResizable(false);
    setVisible(true);
  }
  
  private void buttonNewActionPerformed(final ActionEvent actionEvent) {
    new CreateTrickFrame(this);
  }
  
  private void buttonEditActionPerformed(final ActionEvent actionEvent) {
    Trick t = UIController.createTrickFromJFileChooser();
    if (t != null) {
      new EditTrickFrame(this, t);
    }
  }

  private void buttonLoadActionPerformed(final ActionEvent actionEvent) {
    Trick t = UIController.createTrickFromJFileChooser();
    if (t != null) {
      new TrickControlPanelFrame(this, t);
    }
  }

  private void buttonSettingsActionPerformed(final ActionEvent actionEvent) {
    new KeyBindingFrame(this);
  }

  private void buttonAboutActionPerformed(final ActionEvent actionEvent) {
    new AboutFrame();
  }
}
