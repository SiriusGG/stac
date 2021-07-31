package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.TrickVisualizationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;
import static com.nwawsoft.stac.model.VisualizationSettingsFileHandler.*;
import static com.nwawsoft.stac.model.VisualizationTupelListFunctions.getActiveMetricsAmount;

public class TrickVisualizationController implements STACFrameController {
  public final static int FRAME_WIDTH = 340;
  public final static int TITLE_BAR_SIZE = 30;
  private final int initialOffset = 10;
  private final DecimalFormat df = new DecimalFormat("#.##");
  private final TrickControlPanelController tcpc;
  private int frameHeight;
  private ArrayList<VisualizationTupel> vts;
  private int activeModules;
  private TrickVisualizationFrame tvf;

  private VisualizationSettings vs;

  public TrickVisualizationController(final TrickControlPanelController tcpc) {
    this.tcpc = tcpc;
  }

  public void frameSetup() {
    String fileName = tcpc.getTrick().getFileName();
    String path = USER_HOME + "/" + DIRECTORY_NAME + "/" + fileName + "." +
        TRICK_VISUALIZATION_FILE_FORMAT;
    File f = new File(path);
    if (!f.exists()) {
      VisualizationSettings mainVisualizationSettings = load(VISUALIZATION_FILE_FULL_NAME);
      save(mainVisualizationSettings, fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
    }
    vs = load(fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
    vts = VisualizationTupelListFunctions.sortByIndex(
        VisualizationTupelListFunctions.filterActive(vs.getVisualizationTupels()));
    activeModules = getActiveMetricsAmount(vts);
    frameHeight = calcHeight(vs);
  }

  public int getRowSpacing() {
    return vs.getSpacing();
  }

  public VisualizationSettings getVisualizationSettings() {
    return vs;
  }

  @Override
  public JFrame getFrame() {
    return tvf;
  }

  @Override
  public void centerFrame() {
    setFrameSize();
    setFramePos();
  }

  @Override
  public void createFrame() {
    tvf = new TrickVisualizationFrame(this);
  }

  @Override
  public void fullCreate() {
    createFrame();
    centerFrame();
  }

  public void updateStats() {
    int counter = 0;
    for (VisualizationTupel vt : vts) {
      Metric metric = vt.getMetric();
      switch (metric) {
        case TRICK_NAME:
          //noinspection RedundantCast
          tvf.getLabels()[counter].setText(vt.getName() + (String) tcpc.getTrick().getValueByMetric(metric));
          break;
        case ATTEMPTS:
        case FAILS:
        case SUCCESSES:
        case SUCCESSES_BACK_TO_BACK:
        case SUCCESSES_HIGHSCORE:
          tvf.getLabels()[counter].setText(vt.getName() + (int) tcpc.getTrick().getValueByMetric(metric));
          break;
        case SUCCESS_PERCENTAGE:
          tvf.getLabels()[counter].setText(vt.getName() + getSuccessPercentage());
          break;
        default:
          //noinspection UnnecessaryToStringCall
          System.err.println("Unknown Metric: " + metric.toString());
          break;
      }
      counter++;
    }
  }

  private int calcHeight(final VisualizationSettings vs) {
    int spacing = vs.getSpacing();
    int fontSize = vs.getFont().getSize();
    return TITLE_BAR_SIZE + (activeModules * fontSize) + (spacing * activeModules) + (2 * initialOffset);
  }

  public void openSaveDialog() {
    SaveWarningController swc = new SaveWarningController(this, "visualization", "close");
    swc.createDialog();
  }

  public String getSuccessPercentage() {
    if (tcpc.getTrick().getAttempts() != 0) {
      return df.format(tcpc.getTrick().getSuccessPercentage()) + "%";
    } else {
      return "undefined";
    }
  }

  public TrickControlPanelController getControlPanelController() {
    return tcpc;
  }

  public void setFrameSize() {
    tvf.setSize(FRAME_WIDTH, frameHeight);
  }

  public void setFramePos() {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int width = FRAME_WIDTH + TrickControlPanelController.FRAME_WIDTH;
    int x = (d.width - width) / 2;
    int y = (d.height - frameHeight) / 2;
    tvf.setLocation(x + TrickControlPanelController.FRAME_WIDTH, y);
  }

  public void handleRounding() {
    df.setRoundingMode(RoundingMode.HALF_UP);
  }

  public WindowListener handleClosing() {
    return new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (!tcpc.getTrick().equals(TrickFileHandler.load(tcpc.getTrick().getFileName()))) {
          openSaveDialog();
        } else {
          System.exit(0);
        }
      }
    };
  }

  public int getActiveModules() {
    return activeModules;
  }

  public int getInitialOffset() {
    return initialOffset;
  }
}
