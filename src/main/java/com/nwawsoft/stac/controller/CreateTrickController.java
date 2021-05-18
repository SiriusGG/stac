package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;

import javax.swing.*;

import static com.nwawsoft.stac.BuildData.*;

public class CreateTrickController {
  private final CreateTrickFrame ctf;

  public CreateTrickController(final CreateTrickFrame ctf) {
    this.ctf = ctf;
  }

  public void addTrick(final JTextField textFieldName, final JTextField textFieldFileName) {
    addTrick(textFieldName.getText().trim(), TrickFileHandler.trimmedFileString(textFieldFileName.getText()));
  }

  private void addTrick(final String name, final String file) {
    if (!file.equals("")) {
      Trick t = new Trick(VERSION, name, file, 0, 0, 0, 0);
      TrickFileHandler.save(t);
      new TrickControlPanelFrame(ctf, t);
    } else {
      new FileNameDialog(ctf);
    }
  }

  public void doCancel() {
    new MainMenuFrame();
    ctf.dispose();
  }
}
