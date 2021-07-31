package com.nwawsoft.stac.controller.frame;

import com.nwawsoft.stac.controller.dialog.FileNameController;
import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.CreateTrickFrame;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.nwawsoft.stac.BuildData.VERSION;

public class CreateTrickController implements STACFrameController {
  private CreateTrickFrame ctf;
  private boolean defaultName;

  public CreateTrickController() {
    defaultName = true;
  }

  @Override
  public void centerFrame() {
    ComponentFunctions.center(ctf);
  }

  @Override
  public void createFrame() {
    ctf = new CreateTrickFrame(this);
  }

  @Override
  public void fullCreate() {
    createFrame();
    centerFrame();
  }

  public void addTrick(final JTextField textFieldName, final JTextField textFieldFileName) {
    addTrick(textFieldName.getText().trim(), TrickFileHandler.trimmedFileString(textFieldFileName.getText()));
  }

  private void addTrick(final String name, final String file) {
    if (!file.equals("")) {
      Trick trick = new Trick(VERSION, name, file, 0, 0, 0, 0);
      TrickFileHandler.save(trick);
      TrickControlPanelController tcpc = new TrickControlPanelController(trick);
      tcpc.fullCreate();
      ctf.dispose();
    } else {
      FileNameController fnc = new FileNameController(this);
      fnc.createDialog();
    }
  }

  public KeyListener getNameKeyListener(final JTextField textFieldName, final JTextField textFieldFileName) {
    return new KeyListener() {
      public void keyTyped(final KeyEvent e) {
      }

      public void keyPressed(final KeyEvent e) {
      }

      public void keyReleased(final KeyEvent e) {
        if (defaultName) {
          textFieldFileName.setText(TrickFileHandler.trimmedFileString(textFieldName.getText()));
        }
      }
    };
  }

  public KeyListener getFileNameKeyListener() {
    return new KeyListener() {
      public void keyTyped(KeyEvent e) {
        defaultName = false;
      }

      public void keyPressed(KeyEvent e) {
        defaultName = false;
      }

      public void keyReleased(KeyEvent e) {
        defaultName = false;
      }
    };
  }

  public MouseAdapter getFileNameMouseListener(final JTextField textFieldFileName) {
    return new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (defaultName) {
          textFieldFileName.selectAll();
          textFieldFileName.setForeground(Color.BLACK);
        }
      }
    };
  }

  public FocusAdapter getFileNameFocusAdapter(final JTextField textFieldFileName) {
    return new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
        if (defaultName) {
          textFieldFileName.selectAll();
          textFieldFileName.setForeground(Color.BLACK);
        }
      }
    };
  }

  public void doCancel() {
    MainMenuController mmc = new MainMenuController();
    mmc.fullCreate();
    ctf.dispose();
  }

  @Override
  public JFrame getFrame() {
    return ctf;
  }
}
