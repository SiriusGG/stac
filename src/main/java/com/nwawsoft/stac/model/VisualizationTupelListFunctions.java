package com.nwawsoft.stac.model;

import java.util.ArrayList;
import java.util.Optional;

public class VisualizationTupelListFunctions {
  
  public static void updateShow(final ArrayList<VisualizationTupel> visualizationTupels, int index) {
    VisualizationTupel tupleToUpdate = getByInternalIndex(visualizationTupels, index);
    if (tupleToUpdate != null) {
      tupleToUpdate.setActive(!tupleToUpdate.isActive());
    } else {
      //TODO: error handling here
    }
  }
  
  public static void swapIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                               final int indexOne, final int indexTwo) {
    // ToDo: THE ERROR LIES HERE!
    // ToDo: the wrong tupels are swapped. Search by original index?
    VisualizationTupel tupleOne = getByInternalIndex(visualizationTupels, indexOne);
    VisualizationTupel tupleTwo = getByInternalIndex(visualizationTupels, indexTwo);
    if (tupleOne != null && tupleTwo != null) {
      tupleOne.setIndex(indexTwo);
      tupleTwo.setIndex(indexOne);
    } else {
      //TODO: error handling here
    }
  }

  public static VisualizationTupel getByInternalIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                                                      final int index) {
    return internalGetByInternalIndex(visualizationTupels, index).orElse(null);
  }

  public static Optional<VisualizationTupel> internalGetByInternalIndex(
          final ArrayList<VisualizationTupel> visualizationTupels,
          final int index) {
    return visualizationTupels
            .stream()
            .filter(tuple -> tuple.getIndex() == index)
            .findAny();
  }

  public static int getListIndexByInternalIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                                                final int index) {
    return internalGetListIndexByInternalIndex(visualizationTupels, index).orElse(-1);
  }

  public static Optional<Integer> internalGetListIndexByInternalIndex(
          final ArrayList<VisualizationTupel> visualizationTupels,
          final int index) {
    return internalGetByInternalIndex(visualizationTupels, index)
            .map(visualizationTupels::indexOf)
            .filter(i -> i != -1);
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
  
  public static Metric[] getMetricsInInternalIndexOrder(final ArrayList<VisualizationTupel> visualizationTupels) {
    int amount = visualizationTupels.size();
    Metric[] metrics = new Metric[amount];
    for (int i = 0; i < amount; i++) {
      metrics[i] = Metric.getMetric(getTupelIndexByInternalIndex(visualizationTupels, i));
    }
    return metrics;
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
