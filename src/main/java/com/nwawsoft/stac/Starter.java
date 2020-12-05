package com.nwawsoft.stac;

import com.nwawsoft.stac.ui.MainMenu;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Starter {
  public static void main (String[] args) {
    try {
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException e) {
      e.printStackTrace();
    }
    LogManager.getLogManager().reset();
    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    logger.setLevel(Level.OFF);

    new MainMenu();
  }
}
