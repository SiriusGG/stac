package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.FileHandler;
import com.nwawsoft.stac.model.SettingsHandler;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {

  public MainMenu(final JFrame calledBy) {
    calledBy.dispose();
    new MainMenu();
  }

  public MainMenu() {
    super("STAC");
    try {
      SettingsHandler.guaranteeSettings();
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
    new CreateTrick(this);
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
      Trick t = FileHandler.load(trickFileStringNoEnding);
      new ControlPanel(this, t);
    }
  }

  private void buttonSettingsActionPerformed(final ActionEvent actionEvent) {
    new ButtonMapper(this);
  }

  private void buttonAboutActionPerformed(final ActionEvent actionEvent) {
    new About();
  }
}
