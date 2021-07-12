package com.nwawsoft.stac.model;

import com.nwawsoft.stac.ui.TrickVisualizationFrame;
import org.jnativehook.keyboard.*;

public class CounterKeyListenerSingleton implements NativeKeyListener {
  private String failedKey;
  private String successfulKey;
  private boolean remappingActive;
  private String simulatedKey;
  private TrickVisualizationFrame tvf = null;
  private boolean active = false;
  private KeyHandler kp;

  private static final CounterKeyListenerSingleton ckl = new CounterKeyListenerSingleton();

  private CounterKeyListenerSingleton() {
    init();
  }

  private void init() {
    String[] settings = KeyBindingsFileHandler.load();
    failedKey = settings[0];
    successfulKey = settings[1];
    remappingActive = Boolean.parseBoolean(settings[2]);
    simulatedKey = settings[3];
    kp = new KeyHandler();
  }

  public void setVisualization(final TrickVisualizationFrame tvf) {
    this.tvf = tvf;
  }

  @Override
  public void nativeKeyTyped(final NativeKeyEvent nativeEvent) {}

  @Override
  public void nativeKeyPressed(final NativeKeyEvent nativeEvent) {
    if (active) {
      if (!(tvf == null)) {
        String keyString = KeyHandler.getKeyStringFromNativeKeyCode(nativeEvent.getKeyCode());
        if (keyString.equals(failedKey)) {
          tvf.getControlPanel().getTrick().recordFail();
        } else if (keyString.equals(successfulKey)) {
          tvf.getControlPanel().getTrick().recordSuccess();
        }
        if (keyString.equals(failedKey) || keyString.equals(successfulKey)) {
          if (remappingActive) {
            kp.sendKey(simulatedKey);
          }
          tvf.updateStats();
        }
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
    init();
  }
}
