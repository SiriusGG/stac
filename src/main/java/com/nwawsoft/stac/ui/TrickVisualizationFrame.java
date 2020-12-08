package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.CounterKeyListener;
import com.nwawsoft.stac.model.FileHandler;
import org.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TrickVisualizationFrame extends JFrame {
  private final static String PREFIX_TRICK = "Trick: ";
  private final static String PREFIX_ATTEMPTS = "Attempts: ";
  private final static String PREFIX_SUCCESSES = "Successes: ";
  private final static String PREFIX_SUCCESSES_BACK_TO_BACK = "Success Streak: ";
  private final static String PREFIX_SUCCESSES_HIGHSCORE = "Success Highscore: ";
  private final static String PREFIX_SUCCESS_PERCENTAGE = "Success Rate: ";

  private final TrickControlPanelFrame tcpf;

  public final static int FRAME_WIDTH = 340;
  public final static int FRAME_HEIGHT = 180;

  private JLabel labelAttempts;
  private JLabel labelSuccesses;
  private JLabel labelSuccessesBackToBack;
  private JLabel labelSuccessesHighscore;
  private JLabel labelSuccessPercentage;

  private final DecimalFormat df = new DecimalFormat("#.##");

  public TrickVisualizationFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization");
    this.tcpf = tcpf;
    init();
  }

  public void init() {
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

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (!tcpf.getTrick().equals(FileHandler.load(tcpf.getTrick().getFileName()))) {
          openSaveDialog();
        } else {
          System.exit(0);
        }
      }
    });

    JLabel labelTrickName = new JLabel(PREFIX_TRICK + tcpf.getTrick().getName());
    labelAttempts = new JLabel(PREFIX_ATTEMPTS + tcpf.getTrick().getAttempts());
    labelSuccesses = new JLabel(PREFIX_SUCCESSES + tcpf.getTrick().getSuccesses());
    labelSuccessesBackToBack = new JLabel(PREFIX_SUCCESSES_BACK_TO_BACK + tcpf.getTrick().getSuccessesBackToBack());
    labelSuccessesHighscore = new JLabel(PREFIX_SUCCESSES_HIGHSCORE + tcpf.getTrick().getSuccessesHighscore());
    df.setRoundingMode(RoundingMode.HALF_UP);
    labelSuccessPercentage = new JLabel(PREFIX_SUCCESS_PERCENTAGE + getSuccessPercentage());
    labelTrickName.setBounds(10, 10, 4096, 20);
    labelAttempts.setBounds(10, 30, FRAME_WIDTH-20, 20);
    labelSuccesses.setBounds(10, 50, FRAME_WIDTH-20, 20);
    labelSuccessesBackToBack.setBounds(10, 70, FRAME_WIDTH-20, 20);
    labelSuccessesHighscore.setBounds(10, 90, FRAME_WIDTH-20, 20);
    labelSuccessPercentage.setBounds(10, 110, FRAME_WIDTH-20, 20);

    cp.setBackground(Color.WHITE);
    cp.add(labelTrickName);
    cp.add(labelAttempts);
    cp.add(labelSuccesses);
    cp.add(labelSuccessesBackToBack);
    cp.add(labelSuccessesHighscore);
    cp.add(labelSuccessPercentage);

    GlobalScreen.addNativeKeyListener(new CounterKeyListener(this));

    setResizable(true);
    setVisible(true);
  }

  public void updateAttempts() {
    labelAttempts.setText(PREFIX_ATTEMPTS + tcpf.getTrick().getAttempts());
  }

  public void updateSuccesses() {
    labelSuccesses.setText(PREFIX_SUCCESSES + tcpf.getTrick().getSuccesses());
  }

  public void updateSuccessesBackToBack() {
    labelSuccessesBackToBack.setText(PREFIX_SUCCESSES_BACK_TO_BACK + tcpf.getTrick().getSuccessesBackToBack());
  }

  public void updateSuccessesHighscore() {
    labelSuccessesHighscore.setText(PREFIX_SUCCESSES_HIGHSCORE + tcpf.getTrick().getSuccessesHighscore());
  }

  public void updateSuccessPercentage() {
    labelSuccessPercentage.setText(PREFIX_SUCCESS_PERCENTAGE + getSuccessPercentage());
  }

  public void updateStats() {
    updateAttempts();
    updateSuccesses();
    updateSuccessesBackToBack();
    updateSuccessesHighscore();
    updateSuccessPercentage();
  }

  public void openSaveDialog() {
    new SaveWarningDialog(this);
  }

  public TrickControlPanelFrame getController() {
    return tcpf;
  }

  public String getSuccessPercentage() {
    if (tcpf.getTrick().getAttempts() != 0) {
      return df.format(((double) tcpf.getTrick().getSuccesses() * 100) / tcpf.getTrick().getAttempts()) + "%";
    } else {
      return "undefined";
    }
  }
}
