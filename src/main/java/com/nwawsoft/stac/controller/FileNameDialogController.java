package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.ui.FileNameDialog;
import com.nwawsoft.util.ui.ComponentFunctions;

public class FileNameDialogController {
  private final FileNameDialog fnd;

  public FileNameDialogController(final FileNameDialog fnd) {
    this.fnd = fnd;
  }

  public void center() {
    ComponentFunctions.center(fnd);
  }

  public void doOK() {
    fnd.dispose();
  }
}
