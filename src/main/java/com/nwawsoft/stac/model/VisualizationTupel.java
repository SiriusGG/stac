package com.nwawsoft.stac.model;

public class VisualizationTupel {
  private int index;
  private String name;
  private Metric metric;
  private boolean active;
  
  public VisualizationTupel(final int index, final String name, final Metric metric, final boolean active) {
    this.index = index;
    this.name = name;
    this.metric = metric;
    this.active = active;
  }
  
  public int getIndex() {
    return index;
  }
  
  public String getName() {
    return name;
  }
  
  public Metric getMetric() {
    return metric;
  }
  
  public boolean isActive() {
    return active;
  }
  
  public void setIndex(final int index) {
    this.index = index;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setMetric(final Metric metric) {
    this.metric = metric;
  }
  
  public void setActive(final boolean active) {
    this.active = active;
  }
}
