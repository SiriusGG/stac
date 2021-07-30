package com.nwawsoft.stac.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyHandler {
  private Robot robot;

  public KeyHandler() {
    init();
  }

  public static ArrayList<String> getKeys() {
    ArrayList<String> keys = new ArrayList<>();
    keys.add("A");
    keys.add("B");
    keys.add("C");
    keys.add("D");
    keys.add("E");
    keys.add("F");
    keys.add("G");
    keys.add("H");
    keys.add("I");
    keys.add("J");
    keys.add("K");
    keys.add("L");
    keys.add("M");
    keys.add("N");
    keys.add("O");
    keys.add("P");
    keys.add("Q");
    keys.add("R");
    keys.add("S");
    keys.add("T");
    keys.add("U");
    keys.add("V");
    keys.add("W");
    keys.add("X");
    keys.add("Y");
    keys.add("Z");
    keys.add("0");
    keys.add("1");
    keys.add("2");
    keys.add("3");
    keys.add("4");
    keys.add("5");
    keys.add("6");
    keys.add("7");
    keys.add("8");
    keys.add("9");
    keys.add("F1");
    keys.add("F2");
    keys.add("F3");
    keys.add("F4");
    keys.add("F5");
    keys.add("F6");
    keys.add("F7");
    keys.add("F8");
    keys.add("F9");
    keys.add("F10");
    keys.add("F11");
    keys.add("F12");
    return keys;
  }

  public static String getKeyStringFromNativeKeyCode(final int keyCode) {
    switch (keyCode) {
      case 30:
        return "A";
      case 48:
        return "B";
      case 46:
        return "C";
      case 32:
        return "D";
      case 18:
        return "E";
      case 33:
        return "F";
      case 34:
        return "G";
      case 35:
        return "H";
      case 23:
        return "I";
      case 36:
        return "J";
      case 37:
        return "K";
      case 38:
        return "L";
      case 50:
        return "M";
      case 49:
        return "N";
      case 24:
        return "O";
      case 25:
        return "P";
      case 16:
        return "Q";
      case 19:
        return "R";
      case 31:
        return "S";
      case 20:
        return "T";
      case 22:
        return "U";
      case 47:
        return "V";
      case 17:
        return "W";
      case 45:
        return "X";
      case 21:
        return "Y";
      case 44:
        return "Z";
      case 11:
        return "0";
      case 2:
        return "1";
      case 3:
        return "2";
      case 4:
        return "3";
      case 5:
        return "4";
      case 6:
        return "5";
      case 7:
        return "6";
      case 8:
        return "7";
      case 9:
        return "8";
      case 10:
        return "9";
      case 59:
        return "F1";
      case 60:
        return "F2";
      case 61:
        return "F3";
      case 62:
        return "F4";
      case 63:
        return "F5";
      case 64:
        return "F6";
      case 65:
        return "F7";
      case 66:
        return "F8";
      case 67:
        return "F9";
      case 68:
        return "F10";
      case 87:
        return "F11";
      case 88:
        return "F12";
      default:
        return "";
    }
  }

  public static int getKeyCodeFromKeyString(final String keyString) {
    switch (keyString) {
      case "A":
        return KeyEvent.VK_A;
      case "B":
        return KeyEvent.VK_B;
      case "C":
        return KeyEvent.VK_C;
      case "D":
        return KeyEvent.VK_D;
      case "E":
        return KeyEvent.VK_E;
      case "F":
        return KeyEvent.VK_F;
      case "G":
        return KeyEvent.VK_G;
      case "H":
        return KeyEvent.VK_H;
      case "I":
        return KeyEvent.VK_I;
      case "J":
        return KeyEvent.VK_J;
      case "K":
        return KeyEvent.VK_K;
      case "L":
        return KeyEvent.VK_L;
      case "M":
        return KeyEvent.VK_M;
      case "N":
        return KeyEvent.VK_N;
      case "O":
        return KeyEvent.VK_O;
      case "P":
        return KeyEvent.VK_P;
      case "Q":
        return KeyEvent.VK_Q;
      case "R":
        return KeyEvent.VK_R;
      case "S":
        return KeyEvent.VK_S;
      case "T":
        return KeyEvent.VK_T;
      case "U":
        return KeyEvent.VK_U;
      case "V":
        return KeyEvent.VK_V;
      case "W":
        return KeyEvent.VK_W;
      case "X":
        return KeyEvent.VK_X;
      case "Y":
        return KeyEvent.VK_Y;
      case "Z":
        return KeyEvent.VK_Z;
      case "0":
        return KeyEvent.VK_0;
      case "1":
        return KeyEvent.VK_1;
      case "2":
        return KeyEvent.VK_2;
      case "3":
        return KeyEvent.VK_3;
      case "4":
        return KeyEvent.VK_4;
      case "5":
        return KeyEvent.VK_5;
      case "6":
        return KeyEvent.VK_6;
      case "7":
        return KeyEvent.VK_7;
      case "8":
        return KeyEvent.VK_8;
      case "9":
        return KeyEvent.VK_9;
      case "F1":
        return KeyEvent.VK_F1;
      case "F2":
        return KeyEvent.VK_F2;
      case "F3":
        return KeyEvent.VK_F3;
      case "F4":
        return KeyEvent.VK_F4;
      case "F5":
        return KeyEvent.VK_F5;
      case "F6":
        return KeyEvent.VK_F6;
      case "F7":
        return KeyEvent.VK_F7;
      case "F8":
        return KeyEvent.VK_F8;
      case "F9":
        return KeyEvent.VK_F9;
      case "F10":
        return KeyEvent.VK_F10;
      case "F11":
        return KeyEvent.VK_F11;
      case "F12":
        return KeyEvent.VK_F12;
      default:
        return -1;
    }
  }

  private void init() {
    try {
      robot = new Robot();
    } catch (final AWTException e) {
      e.printStackTrace();
    }
  }

  /**
   * Simulates a key press.
   * Does not send the key if the keyEvent value is -1.
   *
   * @param keyEvent the key to be pressed.
   */
  public void sendKey(final int keyEvent) {
    if (keyEvent != -1) {
      robot.keyPress(keyEvent);
      robot.keyRelease(keyEvent);
    }
  }

  public void sendKey(final String key) {
    sendKey(getKeyCodeFromKeyString(key));
  }
}
