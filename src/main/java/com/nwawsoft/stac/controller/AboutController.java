package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.ui.AboutFrame;
import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

public class AboutController implements STACFrameController {
  private AboutFrame af;

  public AboutController() {
  }

  @Override
  public JFrame getFrame() {
    return af;
  }

  @Override
  public void centerFrame() {
    ComponentFunctions.center(af);
  }

  @Override
  public void createFrame() {
    af = new AboutFrame(this);
  }

  public MouseListener createWebsiteLinkMouseAdapter() {
    return new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          Desktop.getDesktop().browse(new URI(BuildData.WEBSITE_LINK));
        } catch (IOException | URISyntaxException e1) {
          e1.printStackTrace();
        }
      }
    };
  }

  public MouseListener createGitHubLinkMouseAdapter() {
    return new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          Desktop.getDesktop().browse(new URI(BuildData.GITHUB_LINK));
        } catch (IOException | URISyntaxException e1) {
          e1.printStackTrace();
        }
      }
    };
  }

  public String getLabelAboutText1() {
    return HTMLTagger.toHTML("Version " + BuildData.VERSION);
  }

  public String getLabelAboutText2() {
    return HTMLTagger.toHTML("by Sirius GG");
  }

  public String getLabelAboutText3() {
    return HTMLTagger.toHTML("Newest version at");
  }

  public String getLabelAboutText4() {
    return HTMLTagger.toLink(BuildData.WEBSITE);
  }

  public String getLabelAboutText5() {
    return HTMLTagger.toHTML("and");
  }

  public String getLabelAboutText6() {
    return HTMLTagger.toLink(BuildData.GITHUB);
  }
}
