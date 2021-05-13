package com.nwawsoft.stac.model;

import java.util.ArrayList;

public class VisualizationTupelListFunctions {
  public static void updateShow(final ArrayList<VisualizationTupel> visualizationTupels, int index) {
    VisualizationTupel currentTupel = getByInternalIndex(visualizationTupels, index);
    if (currentTupel != null) {
      currentTupel.setActive(!currentTupel.isActive());
    } else {
      System.err.println("Error! The specified tupel is null!");
    }
  }

  public static void swapIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                               final int indexOne, final int indexTwo) {
    VisualizationTupel tupelOne = getByInternalIndex(visualizationTupels, indexOne);
    VisualizationTupel tupelTwo = getByInternalIndex(visualizationTupels, indexTwo);
    if (tupelOne != null && tupelTwo != null) {
      tupelOne.setIndex(indexTwo);
      tupelTwo.setIndex(indexOne);
    } else {
      System.err.println("Error! One or more tupels are null!");
    }
  }

  public static VisualizationTupel getByInternalIndex(final ArrayList<VisualizationTupel> visualizationTupels,
                                                      final int index) {
    for (VisualizationTupel visualizationTupel : visualizationTupels) {
      if (visualizationTupel.getIndex() == index) {
        return visualizationTupel;
      }
    }
    System.err.println("No tupel with internal index " + index + " found. Returning null.");
    return null;
  }

  public static ArrayList<VisualizationTupel> filterActive(final ArrayList<VisualizationTupel> visualizationTupels) {
    ArrayList<VisualizationTupel> activeVts = new ArrayList<>();
    for (VisualizationTupel vt : visualizationTupels) {
      if (vt.isActive()) {
        activeVts.add(vt);
      }
    }
    return activeVts;
  }

  public static ArrayList<VisualizationTupel> sortByIndex(final ArrayList<VisualizationTupel> visualizationTupels) {
    ArrayList<VisualizationTupel> sortedTupels = new ArrayList<>();
    ArrayList<VisualizationTupel> remainingTupels = new ArrayList<>(visualizationTupels);
    while (!remainingTupels.isEmpty()) {
      VisualizationTupel vt = findTupelWithLowestIndex(remainingTupels);
      sortedTupels.add(vt);
      remainingTupels.remove(vt);
    }
    return sortedTupels;
  }

  private static VisualizationTupel findTupelWithLowestIndex(final ArrayList<VisualizationTupel> visualizationTupels) {
    int lowest = Integer.MAX_VALUE;
    for (VisualizationTupel visualizationTupel : visualizationTupels) {
      if (visualizationTupel.getIndex() < lowest) {
        lowest = visualizationTupel.getIndex();
      }
    }
    for (VisualizationTupel vt : visualizationTupels) {
      if (vt.getIndex() == lowest) {
        return vt;
      }
    }
    return null;
  }

  public static void print(final ArrayList<VisualizationTupel> visualizationTupels) {
    for (VisualizationTupel vt : visualizationTupels) {
      System.out.println(vt.getIndex() + ";" + vt.getName() + ";" + vt.getMetric() + ";" + vt.isActive());
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
