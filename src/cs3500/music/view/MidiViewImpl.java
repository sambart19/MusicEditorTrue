package cs3500.music.view;

import java.util.Collections;
import java.util.List;


import javax.sound.midi.*;

import cs3500.music.Note;
import cs3500.music.NoteColumn;
import cs3500.music.NoteName;

import static java.lang.Thread.sleep;


/**
 * A skeleton for MIDI playback
 */

public class MidiViewImpl implements IView {
  private Synthesizer synth;
  private Receiver receiver;
  private List<NoteColumn> notes;
  private int tempo;

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
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
    */

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  private int toPitch(NoteName name, int octave) {
    return (octave * 12) + name.ordinal();
  }

  public void view() throws InvalidMidiDataException{
    long start = this.synth.getMicrosecondPosition();
    start += 5000000;
    for (NoteColumn n : this.notes) {
      for (Integer i : n.getBeats().keySet()) {
        Note note = n.getBeats().get(i);
        if (note.getHead()) {
          try {
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                    this.toPitch(n.getName(), n.getOctave()), note.getVolume()),
                    start + (i * this.tempo));
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }
        }
        if (!n.getBeats().containsKey(i + 1)) {
          note = n.getBeats().get(i);
          try {
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                            this.toPitch(n.getName(), n.getOctave()), note.getVolume()),
                    start + ((i + 1) * this.tempo));
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }
        }
      }
    }
    int max = 0;

    for (NoteColumn n : this.notes) {
      if (!n.getBeats().keySet().isEmpty()) {
        max = Math.max(max, Collections.max(n.getBeats().keySet()));
      }
  }
    try {
      sleep((max * this.tempo) / 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.receiver.close();
  }
}
