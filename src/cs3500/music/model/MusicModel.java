package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.music.NoteColumn;
import cs3500.music.NoteName;

/**
 * A model designed to represent a song featuring only one instrument.
 */
public class MusicModel implements IMusicModel {

  private List<NoteColumn> notes;
  private int tempo;

  /**
   * A basic constructor for MusicModel.
   */
  public MusicModel() {
    this.notes = new ArrayList<NoteColumn>();
  }

  @Override
  public void addNote(NoteColumn n) {

    if (this.notes.contains(n)) {
      throw new IllegalArgumentException("This note is already in this model.");
    }

    if (this.notes.isEmpty()) {
      notes.add(n);
      return;
    }

    while (n.compareTo(this.notes.get(0)) < 0) {
      NoteColumn temp = new NoteColumn(this.notes.get(0));
      this.addLess(temp);
      Collections.sort(this.notes);
    }

    while (n.compareTo(this.notes.get(this.notes.size() - 1)) > 0) {
      NoteColumn temp = new NoteColumn(this.notes.get(this.notes.size() - 1));
      this.addGreater(temp);
      Collections.sort(this.notes);
    }

    Collections.sort(this.notes);
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Helper method for addNote if the note is less than this list.
   * @param n The end of this list of notes to decrease by one.
   */
  private void addLess(NoteColumn n) {
    switch (n.getName()) {
      case C :
        this.notes.add(new NoteColumn(NoteName.B, n.getOctave() - 1));
        break;
      case CSHARP :
        this.notes.add(new NoteColumn(NoteName.C, n.getOctave()));
        break;
      case D :
        this.notes.add(new NoteColumn(NoteName.CSHARP, n.getOctave()));
        break;
      case DSHARP :
        this.notes.add(new NoteColumn(NoteName.D, n.getOctave()));
        break;
      case E :
        this.notes.add(new NoteColumn(NoteName.DSHARP, n.getOctave()));
        break;
      case F :
        this.notes.add(new NoteColumn(NoteName.E, n.getOctave()));
        break;
      case FSHARP :
        this.notes.add(new NoteColumn(NoteName.F, n.getOctave()));
        break;
      case G :
        this.notes.add(new NoteColumn(NoteName.FSHARP, n.getOctave()));
        break;
      case GSHARP :
        this.notes.add(new NoteColumn(NoteName.G, n.getOctave()));
        break;
      case A :
        this.notes.add(new NoteColumn(NoteName.GSHARP, n.getOctave()));
        break;
      case ASHARP :
        this.notes.add(new NoteColumn(NoteName.A, n.getOctave()));
        break;
      case B :
        this.notes.add(new NoteColumn(NoteName.ASHARP, n.getOctave()));
        break;
      default :
        throw new IllegalArgumentException("Illegal note name");
    }
  }

  /**
   * Helper method for addNote if the note is greater than this list.
   * @param n The end of this list of notes to increase by one.
   */
  private void addGreater(NoteColumn n) {
    switch (n.getName()) {
      case C :
        this.notes.add(new NoteColumn(NoteName.CSHARP, n.getOctave()));
        break;
      case CSHARP :
        this.notes.add(new NoteColumn(NoteName.D, n.getOctave()));
        break;
      case D :
        this.notes.add(new NoteColumn(NoteName.DSHARP, n.getOctave()));
        break;
      case DSHARP :
        this.notes.add(new NoteColumn(NoteName.E, n.getOctave()));
        break;
      case E :
        this.notes.add(new NoteColumn(NoteName.F, n.getOctave()));
        break;
      case F :
        this.notes.add(new NoteColumn(NoteName.FSHARP, n.getOctave()));
        break;
      case FSHARP :
        this.notes.add(new NoteColumn(NoteName.G, n.getOctave()));
        break;
      case G :
        this.notes.add(new NoteColumn(NoteName.GSHARP, n.getOctave()));
        break;
      case GSHARP :
        this.notes.add(new NoteColumn(NoteName.A, n.getOctave()));
        break;
      case A :
        this.notes.add(new NoteColumn(NoteName.ASHARP, n.getOctave()));
        break;
      case ASHARP :
        this.notes.add(new NoteColumn(NoteName.B, n.getOctave()));
        break;
      case B :
        this.notes.add(new NoteColumn(NoteName.C, n.getOctave() + 1));
        break;
      default :
        throw new IllegalArgumentException("Illegal note name");
    }
  }

  @Override
  public void addBeat(NoteName name, int octave, int start, int duration,
                      int volume, int instrument) {

    String note = name.asString() + Integer.toString(octave);

    NoteColumn n1 = new NoteColumn(name, octave);
    this.addNote(n1);

    for (NoteColumn n : this.notes) {
      if (n.toString().equals(note)) {
        n.addBeat(start, duration, volume, instrument);
        return;
      }
    }
  }

  @Override
  public void removeBeat(NoteName name, int octave, int start) {
    String note = name.asString() + Integer.toString(octave);

    for (NoteColumn n : this.notes) {
      if (n.toString().equals(note)) {
        n.removeBeat(start);
      }
    }
  }

  @Override
  public List<NoteColumn> print() {
    List<NoteColumn> result = new ArrayList<NoteColumn>();
    for (NoteColumn n : notes) {
      result.add(new NoteColumn(n));
    }
    return result;
  }

}
