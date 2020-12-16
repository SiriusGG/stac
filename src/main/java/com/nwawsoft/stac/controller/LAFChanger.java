package com.nwawsoft.stac.controller;

import javax.swing.*;

public class LAFChanger {
  private LookAndFeel defaultLAF;
  private String mode;

  public LAFChanger() {
    defaultLAF = UIManager.getLookAndFeel();
    mode = "default";
  }

  public void setMotif() {
    if (mode.equals("default")) {
      try {
        defaultLAF = UIManager.getLookAndFeel();
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      } catch (ClassNotFoundException |
          UnsupportedLookAndFeelException |
          IllegalAccessException |
          InstantiationException e) {
        e.printStackTrace();
      }
    }
    mode = "motif";
  }

  public void setDefault() {
    if (mode.equals("motif")) {
      try {
        UIManager.setLookAndFeel(defaultLAF);
      } catch (UnsupportedLookAndFeelException e) {
        e.printStackTrace();
      }
    }
    mode = "default";
  }
}
