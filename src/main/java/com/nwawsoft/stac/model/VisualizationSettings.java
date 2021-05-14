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

  public static VisualizationSettings createDefaults() {
    return new VisualizationSettings(getDefaultVisualizationTupels(), getDefaultSpacing(), getDefaultFont());
  }

  private static ArrayList<VisualizationTupel> getDefaultVisualizationTupels() {
    ArrayList<VisualizationTupel> visualizationTupels = new ArrayList<>();
    visualizationTupels.add(new VisualizationTupel(0, "Trick name: ", Metric.TRICK_NAME, true));
    visualizationTupels.add(new VisualizationTupel(1, "Attempts: ", Metric.ATTEMPTS, true));
    visualizationTupels.add(new VisualizationTupel(2, "Fails: ", Metric.FAILS, false));
    visualizationTupels.add(new VisualizationTupel(3, "Successes: ", Metric.SUCCESSES, true));
    visualizationTupels.add(new VisualizationTupel(4, "Successes in a row: ", Metric.SUCCESSES_BACK_TO_BACK, true));
    visualizationTupels.add(new VisualizationTupel(5, "Max successes in a row: ", Metric.SUCCESSES_HIGHSCORE, true));
    visualizationTupels.add(new VisualizationTupel(6, "Success rate: ", Metric.SUCCESS_PERCENTAGE, true));
    return visualizationTupels;
  }

  private static int getDefaultSpacing() {
    return 10;
  }

  private static Font getDefaultFont() {
    return new Font("Dialog", Font.PLAIN, 12);
  }
}
