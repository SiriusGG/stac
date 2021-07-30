package com.nwawsoft.stac;

import com.nwawsoft.stac.controller.MainMenuController;
import com.nwawsoft.stac.model.BackwardsCompatibility;
import org.jnativehook.*;

import javax.swing.*;
import java.util.logging.*;

public class Starter {
  public static void main(String[] args) {
    registerNativeHookAndSuppressLogging();
    setToolTipDuration();
    ensureBackwardsCompatibility();
    MainMenuController mmc = new MainMenuController();
    mmc.createFrame();
    mmc.centerFrame();
  }

  private static void registerNativeHookAndSuppressLogging() {
    try {
      LogManager.getLogManager().reset();
      Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
      logger.setLevel(Level.OFF);
      GlobalScreen.registerNativeHook();
    } catch (NativeHookException e) {
      e.printStackTrace();
    }
  }

  private static void setToolTipDuration() {
    ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
  }

  private static void ensureBackwardsCompatibility() {
    BackwardsCompatibility.convertTrickFiles_1_3_0();
    BackwardsCompatibility.convertKeyBindingsFile_1_3_0();
    BackwardsCompatibility.addKeyRemapping_2_1_0();
  }
}
