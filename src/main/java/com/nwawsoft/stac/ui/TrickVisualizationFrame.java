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
import static com.nwawsoft.stac.model.VisualizationSettingsFileHandler.*;
import static com.nwawsoft.stac.model.VisualizationTupelListFunctions.*;

public class TrickVisualizationFrame extends JFrame {

  private final TrickControlPanelFrame tcpf;
  
  public final static int FRAME_WIDTH = 340;
  public final static int TITLE_BAR_SIZE = 30;
  
  private ArrayList<VisualizationTupel> vts;

  private JLabel[] labels;
  private int[] nonHiddenIndexes;

  private final DecimalFormat df = new DecimalFormat("#.##");
  
  private final int initialOffset = 10;

  public TrickVisualizationFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization");
    this.tcpf = tcpf;
    init();
  }

  public void init() {
    String fileName = tcpf.getTrick().getFileName();
    String path = USER_HOME + "/" + DIRECTORY_NAME + "/" + fileName + "." +
        TRICK_VISUALIZATION_FILE_FORMAT;
    File f = new File(path);
    if (!f.exists()) {
      VisualizationSettings mainVisualizationSettings = load(VISUALIZATION_FILE_FULL_NAME);
      save(mainVisualizationSettings, fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
    }
    VisualizationSettings vs = load(fileName + "." + TRICK_VISUALIZATION_FILE_FORMAT);
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

    df.setRoundingMode(RoundingMode.HALF_UP);
    int rowSpacing = vs.getSpacing();
    nonHiddenIndexes = generateIndexArrayWithoutHiddenEntries();
    labels = new JLabel[nonHiddenIndexes.length];
    Font font = vs.getFont();

    for (int i = 0; i < nonHiddenIndexes.length; i++) {
      labels[i] = new JLabel();
      labels[i].setFont(font);
      labels[i].setBounds(10, initialOffset + (i * rowSpacing) + (i * font.getSize()), 4096, font.getSize());
      cp.add(labels[i]);
    }
    updateStats();
  
    cp.setBackground(Color.WHITE);
    setResizable(true);
    setVisible(true);
  }

  private int calcHeight(final VisualizationSettings vs) {
    int activeModules = getActiveMetricsAmount(vts);
    int spacing = vs.getSpacing();
    int fontSize = vs.getFont().getSize();
    return TITLE_BAR_SIZE + (activeModules*fontSize) + (spacing*activeModules) + (2 * initialOffset);
  }
  
  public int[] generateIndexArrayWithoutHiddenEntries() {
    int[] indexes = new int[getActiveMetricsAmount(vts)];
    for (int i = 0; i < vts.size(); i++) {
      VisualizationTupel vt = vts.get(i);
      if (vt.isActive()) {
        indexes[i] = vt.getIndex();
      }
    }
    return indexes;
  }

  public void updateStats() {
    // ToDo: Fix ArrayOutOfBoundsException which occurs when not all metrics are active.
    for (int i = 0; i < nonHiddenIndexes.length; i++) {
      if (nonHiddenIndexes[i] == 0) {
        labels[nonHiddenIndexes[i]].setText(vts.get(i).getName() + (String) tcpf.getTrick().getValueByMetricIndex(nonHiddenIndexes[i]));
      } else if (nonHiddenIndexes[i] == 1 || nonHiddenIndexes[i] == 2 || nonHiddenIndexes[i] == 3 || nonHiddenIndexes[i] == 4 || nonHiddenIndexes[i] == 5) {
        labels[nonHiddenIndexes[i]].setText(vts.get(i).getName() + (int) tcpf.getTrick().getValueByMetricIndex(nonHiddenIndexes[i]));
      } else if (nonHiddenIndexes[i] == 6) {
        labels[nonHiddenIndexes[i]].setText(vts.get(i).getName() + getSuccessPercentage());
      } else {
        System.err.println("Unknown index for metric: " + nonHiddenIndexes[i]);
      }
    }
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
