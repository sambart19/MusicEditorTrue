package cs3500.music;

/**
 * Designed to represent the name of a note, excluding the octave.
 * E.G. C, D#, ect.
 */
public enum NoteName {
  /**
   * Enumerates all of the possible notes.
   */
  C("C"), CSHARP("C#"), D("D"), DSHARP("D#"), E("E"), F("F"), FSHARP("F#"),
  G("G"), GSHARP("G#"), A("A"), ASHARP("A#"), B("B");

  /**
   * Converts the enum to a string.
   * @return This enum as a string.
   */
  public String asString() {
    return asString;
  }

  private final String asString;

  /**
   * constructor for this enum.
   * @param asString the string to return when asString() is called.
   */
  private NoteName(String asString) {
    this.asString = asString;
  }
}
