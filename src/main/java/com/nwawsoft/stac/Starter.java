package com.nwawsoft.stac;

import com.nwawsoft.stac.model.BackwardsCompatibility;
import com.nwawsoft.stac.ui.MainMenuFrame;
import org.jnativehook.*;

import java.util.logging.*;

public class Starter {
  public static void main (String[] args) {
    suppressLogging();
    ensureBackwardsCompatibility();
    new MainMenuFrame();
  }

  private static void suppressLogging() {
    try {
      LogManager.getLogManager().reset();
      Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
      logger.setLevel(Level.OFF);
      GlobalScreen.registerNativeHook();
    }
    catch (NativeHookException e) {
      e.printStackTrace();
    }
  }

  private static void ensureBackwardsCompatibility() {
    BackwardsCompatibility.convertTrickFiles_1_3_0();
    BackwardsCompatibility.convertKeyBindingsFile_1_3_0();
    BackwardsCompatibility.addKeyRemapping_2_1_0();
  }
}
