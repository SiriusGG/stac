package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.CounterKeyListenerSingleton;
import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.stac.model.VisualizationSettings;
import com.nwawsoft.stac.model.VisualizationSettingsFileHandler;
import org.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.nwawsoft.stac.BuildData.*;
import static com.nwawsoft.stac.ui.TrickInformationStrings.*;

public class TrickVisualizationFrame extends JFrame {

  private final TrickControlPanelFrame tcpf;

  public final static int FRAME_WIDTH = 340;
  public final static int FRAME_HEIGHT = 210;

  private JLabel labelAttempts;
  private JLabel labelFails;
  private JLabel labelSuccesses;
  private JLabel labelSuccessesBackToBack;
  private JLabel labelSuccessesHighscore;
  private JLabel labelSuccessPercentage;
  
  private VisualizationSettings vs;

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
  
    String fileName = tcpf.getTrick().getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT;
    if (new File(fileName).exists()) {
      vs = VisualizationSettingsFileHandler.load(fileName);
    } else {
      // ToDo: Create copy of defaults and rename to name.extension instead of just using defaults.
      vs = VisualizationSettingsFileHandler.load(VISUALIZATION_FILE_FULL_NAME);
      // end of ToDo
    }
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (!tcpf.getTrick().equals(TrickFileHandler.load(tcpf.getTrick().getFileName()))) {
          openSaveDialog();
        } else {
          System.exit(0);
        }
      }
    });
    
    // TODO: Load from visualization file instead
    int rowSpacing = 20;
    int trickNameLabelIndex = 0;
    int attemptsLabelIndex = 1;
    int failsLabelIndex = 2;
    int successesLabelIndex = 3;
    int successesBackToBackLabelIndex = 4;
    int successesHighscoreLabelIndex = 5;
    int successPercentageLabelIndex = 6;
  
    JLabel labelTrickName = new JLabel(PREFIX_TRICK + tcpf.getTrick().getName());
    labelAttempts = new JLabel(PREFIX_ATTEMPTS + tcpf.getTrick().getAttempts());
    labelFails = new JLabel(PREFIX_FAILS + (tcpf.getTrick().getAttempts() - tcpf.getTrick().getSuccesses()));
    labelSuccesses = new JLabel(PREFIX_SUCCESSES + tcpf.getTrick().getSuccesses());
    labelSuccessesBackToBack = new JLabel(PREFIX_SUCCESSES_BACK_TO_BACK + tcpf.getTrick().getSuccessesBackToBack());
    labelSuccessesHighscore = new JLabel(PREFIX_SUCCESSES_HIGHSCORE + tcpf.getTrick().getSuccessesHighscore());
    df.setRoundingMode(RoundingMode.HALF_UP);
    labelSuccessPercentage = new JLabel(PREFIX_SUCCESS_PERCENTAGE + getSuccessPercentage());
    labelTrickName.setBounds(10, 10 + (trickNameLabelIndex * rowSpacing), 4096, 20);
    labelAttempts.setBounds(10, 10 + (attemptsLabelIndex * rowSpacing), FRAME_WIDTH - 20, 20);
    labelFails.setBounds(10, 10 + (failsLabelIndex * rowSpacing), FRAME_WIDTH - 20, 20);
    labelSuccesses.setBounds(10, 10 + (successesLabelIndex * rowSpacing), FRAME_WIDTH - 20, 20);
    labelSuccessesBackToBack.setBounds(10, 10 + (successesBackToBackLabelIndex * rowSpacing), FRAME_WIDTH - 20, 20);
    labelSuccessesHighscore.setBounds(10, 10 + (successesHighscoreLabelIndex * rowSpacing), FRAME_WIDTH - 20, 20);
    labelSuccessPercentage.setBounds(10, 10 + (successPercentageLabelIndex * rowSpacing), FRAME_WIDTH - 20, 20);
    
    cp.setBackground(Color.WHITE);
    cp.add(labelTrickName);
    cp.add(labelAttempts);
    cp.add(labelFails);
    cp.add(labelSuccesses);
    cp.add(labelSuccessesBackToBack);
    cp.add(labelSuccessesHighscore);
    cp.add(labelSuccessPercentage);
  
    setResizable(true);
    setVisible(true);
  }

  public void updateAttempts() {
    labelAttempts.setText(PREFIX_ATTEMPTS + tcpf.getTrick().getAttempts());
  }

  public void updateFails() {

    labelFails.setText(PREFIX_FAILS + (tcpf.getTrick().getAttempts() - tcpf.getTrick().getSuccesses()));
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
    updateFails();
    updateSuccesses();
    updateSuccessesBackToBack();
    updateSuccessesHighscore();
    updateSuccessPercentage();
  }

  public void openSaveDialog() {
    new SaveWarningDialog(this, "visualization", "close");
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
