package cs3500.music.provider;

import javax.sound.midi.InvalidMidiDataException;

/**
 * A Factory of views for easy ability to create a view.
 */
public class MusicViewFactory {

  /**
   * Creates a view of type x based on a string. If the string does not match any of the view types,
   * an exception is thrown.
   *
   * @param type The string of the view.
   * @return The desired view.
   */
  public static IView create(String type) throws InvalidMidiDataException {
    switch (type) {
      case "console":
        return new ConsoleView();
      case "midi":
        return new MidiView();
      case "visual":
        return new GUIView();
      case "composite":
        return new CompositeView();
      default:
        throw new IllegalArgumentException("Invalid view type.");
    }
  }
}
