package com.nwawsoft.stac.model;

public enum Metric {
  TRICK_NAME, ATTEMPTS, FAILS, SUCCESSES, SUCCESSES_BACK_TO_BACK, SUCCESSES_HIGHSCORE, SUCCESS_PERCENTAGE;
  
  public static Metric toMetric(final String metricString) {
    switch (metricString) {
      case "TRICK_NAME":
        return TRICK_NAME;
      case "ATTEMPTS":
        return ATTEMPTS;
      case "FAILS":
        return FAILS;
      case "SUCCESSES":
        return SUCCESSES;
      case "SUCCESSES_BACK_TO_BACK":
        return SUCCESSES_BACK_TO_BACK;
      case "SUCCESSES_HIGHSCORE":
        return SUCCESSES_HIGHSCORE;
      case "SUCCESS_PERCENTAGE":
        return SUCCESS_PERCENTAGE;
      default:
        return null;
    }
  }
}
