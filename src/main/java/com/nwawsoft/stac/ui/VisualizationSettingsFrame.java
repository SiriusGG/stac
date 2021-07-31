package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.controller.frame.STACFrameController;
import com.nwawsoft.stac.controller.frame.VisualizationSettingsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VisualizationSettingsFrame extends JFrame {
  private final VisualizationSettingsController vsc;
  private final STACFrameController sfc;
  private final int squareButtonSize = 50;
  private JButton[][] buttons;
  private JTextField spacingField;
  private JButton fontButton;

  public VisualizationSettingsFrame(final VisualizationSettingsController vsc) {
    super("Visualization Settings");
    this.vsc = vsc;
    this.sfc = null;
    vsc.frameInitDefault();
    init();
  }

  public VisualizationSettingsFrame(final VisualizationSettingsController vsc, final STACFrameController sfc) {
    super("Visualization Settings");
    this.vsc = vsc;
    this.sfc = sfc;
    vsc.frameInitTrick();
    init();
  }

  public void init() {
    vsc.setVisualizationTupels();
    vsc.setFont();
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    Container cp = getContentPane();
    cp.setLayout(null);

    vsc.loadGraphics();

    int buttonColumns = 3;
    int optionsHeight = 20;
    int vertSpacer = 8;
    int rectangleButtonHeight = 40;
    int spacingLabelWidth = 60;
    int spacingFieldWidth = 40;
    int leftSpacer = 10;
    int fontLabelWidth = 40;
    int fontBoxWidth = 160;
    int horiSpacer = 25;

    buttons = new JButton[buttonColumns][vsc.getMetricsAmount()];
    for (int i = 0; i < vsc.getMetricsAmount(); i++) {
      buttons[0][i] = new JButton();
      buttons[0][i].setBounds(0, squareButtonSize * i, squareButtonSize, squareButtonSize);
      buttons[0][i].setText("H" + i); // needed for event recognition
      buttons[0][i].setToolTipText("Show/Hide metric on slot " + i);
      buttons[0][i].addActionListener(this::showButtonActionPerformed);
      buttons[1][i] = new JButton();
      buttons[1][i].setBounds(250, squareButtonSize * i, squareButtonSize, squareButtonSize);
      buttons[1][i].setText("D" + i); // needed for event recognition
      buttons[1][i].setIcon(vsc.getIiDown());
      buttons[1][i].setToolTipText("Move metric from slot " + i + " down to " + (i + 1));
      buttons[1][i].addActionListener(this::downButtonActionPerformed);
      buttons[2][i] = new JButton();
      buttons[2][i].setBounds(300, squareButtonSize * i, squareButtonSize, squareButtonSize);
      buttons[2][i].setText("U" + i); // needed for event recognition
      buttons[2][i].setIcon(vsc.getIiUp());
      buttons[2][i].setToolTipText("Move metric from slot " + i + " up to " + (i - 1));
      buttons[2][i].addActionListener(this::upButtonActionPerformed);
      cp.add(buttons[0][i]);
      cp.add(buttons[1][i]);
      cp.add(buttons[2][i]);
    }
    buttons[2][0].setVisible(false); // disables the "up" button of the metric in slot 1
    buttons[1][vsc.getMetricsAmount() - 1].setVisible(false); // disables the "down" button of the metric in the last slot.

    String spacingToolTipText = "Specify how much space should be shown between metrics";
    JLabel spacingLabel = new JLabel("Spacing: ");
    spacingLabel.setBounds(leftSpacer, squareButtonSize * vsc.getMetricsAmount() + vertSpacer, spacingLabelWidth,
        optionsHeight);
    spacingLabel.setToolTipText(spacingToolTipText);
    cp.add(spacingLabel);
    spacingField = new JTextField();
    spacingField.setBounds(leftSpacer + spacingLabelWidth, squareButtonSize * vsc.getMetricsAmount() + vertSpacer,
        spacingFieldWidth, optionsHeight);
    spacingField.setText("" + vsc.getVisualizationSettings().getSpacing());
    spacingField.setToolTipText(spacingToolTipText);
    cp.add(spacingField);

    String fontToolTipText = "Specify a font";
    JLabel fontLabel = new JLabel("Font: ");
    fontLabel.setBounds(leftSpacer + spacingLabelWidth + spacingFieldWidth + horiSpacer,
        squareButtonSize * vsc.getMetricsAmount() + vertSpacer, fontLabelWidth, optionsHeight);
    fontLabel.setToolTipText(fontToolTipText);
    cp.add(fontLabel);
    fontButton = new JButton(vsc.getFontButtonString());
    fontButton.addActionListener(this::fontButtonActionPerformed);
    fontButton.setBounds(leftSpacer + spacingLabelWidth + spacingFieldWidth + horiSpacer + fontLabelWidth,
        squareButtonSize * vsc.getMetricsAmount() + vertSpacer, fontBoxWidth, optionsHeight);
    fontButton.setToolTipText(fontToolTipText);
    cp.add(fontButton);

    JButton saveButton = new JButton("Save");
    saveButton.setBounds(10, squareButtonSize * vsc.getMetricsAmount() + (vertSpacer * 2) + optionsHeight, 160, rectangleButtonHeight);
    saveButton.addActionListener(this::saveButtonActionPerformed);
    cp.add(saveButton);

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(180, squareButtonSize * vsc.getMetricsAmount() + (vertSpacer * 2) + optionsHeight, 160, rectangleButtonHeight);
    cancelButton.addActionListener(this::cancelButtonActionPerformed);
    cp.add(cancelButton);

    this.addWindowListener(vsc.handleClosing());

    setResizable(false);
    setVisible(true);
  }


  private void showButtonActionPerformed(final ActionEvent actionEvent) {
    vsc.doShow(actionEvent);
  }

  private void downButtonActionPerformed(final ActionEvent actionEvent) {
    vsc.doDown(actionEvent);
  }

  private void upButtonActionPerformed(final ActionEvent actionEvent) {
    vsc.doUp(actionEvent);
  }

  private void fontButtonActionPerformed(final ActionEvent actionEvent) {
    vsc.handleFont();
  }

  private void saveButtonActionPerformed(final ActionEvent actionEvent) {
    vsc.doSave();
  }

  private void cancelButtonActionPerformed(final ActionEvent actionEvent) {
    vsc.doCancel();
  }

  public JButton[][] getButtons() {
    return buttons;
  }

  public JTextField getSpacingField() {
    return spacingField;
  }

  public JButton getFontButton() {
    return fontButton;
  }

  public int getSquareButtonSize() {
    return squareButtonSize;
  }

  public STACFrameController getController() {
    return sfc;
  }
}
