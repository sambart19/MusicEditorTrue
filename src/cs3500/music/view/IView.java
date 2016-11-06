package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * View interface to handle drawing the MusicModel.
 */
public interface IView {
  /**
   * The method that draws this view.
   */
  void view() throws InvalidMidiDataException;
}
