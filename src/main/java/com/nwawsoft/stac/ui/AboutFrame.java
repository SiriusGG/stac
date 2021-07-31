package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.frame.AboutController;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {
  private final AboutController ac;

  public AboutFrame(final AboutController ac) {
    super("About");
    this.ac = ac;
    init();
  }

  private void init() {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 180;
    setSize(frameWidth, frameHeight);
    Container cp = getContentPane();
    cp.setLayout(null);

    JLabel labelAboutText1 = new JLabel(ac.getLabelAboutText1(), SwingConstants.CENTER);
    labelAboutText1.setBounds(0, 0, frameWidth, 20);
    cp.add(labelAboutText1);
    JLabel labelAboutText2 = new JLabel(ac.getLabelAboutText2(), SwingConstants.CENTER);
    labelAboutText2.setBounds(0, 20, frameWidth, 20);
    cp.add(labelAboutText2);
    JLabel labelAboutText3 = new JLabel(ac.getLabelAboutText3(), SwingConstants.CENTER);
    labelAboutText3.setBounds(0, 40, frameWidth, 20);
    cp.add(labelAboutText3);
    JLabel labelAboutText4 = new JLabel(ac.getLabelAboutText4(), SwingConstants.CENTER);
    labelAboutText4.setBounds(0, 60, frameWidth, 20);
    labelAboutText4.setForeground(Color.BLUE.darker());
    labelAboutText4.setCursor(new Cursor(Cursor.HAND_CURSOR));
    labelAboutText4.addMouseListener(ac.createWebsiteLinkMouseAdapter());
    cp.add(labelAboutText4);
    JLabel labelAboutText5 = new JLabel(ac.getLabelAboutText5(), SwingConstants.CENTER);
    labelAboutText5.setBounds(0, 80, frameWidth, 20);
    cp.add(labelAboutText5);
    JLabel labelAboutText6 = new JLabel(ac.getLabelAboutText6(), SwingConstants.CENTER);
    labelAboutText6.setBounds(0, 100, frameWidth, 20);
    labelAboutText6.setForeground(Color.BLUE.darker());
    labelAboutText6.setCursor(new Cursor(Cursor.HAND_CURSOR));
    labelAboutText6.addMouseListener(ac.createGitHubLinkMouseAdapter());
    cp.add(labelAboutText6);

    setResizable(false);
    setVisible(true);
  }
}