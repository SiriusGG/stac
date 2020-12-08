package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.FileHandler;
import com.nwawsoft.stac.model.SettingsHandler;
import com.nwawsoft.stac.model.Trick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrickControlPanelFrame extends JFrame {
  public final static int FRAME_WIDTH = 260;
  public final static int FRAME_HEIGHT = 140;

  private static final String FAILED_PREFIX = "Key for failed attempt: ";
  private static final String SUCCESSFUL_PREFIX = "Key for successful attempt: ";

  private final Trick t;
  private final TrickVisualizationFrame tvf;

  public TrickControlPanelFrame(final JFrame calledBy, final Trick t) {
    super("Control Panel");
    calledBy.dispose();
    this.t = t;
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int width = FRAME_WIDTH + TrickVisualizationFrame.FRAME_WIDTH;
    int height = FRAME_HEIGHT + TrickVisualizationFrame.FRAME_HEIGHT;
    int x = ((d.width - width) / 2) ;
    int y = (d.height - height) / 2;
    setLocation(x, y);
    Container cp = getContentPane();
    cp.setLayout(null);

    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        if (!getTrick().equals(FileHandler.load(getTrick().getFileName()))) {
          openSaveDialogOnClose();
        } else {
          System.exit(0);
        }
      }
    });

    JLabel labelFailedKey = new JLabel();
    JLabel labelFailedKeyValue = new JLabel();
    JLabel labelSuccessfulKey = new JLabel();
    JLabel labelSuccessfulKeyValue = new JLabel();
    labelFailedKey.setBounds(10, 10, 170, 20);
    labelFailedKeyValue.setBounds(180, 10, 30, 20);
    labelSuccessfulKey.setBounds(10, 40, 170, 20);
    labelSuccessfulKeyValue.setBounds(180, 40, 30, 20);
    labelFailedKey.setText(FAILED_PREFIX);
    labelFailedKeyValue.setText(SettingsHandler.load()[0]);
    labelSuccessfulKey.setText(SUCCESSFUL_PREFIX);
    labelSuccessfulKeyValue.setText(SettingsHandler.load()[1]);
    cp.add(labelFailedKey);
    cp.add(labelFailedKeyValue);
    cp.add(labelSuccessfulKey);
    cp.add(labelSuccessfulKeyValue);

    JButton buttonMenu = new JButton("Back to Menu");
    buttonMenu.setBounds(10, 70, 140, 30);
    buttonMenu.addActionListener(this::buttonMenuActionPerformed);

    JButton buttonSave = new JButton("Save");
    buttonSave.setBounds(160, 70, 80, 30);
    buttonSave.addActionListener(this::buttonSaveActionPerformed);

    cp.add(buttonMenu);
    cp.add(buttonSave);

    setResizable(false);
    setVisible(true);

    tvf = new TrickVisualizationFrame(this);
  }

  private void buttonSaveActionPerformed(final ActionEvent actionEvent) {
    FileHandler.save(t);
  }

  public Trick getTrick() {
    return t;
  }

  private void buttonMenuActionPerformed(final ActionEvent actionEvent) {
    if (!getTrick().equals(FileHandler.load(getTrick().getFileName()))) {
      new SaveWarningDialog(this, true);
    } else {
      tvf.dispose();
      new MainMenuFrame(this);
    }
  }

  public TrickVisualizationFrame getVisualization() {
    return tvf;
  }

  public void openSaveDialogOnClose() {
    new SaveWarningDialog(this, false);
  }
}
