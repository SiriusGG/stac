package com.nwawsoft.stac.controller;

import com.nwawsoft.stac.model.*;
import com.nwawsoft.stac.ui.VisualizationSettingsFrame;
import com.nwawsoft.util.ui.*;
import say.swing.JFontChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;
import static com.nwawsoft.stac.model.VisualizationTupelListFunctions.*;

public class VisualizationSettingsController implements STACFrameController {
  private final static int FRAME_WIDTH = 365;
  private final static int FRAME_HEIGHT = 475;
  private final TrickControlPanelController tcpc;
  private final Trick trick;
  int metricsAmount = Metric.values().length;
  private VisualizationSettingsFrame vsf;
  private VisualizationSettings vs;
  private ArrayList<VisualizationTupel> visualizationTupels;
  private Font font;
  private ImageIcon iiOn;
  private ImageIcon iiOff;
  private ImageIcon iiDown;
  private ImageIcon iiUp;
  private JTextField[] textFields;
  private String mode;
  private boolean somethingChanged = false;

  public VisualizationSettingsController() {
    tcpc = null;
    trick = null;
  }

  public VisualizationSettingsController(final TrickControlPanelController tcpc) {
    this.tcpc = tcpc;
    trick = tcpc.getTrick();
  }

  public void frameInitDefault() {
    mode = "default";
    vs = VisualizationSettingsFileHandler.load(VISUALIZATION_FILE_FULL_NAME);
  }

  public void frameInitTrick() {
    mode = "trick";
    vs = VisualizationSettingsFileHandler.load(trick.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT);
  }

  @Override
  public JFrame getFrame() {
    return vsf;
  }

  @Override
  public void centerFrame() {
    ComponentFunctions.center(vsf);
  }

  @Override
  public void createFrame() {
    if (tcpc == null) {
      vsf = new VisualizationSettingsFrame(this);
    } else {
      vsf = new VisualizationSettingsFrame(this, tcpc);
    }
    setFrameSize();
    updateAllHideShowGraphics();
    createTextFields();
    updateNames();
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
      vsf.getButtons()[0][i].setIcon(iiOn);
    } else {
      vsf.getButtons()[0][i].setIcon(iiOff);
    }
  }

  public void saveNames() {
    for (VisualizationTupel tupel : visualizationTupels) {
      tupel.setName(textFields[tupel.getIndex()].getText());
    }
  }

  public void saveSpacing() {
    vs.setSpacing(Integer.parseInt(vsf.getSpacingField().getText()));
  }

  public void saveFont() {
    vs.setFont(font);
  }

  public void updateNames() {
    for (VisualizationTupel tupel : visualizationTupels) {
      textFields[tupel.getIndex()].setText(tupel.getName());
    }
  }

  public void doClose() {
    if (somethingChanged) {
      if (mode.equals("default")) {
        SaveWarningController swc = new SaveWarningController(this, "visualization settings", "close");
        swc.createDialog();
      } else if (mode.equals("trick")) {
        SaveWarningController swc = new SaveWarningController(this, "trick visualization settings", "close");
        swc.createDialog();
      }
    } else {
      if (mode.equals("default")) {
        MainMenuController mmc = new MainMenuController();
        mmc.createFrame();
        mmc.centerFrame();
      } else if (mode.equals("trick")) {
        TrickControlPanelController tcpc = new TrickControlPanelController(trick);
        tcpc.createFrame();
        tcpc.centerFrame();
        tcpc.handleOnClose();
        tcpc.createVisualization();
        tcpc.addNativeHook();
      }
      vsf.dispose();
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

  public void doCancel() {
    if (mode.equals("default")) {
      MainMenuController mmc = new MainMenuController();
      mmc.createFrame();
      mmc.centerFrame();
    } else if (mode.equals("trick")) {
      TrickControlPanelController tcpc = new TrickControlPanelController(trick);
      tcpc.createFrame();
      tcpc.centerFrame();
      tcpc.handleOnClose();
      tcpc.createVisualization();
      tcpc.addNativeHook();
    }
    vsf.dispose();
  }

  public void doSave() {
    saveNames();
    saveSpacing();
    saveFont();
    if (mode.equals("default")) {
      VisualizationSettingsFileHandler.save(vs, VISUALIZATION_FILE_FULL_NAME);
      MainMenuController mmc = new MainMenuController();
      mmc.createFrame();
      mmc.centerFrame();
    } else if (mode.equals("trick")) {
      VisualizationSettingsFileHandler.save(vs, trick.getFileName() + "." + TRICK_VISUALIZATION_FILE_FORMAT);
      TrickControlPanelController tcpc = new TrickControlPanelController(trick);
      tcpc.createFrame();
      tcpc.centerFrame();
      tcpc.handleOnClose();
      tcpc.createVisualization();
      tcpc.addNativeHook();
    }
    vsf.dispose();
  }

  public void handleFont() {
    JFontChooser fontChooser = new JFontChooser();
    fontChooser.setSelectedFont(font);
    int result = fontChooser.showDialog(vsf);
    if (result == JFontChooser.OK_OPTION) {
      font = fontChooser.getSelectedFont();
      vsf.getFontButton().setText(FontFunctions.toFontString(font));
    }
  }

  public void doShow(ActionEvent actionEvent) {
    JButton activeButton = (JButton) actionEvent.getSource();
    int i = Integer.parseInt(activeButton.getText().substring(1));
    VisualizationTupelListFunctions.updateShow(visualizationTupels, i);
    updateHideShowGraphic(i);
    somethingChanged = true;
  }

  public void doDown(ActionEvent actionEvent) {
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

  public void doUp(ActionEvent actionEvent) {
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

  public WindowListener handleClosing() {
    return new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        doClose();
      }
    };
  }

  public void loadGraphics() {
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
  }

  public void setFrameSize() {
    vsf.setSize(FRAME_WIDTH, FRAME_HEIGHT);
  }

  public void setFont() {
    font = vs.getFont();
  }

  public void setVisualizationTupels() {
    visualizationTupels = vs.getVisualizationTupels();
  }

  public int getMetricsAmount() {
    return metricsAmount;
  }

  public ImageIcon getIiDown() {
    return iiDown;
  }

  public ImageIcon getIiUp() {
    return iiUp;
  }

  public Font loadFont() {
    return font;
  }

  public void createTextFields() {
    textFields = new JTextField[metricsAmount];
    for (int i = 0; i < metricsAmount; i++) {
      textFields[i] = new JTextField();
      textFields[i].setBounds(50, vsf.getSquareButtonSize() * i, 200, vsf.getSquareButtonSize());
      Metric[] metricsInOrder = getMetricsInInternalIndexOrder(visualizationTupels);
      textFields[i].setToolTipText("Name for metric " + metricsInOrder[i].toString());
      vsf.getContentPane().add(textFields[i]);
    }
  }

  public String getFontButtonString() {
    return FontFunctions.toFontString(loadFont());
  }
}
