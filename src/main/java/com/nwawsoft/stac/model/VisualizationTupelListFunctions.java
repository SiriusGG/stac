package com.nwawsoft.stac.model;

import java.util.ArrayList;

public class VisualizationTupelListFunctions {
  
  public static void updateShow(final ArrayList<VisualizationTupel> visualizationTupels, int index) {
    visualizationTupels.get(visualizationTupels.get(index).getIndex()).setActive
        (!visualizationTupels.get(visualizationTupels.get(index).getIndex()).isActive());
  }
  
  public static void swapIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                               final int indexOne, final int indexTwo) {
    int swap = visualizationTupels.get(indexOne).getIndex();
    visualizationTupels.get(indexOne).setIndex(visualizationTupels.get(indexTwo).getIndex());
    visualizationTupels.get(indexTwo).setIndex(swap);
  }
  
  public static void printIndexes(final ArrayList<VisualizationTupel> visualizationTupels) {
    System.out.println("Printing indexes");
    for (VisualizationTupel vt : visualizationTupels) {
      System.out.println(vt.getIndex());
    }
  }
  
  public static void printNames(final ArrayList<VisualizationTupel> visualizationTupels) {
    System.out.println("Printing names");
    for (VisualizationTupel vt : visualizationTupels) {
      System.out.println(vt.getName());
    }
  }
  
  public static int getActiveMetricsAmount(final ArrayList<VisualizationTupel> visualizationTupels) {
    int c = 0;
    for (VisualizationTupel vt : visualizationTupels) {
      if (vt.isActive()) {
        c++;
      }
    }
    return c;
  }
}
