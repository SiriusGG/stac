package com.nwawsoft.stac.ui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FileNameDialog extends JDialog {
  public FileNameDialog(final JFrame calledBy) {
    super(calledBy, true);
    init();
  }

  public void init() {
    pack();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 160;
    setSize(frameWidth, frameHeight);
    setLocationRelativeTo(null);
    setTitle("Illegal file name");
    Container cp = getContentPane();
    cp.setLayout(null);

    String fileNameRulesPlain = "Please enter a legal file name in the second text field. Names must be " +
        "non-empty and may only consist of letters from a to Z, digits from 0 to 9 and the symbols \"_\" " +
        "(underscore) and \"-\" (minus).";

    int buttonReservedSpace = 70;
    int heightSpacer = 20;
    int buttonWidth = 100;
    int buttonHeight = 30;
    int leftRightSpacer = 10;
    int upperSpacer = 5;
    int rightBonusSpacer = 5;
    int buttonOffset = 10;

    JTextArea textAreaMessage = new JTextArea();
    textAreaMessage.setText(fileNameRulesPlain);
    textAreaMessage.setEditable(false);
    textAreaMessage.setLineWrap(true);
    textAreaMessage.setWrapStyleWord(true);
    textAreaMessage.setBackground(null);
    textAreaMessage.setBounds(leftRightSpacer / 2, upperSpacer,
        frameWidth - leftRightSpacer - rightBonusSpacer, frameHeight - buttonReservedSpace - heightSpacer);
    cp.add(textAreaMessage);

    JButton buttonOK = new JButton("OK");
    buttonOK.setBounds((frameWidth - buttonWidth) / 2, frameHeight - buttonReservedSpace - buttonOffset,
        buttonWidth, buttonHeight);
    buttonOK.addActionListener(this::buttonOK_ActionPerformed);
    cp.add(buttonOK);

    setResizable(false);
    setVisible(true);
  }

  public void buttonOK_ActionPerformed(final ActionEvent evt) {
    dispose();
  }
}
