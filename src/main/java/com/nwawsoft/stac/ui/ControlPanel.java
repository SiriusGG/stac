package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.FileHandler;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JFrame {
  private final Trick t;

  public ControlPanel(final JFrame calledBy, final Trick t) {
    super("Control Panel");
    calledBy.dispose();
    this.t = t;
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // ToDo: Ask for save
    int frameWidth = 300;
    int frameHeight = 132;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    JButton buttonSave = new JButton("Save");
    buttonSave.setBounds(10, 10, 100, 30);
    buttonSave.addActionListener(this::buttonSaveActionPerformed);

    cp.add(buttonSave);

    setResizable(false);
    setVisible(true);

    new Visualization(t);
  }

  private void buttonSaveActionPerformed(final ActionEvent actionEvent) {
    FileHandler.save(t, t.getFileName());
  }
}
