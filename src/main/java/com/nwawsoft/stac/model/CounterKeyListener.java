package com.nwawsoft.stac.model;

import com.nwawsoft.stac.ui.Visualization;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class CounterKeyListener implements NativeKeyListener {
  private final String failedKey;
  private final String successfulKey;
  private final Trick t;
  private final Visualization v;

  public CounterKeyListener(Visualization v, final Trick t) {
    this.v = v;
    this.t = t;
    String[] settings = SettingsHandler.load();
    failedKey = settings[0];
    successfulKey = settings[1];
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent nativeEvent) {}

  @Override
  public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
    if (AvailableButtons.getKeyFromKeyCode(nativeEvent.getKeyCode()).equals(failedKey)) {
      t.recordFail();
      v.updateStats();
    } else if (AvailableButtons.getKeyFromKeyCode(nativeEvent.getKeyCode()).equals(successfulKey)) {
      t.recordSuccess();
      v.updateStats();
    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent nativeEvent) {}
}
