package cs3500.music.provider;

import java.util.Objects;

/**
 * Represents a note.  I note is represented by a pitch, octave, duration, position in the song,
 * volume, and the instrument that is playing the note.
 */
public class Note {
  Pitch pitch;
  int octave;
  int duration;
  int position;
  int volume;
  int instrument;

  /**
   * Creates a note than can be played with an automatically initialized volume.
   *
   * @param pitch    The pitch of the note.
   * @param octave   The octave the note is located in.
   * @param duration The duration of the note.
   * @param position The starting beat of the note.
   */
  public Note(Pitch pitch, int octave, int duration, int position) {
    this.pitch = pitch;
    this.octave = octave;
    this.volume = 93;
    this.instrument = 1;
    // A valid duration for a Note must be a natural number.
    if (duration < 1) {
      throw new IllegalArgumentException("Invalid Duration.");
    } else {
      this.duration = duration;
    }

    // A valid position for a Note must be an integer value >= 0.
    if (position < 0) {
      throw new IllegalArgumentException("Invalid Position.");
    } else {
      this.position = position;
    }
  }

  /**
   * Creates a note that can be played with a custom volume.
   *
   * @param pitch      The pitch of the note.
   * @param octave     The octave the note is located in.
   * @param duration   The duration of the note.
   * @param position   The starting beat of the note.
   * @param volume     The volume this note should be played at.
   * @param instrument The instrument of this note.
   */
  public Note(Pitch pitch, int octave, int duration, int position, int volume, int instrument) {
    this.pitch = pitch;
    this.octave = octave;

    // A valid duration for a Note must be a natural number.
    if (duration < 0 || position < 0 || volume < 0 || instrument < 1 || instrument >= 128) {
      throw new IllegalArgumentException("Invalid Note");
    }
    this.duration = duration;
    this.position = position;
    this.volume = volume;
    this.instrument = instrument;
  }

  /**
   * Creates a Note for the header of the display.  This note is not meant to be played but merely
   * meant to be used when it is necessary to represent a range of notes.
   */
  public Note(Pitch pitch, int octave) {
    this.pitch = pitch;
    this.octave = octave;
    this.duration = 0;
    this.position = -1;
  }


  /**
   * Calculates the true value of this note. Each note has a unique true value based on its pitch
   * and octave.
   *
   * @return The true value.
   */
  public int trueValue() {
    return pitch.getValue() + 12 * octave;
  }

  /**
   * Gets the starting position of this note.
   *
   * @return The starting position of this note.
   */
  public int getPosition() {
    return this.position;
  }

  /**
   * Gets the duration of this note.
   *
   * @return The duration this note lasts.
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Gets the volume of this note.
   *
   * @return The volume this note should play at.
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Gets the instrument playing this note.
   *
   * @return The instrument of this note.
   */
  public int getInst() {
    return this.instrument;
  }



  @Override
  public String toString() {
    return pitch.getString() + Integer.toString(octave);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Note)) {
      return false;
    } else {
      return this.pitch == ((Note) o).pitch
              && this.octave == ((Note) o).octave
              && this.duration == ((Note) o).duration
              && this.position == ((Note) o).position;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(pitch, octave, duration, position);
  }
}
