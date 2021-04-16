package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.UIController;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.nwawsoft.stac.ui.TrickInformationStrings.*;

public class EditTrickFrame extends JFrame {
  private static final String TRICK_FILE_NAME_RULES = "Trick file name may only contain lower and upper case " +
      "characters from a to z, digits from 0 to 9, - (minus) and _ (underscore)";
  private static final String TRICK_NAME_RULES = "Trick name may be almost anything except empty";

  private final JTextField textFieldName;
  private final JTextField textFieldFileName;
  private final JTextField textFieldAttempts;
  private final JTextField textFieldSuccesses;
  private final JTextField textFieldSuccessesBackToBack;
  private final JTextField textFieldSuccessesHighscore;
  private boolean defaultName = true;
  
  private final Trick t;
  
  public EditTrickFrame(final JFrame calledBy, final Trick t) {
    super("Edit trick");
    this.t = t;
    calledBy.dispose();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 400;
    int frameHeight = 280;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    JLabel labelName = new JLabel("Trick name: ");
    textFieldName = new JTextField(t.getName());
    JLabel labelFileName = new JLabel("Trick file name: ");
    textFieldFileName = new JTextField(t.getFileName());
    JLabel labelAttempts = new JLabel(PREFIX_ATTEMPTS);
    System.out.println(t.getAttempts());
    textFieldAttempts = new JTextField("" + t.getAttempts());
    JLabel labelSuccesses = new JLabel(PREFIX_SUCCESSES);
    textFieldSuccesses = new JTextField("" + t.getSuccesses());
    JLabel labelSuccessesBackToBack = new JLabel(PREFIX_SUCCESSES_BACK_TO_BACK);
    textFieldSuccessesBackToBack = new JTextField("" + t.getSuccessesBackToBack());
    JLabel labelSuccessesHighscore = new JLabel(PREFIX_SUCCESSES_HIGHSCORE);
    textFieldSuccessesHighscore = new JTextField("" + t.getSuccessesHighscore());
  
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

    labelName.setToolTipText(TRICK_NAME_RULES);
    textFieldName.setToolTipText(TRICK_NAME_RULES);
    labelFileName.setToolTipText(TRICK_FILE_NAME_RULES);
    textFieldFileName.setToolTipText(TRICK_FILE_NAME_RULES);

    textFieldFileName.setForeground(Color.GRAY);

    buttonEditTrick.addActionListener(this::buttonEditTrickActionPerformed);
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
    new MainMenuFrame();
    dispose();
  }
  
  private void buttonEditTrickActionPerformed(final ActionEvent actionEvent) {
    UIController.editTrick(this, TrickFileHandler.trimmedFileString(textFieldFileName.getText()), t);
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
