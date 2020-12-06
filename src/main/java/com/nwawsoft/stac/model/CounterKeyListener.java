package com.nwawsoft.stac.model;

import com.nwawsoft.stac.ui.TrickVisualizationFrame;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class CounterKeyListener implements NativeKeyListener {
  private final String failedKey;
  private final String successfulKey;
  private final Trick t;
  private final TrickVisualizationFrame tvf;

  public CounterKeyListener(final TrickVisualizationFrame tvf, final Trick t) {
    this.tvf = tvf;
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
      tvf.updateStats();
    } else if (AvailableButtons.getKeyFromKeyCode(nativeEvent.getKeyCode()).equals(successfulKey)) {
      t.recordSuccess();
      tvf.updateStats();
    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent nativeEvent) {}
}
