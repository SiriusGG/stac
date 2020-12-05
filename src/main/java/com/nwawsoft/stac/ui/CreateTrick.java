package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.model.FileHandler;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateTrick extends JFrame {
  private static final String TRICK_FILE_NAME_RULES = "Trick file name may only contain lower and upper case " +
      "characters from a to z, digits from 0 to 9, - (minus) and _ (underscore)";
  private static final String TRICK_NAME_RULES = "Trick name may be almost anything except empty";

  JLabel labelName;
  JTextField textFieldName;
  JLabel labelFileName;
  JTextField textFieldFileName;
  JButton buttonAddTrick;

  public CreateTrick(final JFrame calledBy) {
    super("Add new trick");
    calledBy.dispose();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 280;
    int frameHeight = 140;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    labelName = new JLabel("Trick name: ");
    textFieldName = new JTextField("");
    labelFileName = new JLabel("Trick file name: ");
    textFieldFileName = new JTextField("");
    buttonAddTrick = new JButton("Add Trick");

    labelName.setBounds(10, 10, 100, 20);
    textFieldName.setBounds(110, 10, 150, 20);
    labelFileName.setBounds(10, 40, 100, 20);
    textFieldFileName.setBounds(110, 40, 150, 20);
    buttonAddTrick.setBounds(50, 70, 170, 30);

    labelName.setToolTipText(TRICK_NAME_RULES);
    textFieldName.setToolTipText(TRICK_NAME_RULES);
    labelFileName.setToolTipText(TRICK_FILE_NAME_RULES);
    textFieldFileName.setToolTipText(TRICK_FILE_NAME_RULES);

    buttonAddTrick.addActionListener(this::buttonAddTrickActionPerformed);

    cp.add(labelName);
    cp.add(textFieldName);
    cp.add(labelFileName);
    cp.add(textFieldFileName);
    cp.add(buttonAddTrick);

    setResizable(false);
    setVisible(true);
  }

  private void buttonAddTrickActionPerformed(final ActionEvent actionEvent) {
    String newName = textFieldName.getText(); // ToDo: Trim (Here or better also sooner at all save occasions)
    String newFileName = textFieldFileName.getText(); // ToDo: Check for the required criteria and throw error dialog
    Trick t = new Trick(BuildData.VERSION, newName, newFileName, 0, 0, 0, 0);
    FileHandler.save(t, newFileName);
    new ControlPanel(this, t);
  }
}
