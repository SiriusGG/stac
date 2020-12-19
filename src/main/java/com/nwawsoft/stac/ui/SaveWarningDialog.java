package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;
import org.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SaveWarningDialog extends JDialog {
  private final JFrame calledBy;
  private final String mode;

  public SaveWarningDialog(final JFrame calledBy, final String caller, final String destination) {
    super(calledBy, true);
    this.calledBy = calledBy;
    mode = caller + ", " + destination;
    init();
  }

  private void init() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 320;
    int frameHeight = 120;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    setTitle("Unsaved changes");
    Container cp = getContentPane();
    cp.setLayout(null);

    int smallButtonWidth = 80;
    int largeButtonWidth = 100;
    int buttonHeight = 30;
    int verticalOffset = 40;
    JLabel labelSaveQuestion1 = new JLabel(HTMLTagger.toHTML("There have been unsaved attempts on this trick."),
        SwingConstants.CENTER);
    labelSaveQuestion1.setBounds(0, 0, frameWidth, 20);
    cp.add(labelSaveQuestion1);
    JLabel labelSaveQuestion2 = new JLabel(HTMLTagger.toHTML("Save trick file before closing?"), SwingConstants.CENTER);
    labelSaveQuestion2.setBounds(0, 20, frameWidth, 20);
    cp.add(labelSaveQuestion2);

    JButton buttonYes = new JButton("Yes");
    buttonYes.setBounds(20, frameHeight - buttonHeight - verticalOffset, smallButtonWidth, buttonHeight);
    buttonYes.addActionListener(this::buttonYes_ActionPerformed);
    cp.add(buttonYes);
    JButton buttonNo = new JButton("No");
    buttonNo.setBounds(110, frameHeight - buttonHeight - verticalOffset, smallButtonWidth, buttonHeight);
    buttonNo.addActionListener(this::buttonNo_ActionPerformed);
    cp.add(buttonNo);
    JButton buttonCancel = new JButton("Cancel");
    buttonCancel.setBounds(200, frameHeight - buttonHeight - verticalOffset, largeButtonWidth, buttonHeight);
    buttonCancel.addActionListener(this::buttonCancel_ActionPerformed);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  public void buttonYes_ActionPerformed(final ActionEvent evt) {
    switch (mode) {
      case "controlpanel, close":
        if (calledBy instanceof TrickControlPanelFrame) {
          TrickFileHandler.save(((TrickControlPanelFrame)calledBy).getTrick());
          System.exit(0);
        }
        break;
      case "controlpanel, menu":
        if (calledBy instanceof TrickControlPanelFrame) {
          TrickFileHandler.save(((TrickControlPanelFrame)calledBy).getTrick());
          ((TrickControlPanelFrame)calledBy).getVisualization().dispose();
          new MainMenuFrame(this);
          (calledBy).dispose();
          dispose();
        }
        break;
      case "controlpanel, visualization settings":
        if (calledBy instanceof TrickControlPanelFrame) {
          TrickFileHandler.save(((TrickControlPanelFrame)calledBy).getTrick());
          ((TrickControlPanelFrame)calledBy).getVisualization().dispose();
          new VisualizationSettingsFrame((TrickControlPanelFrame)calledBy);
          (calledBy).dispose();
          dispose();
        }
        break;
      case "visualization, close":
        if (calledBy instanceof TrickVisualizationFrame) {
          TrickFileHandler.save(((TrickVisualizationFrame)calledBy).getController().getTrick());
          System.exit(0);
        }
        break;
      case "visualization settings, close":
        if (calledBy instanceof VisualizationSettingsFrame) {
          // ToDo: save
          new TrickControlPanelFrame(calledBy, ((VisualizationSettingsFrame)calledBy).getT());
          calledBy.dispose();
          dispose();
        }
        break;
    }
  }

  public void buttonNo_ActionPerformed(final ActionEvent evt) {
    switch (mode) {
      case "controlpanel, close":
      case "visualization, close":
        System.exit(0);
        break;
      case "controlpanel, menu":
        if (calledBy instanceof TrickControlPanelFrame) {
          ((TrickControlPanelFrame)calledBy).getVisualization().dispose();
          new MainMenuFrame(this);
          calledBy.dispose();
          dispose();
        }
        break;
      case "controlpanel, visualization settings":
        if (calledBy instanceof TrickControlPanelFrame) {
          ((TrickControlPanelFrame)calledBy).getVisualization().dispose();
          new VisualizationSettingsFrame((TrickControlPanelFrame)calledBy);
          calledBy.dispose();
          dispose();
        }
        break;
      case "visualization settings, close":
        new TrickControlPanelFrame(calledBy, ((TrickControlPanelFrame)calledBy).getT());
        dispose();
        break;
    }
  }

  public void buttonCancel_ActionPerformed(final ActionEvent evt) {
    dispose();
  }

  public JFrame getCalledBy() {
    return calledBy;
  }
}
