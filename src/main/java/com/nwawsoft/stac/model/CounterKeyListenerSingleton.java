package com.nwawsoft.stac.model;

import com.nwawsoft.stac.ui.TrickVisualizationFrame;
import org.jnativehook.keyboard.*;

public class CounterKeyListenerSingleton implements NativeKeyListener {
  private String failedKey;
  private String successfulKey;
  private TrickVisualizationFrame tvf = null;
  private boolean active = false;

  private static final CounterKeyListenerSingleton ckl = new CounterKeyListenerSingleton();

  private CounterKeyListenerSingleton() {
    String[] settings = KeyBindingsFileHandler.load();
    failedKey = settings[0];
    successfulKey = settings[1];
  }

  public void setVisualization(final TrickVisualizationFrame tvf) {
    this.tvf = tvf;
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent nativeEvent) {}

  @Override
  public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
    if (!(tvf == null)) {
      if (AvailableKeys.getKeyFromKeyCode(nativeEvent.getKeyCode()).equals(failedKey)) {
        tvf.getControlPanel().getTrick().recordFail();
        tvf.updateStats();
      } else if (AvailableKeys.getKeyFromKeyCode(nativeEvent.getKeyCode()).equals(successfulKey)) {
        tvf.getControlPanel().getTrick().recordSuccess();
        tvf.updateStats();
      }
    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent nativeEvent) {}

  public static CounterKeyListenerSingleton getCounterKeyListener() {
    return ckl;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isActive() {
    return active;
  }

  public void reset() {
    String[] settings = KeyBindingsFileHandler.load();
    failedKey = settings[0];
    successfulKey = settings[1];
  }
}
