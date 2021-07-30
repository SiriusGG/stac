package com.nwawsoft.stac.model;

import static com.nwawsoft.stac.BuildData.VERSION;

public class Trick {
  private String version;
  private String name;
  private String fileName;
  private int attempts;
  private int successes;
  private int successesBackToBack;
  private int successesHighscore;

  public Trick(final String version, final String name, final String fileName, final int attempts, final int successes,
               final int successesBackToBack, final int successesHighscore) {
    if (version != null) {
      this.version = version;
    } else {
      this.version = VERSION;
    }
    this.name = name;
    this.fileName = fileName;
    this.attempts = attempts;
    this.successes = successes;
    this.successesBackToBack = successesBackToBack;
    this.successesHighscore = successesHighscore;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(final String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(final String fileName) {
    this.fileName = fileName;
  }

  public int getAttempts() {
    return attempts;
  }

  public void setAttempts(final int attempts) {
    this.attempts = attempts;
  }

  public int getFails() {
    return attempts - successes;
  }

  public int getSuccesses() {
    return successes;
  }

  public void setSuccesses(final int successes) {
    this.successes = successes;
  }

  public int getSuccessesBackToBack() {
    return successesBackToBack;
  }

  public void setSuccessesBackToBack(final int successesBackToBack) {
    this.successesBackToBack = successesBackToBack;
  }

  public int getSuccessesHighscore() {
    return successesHighscore;
  }

  public void setSuccessesHighscore(final int successesHighscore) {
    this.successesHighscore = successesHighscore;
  }

  public double getSuccessPercentage() {
    return ((double) successes * 100) / attempts;
  }

  public Object getValueByMetricIndex(final int metricIndex) {
    switch (metricIndex) {
      case 0:
        return name;
      case 1:
        return attempts;
      case 2:
        return getFails();
      case 3:
        return successes;
      case 4:
        return successesBackToBack;
      case 5:
        return successesHighscore;
      case 6:
        return getSuccessPercentage();
    }
    return null;
  }

  public Object getValueByMetric(final Metric metric) {
    switch (metric) {
      case TRICK_NAME:
        return name;
      case ATTEMPTS:
        return attempts;
      case FAILS:
        return getFails();
      case SUCCESSES:
        return successes;
      case SUCCESSES_BACK_TO_BACK:
        return successesBackToBack;
      case SUCCESSES_HIGHSCORE:
        return successesHighscore;
      case SUCCESS_PERCENTAGE:
        return getSuccessPercentage();
    }
    return null;
  }

  /**
   * Add a failed attempt to the counter.
   */
  public void recordFail() {
    attempts++;
    successesBackToBack = 0;
  }

  /**
   * Add a successful attempt to the counter.
   */
  public void recordSuccess() {
    attempts++;
    successes++;
    successesBackToBack++;
    if (successesBackToBack > successesHighscore) {
      successesHighscore = successesBackToBack;
    }
  }

  @Override
  public boolean equals(final Object otherTrick) {
    if (otherTrick instanceof Trick) {
      if (this == otherTrick) {
        return true;
      }
      Trick trick = (Trick) otherTrick;
      return name.equals(trick.name) &&
          fileName.equals(trick.fileName) &&
          attempts == trick.attempts &&
          successes == trick.successes &&
          successesBackToBack == trick.successesBackToBack &&
          successesHighscore == trick.successesHighscore;
    } else {
      return false;
    }
  }
}
