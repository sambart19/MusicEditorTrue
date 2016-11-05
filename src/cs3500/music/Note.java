package cs3500.music;



/**
 * Class to represent a note.
 */

public class Note {

  private boolean head;
  private int volume;
  private int instrument;

  /**
   * A basic constructor for note.
   * @param head If this note is the head.
   * @param volume The volume of this note.
   * @param instrument The instrument this note is played on.
   */
  public Note(boolean head, int volume, int instrument) {
    this.head = head;
    this.volume = volume;
    this.instrument = instrument;
  }

  /**
   * A getter for the boolean head.
   * @return True if this note is the head.
   */
  public boolean getHead() {
    return this.head;
  }

  /**
   * A getter for the volume of this note.
   * @return The volume of this note.
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * A getter for the instrument this note is played on.
   * @return The instrument this note is played on.
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * A setter for the volume of this note
   * @param volume The volume this note should play at.
   */
  public void setVolume(int volume) {
    this.volume = volume;
  }

  /**
   * Converts this note to a string, with "  X  " for a head or
   * "  |  " for a tail.
   * @return This note as a string.
   */
  public String toString() {
    if (head) {
      return "  X  ";
    } else {
      return "  |  ";
    }
  }
}
