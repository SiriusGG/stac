package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualizationSettingsFrame extends JFrame {
  private Trick t = null;

  private final static int FRAME_WIDTH = 340;
  private final static int FRAME_HEIGHT = 210;
  
  private String mode;
  private boolean somethingChanged = false; // ToDo: set to true on change

  public VisualizationSettingsFrame() {
    super("Visualization Settings");
    mode = "default";
    init();
  }

  public VisualizationSettingsFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization Settings");
    this.t = tcpf.getTrick();
    mode = "trick";
    init();
  }

  public void init() {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    // ToDo: Add elements

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        doClose();
      }
    });

    setResizable(false);
    setVisible(true);
  }

  // ToDo: "save and start" button

  public void doClose() {
    if (somethingChanged) {
      new SaveWarningDialog(this, "visualization settings", "close");
    } else {
      if (!(t == null)) {
        new TrickControlPanelFrame(this, t);
      } else {
        new MainMenuFrame();
        dispose();
      }
    }
  }

  public Trick getTrick() {
    return t;
  }
  
  public static void main(String[] args) {
    new VisualizationSettingsFrame();
  }
}
