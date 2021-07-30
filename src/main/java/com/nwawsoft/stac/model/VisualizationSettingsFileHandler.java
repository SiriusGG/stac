package com.nwawsoft.stac.model;

import com.nwawsoft.util.ui.FontFunctions;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static com.nwawsoft.stac.BuildData.*;

public class VisualizationSettingsFileHandler {
  /**
   * Saves the visualization settings to a text file.
   * The file extension will be set accordingly.
   * ".stacv" for the default visualization file and ".stactv" for trick visualization files.
   *
   * @param vs       any VisualizationSettings object.
   * @param fileName the name of the visualization file to be saved.
   */
  public static void save(final VisualizationSettings vs, final String fileName) {
    File d;
    File f;
    try {
      d = new File(USER_HOME + "/" + DIRECTORY_NAME);
      if (!d.exists()) {
        if (!d.mkdir()) {
          throw new IOException();
        }
      }
      f = new File(USER_HOME + "/" + DIRECTORY_NAME + "/" + fileName);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("SPACING=" + vs.getSpacing() + "\n");
      bw.write("FONT=" + FontFunctions.toFontString(vs.getFont()) + "\n");
      bw.write("#TUPELS_START\n");
      for (VisualizationTupel vt : vs.getVisualizationTupels()) {
        bw.write(toTupelString(vt) + "\n");
      }
      bw.write("#TUPELS_END\n");
      bw.flush();
      bw.close();
    } catch (IOException e) {
      System.err.println("Error in save(\"default\", {VisualizationSettings}, \"" + fileName + "\"): ");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("StringConcatenationInLoop")
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
          font = FontFunctions.parseFont(currentLine.substring(currentLine.lastIndexOf("=") + 1));
        } else if (currentLine.contains("TUPELS_START")) {
          tupelsString = tupelsString + currentLine + "\n";
          while (!(currentLine = br.readLine()).contains("TUPELS_END")) {
            tupelsString = tupelsString + currentLine + "\n";
          }
        }
      }
      br.close();
      tupelsString = tupelsString.substring(tupelsString.indexOf("\n"));
      tupelsString = tupelsString.trim();
      visualizationTupels = parseTupels(tupelsString);
    } catch (IOException e) {
      System.err.println("Something went wrong at:\nVisualizationSettings.load(" + fileName + ")");
      e.printStackTrace();
      System.err.println("Continuing with new VisualizationSettings(null, 0, null)");
      return new VisualizationSettings(null, 0, null);
    }
    return new VisualizationSettings(visualizationTupels, spacing, font);
  }

  public static ArrayList<VisualizationTupel> parseTupels(final String tupelsString) {
    String[] tupelStrings = tupelsString.split("\n");
    ArrayList<VisualizationTupel> visualizationTupels = new ArrayList<>();
    for (String s : tupelStrings) {
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

  public static String toTupelString(final VisualizationTupel visualizationTupel) {
    return visualizationTupel.getIndex() + ";" +
        visualizationTupel.getName() + ";" +
        visualizationTupel.getMetric() + ";" +
        visualizationTupel.isActive();
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
      save(VisualizationSettings.createDefaults(), VISUALIZATION_FILE_FULL_NAME);
    }
  }
}
