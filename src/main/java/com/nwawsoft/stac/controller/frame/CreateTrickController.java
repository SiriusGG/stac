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
  private boolean firstSelection = true;

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

  public void addTrick() {
    addTrick(ctf.getTextFieldName().getText().trim(),
        TrickFileHandler.trimmedFileString(ctf.getTextFieldFileName().getText()));
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          addTrick();
        }
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          addTrick();
        }
      }

      public void keyReleased(KeyEvent e) {
        defaultName = false;
      }
    };
  }

  public FocusAdapter getFileNameFocusAdapter() {
    return new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
        fileNameFieldOnSelect();
      }
    };
  }

  private void fileNameFieldOnSelect() {
    if (defaultName && firstSelection) {
      ctf.getTextFieldFileName().selectAll();
      ctf.getTextFieldFileName().setForeground(Color.BLACK);
      firstSelection = false;
    }
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
