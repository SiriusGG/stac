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
  
  private ArrayList<VisualizationTupel> vts;
  
  private JLabel[] labels;
  private int[] labelIndexes;
  private int[] nonHiddenLabelIndexes;

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
    
    labels = new JLabel[vts.size()];
    int rowSpacing = vs.getSpacing();
    setIndexes();
    setIndexesWithoutHidden();
    labels[0] = new JLabel(vts.get(0).getName() + tcpf.getTrick().getName());
    labels[1] = new JLabel(vts.get(1).getName() + tcpf.getTrick().getAttempts());
    labels[2] = new JLabel(vts.get(2).getName() + (tcpf.getTrick().getAttempts() - tcpf.getTrick().getSuccesses()));
    labels[3] = new JLabel(vts.get(3).getName() + tcpf.getTrick().getSuccesses());
    labels[4] = new JLabel(vts.get(4).getName() + tcpf.getTrick().getSuccessesBackToBack());
    labels[5] = new JLabel(vts.get(5).getName() + tcpf.getTrick().getSuccessesHighscore());
    df.setRoundingMode(RoundingMode.HALF_UP);
    labels[6] = new JLabel(vts.get(6).getName() + getSuccessPercentage());
    // ToDo: Load spacing and font from vs
    // ToDo: replace labelIndexes with nonHiddenLabelIndexes
    // ToDo: probably with a lot of "if"s where isActive() is checked
    labels[0].setBounds(10, 10 + (labelIndexes[0] * rowSpacing), 4096, 20);
    labels[1].setBounds(10, 10 + (labelIndexes[1] * rowSpacing), FRAME_WIDTH - 20, 20);
    labels[2].setBounds(10, 10 + (labelIndexes[2] * rowSpacing), FRAME_WIDTH - 20, 20);
    labels[3].setBounds(10, 10 + (labelIndexes[3] * rowSpacing), FRAME_WIDTH - 20, 20);
    labels[4].setBounds(10, 10 + (labelIndexes[4] * rowSpacing), FRAME_WIDTH - 20, 20);
    labels[5].setBounds(10, 10 + (labelIndexes[5] * rowSpacing), FRAME_WIDTH - 20, 20);
    labels[6].setBounds(10, 10 + (labelIndexes[6] * rowSpacing), FRAME_WIDTH - 20, 20);
    // end of ToDo
    for (JLabel label : labels) {
      cp.add(label);
    }
  
    cp.setBackground(Color.WHITE);
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
    labelIndexes = new int[vts.size()];
    labelIndexes[0] = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.TRICK_NAME).getIndex();
    labelIndexes[1] = VisualizationTupelListFunctions.getTupelByMetric
        (vts, Metric.ATTEMPTS).getIndex();
    labelIndexes[2] = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.FAILS).getIndex();
    labelIndexes[3] = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES).getIndex();
    labelIndexes[4] = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_BACK_TO_BACK).getIndex();
    labelIndexes[5] = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_HIGHSCORE).getIndex();
    labelIndexes[6] = VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESS_PERCENTAGE).getIndex();
  }
  
  public void setIndexesWithoutHidden() {
    nonHiddenLabelIndexes = new int[VisualizationTupelListFunctions.getActiveMetricsAmount(vts)];
    int counter = 0;
    for (int i = 0; i < labelIndexes.length; i++) {
      if (vts.get(i).isActive()) {
        nonHiddenLabelIndexes[counter] = vts.get(i).getIndex();
        counter++;
      }
    }
  }
  
  public void updateAttempts() {
    labels[1].setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.ATTEMPTS).getName() + tcpf.getTrick().getAttempts());
  }

  public void updateFails() {
  
    labels[2].setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.FAILS).getName() + (tcpf.getTrick().getAttempts() - tcpf.getTrick().getSuccesses()));
  }

  public void updateSuccesses() {
    labels[3].setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES).getName() + tcpf.getTrick().getSuccesses());
  }

  public void updateSuccessesBackToBack() {
    labels[4].setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_BACK_TO_BACK).getName() + tcpf.getTrick().getSuccessesBackToBack());
  }

  public void updateSuccessesHighscore() {
    labels[5].setText(VisualizationTupelListFunctions.getTupelByMetric(
        vts, Metric.SUCCESSES_HIGHSCORE).getName() + tcpf.getTrick().getSuccessesHighscore());
  }

  public void updateSuccessPercentage() {
    labels[6].setText(VisualizationTupelListFunctions.getTupelByMetric(
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
      return df.format(tcpf.getTrick().getSuccessPercentage()) + "%";
    } else {
      return "undefined";
    }
  }
}
