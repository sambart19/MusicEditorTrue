package cs3500.music.view;

import java.util.Collections;
import java.util.List;


import javax.sound.midi.Synthesizer;
import javax.sound.midi.Receiver;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

import cs3500.music.MockSynth;
import cs3500.music.Note;
import cs3500.music.NoteColumn;
import cs3500.music.NoteName;

import static java.lang.Thread.sleep;


/**
 * A skeleton for MIDI playback.
 */

public class MidiViewImpl implements IView {
  private Synthesizer synth;
  private Receiver receiver;
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
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * A mock constructor.
   * @param notes The notes.
   * @param tempo The tempo.
   * @param s The stringbuilder.
   */
  public MidiViewImpl(List<NoteColumn> notes, int tempo, StringBuilder s) {
    this.notes = notes;
    this.tempo = tempo;
    try {
      this.synth = new MockSynth(s);
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
    */

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
    this.receiver.close(); // Only call this once you're done playing *all* notes
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
    long start = this.synth.getMicrosecondPosition();
    start += 1000000;
    int max = 0;
    for (NoteColumn n : this.notes) {
      if (!n.getBeats().keySet().isEmpty()) {
        max = Math.max(max, Collections.max(n.getBeats().keySet()));
      }
      for (Integer i : n.getBeats().keySet()) {
        Note note = n.getBeats().get(i);
        if (note.getHead()) {
          try {
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
                    this.toPitch(n.getName(), n.getOctave()), note.getVolume()),
                    start + (i * this.tempo));
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
                            this.toPitch(n.getName(), n.getOctave()), note.getVolume()),
                    start + (note.getEnd() * this.tempo));
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }
        }
      }
    }
    long end = this.synth.getMicrosecondPosition();
    end = end - start;
    end = end / 1000;
    try {
      sleep(Math.max((((max * this.tempo) / 1000) - end), 1));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.receiver.close();
  }
}
