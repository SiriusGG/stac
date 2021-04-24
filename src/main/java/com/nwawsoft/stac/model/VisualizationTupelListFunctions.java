package com.nwawsoft.stac.model;

import java.util.ArrayList;

public class VisualizationTupelListFunctions {
  
  public static void updateShow(final ArrayList<VisualizationTupel> visualizationTupels, int index) {
    visualizationTupels.get(visualizationTupels.get(index).getIndex()).setActive
        (!visualizationTupels.get(visualizationTupels.get(index).getIndex()).isActive());
  }
  
  public static void swapIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                               final int indexOne, final int indexTwo) {
    // ToDo: THE ERROR LIES HERE!
    // ToDo: the wrong tupels are swapped. Search by original index?
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
  
  public static void printMetricsInListIndexOrder(final ArrayList<VisualizationTupel> visualizationTupels) {
    System.out.println("Printing metrics");
    for (VisualizationTupel vt : visualizationTupels) {
      System.out.println(vt.getMetric().toString());
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
  
  public static VisualizationTupel getTupelByMetric(final ArrayList<VisualizationTupel> visualizationTupels,
                                                    final Metric metric) {
    VisualizationTupel found = null;
    for (VisualizationTupel visualizationTupel : visualizationTupels) {
      if (visualizationTupel.getMetric().equals(metric)) {
        found = visualizationTupel;
      }
    }
    return found;
  }
  
  public static int getTupelIndexByInternalIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                                                    final int index) {
    for (int i = 0; i < visualizationTupels.size(); i++) {
      if (visualizationTupels.get(i).getIndex() == index) {
        return i;
      }
    }
    System.err.println("getTupelIndexByInternalIndex failed. Returning -1.");
    return -1;
  }
}
