package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

public class AboutFrame extends JFrame {
  public AboutFrame() {
    super("About");
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 180;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    JLabel labelAboutText1 = new JLabel(HTMLTagger.toHTML("Version " + BuildData.VERSION), SwingConstants.CENTER);
    labelAboutText1.setBounds(0, 0, frameWidth, 20);
    cp.add(labelAboutText1);
    JLabel labelAboutText2 = new JLabel(HTMLTagger.toHTML("by Sirius GG"), SwingConstants.CENTER);
    labelAboutText2.setBounds(0, 20, frameWidth, 20);
    cp.add(labelAboutText2);
    JLabel labelAboutText3 = new JLabel(HTMLTagger.toHTML("Newest version at"), SwingConstants.CENTER);
    labelAboutText3.setBounds(0, 40, frameWidth, 20);
    cp.add(labelAboutText3);
    JLabel labelAboutText4 = new JLabel(HTMLTagger.toLink(BuildData.WEBSITE), SwingConstants.CENTER);
    labelAboutText4.setBounds(0, 60, frameWidth, 20);
    labelAboutText4.setForeground(Color.BLUE.darker());
    labelAboutText4.setCursor(new Cursor(Cursor.HAND_CURSOR));
    labelAboutText4.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          Desktop.getDesktop().browse(new URI(BuildData.WEBSITE_LINK));
        } catch (IOException | URISyntaxException e1) {
          e1.printStackTrace();
        }
      }
    });
    cp.add(labelAboutText4);
    JLabel labelAboutText5 = new JLabel(HTMLTagger.toHTML("and"), SwingConstants.CENTER);
    labelAboutText5.setBounds(0, 80, frameWidth, 20);
    cp.add(labelAboutText5);
    JLabel labelAboutText6 = new JLabel(HTMLTagger.toLink(BuildData.GITHUB), SwingConstants.CENTER);
    labelAboutText6.setBounds(0, 100, frameWidth, 20);
    labelAboutText6.setForeground(Color.BLUE.darker());
    labelAboutText6.setCursor(new Cursor(Cursor.HAND_CURSOR));
    labelAboutText6.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          Desktop.getDesktop().browse(new URI(BuildData.GITHUB_LINK));
        } catch (IOException | URISyntaxException e1) {
          e1.printStackTrace();
        }
      }
    });
    cp.add(labelAboutText6);

    setResizable(false);
    setVisible(true);
  }
}