package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.TrickVisualizationController;

import javax.swing.*;
import java.awt.*;

public class TrickVisualizationFrame extends JFrame {
  private final TrickVisualizationController tvc;

  private JLabel[] labels;

  public TrickVisualizationFrame(final TrickVisualizationController tvc) {
    super("Visualization");
    this.tvc = tvc;
    init();
  }

  public void init() {
    tvc.frameSetup(); // ToDo: Split this into multiple functions and rename
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    Container cp = getContentPane();
    cp.setLayout(null);
    this.addWindowListener(tvc.handleClosing());

    tvc.handleRounding();

    int rowSpacing = tvc.getRowSpacing();
    int verticalTextMargin = 4;
    labels = new JLabel[tvc.getActiveModules()];
    Font font = tvc.getVisualizationSettings().getFont();
    for (int i = 0; i < tvc.getActiveModules(); i++) {
      labels[i] = new JLabel();
      labels[i].setFont(font);
      labels[i].setBounds(10, tvc.getInitialOffset() + (i * rowSpacing) + (i * font.getSize()),
          4096, font.getSize() + verticalTextMargin);
      cp.add(labels[i]);
    }

    cp.setBackground(Color.WHITE);
    setResizable(true);
    setVisible(true);
  }

  public JLabel[] getLabels() {
    return labels;
  }
}
