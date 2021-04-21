package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;

public class TrickVisualizationFrame extends JFrame {

  private final TrickControlPanelFrame tcpf;
  
  public final static int FRAME_WIDTH = 340;
  public final static int TITLE_BAR_SIZE = 30;

  private JLabel labelAttempts;
  private JLabel labelFails;
  private JLabel labelSuccesses;
  private JLabel labelSuccessesBackToBack;
  private JLabel labelSuccessesHighscore;
  private JLabel labelSuccessPercentage;
  
  private ArrayList<VisualizationTupel> vts;
  
  int trickNameLabelIndex;
  int attemptsLabelIndex;
  int failsLabelIndex;
  int successesLabelIndex;
  int successesBackToBackLabelIndex;
  int successesHighscoreLabelIndex;
  int successPercentageLabelIndex;

  private final DecimalFormat df = new DecimalFormat("#.##");

  public TrickVisualizationFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization");
    this.tcpf = tcpf;
    init();
  }

  public void init() {
    String fileName = tcpf.getTrick().getFileName();
    String path = USER_HOME + "/" + DIRECTORY_NAME + "/" + fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT;
    File f = new File(path);
    if (!f.exists()) {
      VisualizationSettings mainVisualizationSettings = VisualizationSettingsFileHandler.load(VISUALIZATION_FILE_FULL_NAME);
      VisualizationSettingsFileHandler.save(mainVisualizationSettings, fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
    }
    VisualizationSettings vs = VisualizationSettingsFileHandler.load(fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
    vts = vs.getVisualizationTupels();
  
    int frameHeight = calcHeight(vs);
    
    // always opened to the right of TrickControlPanelFrame
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(FRAME_WIDTH, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int width = FRAME_WIDTH + TrickControlPanelFrame.FRAME_WIDTH;
    int x = (d.width - width) / 2;
    int y = (d.height - frameHeight) / 2;
    setLocation(x + TrickControlPanelFrame.FRAME_WIDTH, y);
    Container cp = getContentPane();
    cp.setLayout(null);
    
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (!tcpf.getTrick().equals(TrickFileHandler.load(tcpf.getTrick().getFileName()))) {
          openSaveDialog();
        } else {
          System.exit(0);
        }
      }
    });
    
    int rowSpacing = vs.getSpacing();
    setIndexes();
    
    JLabel labelTrickName = new JLabel(vts.get(0).getName() + tcpf.getTrick().getName());
    labelAttempts = new JLabel(vts.get(1).getName() + tcpf.getTrick().getAttempts());
    labelFails = new JLabel(vts.get(2).getName() + (tcpf.getTrick().getAttempts() - tcpf.getTrick().getSuccesses()));
    labelSuccesses = new JLabel(vts.get(3).getName() + tcpf.getTrick().getSuccesses());
    labelSuccessesBackToBack = new JLabel(vts.get(4).getName() + tcpf.getTrick().getSuccessesBackToBack());
    labelSuccessesHighscore = new JLabel(vts.get(5).getName() + tcpf.getTrick().getSuccessesHighscore());
    df.setRoundingMode(RoundingMode.HALF_UP);
    labelSuccessPercentage = new JLabel(vts.get(6).getName() + getSuccessPercentage());
  
    // TODO: Load spacing and font from vs
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

  private int calcHeight(final VisualizationSettings vs) {
    int activeModules = VisualizationTupelListFunctions.getActiveMetricsAmount(vts);
    int spacing = vs.getSpacing();
    int fontSize = vs.getFont().getSize();
    return TITLE_BAR_SIZE + (activeModules*fontSize) + (spacing*(activeModules-1));
  }
  
  public void setIndexes() {
    trickNameLabelIndex = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.TRICK_NAME).getIndex();
    attemptsLabelIndex = VisualizationTupelListFunctions.getTupelByMetric
        (vts, Metric.ATTEMPTS).getIndex();
    failsLabelIndex = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.FAILS).getIndex();
    successesLabelIndex = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES).getIndex();
    successesBackToBackLabelIndex = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_BACK_TO_BACK).getIndex();
    successesHighscoreLabelIndex = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_HIGHSCORE).getIndex();
    successPercentageLabelIndex = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESS_PERCENTAGE).getIndex();
  }
  
  public void updateAttempts() {
    labelAttempts.setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.ATTEMPTS).getName() + tcpf.getTrick().getAttempts());
  }

  public void updateFails() {

    labelFails.setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.FAILS).getName() + (tcpf.getTrick().getAttempts() - tcpf.getTrick().getSuccesses()));
  }

  public void updateSuccesses() {
    labelSuccesses.setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES).getName() + tcpf.getTrick().getSuccesses());
  }

  public void updateSuccessesBackToBack() {
    labelSuccessesBackToBack.setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_BACK_TO_BACK).getName() + tcpf.getTrick().getSuccessesBackToBack());
  }

  public void updateSuccessesHighscore() {
    labelSuccessesHighscore.setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_HIGHSCORE).getName() + tcpf.getTrick().getSuccessesHighscore());
  }

  public void updateSuccessPercentage() {
    labelSuccessPercentage.setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESS_PERCENTAGE).getName() + getSuccessPercentage());
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
