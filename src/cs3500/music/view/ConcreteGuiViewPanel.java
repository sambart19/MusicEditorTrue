package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import cs3500.music.Note;
import cs3500.music.NoteColumn;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {
  private List<NoteColumn> notes;
  private int gridScale;

  ConcreteGuiViewPanel(List<NoteColumn> notes) {
    this.notes = notes;
    this.gridScale = 10;
  }
  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    this.drawTopRow(g);
    for (int i = 0; i < notes.size(); i++) {
      this.drawRow(g, notes.get(i), i * gridScale + 50);
    }
  }

  /**
   * We realize now that NoteColumn name may indicate that it represents a column, but in
   * actuality it represents an array of columns, aka a row, aka get wrekt.
   *
   * Method draws out the row.
   *
   * @param row A NoteColumn(1 row).
   */
  public void drawRow(Graphics g, NoteColumn row, int y) {
    g.setColor(Color.black);
    String oct = row.toString();
    while (oct.length() < 5) {
      oct += " ";
    }
    g.drawChars(oct.toCharArray(), 0, 5, 0, y + this.gridScale);
    // Draws the notes at proper lcoations
    for (int i = 0; i < row.getBeats().size(); i++) {
      if (Objects.nonNull(row.getBeats().get(i))) {
        if (row.getBeats().get(i).getHead()) {
          g.setColor(Color.green);
        } else {
          g.setColor(Color.orange);
        }
        g.fillRect(50 + (i * this.gridScale), y, this.gridScale, this.gridScale);
        g.setColor(Color.black);
      }
    }

    // Draws empty white rectangles
    for (int i = 0; i < maxBeat() / 4 + 1; i++) {
      g.drawRect(50 + (i * this.gridScale * 4), y, 4 * this.gridScale, this.gridScale);
    }

  }

  public void drawTopRow(Graphics g) {
    for (int i = 0; i < maxBeat() / 16 + 1; i++) {
      String temp = Integer.toString(i * 16);
      while (temp.length() < Integer.toString(maxBeat() + 16).length()) {
        temp += " ";
      }
      g.drawChars(temp.toCharArray(), 0,
              Integer.toString(maxBeat() + 16).length(), 50 + i * 16 * this.gridScale, 40);
    }
  }

  /**
   * Changes the scale.
   * @param gridScale the scale
   */
  public void setGridScale(int gridScale) {
    this.gridScale = gridScale;
  }

  /**
   * Finds the maxBeat within all NoteColumns
   * @return the maxBeat within all NoteColumns.
   */
  private int maxBeat() {
    int result = 0;
    for (NoteColumn a  : notes) {
      result = Math.max(a.getBeats().size(), result);
    }
    return  result;
  }

}
