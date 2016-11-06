package cs3500.music.view;

import java.util.Collections;
import java.util.List;

import cs3500.music.NoteColumn;

/**
 * View that displays the text form of MusicModel.
 */
public class TextView implements IView {

  private List<NoteColumn> notes;

  /**
   * The basic constructor for this textview.
   * @param notes The List of notes to be drawn.
   */
  public TextView(List<NoteColumn> notes) {
    this.notes = notes;
  }

  /**
   * Converts the list of notes to a string.
   * @return The string corresponding to this list of notes.
   */
  public String toString() {
    String result = "";

    int lines = 0;
    for (NoteColumn n : this.notes) {
      lines = Math.max(lines, Collections.max(n.getBeats().keySet()));
    }
    int length = Integer.toString(lines).length();
    String pad = "%" + Integer.toString(length) + "d";

    for (int i = length; i > 0; i--) {
      result += " ";
    }

    for (NoteColumn n : this.notes) {
      switch (n.toString().length()) {
        case 2 : result += "  " + n.toString() + " ";
          break;
        case 3 : result += " " + n.toString() + " ";
          break;
        case 4 : result += " " + n.toString();
          break;
        case 5 : result += n.toString();
          break;
        default : throw new IllegalArgumentException("this.notes malformed");
      }
    }

    result += "\n";

    for (int i = 0; i < lines; i++) {
      result += String.format(pad, i);
      for (NoteColumn n : this.notes) {
        result += n.print(i);
      }
      result += "\n";
    }
    return result;
  }


  @Override
  public void view() {
    System.out.println(this.toString());
  }
}
