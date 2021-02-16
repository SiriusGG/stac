package com.nwawsoft.stac.model;

import java.awt.*;
import java.util.ArrayList;

public class VisualizationSettings {
  private final ArrayList<VisualizationTupel> visualizationTupels;
  private final int spacing;
  private final Font font;
  
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
}
