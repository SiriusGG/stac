package com.nwawsoft.stac.model;

import com.nwawsoft.stac.BuildData;

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
      this.version = BuildData.VERSION;
    }
    this.name = name;
    this.fileName = fileName;
    this.attempts = attempts;
    this.successes = successes;
    this.successesBackToBack = successesBackToBack;
    this.successesHighscore = successesHighscore;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setAttempts(final int attempts) {
    this.attempts = attempts;
  }

  public void setSuccesses(final int successes) {
    this.successes = successes;
  }

  public void setSuccessesBackToBack(final int successesBackToBack) {
    this.successesBackToBack = successesBackToBack;
  }

  public void setSuccessesHighscore(final int successesHighscore) {
    this.successesHighscore = successesHighscore;
  }

  public String getVersion() {
    return version;
  }

  public String getName() {
    return name;
  }

  public String getFileName() {
    return fileName;
  }

  public int getAttempts() {
    return attempts;
  }

  public int getSuccesses() {
    return successes;
  }

  public int getSuccessesBackToBack() {
    return successesBackToBack;
  }

  public int getSuccessesHighscore() {
    return successesHighscore;
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
}
