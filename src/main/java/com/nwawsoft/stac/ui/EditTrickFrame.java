package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.EditTrickController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditTrickFrame extends JFrame {
  private final EditTrickController etc;
  private JTextField textFieldName;
  private JTextField textFieldFileName;
  private JTextField textFieldAttempts;
  private JTextField textFieldSuccesses;
  private JTextField textFieldSuccessesBackToBack;
  private JTextField textFieldSuccessesHighscore;

  public EditTrickFrame(final EditTrickController etc) {
    super("Edit trick");
    this.etc = etc;
    init();
  }

  public void init() {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // ToDo add window listener and action
    int frameWidth = 400;
    int frameHeight = 280;
    setSize(frameWidth, frameHeight);
    Container cp = getContentPane();
    cp.setLayout(null);

    String trickNameRules = "Trick name may be almost anything except empty";
    String trickFileNameRules = "Trick file name may only contain lower and upper case characters from a to z, " +
        "digits from 0 to 9, - (minus) and _ (underscore)";

    JLabel labelName = new JLabel(etc.getLabelNameText());
    textFieldName = new JTextField(etc.getFieldNameText());
    JLabel labelFileName = new JLabel("Trick file name: ");
    textFieldFileName = new JTextField(etc.getFieldFileNameText());
    JLabel labelAttempts = new JLabel(etc.getLabelAttemptsText());
    textFieldAttempts = new JTextField(etc.getFieldAttemptsText());
    JLabel labelSuccesses = new JLabel(etc.getLabelSuccessesText());
    textFieldSuccesses = new JTextField(etc.getFieldSuccessesText());
    JLabel labelSuccessesBackToBack = new JLabel(etc.getLabelSuccessesBackToBackText());
    textFieldSuccessesBackToBack = new JTextField(etc.getFieldSuccessesBackToBackText());
    JLabel labelSuccessesHighscore = new JLabel(etc.getLabelSuccessesHighscoreText());
    textFieldSuccessesHighscore = new JTextField(etc.getFieldSuccessesHighscoreText());

    JButton buttonEditTrick = new JButton("Edit Trick");
    JButton buttonCancel = new JButton("Cancel");

    int leftColumnWidth = 150;
    int leftColumnSpacer = 180;
    int rightColumnWidth = 200;

    labelName.setBounds(10, 10, leftColumnWidth, 20);
    textFieldName.setBounds(leftColumnSpacer, 10, rightColumnWidth, 20);
    labelFileName.setBounds(10, 40, leftColumnWidth, 20);
    textFieldFileName.setBounds(leftColumnSpacer, 40, rightColumnWidth, 20);
    labelAttempts.setBounds(10, 70, leftColumnWidth, 20);
    textFieldAttempts.setBounds(leftColumnSpacer, 70, rightColumnWidth, 20);
    labelSuccesses.setBounds(10, 100, leftColumnWidth, 20);
    textFieldSuccesses.setBounds(leftColumnSpacer, 100, rightColumnWidth, 20);
    labelSuccessesBackToBack.setBounds(10, 130, leftColumnWidth, 20);
    textFieldSuccessesBackToBack.setBounds(leftColumnSpacer, 130, rightColumnWidth, 20);
    labelSuccessesHighscore.setBounds(10, 160, leftColumnWidth, 20);
    textFieldSuccessesHighscore.setBounds(leftColumnSpacer, 160, rightColumnWidth, 20);
    buttonEditTrick.setBounds(20, 190, 150, 30);
    buttonCancel.setBounds(180, 190, 180, 30);

    labelName.setToolTipText(trickNameRules);
    textFieldName.setToolTipText(trickNameRules);
    labelFileName.setToolTipText(trickFileNameRules);
    textFieldFileName.setToolTipText(trickFileNameRules);

    buttonEditTrick.addActionListener(this::buttonEditTrickActionPerformed);
    buttonCancel.addActionListener(this::buttonCancelActionPerformed);

    cp.add(labelName);
    cp.add(textFieldName);
    cp.add(labelFileName);
    cp.add(textFieldFileName);
    cp.add(labelAttempts);
    cp.add(textFieldAttempts);
    cp.add(labelSuccesses);
    cp.add(textFieldSuccesses);
    cp.add(labelSuccessesBackToBack);
    cp.add(textFieldSuccessesBackToBack);
    cp.add(labelSuccessesHighscore);
    cp.add(textFieldSuccessesHighscore);
    cp.add(buttonEditTrick);
    cp.add(buttonCancel);

    setResizable(false);
    setVisible(true);
  }

  private void buttonCancelActionPerformed(final ActionEvent actionEvent) {
    etc.doCancel();
  }

  private void buttonEditTrickActionPerformed(final ActionEvent actionEvent) {
    etc.editTrick();
  }

  public String getNameFieldContent() {
    return textFieldName.getText();
  }

  public String getFileNameFieldContent() {
    return textFieldFileName.getText();
  }

  public String getAttemptsFieldContent() {
    return textFieldAttempts.getText();
  }

  public String getSuccessesFieldContent() {
    return textFieldSuccesses.getText();
  }

  public String getSuccessesBackToBackFieldContent() {
    return textFieldSuccessesBackToBack.getText();
  }

  public String getSuccessesHighscoreFieldContent() {
    return textFieldSuccessesHighscore.getText();
  }
}
