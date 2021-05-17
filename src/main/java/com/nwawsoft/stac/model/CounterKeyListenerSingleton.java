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
  public void nativeKeyTyped(final NativeKeyEvent nativeEvent) {}

  @Override
  public void nativeKeyPressed(final NativeKeyEvent nativeEvent) {
    if (!(tvf == null)) {
      String keyString = AvailableKeys.getKeyStringFromKeyCode(nativeEvent.getKeyCode());
      if (keyString.equals(failedKey)) {
        tvf.getControlPanel().getTrick().recordFail();
        tvf.updateStats();
      } else if (keyString.equals(successfulKey)) {
        tvf.getControlPanel().getTrick().recordSuccess();
        tvf.updateStats();
      }
    }
  }

  @Override
  public void nativeKeyReleased(final NativeKeyEvent nativeEvent) {}

  public static CounterKeyListenerSingleton getCounterKeyListener() {
    return ckl;
  }

  public void setActive(final boolean active) {
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
