package com.nwawsoft.stac.model;

import com.nwawsoft.stac.controller.TrickVisualizationController;
import org.jnativehook.keyboard.*;

public class CounterKeyListenerSingleton implements NativeKeyListener {
  private static final CounterKeyListenerSingleton ckl = new CounterKeyListenerSingleton();
  private String failedKey;
  private String successfulKey;
  private boolean remappingActive;
  private String simulatedKey;
  private TrickVisualizationController tvc;
  private boolean active = false;
  private KeyHandler kp;

  private CounterKeyListenerSingleton() {
    init();
  }

  public static CounterKeyListenerSingleton getCounterKeyListener() {
    return ckl;
  }

  private void init() {
    String[] settings = KeyBindingsFileHandler.load();
    failedKey = settings[0];
    successfulKey = settings[1];
    remappingActive = Boolean.parseBoolean(settings[2]);
    simulatedKey = settings[3];
    kp = new KeyHandler();
  }

  public void setVisualization(final TrickVisualizationController tvc) {
    this.tvc = tvc;
  }

  @Override
  public void nativeKeyTyped(final NativeKeyEvent nativeEvent) {
  }

  @Override
  public void nativeKeyPressed(final NativeKeyEvent nativeEvent) {
    if (active) {
      if (!(tvc == null)) {
        String keyString = KeyHandler.getKeyStringFromNativeKeyCode(nativeEvent.getKeyCode());
        if (keyString.equals(failedKey)) {
          tvc.getControlPanelController().getTrick().recordFail();
        } else if (keyString.equals(successfulKey)) {
          tvc.getControlPanelController().getTrick().recordSuccess();
        }
        if (keyString.equals(failedKey) || keyString.equals(successfulKey)) {
          if (remappingActive) {
            kp.sendKey(simulatedKey);
          }
          tvc.updateStats();
        }
      }
    }
  }

  @Override
  public void nativeKeyReleased(final NativeKeyEvent nativeEvent) {
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(final boolean active) {
    this.active = active;
  }

  public void reset() {
    init();
  }
}
