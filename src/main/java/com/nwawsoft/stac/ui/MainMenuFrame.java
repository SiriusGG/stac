package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.stac.model.SettingsFileHandler;
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
    calledBy.getCalledByController().getVisualization().dispose();
    calledBy.getCalledByController().dispose();
    calledBy.dispose();
    init();
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
      SettingsFileHandler.guaranteeSettings();
    } catch (IOException e) {
      e.printStackTrace();
    }
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 240;
    int frameHeight = 200;
    int modules = 4;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);
    int buttonWidth = frameWidth-10;
    int buttonHeight = ((frameHeight/modules)-10);
    JButton[] buttons = new JButton[modules];
    JButton buttonNew = new JButton("Create New Trick File");
    buttons[0] = buttonNew;
    JButton buttonLoad = new JButton("Load Trick File");
    buttons[1] = buttonLoad;
    JButton buttonSettings = new JButton("Key Bindings");
    buttons[2] = buttonSettings;
    JButton buttonAbout = new JButton("About");
    buttons[3] = buttonAbout;
    buttonNew.setBounds(0, 0, buttonWidth, buttonHeight);
    buttonLoad.setBounds(0, buttonHeight, buttonWidth, buttonHeight);
    buttonSettings.setBounds(0, (buttonHeight)*2, buttonWidth, buttonHeight);
    buttonAbout.setBounds(0, (buttonHeight)*3, buttonWidth, buttonHeight);
    buttonNew.addActionListener(this::buttonNewActionPerformed);
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

  private void buttonLoadActionPerformed(final ActionEvent actionEvent) {
    JFileChooser jfc = new JFileChooser();
    jfc.setCurrentDirectory(new File
        (System.getProperty("user.home") + System.getProperty("file.separator") + ".stac"));
    FileFilter filter = new FileNameExtensionFilter("Siri's Attempt Counter File (.sacf)", "sacf");
    jfc.addChoosableFileFilter(filter);
    jfc.setFileFilter(filter);
    jfc.showOpenDialog(null);
    File trickFile = jfc.getSelectedFile();
    if (trickFile != null && trickFile.exists()) {
      String trickFileString = trickFile.getName();
      String trickFileStringNoEnding;
      if (trickFileString.endsWith(".sacf")) {
        trickFileStringNoEnding = trickFileString.substring(0, trickFileString.indexOf('.'));
      } else {
        trickFileStringNoEnding = trickFileString;
      }
      Trick t = TrickFileHandler.load(trickFileStringNoEnding);
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
