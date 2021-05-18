package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.CreateTrickController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTrickFrame extends JFrame {
  private JTextField textFieldName;
  private JTextField textFieldFileName;

  private CreateTrickController ctc;

  public CreateTrickFrame() {
    super("Add new trick");
    init();
  }

  private void init() {
    ctc = new CreateTrickController(this);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 280;
    int frameHeight = 150;
    setSize(frameWidth, frameHeight);
    ctc.center();
    Container cp = getContentPane();
    cp.setLayout(null);

    String trickNameRules = "Trick name may be almost anything except empty";
    String trickFileNameRules = "Trick file name may only contain lower and upper case characters from a to z, " +
        "digits from 0 to 9, - (minus) and _ (underscore)";

    JLabel labelName = new JLabel("Trick name: ");
    textFieldName = new JTextField("");
    JLabel labelFileName = new JLabel("Trick file name: ");
    textFieldFileName = new JTextField("");
    JButton buttonAddTrick = new JButton("Add Trick");
    JButton buttonCancel = new JButton("Cancel");

    labelName.setBounds(10, 10, 100, 20);
    textFieldName.setBounds(110, 10, 150, 20);
    labelFileName.setBounds(10, 40, 100, 20);
    textFieldFileName.setBounds(110, 40, 150, 20);
    buttonAddTrick.setBounds(10, 70, 110, 30);
    buttonCancel.setBounds(130, 70, 110, 30);

    labelName.setToolTipText(trickNameRules);
    textFieldName.setToolTipText(trickNameRules);
    labelFileName.setToolTipText(trickFileNameRules);
    textFieldFileName.setToolTipText(trickFileNameRules);

    textFieldFileName.setForeground(Color.GRAY);

    buttonAddTrick.addActionListener(this::buttonAddTrickActionPerformed);
    buttonCancel.addActionListener(this::buttonCancelActionPerformed);

    textFieldName.addKeyListener(ctc.getNameKeyListener(textFieldName, textFieldFileName));

    textFieldFileName.addKeyListener(ctc.getFileNameKeyListener());
    textFieldFileName.addMouseListener(ctc.getFileNameMouseListener(textFieldFileName));
    textFieldFileName.addFocusListener(ctc.getFileNameFocusAdapter(textFieldFileName));

    cp.add(labelName);
    cp.add(textFieldName);
    cp.add(labelFileName);
    cp.add(textFieldFileName);
    cp.add(buttonAddTrick);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  private void buttonAddTrickActionPerformed(final ActionEvent actionEvent) {
    ctc.addTrick(textFieldName, textFieldFileName);
  }
  
  private void buttonCancelActionPerformed(final ActionEvent actionEvent) {
    ctc.doCancel();
  }
}
