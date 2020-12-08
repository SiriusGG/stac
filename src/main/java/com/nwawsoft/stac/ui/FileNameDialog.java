package com.nwawsoft.stac.ui;

import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FileNameDialog extends JDialog {
  public FileNameDialog(final JFrame calledBy) {
    super(calledBy, true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 180;
    setSize(frameWidth, frameHeight);
    ComponentFunctions.center(this);
    setTitle("Illegal file name");
    Container cp = getContentPane();
    cp.setLayout(null);

    int buttonReservedSpace = 70;
    int heightSpacer = 10;
    int buttonWidth = 100;
    int buttonHeight = 30;
    JTextPane textPaneMessage = new JTextPane();
    textPaneMessage.setText("Please enter a legal file name in the second\n" +
        "text field. Names must be non-empty and may\n" +
        "only consist of letters from a to Z,\n" +
        "digits from 0 to 9 and the symbols\n" +
        "\"_\" (underscore) and \"-\" (minus)");
    textPaneMessage.setBounds(0, 0, frameWidth, frameHeight - buttonReservedSpace - heightSpacer);
    StyledDocument doc = textPaneMessage.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    textPaneMessage.setEditable(false);
    cp.add(textPaneMessage);
    JButton buttonOK = new JButton("OK");
    buttonOK.setBounds((frameWidth - buttonWidth) / 2, frameHeight - buttonReservedSpace, buttonWidth, buttonHeight);
    buttonOK.addActionListener(this::buttonOK_ActionPerformed);
    cp.add(buttonOK);

    setResizable(false);
    setVisible(true);
  }

  public void buttonOK_ActionPerformed(final ActionEvent evt) {
    dispose();
  }
}
