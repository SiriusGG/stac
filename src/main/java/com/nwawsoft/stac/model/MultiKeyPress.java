package com.nwawsoft.stac.model;

import java.awt.*;

public class MultiKeyPress {
  private Robot robot;

  public MultiKeyPress() {
    init();
  }

  private void init() {
    try {
      robot = new Robot();
    } catch (final AWTException e) {
      e.printStackTrace();
    }
  }

  /**
   * Simulates up to two key presses at a time.
   * The first key specified will be pressed first and released first.
   * Does not send the key if the keyEvent value is -1.
   *
   * @param keyEvent1 the first key to be pressed.
   * @param keyEvent2 the second key to be pressed.
   */
  public void sendKeys(final int keyEvent1, final int keyEvent2) {
    boolean key1Active = false;
    boolean key2Active = false;
    if (keyEvent1 != -1) {
      robot.keyPress(keyEvent1);
      key1Active = true;
    }
    if (keyEvent2 != -1) {
      robot.keyPress(keyEvent2);
      key2Active = true;
    }
    if (key1Active) {
      robot.keyRelease(keyEvent1);
    }
    if (key2Active) {
      robot.keyRelease(keyEvent2);
    }
  }
}
