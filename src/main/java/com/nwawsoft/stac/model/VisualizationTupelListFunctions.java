package com.nwawsoft.stac.model;

import java.util.ArrayList;

public class VisualizationTupelListFunctions {
  
  public static void updateShow(final ArrayList<VisualizationTupel> visualizationTupels, int index) {
    visualizationTupels.get(index).setActive(!visualizationTupels.get(index).isActive());
  }
}
