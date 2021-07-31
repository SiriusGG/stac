package com.nwawsoft.stac.controller.dialog;

import com.nwawsoft.stac.controller.frame.STACFrameController;
import com.nwawsoft.stac.ui.FileNameDialog;

import javax.swing.*;

public class FileNameController implements STACDialogController {
  private final STACFrameController sfc;
  private FileNameDialog fnd;

  public FileNameController(final STACFrameController sfc) {
    this.sfc = sfc;
  }

  @Override
  public JDialog getDialog() {
    return fnd;
  }

  @Override
  public void createDialog() {
    fnd = new FileNameDialog(sfc.getFrame());
  }
}
