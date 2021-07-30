package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.ui.FileNameDialog;

import javax.swing.*;

public class FileNameController implements STACDialogController {
  private final JFrame calledBy;
  private FileNameDialog fnd;

  public FileNameController(final JFrame calledBy) {
    this.calledBy = calledBy;
  }

  @Override
  public JDialog getDialog() {
    return fnd;
  }

  @Override
  public void createDialog() {
    fnd = new FileNameDialog(calledBy);
  }
}
