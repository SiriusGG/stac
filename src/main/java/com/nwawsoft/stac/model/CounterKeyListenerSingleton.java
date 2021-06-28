package com.nwawsoft.stac.model;

import com.nwawsoft.stac.ui.TrickVisualizationFrame;
import org.jnativehook.keyboard.*;

public class CounterKeyListenerSingleton implements NativeKeyListener {
  private String failedKey;
  private String successfulKey;
  private boolean multiMappingActive;
  private KeyMapping km;
  private TrickVisualizationFrame tvf = null;
  private boolean active = false;

  private static final CounterKeyListenerSingleton ckl = new CounterKeyListenerSingleton();

  private CounterKeyListenerSingleton() {
    init();
  }

  private void init() {
    String[] settings = KeyBindingsFileHandler.load();
    failedKey = settings[0];
    successfulKey = settings[1];
    multiMappingActive = Boolean.parseBoolean(settings[2]);
    km = new KeyMapping(settings[3]);
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
        if (!multiMappingActive) { // case no multi mapping ("old mode")
          String keyString = AvailableKeys.getKeyStringFromKeyCode(nativeEvent.getKeyCode());
          if (keyString.equals(failedKey)) {
            tvf.getControlPanel().getTrick().recordFail();
            tvf.updateStats();
          } else if (keyString.equals(successfulKey)) {
            tvf.getControlPanel().getTrick().recordSuccess();
            tvf.updateStats();
          }
        } else { // case multi mapping active
          // ToDo
          // also remember this needs to record fails AND successes, not ONLY send the keys!
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
