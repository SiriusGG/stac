package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.SaveWarningController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SaveWarningDialog extends JDialog {
  private final SaveWarningController swc;

  public SaveWarningDialog(final SaveWarningController swc) {
    super(swc.getCalledBy().getFrame(), true);
    this.swc = swc;
    init();
  }

  private void init() {
    pack();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 380;
    int frameHeight = 130;
    setSize(frameWidth, frameHeight);
    setLocationRelativeTo(null);
    setTitle("Unsaved changes");
    Container cp = getContentPane();
    cp.setLayout(null);

    int buttonWidth = 100;
    int buttonHeight = 30;
    int verticalOffset = 50;
    JLabel labelSaveQuestion1 = swc.createSaveQuestion1Label();
    labelSaveQuestion1.setBounds(0, 0, frameWidth, 20);
    cp.add(labelSaveQuestion1);
    JLabel labelSaveQuestion2 = swc.createSaveQuestion2Label();
    labelSaveQuestion2.setBounds(0, 20, frameWidth, 20);
    cp.add(labelSaveQuestion2);

    JButton buttonYes = new JButton("Yes");
    buttonYes.setBounds(20, frameHeight - buttonHeight - verticalOffset, buttonWidth, buttonHeight);
    buttonYes.addActionListener(this::buttonYes_ActionPerformed);
    cp.add(buttonYes);
    JButton buttonNo = new JButton("No");
    buttonNo.setBounds(130, frameHeight - buttonHeight - verticalOffset, buttonWidth, buttonHeight);
    buttonNo.addActionListener(this::buttonNo_ActionPerformed);
    cp.add(buttonNo);
    JButton buttonCancel = new JButton("Cancel");
    buttonCancel.setBounds(240, frameHeight - buttonHeight - verticalOffset, buttonWidth, buttonHeight);
    buttonCancel.addActionListener(this::buttonCancel_ActionPerformed);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  public void buttonYes_ActionPerformed(final ActionEvent evt) {
    swc.doYes();
    dispose();
  }

  public void buttonNo_ActionPerformed(final ActionEvent evt) {
    swc.doNo();
    dispose();
  }

  public void buttonCancel_ActionPerformed(final ActionEvent evt) {
    dispose();
  }
}
