package com.nwawsoft.stac.model;

import com.nwawsoft.stac.BuildData;

import java.util.Objects;

public class Trick {
  private String version;
  private String name;
  private final String fileName;
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

  @Override
  public boolean equals(final Object otherTrick) {
    if (this == otherTrick) {
      return true;
    }
    if (otherTrick == null || getClass() != otherTrick.getClass()) {
      return false;
    }
    Trick trick = (Trick) otherTrick;
    return version.equals(trick.version) &&
        name.equals(trick.name) &&
        fileName.equals(trick.fileName) &&
        attempts == trick.attempts &&
        successes == trick.successes &&
        successesBackToBack == trick.successesBackToBack &&
        successesHighscore == trick.successesHighscore;
  }
}
