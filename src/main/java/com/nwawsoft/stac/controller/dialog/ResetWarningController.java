package com.nwawsoft.stac.controller.dialog;

import com.nwawsoft.stac.controller.frame.TrickControlPanelController;
import com.nwawsoft.stac.ui.ResetWarningDialog;
import com.nwawsoft.util.html.HTMLTagger;

import javax.swing.*;

public class ResetWarningController implements STACDialogController {
  private final TrickControlPanelController tcpc;
  private ResetWarningDialog rwd;

  public ResetWarningController(final TrickControlPanelController tcpc) {
    this.tcpc = tcpc;
  }

  @Override
  public JDialog getDialog() {
    return rwd;
  }

  @Override
  public void createDialog() {
    rwd = new ResetWarningDialog(this);
  }

  public String getResetQuestion1Text() {
    return HTMLTagger.toHTML("Your unsaved attempts will be lost.");
  }

  public String getResetQuestion2Text() {
    return HTMLTagger.toHTML("Really reload from file?");
  }

  public void reloadTrick() {
    tcpc.reloadTrick();
  }

  public TrickControlPanelController getSuperController() {
    return tcpc;
  }
}
