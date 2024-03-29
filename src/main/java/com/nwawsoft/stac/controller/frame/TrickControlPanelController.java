package com.nwawsoft.stac.controller.frame;

import com.nwawsoft.stac.controller.dialog.ResetWarningController;
import com.nwawsoft.stac.controller.dialog.SaveWarningController;
import com.nwawsoft.stac.controller.LAFFunctions;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.TrickControlPanelFrame;
import com.nwawsoft.stac.controller.TrickChooser;
import org.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrickControlPanelController implements STACFrameController {
  public final static int FRAME_WIDTH = 280;
  public final static int FRAME_HEIGHT = 310;
  private Trick trick;
  private TrickControlPanelFrame tcpf;
  private TrickVisualizationController tvc;
  private LAFFunctions lc;

  public TrickControlPanelController(final Trick trick) {
    this.trick = trick;
  }

  public void createVisualization() {
    tvc = new TrickVisualizationController(this);
    createLAFHandler();
    setMotifLAF();
    tvc.fullCreate();
    tvc.updateStats();
    setDefaultLAF();
  }

  public void addNativeHook() {
    CounterKeyListenerSingleton ckl = CounterKeyListenerSingleton.getCounterKeyListener();
    ckl.setVisualization(tvc);
    if (!CounterKeyListenerSingleton.getCounterKeyListener().isActive()) {
      GlobalScreen.addNativeKeyListener(ckl);
    }
    ckl.setActive(true);
  }

  public void handleOnClose() {
    tcpf.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (!trick.equals(TrickFileHandler.load(trick.getFileName()))) {
          openSaveDialogOnClose();
        } else {
          System.exit(0);
        }
      }
    });
  }

  private void openSaveDialogOnClose() {
    SaveWarningController swc = new SaveWarningController(this, "controlpanel", "close");
    swc.createDialog();
  }

  public void doSave() {
    TrickFileHandler.save(trick);
  }

  public void goToMenu() {
    if (!trick.equals(TrickFileHandler.load(trick.getFileName()))) {
      SaveWarningController swc = new SaveWarningController(this, "controlpanel", "menu");
      swc.createDialog();
    } else {
      tvc.getFrame().dispose();
      MainMenuController mmc = new MainMenuController();
      mmc.fullCreate();
      tcpf.dispose();
    }
  }

  public void doManualFail() {
    trick.recordFail();
    tvc.updateStats();
  }

  public void doManualSuccess() {
    trick.recordSuccess();
    tvc.updateStats();
  }

  public void doReset() {
    if (!trick.equals(TrickFileHandler.load(trick.getFileName()))) {
      ResetWarningController rwc = new ResetWarningController(this);
      rwc.createDialog();
    }
  }

  public void openVisualizationSettings() {
    if (!trick.equals(TrickFileHandler.load(trick.getFileName()))) {
      SaveWarningController swc = new SaveWarningController(this, "controlpanel", "visualization settings");
      swc.createDialog();
    } else {
      tvc.getFrame().dispose();
      VisualizationSettingsController vsc = new VisualizationSettingsController(this);
      vsc.fullCreate();
      tcpf.dispose();
    }
  }

  public Trick getTrick() {
    return trick;
  }

  public void reloadTrick() {
    trick = TrickFileHandler.load(trick.getFileName());
    tvc.updateStats();
  }

  @Override
  public JFrame getFrame() {
    return tcpf;
  }

  @Override
  public void centerFrame() {
    setFrameSize();
    setFramePos();
  }

  @Override
  public void createFrame() {
    tcpf = new TrickControlPanelFrame(this);
  }

  @Override
  public void fullCreate() {
    createFrame();
    handleOnClose();
    createVisualization();
    centerFrame();
    addNativeHook();
  }

  public TrickVisualizationController getVisualizationController() {
    return tvc;
  }

  public void setFrameSize() {
    tcpf.setSize(FRAME_WIDTH, FRAME_HEIGHT);
  }

  public void setFramePos() {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int width = FRAME_WIDTH + tvc.getFrameWidth();
    int x = ((d.width - width) / 2);
    int y = (d.height - FRAME_HEIGHT) / 2;
    tcpf.setLocation(x, y);
  }

  public String loadFailedKeyText() {
    return KeyBindingsFileHandler.load()[0];
  }

  public String loadSuccessfulKeyText() {
    return KeyBindingsFileHandler.load()[1];
  }

  public void createLAFHandler() {
    lc = new LAFFunctions();
  }

  public void setMotifLAF() {
    lc.setMotif();
  }

  public void setDefaultLAF() {
    lc.setDefault();
  }

  public void editTrick() {
    if (!trick.equals(TrickFileHandler.load(trick.getFileName()))) {
      SaveWarningController swc = new SaveWarningController(this, "controlpanel", "edit trick");
      swc.createDialog();
    } else {
      tvc.getFrame().dispose();
      EditTrickController etc = new EditTrickController(trick, "trick control panel");
      etc.fullCreate();
      tcpf.dispose();
    }
  }

  public void switchTrick() {
    if (!trick.equals(TrickFileHandler.load(trick.getFileName()))) {
      SaveWarningController swc = new SaveWarningController(this, "controlpanel", "switch trick");
      swc.createDialog();
    } else {
      Trick t = TrickChooser.createTrickFromJFileChooser();
      if (t != null) {
        TrickControlPanelController tcpc = new TrickControlPanelController(t);
        tcpc.fullCreate();
        tvc.getFrame().dispose();
        tcpf.dispose();
      }
    }
  }
}
