package com.nwawsoft.stac.model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;

public class VisualizationSettingsFileHandler {
  // ToDo
  
  /**
   * Saves the visualization settings to a text file.
   * The file extension will be set accordingly.
   * ".stacv" for the default visualization file and ".stactv" for trick visualization files.
   *
   * @param mode either "default" or "trick".
   * @param vs any VisualizationSettings object.
   * @param fileName the name of the visualization file to be saved.
   */
  public static void save(final String mode, final VisualizationSettings vs, final String fileName) {
    // ToDo
    System.out.println(fileName);
  }
  
  public static VisualizationSettings load(final String fileName) {
    int spacing = 0;
    Font font = null;
    String tupelsString = "";
    ArrayList<VisualizationTupel> visualizationTupels;
    try {
      File f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + fileName);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        if (currentLine.startsWith("SPACING")) {
          spacing = Integer.parseInt(currentLine.substring(currentLine.lastIndexOf("=") + 1));
        } else if (currentLine.startsWith("FONT")) {
          font = parseFont(currentLine.substring(currentLine.lastIndexOf("=") + 1));
        } else if (currentLine.contains("TUPELS_START")) {
          tupelsString = tupelsString + currentLine + "\n";
          while(!(currentLine = br.readLine()).contains("TUPELS_END")) {
            tupelsString = tupelsString + currentLine + "\n";
          }
        }
      }
      br.close();
      tupelsString = tupelsString.substring(tupelsString.indexOf("\n"));
      tupelsString = tupelsString.trim();
      visualizationTupels = parseTupels(tupelsString);
    } catch (IOException e) {
      System.err.println("Something went whoopsie at:\nVisualizationSettings.load(" + fileName + ")");
      e.printStackTrace();
      System.err.println("Continuing with new VisualizationSettings(null, 0, null)");
      return new VisualizationSettings(null, 0, null);
    }
    return new VisualizationSettings(visualizationTupels, spacing, font);
  }
  
  public static Font parseFont(final String fontString) {
    String name;
    int style;
    int size;
    name = fontString.substring(0, fontString.indexOf(","));
    style = Integer.parseInt(fontString.substring(fontString.indexOf(",") + 1, fontString.lastIndexOf(",")));
    size = Integer.parseInt(fontString.substring(fontString.lastIndexOf(",") + 1));
    return new Font(name, style, size);
  }
  
  public static ArrayList<VisualizationTupel> parseTupels(final String tupelsString) {
    String[] tupelStrings = tupelsString.split("\n");
    ArrayList<VisualizationTupel> visualizationTupels = new ArrayList<>();
    for(String s : tupelStrings) {
      visualizationTupels.add(parseTupel(s));
    }
    return visualizationTupels;
  }
  
  public static VisualizationTupel parseTupel(final String tupelString) {
    int index;
    String name;
    Metric metric;
    boolean active;
    String remainder = tupelString;
    index = Integer.parseInt(remainder.substring(0, remainder.indexOf(";")));
    remainder = remainder.substring(remainder.indexOf(";") + 1);
    name = remainder.substring(0, remainder.indexOf(";"));
    remainder = remainder.substring(remainder.indexOf(";") + 1);
    metric = Metric.toMetric(remainder.substring(0, remainder.indexOf(";")));
    remainder = remainder.substring(remainder.indexOf(";") + 1);
    active = Boolean.parseBoolean(remainder);
    return new VisualizationTupel(index, name, metric, active);
  }
  
  public static void guaranteeSettings() throws IOException {
    File d = new File(USER_HOME + "/" + DIRECTORY_NAME + "/");
    if (!d.exists()) {
      if (!d.mkdir()) {
        throw new IOException();
      }
    }
    File f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + VISUALIZATION_FILE_FULL_NAME);
    if (!f.exists()) {
      save("default", createDefaults(), VISUALIZATION_FILE_NAME);
    }
  }
  
  private static VisualizationSettings createDefaults() {
    return new VisualizationSettings(getDefaultVisualizationTupels(), getDefaultSpacing(), getDefaultFont());
  }
  
  private static ArrayList<VisualizationTupel> getDefaultVisualizationTupels() {
    ArrayList<VisualizationTupel> visualizationTupels = new ArrayList<>();
    visualizationTupels.add(new VisualizationTupel(0, "Trick name: ", Metric.TRICK_NAME, true));
    visualizationTupels.add(new VisualizationTupel(1, "Attempts: ", Metric.ATTEMPTS, true));
    visualizationTupels.add(new VisualizationTupel(2, "Fails: ", Metric.FAILS, false));
    visualizationTupels.add(new VisualizationTupel(3, "Successes: ", Metric.SUCCESSES, true));
    visualizationTupels.add(new VisualizationTupel(4, "Successes in a row: ", Metric.SUCCESSES_BACK_TO_BACK, true));
    visualizationTupels.add(new VisualizationTupel(5, "Max successes in a row: ", Metric.SUCCESSES_HIGHSCORE, true));
    visualizationTupels.add(new VisualizationTupel(6, "Success rate (in %): ", Metric.SUCCESS_PERCENTAGE, true));
    return visualizationTupels;
  }
  
  private static int getDefaultSpacing() {
    return 10;
  }
  
  private static Font getDefaultFont() {
    return new Font("Dialog", Font.PLAIN, 12);
  }
}
