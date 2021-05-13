package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;
import static com.nwawsoft.stac.model.VisualizationTupelListFunctions.*;

public class VisualizationSettingsFrame extends JFrame {
  private Trick trick = null;

  private final static int FRAME_WIDTH = 365;
  private final static int FRAME_HEIGHT = 460;

  private final String mode;
  private boolean somethingChanged = false;

  private final VisualizationSettings vs;
  private ArrayList<VisualizationTupel> visualizationTupels;

  private ImageIcon iiOn;
  private ImageIcon iiOff;
  private ImageIcon iiDown;
  private ImageIcon iiUp;
  private JTextField[] textFields;
  private JButton[][] buttons;

  int metricsAmount = Metric.values().length;

  public VisualizationSettingsFrame() {
    super("Visualization Settings");
    mode = "default";
    vs = VisualizationSettingsFileHandler.load(VISUALIZATION_FILE_FULL_NAME);
    init();
  }

  public VisualizationSettingsFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization Settings");
    this.trick = tcpf.getTrick();
    mode = "trick";
    vs = VisualizationSettingsFileHandler.load(trick.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT);
    init();
  }

  public void init() {
    visualizationTupels = vs.getVisualizationTupels();
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    ComponentFunctions.center(this);
    Container cp = getContentPane();
    cp.setLayout(null);

    String currentImage = "/" + GRAPHICS_DIRECTORY + "/on.png";
    InputStream iiStream = getClass().getResourceAsStream(currentImage);
    if (iiStream != null) {
      try {
        iiOn = new ImageIcon(ImageIO.read(iiStream));
      } catch (IOException e) {
        iiOn = new ImageIcon();
        System.err.println("Did not find graphics file " + currentImage);
        e.printStackTrace();
      }
    }
    currentImage = "/" + GRAPHICS_DIRECTORY + "/off.png";
    iiStream = getClass().getResourceAsStream(currentImage);
    if (iiStream != null) {
      try {
        iiOff = new ImageIcon(ImageIO.read(iiStream));
      } catch (IOException e) {
        iiOff = new ImageIcon();
        System.err.println("Did not find graphics file " + currentImage);
        e.printStackTrace();
      }
      currentImage = "/" + GRAPHICS_DIRECTORY + "/down.png";
    }
    iiStream = getClass().getResourceAsStream(currentImage);
    if (iiStream != null) {
      try {
        iiDown = new ImageIcon(ImageIO.read(iiStream));
      } catch (IOException e) {
        iiDown = new ImageIcon();
        System.err.println("Did not find graphics file " + currentImage);
        e.printStackTrace();
      }
      currentImage = "/" + GRAPHICS_DIRECTORY + "/up.png";
    }
    iiStream = getClass().getResourceAsStream(currentImage);
    if (iiStream != null) {
      try {
        iiUp = new ImageIcon(ImageIO.read(iiStream));
      } catch (IOException e) {
        iiUp = new ImageIcon();
        System.err.println("Did not find graphics file " + currentImage);
        e.printStackTrace();
      }
    }

    int buttonColumns = 3;

    // ToDo: Fix non-centered button graphics, especially up/down (probably because of text?)
    buttons = new JButton[buttonColumns][metricsAmount];
    for (int i = 0; i < metricsAmount; i++) {
      buttons[0][i] = new JButton();
      buttons[0][i].setBounds(0, 50 * i, 50, 50);
      buttons[0][i].setText("H" + i); // Currently needed for event recognition
      buttons[0][i].setToolTipText("Show/Hide metric on slot " + i);
      buttons[0][i].addActionListener(this::showButtonActionPerformed);
      buttons[1][i] = new JButton();
      buttons[1][i].setBounds(250, 50 * i, 50, 50);
      buttons[1][i].setText("D" + i); // Currently needed for event recognition
      buttons[1][i].setIcon(iiDown);
      buttons[1][i].setToolTipText("Move metric from slot " + i + " down to " + (i + 1));
      buttons[1][i].addActionListener(this::downButtonActionPerformed);
      buttons[2][i] = new JButton();
      buttons[2][i].setBounds(300, 50 * i, 50, 50);
      buttons[2][i].setText("U" + i); // Currently needed for event recognition
      buttons[2][i].setIcon(iiUp);
      buttons[2][i].setToolTipText("Move metric from slot " + i + " up to " + (i - 1));
      buttons[2][i].addActionListener(this::upButtonActionPerformed);
      cp.add(buttons[0][i]);
      cp.add(buttons[1][i]);
      cp.add(buttons[2][i]);
    }
    updateAllHideShowGraphics();
    buttons[2][0].setVisible(false); // disables the "up" button of the metric in slot 1
    buttons[1][metricsAmount - 1].setVisible(false); // disables the "down" button of the metric in the last slot.

    textFields = new JTextField[metricsAmount];
    for (int i = 0; i < metricsAmount; i++) {
      textFields[i] = new JTextField();
      textFields[i].setBounds(50, 50 * i, 200, 50);
      Metric[] metricsInOrder = getMetricsInInternalIndexOrder(visualizationTupels);
      textFields[i].setToolTipText("Name for metric " + metricsInOrder[i].toString());
      cp.add(textFields[i]);
    }
    updateNames();

    // ToDo: Spacing stuff

    // ToDo: Font stuff

    JButton saveButton = new JButton("Save");
    saveButton.setBounds(10, 50 * metricsAmount + 10, 160, 50);
    saveButton.addActionListener(this::saveButtonActionPerformed);
    cp.add(saveButton);

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(180, 50 * metricsAmount + 10, 160, 50);
    cancelButton.addActionListener(this::cancelButtonActionPerformed);
    cp.add(cancelButton);

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        doClose();
      }
    });

    setResizable(false);
    setVisible(true);
  }


  private void showButtonActionPerformed(ActionEvent actionEvent) {
    JButton activeButton = (JButton) actionEvent.getSource();
    int i = Integer.parseInt(activeButton.getText().substring(1));
    VisualizationTupelListFunctions.updateShow(visualizationTupels, i);
    updateHideShowGraphic(i);
    somethingChanged = true;
  }

  private void downButtonActionPerformed(ActionEvent actionEvent) {
    saveNames();
    int i = Integer.parseInt(((JButton) actionEvent.getSource()).getText().substring(1));
    VisualizationTupelListFunctions.swapIndex(visualizationTupels, i, i + 1);
    Metric[] metricsInOrder = getMetricsInInternalIndexOrder(visualizationTupels);
    textFields[i].setToolTipText("Name for metric " + metricsInOrder[i].toString());
    textFields[i + 1].setToolTipText("Name for metric " + metricsInOrder[i + 1].toString());
    updateAllHideShowGraphics();
    updateNames();
    somethingChanged = true;
  }

  private void upButtonActionPerformed(ActionEvent actionEvent) {
    saveNames();
    int i = Integer.parseInt(((JButton) actionEvent.getSource()).getText().substring(1));
    VisualizationTupelListFunctions.swapIndex(visualizationTupels, i, i - 1);
    Metric[] metricsInOrder = getMetricsInInternalIndexOrder(visualizationTupels);
    textFields[i].setToolTipText("Name for metric " + metricsInOrder[i].toString());
    textFields[i - 1].setToolTipText("Name for metric " + metricsInOrder[i - 1].toString());
    updateAllHideShowGraphics();
    updateNames();
    somethingChanged = true;
  }

  private void saveButtonActionPerformed(final ActionEvent actionEvent) {
    saveNames();
    if (mode.equals("default")) {
      VisualizationSettingsFileHandler.save(vs, VISUALIZATION_FILE_FULL_NAME);
      new MainMenuFrame();
    } else if (mode.equals("trick")) {
      VisualizationSettingsFileHandler.save(vs, trick.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT);
      new TrickControlPanelFrame(this, trick);
    }
    dispose();
  }

  private void cancelButtonActionPerformed(final ActionEvent actionEvent) {
    if (mode.equals("default")) {
      new MainMenuFrame();
    } else if (mode.equals("trick")) {
      new TrickControlPanelFrame(this, trick);
    }
    dispose();
  }

  public void updateAllHideShowGraphics() {
    for (VisualizationTupel tupel : visualizationTupels) {
      updateHideShowGraphic(tupel.getIndex(), tupel.isActive());
    }
  }

  public void updateHideShowGraphic(final int i) {
    VisualizationTupel tupel = getByInternalIndex(visualizationTupels, i);
    if (tupel != null) {
      updateHideShowGraphic(i, tupel.isActive());
    } else {
      System.err.println("Tupel " + i + " is null.");
    }
  }

  public void updateHideShowGraphic(final int i, final boolean isActive) {
    if (isActive) {
      buttons[0][i].setIcon(iiOn);
    } else {
      buttons[0][i].setIcon(iiOff);
    }
  }

  public void saveNames() {
    for (VisualizationTupel tupel : visualizationTupels) {
      tupel.setName(textFields[tupel.getIndex()].getText());
    }
  }

  public void updateNames() {
    for (VisualizationTupel tupel : visualizationTupels) {
      textFields[tupel.getIndex()].setText(tupel.getName());
    }
  }

  public void doClose() {
    if (somethingChanged) {
      if (mode.equals("default")) {
        new SaveWarningDialog(this, "visualization settings", "close");
      } else if (mode.equals("trick")) {
        new SaveWarningDialog(this, "trick visualization settings", "close");
      }
    } else {
      if (mode.equals("default")) {
        new MainMenuFrame();
      } else if (mode.equals("trick")) {
        new TrickControlPanelFrame(this, trick);
      }
      dispose();
    }
  }

  public Trick getTrick() {
    return trick;
  }

  public String getMode() {
    return mode;
  }

  public VisualizationSettings getVisualizationSettings() {
    return vs;
  }
}
