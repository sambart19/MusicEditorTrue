package cs3500.music.provider;

import java.util.ArrayList;

import cs3500.music.provider.IMusicEditorModel;
import cs3500.music.provider.Note;

/**
 * Represents a ViewModel which allows the view to access parts of the model it needs.
 */
public class ViewModel {
  IMusicEditorModel model;

  /**
   * Constructs the view model with the ability to obtain information from the given model.
   *
   * @param model The model the view model has access to.
   */
  public ViewModel(IMusicEditorModel model) {
    this.model = model;
  }

  /**
   * Gets the length of the model.
   *
   * @return The length of the model.
   */
  int length() {
    return this.model.length();
  }

  /**
   * Gets the tempo of the model.
   *
   * @return The tempo of the model.
   */
  int getTempo() {
    return model.getTempo();
  }

  /**
   * Gets the string output of the model.
   *
   * @return The state of the model.
   */
  String getState() {
    return model.getState();
  }

  /**
   * Gets the notes of the model.
   *
   * @return The notes of the model.
   */
  ArrayList<Note> getNotes() {
    return model.getNotes();
  }

  /**
   * Gets the note range of the model.
   *
   * @return The note range of the model.
   */
  ArrayList<Note> range() {
    return model.range();
  }

  /**
   * Gets the highest note of the model.
   *
   * @return The highest note of the model.
   */
  Note highestNote() {
    return model.highestNote();
  }

  /**
   * Gets the lowest note of the model.
   *
   * @return The lowest note of the model.
   */
  Note lowestNote() {
    return model.lowestNote();
  }
}
