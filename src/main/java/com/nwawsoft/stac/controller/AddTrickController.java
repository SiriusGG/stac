package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.*;

import static com.nwawsoft.stac.BuildData.*;

public class AddTrickController {
  public static void addTrick(final String name, final String file, final CreateTrickFrame ctf) {
    if (!file.equals("")) {
      Trick t = new Trick(VERSION, name, file, 0, 0, 0, 0);
      TrickFileHandler.save(t);
      new TrickControlPanelFrame(ctf, t);
    } else {
      new FileNameDialog(ctf);
    }
  }
}
