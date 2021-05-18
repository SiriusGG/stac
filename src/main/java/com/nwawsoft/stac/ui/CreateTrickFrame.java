package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.CreateTrickController;
import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTrickFrame extends JFrame {
  private static final String TRICK_FILE_NAME_RULES = "Trick file name may only contain lower and upper case " +
      "characters from a to z, digits from 0 to 9, - (minus) and _ (underscore)";
  private static final String TRICK_NAME_RULES = "Trick name may be almost anything except empty";

  private JTextField textFieldName;
  private JTextField textFieldFileName;
  private boolean defaultName = true;

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
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

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

    labelName.setToolTipText(TRICK_NAME_RULES);
    textFieldName.setToolTipText(TRICK_NAME_RULES);
    labelFileName.setToolTipText(TRICK_FILE_NAME_RULES);
    textFieldFileName.setToolTipText(TRICK_FILE_NAME_RULES);

    textFieldFileName.setForeground(Color.GRAY);

    buttonAddTrick.addActionListener(this::buttonAddTrickActionPerformed);
    buttonCancel.addActionListener(this::buttonCancelActionPerformed);

    KeyListener nameListener = new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
        if (defaultName) {
          textFieldFileName.setText(TrickFileHandler.trimmedFileString(textFieldName.getText()));
        }
      }
    };
    textFieldName.addKeyListener(nameListener);

    KeyListener fileNameListener = new KeyListener() {
      public void keyTyped(KeyEvent e) {
        defaultName = false;
      }

      public void keyPressed(KeyEvent e) {
        defaultName = false;
      }

      public void keyReleased(KeyEvent e) {
        defaultName = false;
      }
    };
    textFieldFileName.addKeyListener(fileNameListener);

    textFieldFileName.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (defaultName) {
          textFieldFileName.selectAll();
          textFieldFileName.setForeground(Color.BLACK);
        }
      }
    });

    textFieldFileName.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
        if (defaultName) {
          textFieldFileName.selectAll();
          textFieldFileName.setForeground(Color.BLACK);
        }
      }
    });

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
