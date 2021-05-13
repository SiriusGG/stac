package com.nwawsoft.stac.model;

import java.awt.*;
import java.util.ArrayList;

public class VisualizationSettings {
  private final ArrayList<VisualizationTupel> visualizationTupels;
  private int spacing;
  private Font font;
  
  public VisualizationSettings(final ArrayList<VisualizationTupel> visualizationTupels, final int spacing,
                               final Font font) {
    this.visualizationTupels = visualizationTupels;
    this.spacing = spacing;
    this.font = font;
  }
  
  public ArrayList<VisualizationTupel> getVisualizationTupels() {
    return visualizationTupels;
  }
  
  public int getSpacing() {
    return spacing;
  }
  
  public Font getFont() {
    return font;
  }

  public void setSpacing(final int spacing) {
    this.spacing = spacing;
  }

  public void setFont(final Font font) {
    this.font = font;
  }
}
