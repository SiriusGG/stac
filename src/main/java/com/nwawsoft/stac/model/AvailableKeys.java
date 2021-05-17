package com.nwawsoft.stac.model;

import java.util.ArrayList;

public class AvailableKeys {
  private final ArrayList<String> keys = new ArrayList<>();

  public AvailableKeys() {
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
  }

  public ArrayList<String> getKeys() {
    return keys;
  }

  public static String getKeyStringFromKeyCode(final int keyCode) {
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
      default: return "";
    }
  }
}
