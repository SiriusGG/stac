package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.BuildData;
import com.nwawsoft.stac.model.TrickFileHandler;
import com.nwawsoft.stac.model.Trick;
import com.nwawsoft.stac.ui.CreateTrickFrame;
import com.nwawsoft.stac.ui.FileNameDialog;
import com.nwawsoft.stac.ui.TrickControlPanelFrame;

public class UIController {
  public static void addTrick(final String name, final String file, final CreateTrickFrame ctf) {
    if (!file.equals("")) {
      Trick t = new Trick(BuildData.VERSION, name, file, 0, 0, 0, 0);
      TrickFileHandler.save(t);
      new TrickControlPanelFrame(ctf, t);
    } else {
      new FileNameDialog(ctf);
    }
  }
}
