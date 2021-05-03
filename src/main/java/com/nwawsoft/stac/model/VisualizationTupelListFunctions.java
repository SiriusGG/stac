package com.nwawsoft.stac.model;

import java.util.ArrayList;
import java.util.Optional;

public class VisualizationTupelListFunctions {
  
  public static void updateShow(final ArrayList<VisualizationTupel> visualizationTupels, int index) {
    getByInternalIndex(visualizationTupels, index).ifPresent(tuple-> tuple.setActive(!tuple.isActive()));
  }
  
  public static void swapIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                               final int indexOne, final int indexTwo) {
    // ToDo: THE ERROR LIES HERE!
    // ToDo: the wrong tupels are swapped. Search by original index?
    getListIndexByInternalIndex(visualizationTupels, indexOne)
            .ifPresent(listIdxOne ->
              getListIndexByInternalIndex(visualizationTupels, indexTwo)
                      .ifPresent(listIdxTwo -> {
                        visualizationTupels.get(listIdxOne).setIndex(indexTwo);
                        visualizationTupels.get(listIdxTwo).setIndex(indexOne);
                      })
            );
    /*int swap = visualizationTupels.get(indexOne).getIndex();
    visualizationTupels.get(indexOne).setIndex(visualizationTupels.get(indexTwo).getIndex());
    visualizationTupels.get(indexTwo).setIndex(swap);*/
  }

  public static Optional<VisualizationTupel> getByInternalIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                                                                final int index)
  {
    return visualizationTupels
            .stream()
            .filter(tuple -> tuple.getIndex() == index)
            .findAny();
  }
  public static Optional<Integer> getListIndexByInternalIndex(
          final ArrayList<VisualizationTupel> visualizationTupels,
          final int index)
  {
    return getByInternalIndex(visualizationTupels, index)
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
