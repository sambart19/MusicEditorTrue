package cs3500.music.view;


import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;

import java.util.List;


import javax.swing.JPanel;

import cs3500.music.NoteColumn;

/**
 * This view creates the JPanel object that has the notes drawn out on a grid.
 */
class GuiPanel extends JPanel {
  private List<NoteColumn> notes;
  private int gridScale;
  private long loc;

  /**
   * Constructor reverses the order of the note columns to make it easier to draw the higher pitch
   * columns on top.
   *
   * @param notes The intermediate representation of the music model
   */
  GuiPanel(List<NoteColumn> notes, long loc) {
    Collections.reverse(notes);
    this.notes = notes;
    this.gridScale = 20;
    this.loc = loc;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawTopRow(g);
    this.drawNotes(g);
    this.drawGrid(g);
    this.drawLine(g);
  }

  /**
   * Sets the beat for the line.
   * @param loc The beat the line should be on.
   */
  public void setLoc(long loc) {
    this.loc = loc;
  }

  /**
   * This method draws out all straight lines for the rows. The number of lines drawn should
   * correspond to the number of different note-names there are + 1.
   *
   * @param g The graphics class used to draw.
   */
  private void drawGrid(Graphics g) {
    int max = maxBeat();

    //Draws the row lines
    for (int i = 0; i <= notes.size(); i++) {
      g.drawLine(50, i * gridScale + 10, (max + (4 - (max % 4))) * gridScale + 50,
              i * gridScale + 10);
    }

    //Draws the col lines
    for (int i = 0; i <= max / 4 + (4 - (max % 4)); i++) {
      g.drawLine(50 + i * gridScale * 4, 10, 50 + i * gridScale * 4,
              this.notes.size() * gridScale + 10);
    }
  }

  /**
   * Draws rectangles that represent notes at proper position. The rectangle is green if its a head
   * and it is orange if its not. Each rectangle is gridscale wide by gridscale length. Also draws
   * the notenames at correct position
   *
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
   *
   * @param g The graphics object used to draw.
   */
  private void drawTopRow(Graphics g) {
    int max = maxBeat();
    for (int i = 0; i < max / 16 + 1; i++) {
      String temp = Integer.toString(i * 16);
      while (temp.length() < Integer.toString(max + 16).length()) {
        temp += " ";
      }
      g.drawChars(temp.toCharArray(), 0,
              Integer.toString(max + 16).length(), 50 + i * 16 * this.gridScale, 10);
    }
  }

  /**
   * Method draws the red line which indicates current position in the song.
   * @param g Graphics object used to draw.
   */
  public void drawLine(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(50 + (int) loc * gridScale, 10, 5, this.notes.size() * gridScale);
    g.setColor(Color.black);
  }

  /**
   * The current position of the line.
   * @returnThe line position.
   */
  public int getLinePos() {
    return 50 + (int) loc * gridScale;
  }

  /**
   * The end position of this song.
   * @return The pixel this song ends on.
   */
  public int endpos() {
    int max = this.maxBeat();
    return ((max + (4 - (max % 4))) * gridScale + 50);
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
    for (NoteColumn a : notes) {
      if (!a.getBeats().keySet().isEmpty()) {
        result = Math.max(Collections.max(a.getBeats().keySet()), result);
      }
    }
    return result;
  }

  @Override
  public Dimension getPreferredSize() {
    int x;
    int y;
    y = (this.notes.size()) * gridScale + 50;
    x = (this.maxBeat() + 8) * gridScale;

    return new Dimension(x, y);
  }

  /**
   * Updates the note column.
   * @param notes The new note column.
   */
  public void update(List<NoteColumn> notes) {
    this.notes = notes;
  }

}
