package cs3500.music.provider;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.provider.IMusicEditorModel;

/**
 * The interface for a view of an IMusicEditorModel.
 */
public interface IView {

  /**
   * Sets the view model of this IView so that it can be accessed for display.
   *
   * @param model The model to be used as the foundation for the viewmodel.
   */
  void setViewModel(IMusicEditorModel model);

  /**
   * Displays this IView.
   *
   * @throws InvalidMidiDataException if the view is a MidiView, an exception may be thrown as a
   *                                  result of invalid midi data.
   */
  void display() throws InvalidMidiDataException;
}
