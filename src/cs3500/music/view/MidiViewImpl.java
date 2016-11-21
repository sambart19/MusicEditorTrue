package cs3500.music.view;

import java.util.List;


import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.Note;
import cs3500.music.NoteColumn;
import cs3500.music.NoteName;

/**
 * A skeleton for MIDI playback.
 */

public class MidiViewImpl implements IView {
  private Sequencer seq;
  private List<NoteColumn> notes;
  private int tempo;

  /**
   * A basic constructor.
   * @param notes The list of note columns to use.
   * @param tempo The tempo to play them at.
   */
  public MidiViewImpl(List<NoteColumn> notes, int tempo) {
    this.notes = notes;
    this.tempo = tempo;
    try {
      this.seq = MidiSystem.getSequencer();
      this.seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    try {
      this.build();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.seq.setTempoInMPQ(tempo);
  }



  /**
   * Loads all the notes onto a track, which loads onto a sequence which is
   * used by the sequencer.
   * @throws InvalidMidiDataException
   */
  public void build() throws InvalidMidiDataException{
    Sequence sequence = new Sequence(Sequence.PPQ, 1);
    sequence.createTrack();
    Track track = sequence.getTracks()[0];
    for (NoteColumn n : this.notes) {
      for (Integer i : n.getBeats().keySet()) {
        Note note = n.getBeats().get(i);
        if (note.getHead()) {
          track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
                  this.toPitch(n.getName(), n.getOctave()), note.getVolume()), i));
          if (note.getEnd() - i != 1) {
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
                    this.toPitch(n.getName(), n.getOctave()), note.getVolume()), note.getEnd()));
          } else {
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
                    this.toPitch(n.getName(), n.getOctave()), note.getVolume()), i));
          }
        }
      }
    }
    this.seq.setSequence(sequence);
  }

  /**
   * A helper method to convert a notename and an octave to a pitch.
   * @param name The name of the note.
   * @param octave The octave the note is in
   * @return A pitch in int form.
   */
  private int toPitch(NoteName name, int octave) {
    return (octave * 12) + name.ordinal();
  }

  @Override
  public void view() {
    this.seq.start();
    this.seq.setTempoInMPQ(tempo);
  }

  /**
   * Gets the current location of the sequencer which is used to help sync the MIDI View
   * with the GUI View.
   * @return  Current Tick Position of hte sequencer.
   */
  public long getLoc() {
    return this.seq.getTickPosition();
  }

  /**
   * Pauses the sequencer which causes notes to stop playing.
   */
  public void pause() {
    this.seq.stop();
  }

  /**
   * Updates the list of notecolumns for this view.
   * @param notes The new list to use.
   */
  public void update(List<NoteColumn> notes) {
    this.notes = notes;
  }
}
