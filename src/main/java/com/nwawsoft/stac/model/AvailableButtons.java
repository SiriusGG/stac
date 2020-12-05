package com.nwawsoft.stac.model;

import java.util.ArrayList;

public class AvailableButtons {
  private final ArrayList<String> buttons = new ArrayList<>();

  public AvailableButtons() {
    buttons.add("A");
    buttons.add("B");
    buttons.add("C");
    buttons.add("D");
    buttons.add("E");
    buttons.add("F");
    buttons.add("G");
    buttons.add("H");
    buttons.add("I");
    buttons.add("J");
    buttons.add("K");
    buttons.add("L");
    buttons.add("M");
    buttons.add("N");
    buttons.add("O");
    buttons.add("P");
    buttons.add("Q");
    buttons.add("R");
    buttons.add("S");
    buttons.add("T");
    buttons.add("U");
    buttons.add("V");
    buttons.add("W");
    buttons.add("X");
    buttons.add("Y");
    buttons.add("Z");
    buttons.add("0");
    buttons.add("1");
    buttons.add("2");
    buttons.add("3");
    buttons.add("4");
    buttons.add("5");
    buttons.add("6");
    buttons.add("7");
    buttons.add("8");
    buttons.add("9");
    buttons.add("F1");
    buttons.add("F2");
    buttons.add("F3");
    buttons.add("F4");
    buttons.add("F5");
    buttons.add("F6");
    buttons.add("F7");
    buttons.add("F8");
    buttons.add("F9");
    buttons.add("F10");
    buttons.add("F11");
    buttons.add("F12");
  }

  public ArrayList<String> getButtons() {
    return buttons;
  }

  public static String getKeyFromKeyCode(final int keyCode) {
    String s = "";
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
