package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.CounterKeyListener;
import com.nwawsoft.stac.model.FileHandler;
import com.nwawsoft.stac.model.Trick;
import org.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrickVisualizationFrame extends JFrame {
  private final static String PREFIX_TRICK = "Trick: ";
  private final static String PREFIX_ATTEMPTS = "Attempts: ";
  private final static String PREFIX_SUCCESSES = "Successes: ";
  private final static String PREFIX_SUCCESSES_BACK_TO_BACK = "Successes in a row: ";
  private final static String PREFIX_SUCCESSES_HIGHSCORE = "Highscore: ";

  private final TrickControlPanelFrame tcpf;
  private final Trick t;

  private final JLabel labelAttemptsValue;
  private final JLabel labelSuccessesValue;
  private final JLabel labelSuccessesBackToBackValue;
  private final JLabel labelSuccessesHighscoreValue;

  public final static int FRAME_WIDTH = 340;
  public final static int FRAME_HEIGHT = 160;

  public TrickVisualizationFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization");
    this.tcpf = tcpf;
    t = tcpf.getTrick();
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int width = FRAME_WIDTH + TrickControlPanelFrame.FRAME_WIDTH;
    int height = FRAME_HEIGHT + TrickControlPanelFrame.FRAME_HEIGHT;
    int x = (d.width - width) / 2;
    int y = (d.height - height) / 2;
    setLocation(x + TrickControlPanelFrame.FRAME_WIDTH, y);
    Container cp = getContentPane();
    cp.setLayout(null);

    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        if (!tcpf.getTrick().equals(FileHandler.load(tcpf.getTrick().getFileName()))) {
          openSaveDialog();
        } else {
          System.exit(0);
        }
      }
    });

    int left_column_width = 120;
    int right_column_start = left_column_width + 20;
    int right_column_width = 150;
    JLabel labelTrickName = new JLabel(PREFIX_TRICK);
    JLabel labelAttempts = new JLabel(PREFIX_ATTEMPTS);
    JLabel labelSuccesses = new JLabel(PREFIX_SUCCESSES);
    JLabel labelSuccessesBackToBack = new JLabel(PREFIX_SUCCESSES_BACK_TO_BACK);
    JLabel labelSuccessesHighscore = new JLabel(PREFIX_SUCCESSES_HIGHSCORE);
    JLabel labelTrickNameValue = new JLabel(t.getName());
    labelAttemptsValue = new JLabel("" + t.getAttempts());
    labelSuccessesValue = new JLabel("" + t.getSuccesses());
    labelSuccessesBackToBackValue = new JLabel("" + t.getSuccessesBackToBack());
    labelSuccessesHighscoreValue = new JLabel("" + t.getSuccessesHighscore());
    labelTrickName.setBounds(10, 10, left_column_width, 20);
    labelTrickNameValue.setBounds(right_column_start, 10, 2000, 20);
    labelAttempts.setBounds(10, 30, left_column_width, 20);
    labelAttemptsValue.setBounds(right_column_start, 30, right_column_width, 20);
    labelSuccesses.setBounds(10, 50, left_column_width, 20);
    labelSuccessesValue.setBounds(right_column_start, 50, right_column_width, 20);
    labelSuccessesBackToBack.setBounds(10, 70, left_column_width, 20);
    labelSuccessesBackToBackValue.setBounds(right_column_start, 70, right_column_width, 20);
    labelSuccessesHighscore.setBounds(10, 90, left_column_width, 20);
    labelSuccessesHighscoreValue.setBounds(right_column_start, 90, right_column_width, 20);

    cp.setBackground(Color.WHITE);
    cp.add(labelTrickName);
    cp.add(labelTrickNameValue);
    cp.add(labelAttempts);
    cp.add(labelAttemptsValue);
    cp.add(labelSuccesses);
    cp.add(labelSuccessesValue);
    cp.add(labelSuccessesBackToBack);
    cp.add(labelSuccessesBackToBackValue);
    cp.add(labelSuccessesHighscore);
    cp.add(labelSuccessesHighscoreValue);

    GlobalScreen.addNativeKeyListener(new CounterKeyListener(this, t));

    setResizable(true);
    setVisible(true);
  }

  public void updateAttempts() {
    labelAttemptsValue.setText("" + t.getAttempts());
  }

  public void updateSuccesses() {
    labelSuccessesValue.setText("" + t.getSuccesses());
  }

  public void updateSuccessesBackToBack() {
    labelSuccessesBackToBackValue.setText("" + t.getSuccessesBackToBack());
  }

  public void updateSuccessesHighscore() {
    labelSuccessesHighscoreValue.setText("" + t.getSuccessesHighscore());
  }

  public void updateStats() {
    updateAttempts();
    updateSuccesses();
    updateSuccessesBackToBack();
    updateSuccessesHighscore();
  }

  public void openSaveDialog() {
    new SaveWarningDialog(this);
  }

  public TrickControlPanelFrame getController() {
    return tcpf;
  }
}
