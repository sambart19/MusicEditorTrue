package cs3500.music.provider;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface of the music editor model, parameterized over the type K.
 */
public interface IMusicEditorModel<K> {

  /**
   * Adds the note n to the piece given that the note is not already in the piece.
   *
   * @param n The note to be added.
   */
  void add(K n);

  /**
   * Removes the given note, given that the note exists in this piece of music.
   *
   * @param n The note to remove.
   */
  void remove(K n);

  /**
   * Returns the musical state of a song as a string.
   */
  String getState();

  /**
   * Combines this piece of music with the notes of another piece of music simultaneously.
   *
   * @param n The list of notes to combine with this piece of music.
   */
  void combineSim(ArrayList<K> n);

  /**
   * Combines this piece of music with the notes of another piece of music consecutively.
   *
   * @param n The list of notes to combine with this piece of music.
   */
  void combineCons(ArrayList<K> n);

  /**
   * Returns the total length of the song.
   */
  int length();

  /**
   * Gets all notes that are playing at the current position.
   *
   * @param pos The beat to retrieve all notes from.
   * @return A list of K that are playing at the given beat.
   */
  List<Note> currentNotes(int pos);

  /**
   * Gets the tempo of this song.
   *
   * @return The tempo.
   */
  int getTempo();

  /**
   * Gets the notes.
   *
   * @return The notes.
   */
  ArrayList<Note> getNotes();

  /**
   * Gets a list spanning the total range of notes.
   *
   * @return The note range.
   */
  ArrayList<Note> range();

  /**
   * Finds the highest note based on pitch and octave value.
   *
   * @return The highest note of the song.
   */
  Note highestNote();

  /**
   * Finds the lowest note based on pitch and octave value.
   *
   * @return The lowest note of the song.
   */
  Note lowestNote();
}
