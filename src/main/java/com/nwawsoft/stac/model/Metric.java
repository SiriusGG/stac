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
  
  public static void printMetrics(final Metric[] metrics) {
    for (Metric metric : metrics) {
      System.out.println(metric.toString());
    }
  }
  
  public static Metric getMetric(final int metricIndex) {
    switch (metricIndex) {
      case 0:
        return TRICK_NAME;
      case 1:
        return ATTEMPTS;
      case 2:
        return FAILS;
      case 3:
        return SUCCESSES;
      case 4:
        return SUCCESSES_BACK_TO_BACK;
      case 5:
        return SUCCESSES_HIGHSCORE;
      case 6:
        return SUCCESS_PERCENTAGE;
      default:
        return null;
    }
  }

  public static int getMetricIndex(final Metric metric) {
    switch (metric) {
      case TRICK_NAME:
        return 0;
      case ATTEMPTS:
        return 1;
      case FAILS:
        return 2;
      case SUCCESSES:
        return 3;
      case SUCCESSES_BACK_TO_BACK:
        return 4;
      case SUCCESSES_HIGHSCORE:
        return 5;
      case SUCCESS_PERCENTAGE:
        return 6;
      default:
        return -1;
    }
  }
}
