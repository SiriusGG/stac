package com.nwawsoft.stac;

import com.nwawsoft.stac.model.BackwardsCompatibility;
import com.nwawsoft.stac.ui.MainMenuFrame;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Starter {
  public static void main (String[] args) {
    try {
      LogManager.getLogManager().reset();
      Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
      logger.setLevel(Level.OFF);
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException e) {
      e.printStackTrace();
    }
    
    // START OF BACKWARDS COMPATIBILITY ROUTINE
    
    BackwardsCompatibility.convertTrickFiles(); // Introduced in v1.3.0 / 2.0.0
    BackwardsCompatibility.convertKeyBindingsFile(); // Introduced in v1.3.0 / 2.0.0
    
    // END OF BACKWARDS COMPATIBILITY ROUTINE
    
    new MainMenuFrame();
  }
}
