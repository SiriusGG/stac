package com.nwawsoft.stac.model;

public class KeyMapping {
  private String keyFrom;
  private String keyTo;

  public KeyMapping(final String keyFrom, final String keyTo) {
    this.keyFrom = keyFrom;
    this.keyTo = keyTo;
  }

  public KeyMapping(final String mappingString) {
    keyFrom = mappingString.substring(0, mappingString.indexOf(","));
    keyTo = mappingString.substring(mappingString.indexOf(",") + 1);
  }

  public String getKeyFrom() {
    return keyFrom;
  }

  public void setKeyFrom(final String keyFrom) {
    this.keyFrom = keyFrom;
  }

  public String getKeyTo() {
    return keyTo;
  }

  public void setKeyTo(final String keyTo) {
    this.keyTo = keyTo;
  }

  public String getMappingString() {
    return keyFrom + "," + keyTo;
  }
}
