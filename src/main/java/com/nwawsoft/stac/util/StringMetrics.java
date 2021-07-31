package com.nwawsoft.stac.util;

import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;

/**
 * Class StringMetrics by Ed Poor
 * https://stackoverflow.com/questions/258486/calculate-the-display-width-of-a-string-in-java
 */
public class StringMetrics {
  private final Font font;
  private final FontRenderContext context;

  public StringMetrics(final Graphics2D g2) {
    font = g2.getFont();
    context = g2.getFontRenderContext();
  }

  private Rectangle2D getBounds(final String message) {
    return font.getStringBounds(message, context);
  }

  public double getWidth(final String message) {
    Rectangle2D bounds = getBounds(message);
    return bounds.getWidth();
  }

  public double getHeight(final String message) {
    Rectangle2D bounds = getBounds(message);
    return bounds.getHeight();
  }
}