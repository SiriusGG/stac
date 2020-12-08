package com.nwawsoft.stac.ui;

import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ResetWarningDialog extends JDialog {
  private final TrickControlPanelFrame calledBy;

  public ResetWarningDialog(final TrickControlPanelFrame calledBy) {
    super(calledBy, true);
    this.calledBy = calledBy;
    init();
  }

  private void init() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 320;
    int frameHeight = 120;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    setTitle("Reset warning");
    Container cp = getContentPane();
    cp.setLayout(null);

    int largeButtonWidth = 130;
    int buttonHeight = 30;
    int verticalOffset = 40;
    JLabel labelResetQuestion1 = new JLabel(HTMLTagger.toHTML("Your unsaved attempts will be lost."),
        SwingConstants.CENTER);
    labelResetQuestion1.setBounds(0, 0, frameWidth, 20);
    cp.add(labelResetQuestion1);
    JLabel labelResetQuestion2 = new JLabel(HTMLTagger.toHTML("Really reload from file?"), SwingConstants.CENTER);
    labelResetQuestion2.setBounds(0, 20, frameWidth, 20);
    cp.add(labelResetQuestion2);

    JButton buttonYes = new JButton("Yes");
    buttonYes.setBounds(20, frameHeight - buttonHeight - verticalOffset, largeButtonWidth, buttonHeight);
    buttonYes.addActionListener(this::buttonYes_ActionPerformed);
    cp.add(buttonYes);
    JButton buttonCancel = new JButton("Cancel");
    buttonCancel.setBounds(160, frameHeight - buttonHeight - verticalOffset, largeButtonWidth, buttonHeight);
    buttonCancel.addActionListener(this::buttonCancel_ActionPerformed);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  public void buttonYes_ActionPerformed(final ActionEvent evt) {
    calledBy.reloadTrick();
    dispose();
  }

  public void buttonCancel_ActionPerformed(final ActionEvent evt) {
    dispose();
  }
}
