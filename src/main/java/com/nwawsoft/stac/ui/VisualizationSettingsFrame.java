package com.nwawsoft.stac.ui;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;

// BIG TODO

public class VisualizationSettingsFrame extends JFrame {
  private Trick t = null;
  
  private final static int FRAME_WIDTH = 365;
  private final static int FRAME_HEIGHT = 460;
  
  private final String mode;
  private boolean somethingChanged = false;
  
  private final VisualizationSettings vs;
  private ArrayList<VisualizationTupel> visualizationTupels = new ArrayList<>();
  
  private ImageIcon iiOn;
  private ImageIcon iiOff;
  
  public VisualizationSettingsFrame() {
    super("Visualization Settings");
    mode = "default";
    vs = VisualizationSettingsFileHandler.load(VISUALIZATION_FILE_FULL_NAME);
    init();
  }
  
  public VisualizationSettingsFrame(final TrickControlPanelFrame tcpf) {
    super("Visualization Settings");
    this.t = tcpf.getTrick();
    mode = "trick";
    vs = VisualizationSettingsFileHandler.load(t.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT);
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
    try {
      iiOn = new ImageIcon(ImageIO.read(iiStream));
    } catch (IOException e) {
      iiOn = new ImageIcon();
      System.err.println("Did not find graphics file " + currentImage);
      e.printStackTrace();
    }
    currentImage = "/" + GRAPHICS_DIRECTORY + "/off.png";
    iiStream = getClass().getResourceAsStream(currentImage);
    try {
      iiOff = new ImageIcon(ImageIO.read(iiStream));
    } catch (IOException e) {
      iiOff = new ImageIcon();
      System.err.println("Did not find graphics file " + currentImage);
      e.printStackTrace();
    }
    currentImage = "/" + GRAPHICS_DIRECTORY + "/down.png";
    iiStream = getClass().getResourceAsStream(currentImage);
    ImageIcon iiDown;
    try {
      iiDown = new ImageIcon(ImageIO.read(iiStream));
    } catch (IOException e) {
      iiDown = new ImageIcon();
      System.err.println("Did not find graphics file " + currentImage);
      e.printStackTrace();
    }
    currentImage = "/" + GRAPHICS_DIRECTORY + "/up.png";
    iiStream = getClass().getResourceAsStream(currentImage);
    ImageIcon iiUp;
    try {
      iiUp = new ImageIcon(ImageIO.read(iiStream));
    } catch (IOException e) {
      iiUp = new ImageIcon();
      System.err.println("Did not find graphics file " + currentImage);
      e.printStackTrace();
    }
  
    int buttonColumns = 3;
    int rows = Metric.values().length;
    
    JButton[][] buttons = new JButton[buttonColumns][rows];
    for (int i = 0; i < rows; i++) {
      buttons[0][i] = new JButton();
      buttons[0][i].setBounds(0, 50 * i, 50, 50);
      buttons[0][i].setText("H" + i);
      if (visualizationTupels.get(i).isActive()) {
        buttons[0][i].setIcon(iiOn);
      } else {
        buttons[0][i].setIcon(iiOff);
      }
      buttons[0][i].setToolTipText("Show/Hide metric on slot " + i);
      buttons[0][i].addActionListener(this::showButtonActionPerformed);
      buttons[1][i] = new JButton();
      buttons[1][i].setBounds(250, 50 * i, 50, 50);
      buttons[1][i].setText("D" + i);
      buttons[1][i].setIcon(iiDown);
      buttons[1][i].setToolTipText("Move metric from slot " + i + " down to " + (i+1));
      buttons[1][i].addActionListener(this::downButtonActionPerformed);
      buttons[2][i] = new JButton();
      buttons[2][i].setBounds(300, 50 * i, 50, 50);
      buttons[2][i].setText("U" + i);
      buttons[2][i].setIcon(iiUp);
      buttons[2][i].setToolTipText("Move metric from slot " + i + " up to " + (i-1));
      buttons[2][i].addActionListener(this::upButtonActionPerformed);
      cp.add(buttons[0][i]);
      cp.add(buttons[1][i]);
      cp.add(buttons[2][i]);
    }
    buttons[2][0].setVisible(false); // disables the "up" button of the metric in slot 1
    buttons[1][rows-1].setVisible(false); // disables the "down" button of the metric in the last slot.
  
    // ToDo: Optimize this a lot, add text presets
    JTextField[] textFields = new JTextField[rows];
    for (int i = 0; i < rows; i++) {
      textFields[i] = new JTextField();
      textFields[i].setBounds(50, 50 * i, 200, 50);
      cp.add(textFields[i]);
    }
    
    // ToDo: Spacing stuff
    
    // ToDo: Font stuff
    
    JButton saveButton = new JButton("Save");
    saveButton.setBounds(10, 50*rows + 10, 160, 50);
    saveButton.addActionListener(this::saveButtonActionPerformed);
    cp.add(saveButton);
    
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(180, 50*rows + 10, 160, 50);
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
    // ToDo
    JButton activeButton = (JButton)actionEvent.getSource();
    int i = Integer.parseInt(activeButton.getText().substring(1));
    VisualizationTupelListFunctions.updateShow(visualizationTupels, i);
    if (visualizationTupels.get(i).isActive()) {
      activeButton.setIcon(iiOn);
    } else {
      activeButton.setIcon(iiOff);
    }
    System.out.println(visualizationTupels.get(i).isActive());
    somethingChanged = true;
  }
  
  private void downButtonActionPerformed(ActionEvent actionEvent) {
    // ToDo
    int i = Integer.parseInt(((JButton)actionEvent.getSource()).getText().substring(1));
    System.out.println(i);
    somethingChanged = true;
  }
  
  private void upButtonActionPerformed(ActionEvent actionEvent) {
    // ToDo
    int i = Integer.parseInt(((JButton)actionEvent.getSource()).getText().substring(1));
    System.out.println(i);
    somethingChanged = true;
  }
  
  private void saveButtonActionPerformed(ActionEvent actionEvent) {
    if (mode.equals("default")) {
      VisualizationSettingsFileHandler.save(vs, VISUALIZATION_FILE_FULL_NAME);
      new MainMenuFrame();
    } else if (mode.equals("trick")) {
      VisualizationSettingsFileHandler.save(vs, t.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT);
      new TrickControlPanelFrame(this, t);
    }
    dispose();
  }
  
  private void cancelButtonActionPerformed(ActionEvent actionEvent) {
    if (mode.equals("default")) {
      new MainMenuFrame();
      dispose();
    } else if (mode.equals("trick")) {
      new TrickControlPanelFrame(this, t);
      dispose();
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
        dispose();
      } else if (mode.equals("trick")) {
        new TrickControlPanelFrame(this, t);
        dispose();
      }
    }
    // ToDo? Directly to Menu/ControlPanel?
    
  }
  
  public Trick getTrick() {
    return t;
  }
  
  public String getMode() {
    return mode;
  }
  
  public VisualizationSettings getVisualizationSettings() {
    return vs;
  }
}
