package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.FileHandler;
import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SaveWarningDialog extends JDialog {
  private TrickControlPanelFrame calledByController;
  private TrickVisualizationFrame calledByVisualization;
  private final String mode;

  public SaveWarningDialog(final TrickControlPanelFrame calledBy, final boolean toMenu) {
    super(calledBy, true);
    this.calledByController = calledBy;
    if (toMenu) {
      mode = "controller, menu";
    } else {
      mode = "controller, close";
    }
    init();
  }

  public SaveWarningDialog(final TrickVisualizationFrame calledBy) {
    super(calledBy, true);
    this.calledByVisualization = calledBy;
    mode = "visualization, close";
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
      case "controller, close":
        FileHandler.save(calledByController.getTrick());
        System.exit(0);
        break;
      case "controller, menu":
        FileHandler.save(calledByController.getTrick());
        calledByController.getVisualization().dispose();
        calledByController.dispose();
        new MainMenuFrame(this);
        dispose();
        break;
      case "visualization, close":
        FileHandler.save(calledByVisualization.getController().getTrick());
        System.exit(0);
        break;
    }
  }

  public void buttonNo_ActionPerformed(final ActionEvent evt) {
    switch (mode) {
      case "controller, close":
      case "visualization, close":
        System.exit(0);
        break;
      case "controller, menu":
        calledByController.getVisualization().dispose();
        calledByController.dispose();
        new MainMenuFrame(this);
        dispose();
        break;
    }
  }

  public void buttonCancel_ActionPerformed(final ActionEvent evt) {
    dispose();
  }

  public TrickControlPanelFrame getCalledByController() {
    return calledByController;
  }
}
