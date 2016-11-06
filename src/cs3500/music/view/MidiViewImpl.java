package cs3500.music.view;

import java.util.List;
import java.util.Objects;

import javax.sound.midi.*;

import cs3500.music.Note;
import cs3500.music.NoteColumn;
import cs3500.music.NoteName;
import cs3500.music.model.IMusicModel;

/**
 * A skeleton for MIDI playback
 */

public class MidiViewImpl implements IView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private List<NoteColumn> notes;

  public MidiViewImpl(List<NoteColumn> notex) {
    this.notes = notes;
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

  public void view() {
    long start = this.synth.getMicrosecondPosition();
    for (NoteColumn n : this.notes) {
      for (int i = 0; i < n.getBeats().size(); i++) {

        if (n.getBeats().get(i) != null) {
          Note note = n.getBeats().get(i);
          if (note.getHead()) {
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(), this.toPitch(n.getName(), n.getOctave()), note.getVolume()), start + (i * ));
          }
        }
      }
    }
  }
}
