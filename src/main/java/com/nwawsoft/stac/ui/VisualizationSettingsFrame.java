package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualizationSettingsFrame extends JFrame {
  private Trick t;

  private final static int FRAME_WIDTH = 340;
  private final static int FRAME_HEIGHT = 210;

  public VisualizationSettingsFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization Settings");
    this.t = tcpf.getTrick();
    tcpf.getVisualization().dispose();
    tcpf.dispose();
    init();
  }

  public void init() {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    // ToDo: Add elements

    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        // ToDo: restore some stable state.
      }
    });

    setResizable(true);
    setVisible(true);
  }

  // ToDo: "Save and start" button

  public static void main(String[] args) { // ToDo: Remove this
    new VisualizationSettingsFrame(new TrickControlPanelFrame(new JFrame(), TrickFileHandler.load("abcde")));
  }
}
