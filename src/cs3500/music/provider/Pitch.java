package cs3500.music.provider;

import java.util.Objects;

/**
 * Represents the Pitch of a Note.
 */
public enum Pitch {
  C("C", 0), Cs("C#", 1), D("D", 2), Ds("D#", 3), E("E", 4), F("F", 5),
  Fs("F#", 6), G("G", 7), Gs("G#", 8), A("A", 9), As("A#", 10), B("B", 11);

  private final String s;
  private final int v;

  Pitch(String s, int v) {
    this.s = s;
    this.v = v;
  }

  /**
   * Gets the value of this pitch.
   *
   * @return this pitch's value.
   */
  int getValue() {
    return v;
  }

  /**
   * Get's the string representation of this pitch.
   *
   * @return The string associated to this pitch.
   */
  public String getString() {
    return s;
  }

  /**
   * Finds the pitch that corresponds to the given integer modulo 12.
   *
   * @param i The value to find.
   * @return The pitch that corresponds to i.
   */
  public static Pitch findPitch(int i) {
    Pitch result = null;
    for (Pitch p : Pitch.values()) {
      if (i % 12 == p.v) {
        result = p;
      }
    }
    return result;
  }

  /**
   * Makes a string into a pitch.
   * @param s The string to make into a pitch.
   * @return The pitch.
   */
  public static Pitch stringToPitch(String s) {
    Pitch result = null;
    for (Pitch p : Pitch.values()) {
      if (Objects.equals(s, p.s)) {
        result = p;
      }
    }
    return result;
  }
}
