package cs3500.music;

import java.util.HashMap;

/**
 * Designed to represent a note with octave included.
 */
public class NoteColumn implements Comparable<NoteColumn> {

  private final NoteName name;
  private final int octave;
  private HashMap<Integer, Note> beats;

  /**
   * Gets the notename of this note.
   * @return The notename of this note.
   */
  public NoteName getName() {
    return this.name;
  }

  /**
   * Gets the octave of this note.
   * @return The octave of this note.
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Gets the beats of this note.
   * @return The beats of this note.
   */
  public HashMap<Integer, Note> getBeats() {
    //HashMap<Integer, Note> result = new HashMap<>();
    //for (Integer i : this.beats.keySet()) {
    //  result.put(i, this.beats.get(i));
    //}
    return this.beats;
  }

  /**
   * Basic constructor for a note.
   * @param name The NoteName of this note.
   * @param octave The octave number of this note.
   */
  public NoteColumn(NoteName name, int octave) {
    if (name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }
    this.name = name;
    this.octave = octave;
    this.beats = new HashMap<Integer, Note>();
  }

  /**
   * A copy constructor for Note.
   * @param other The note to copy.
   */
  public NoteColumn(NoteColumn other) {
    this.name = other.getName();
    this.octave = other.getOctave();
    this.beats = other.getBeats();
  }

  /**
   * Adds a series of beats to this list of beats. It extends the list to fit the new beat given,
   * adding an "  X  " as the "head" of the note and "  |  " for the rest of the duration given.
   * Throws an IllegalArgumentException if the start index is less than 0.
   * @param start The beat to start the note on.
   * @param duration How long the beat should last.
   */
  public void addBeat(int start, int duration, int volume, int instrument) {

    if (start < 0) {
      throw new IllegalArgumentException("start index out of range");
    }

    if (duration < 0) {
      throw new IllegalArgumentException("duration cannot be negative");
    }

    this.beats.put(start, new Note(true, volume, instrument));
    this.beats.get(start).setEnd(start + duration);
    int count = 1;

    while (duration > 1) {
      this.beats.put(start + count, new Note(false, volume, instrument));
      count++;
      duration--;
    }

  }

  /**
   * Removes a series of beats starting from the given index of the head to the end of the tail.
   * Does not resize the array. Throws an IllegalArgumentException if the given start is out of
   * range or if the start is not the head of a note.
   * @param start = The index of the note to be removed.
   */
  public void removeBeat(int start) {
    if (start < 0 || !this.beats.containsKey(start)) {
      throw new IllegalArgumentException("start index out of range");
    }

    if (!(this.beats.get(start).toString().equals("  X  "))) {
      throw new IllegalArgumentException("index not at the start of a note");
    }

    this.beats.remove(start);

    int count = 1;
    while (this.beats.containsKey(start + count) && this.print(start + count).equals("  |  ")) {
      this.beats.remove(start + count);
      count++;
    }
  }

  /**
   * Converts this note to a string.
   * @return This note as a string.
   */
  public String toString() {
    return name.asString() + Integer.toString(octave);
  }

  /**
   * Used to print a single beat of this.
   * @param line = beat to be printed.
   * @return The beat given, if out of range return "     ".
   */
  public String print(int line) {
    if (!this.beats.containsKey(line)) {
      return "     ";
    } else {
      return this.beats.get(line).toString();
    }
  }

  /**
   * Used to compare notes. Returns negative if that is greater than this.
   * @param that = note to compare to this note.
   * @return The difference between the notes.
   */
  public int compareTo(NoteColumn that) {
    if (this.octave != that.octave) {
      return (this.octave - that.octave) * 13;
    } else {
      return this.name.ordinal() - that.name.ordinal();
    }
  }

}
