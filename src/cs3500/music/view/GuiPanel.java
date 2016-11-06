package cs3500.music.view;

import java.awt.*;

import java.util.Collections;

import java.util.List;

import javax.swing.*;

import cs3500.music.NoteColumn;

/**
 * This view creates the JPanel object that has the notes drawn out on a grid.
 */
class GuiPanel extends JPanel {
  private List<NoteColumn> notes;
  private int gridScale;

  /**
   * Constructor reverses the order of the note columns to make it easier to draw the higher pitch
   * columns on top.
   * @param notes The intermediate representation of the music model
   */
 GuiPanel(List<NoteColumn> notes) {
    Collections.reverse(notes);
    this.notes = notes;
    this.gridScale = 20;
  }
  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    this.drawTopRow(g);
    this.drawNotes(g);
    this.drawGrid(g);

  }

  /**
   * This method draws out all straight lines for the rows. The number of lines drawn should
   * correspond to the number of different note-names there are + 1.
   * @param g The graphics class used to draw.
   */
  private void drawGrid(Graphics g) {
    //Draws the row lines
    for (int i = 0; i <= notes.size(); i++) {
      g.drawLine(50, i * gridScale + 10, (this.maxBeat() + 4) * gridScale  + 50, i * gridScale + 10);
    }

    //Draws the col lines
    for (int i = 0; i <= maxBeat() / 4 + 1; i++) {
      g.drawLine(50 + i * gridScale * 4, 10, 50 + i * gridScale * 4, this.notes.size() * gridScale + 10);
    }
  }

  /**
   * Draws rectangles that represent notes at proper position. The rectangle is green if its a head
   * and it is orange if its not. Each rectangle is gridscale wide by gridscale length. Also draws
   * the notenames at correct position
   * @param g The graphics object used to draw.
   */
  private void drawNotes(Graphics g) {
    //Draws the beats at proper location
    for (int i = 0; i < notes.size(); i++) {
      NoteColumn row = notes.get(i);
      String name = row.toString();
      while (name.length() < 5) {
        name += " ";
      }
      g.drawChars(name.toCharArray(), 0, 5, 0, i * gridScale + this.gridScale);
      for (Integer loc : row.getBeats().keySet()) {
        if (row.getBeats().get(loc).getHead()) {
          g.setColor(Color.green); //if this note is a head
        } else {
          g.setColor(Color.orange);
        }
        g.fillRect(50 + (loc * this.gridScale), i * gridScale + 10, this.gridScale, this.gridScale);
        g.setColor(Color.black);
      }
    }
  }

  /**
   * Draws the top row which consists of the Character representation of every 16 beats and also
   * spaced correctly.
   * @param g The graphics object used to draw.
   */
  private void drawTopRow(Graphics g) {
    for (int i = 0; i < maxBeat() / 16 + 1; i++) {
      String temp = Integer.toString(i * 16);
      while (temp.length() < Integer.toString(maxBeat() + 16).length()) {
        temp += " ";
      }
      g.drawChars(temp.toCharArray(), 0,
              Integer.toString(maxBeat() + 16).length(), 50 + i * 16 * this.gridScale, 10);
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
      if (!a.getBeats().keySet().isEmpty()) {
        result = Math.max(Collections.max(a.getBeats().keySet()), result);
      }
    }
    return  result;
  }

  @Override
  public Dimension getPreferredSize(){
    int x, y;
    y = (this.notes.size()) * 50;
    x = (this.maxBeat() + 8) * gridScale;

    return new Dimension(x, y);
  }

}
