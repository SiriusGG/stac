package com.nwawsoft.stac.model;

public class VisualizationTupel {
  private int index;
  private Metric metric;
  
  public VisualizationTupel(final int index, final Metric metric) {
    this.index = index;
    this.metric = metric;
  }
  
  public int getIndex() {
    return index;
  }
  
  public Metric getMetric() {
    return metric;
  }
  
  public void setIndex(final int index) {
    this.index = index;
  }
  
  public void setMetric(final Metric metric) {
    this.metric = metric;
  }
}
