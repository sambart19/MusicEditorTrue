package cs3500.music.model;

import java.util.List;

import cs3500.music.NoteColumn;
import cs3500.music.NoteName;

/**
 * An interface to represent a model of music.
 */
public interface IMusicModel {

  /**
   * Allows the user to add notes directly, rather than individual beats.
   * @param n = The note to be added.
   */
  void addNote(NoteColumn n);

  /**
   * Used to add a beat to the given note. If the note is not already in this model, add it.
   * Also adds any notes between the new notes and the notes already in this model.
   * @param name = The name of the note do alter / add.
   * @param octave = The octave of the note to alter / add.
   * @param start = The beat to start on.
   * @param duration = The duration of the note.
   */
  void addBeat(NoteName name, int octave, int start, int duration, int volume, int instrument);

  /**
   * Allows the user to remove beats from this model. Does not resize the containers.
   * @param name = Name of the note to remove a beat from.
   * @param octave = Octave to remove the beat from
   * @param start = Location of the head of the beat to remove.
   */
  void removeBeat(NoteName name, int octave, int start);

  /**
   * Gets the list of notes in this model so that a view can display it.
   * @return The list of notes in this model.
   */
  List<NoteColumn> print();
}
