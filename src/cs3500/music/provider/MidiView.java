package cs3500.music.provider;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.provider.IMusicEditorModel;
import cs3500.music.provider.Note;

/**
 * A MidiView which allows an IMusicEditorModel to be played
 * through midi using java midi resources.
 */
public class MidiView implements IView {
  private Sequencer seq;
  private Sequence sequence;
  private ViewModel model;
  private boolean isplaying;
  private int currentBeat;

  /**
   * Constructs a midiview with the default sequencer of the MidiSystem.
   */
  MidiView() throws InvalidMidiDataException {
    try {
      this.seq = MidiSystem.getSequencer();
      this.seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequence = new Sequence(Sequence.PPQ, 1);
    this.model = null;
    this.isplaying = false;
    this.currentBeat = 0;
  }

  /**
   * Constructs a midiview with a given sequencer and model.  Used for the purpose of testing.
   *
   * @param seq   The sequencer that will play the music.
   * @param model The view model that will be used.
   */
  MidiView(ViewModel model, Sequencer seq) throws InvalidMidiDataException {
    this.seq = seq;
    try {
      this.seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequence = new Sequence(Sequence.PPQ, 1);
    this.model = model;
    this.isplaying = false;
    this.currentBeat = 0;
  }

  @Override
  public void setViewModel(IMusicEditorModel model) {
    this.model = new ViewModel(model);
  }


  private void initializeNotes() throws InvalidMidiDataException {
    List<Note> notes = model.getNotes();
    Track track = sequence.createTrack();
    for (Note n: notes) {
      MidiEvent start = new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, n.getInst() - 1,
              n.trueValue(), n.getVolume()), n.getPosition());
      MidiEvent end = new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, n.getInst() - 1,
              n.trueValue(), n.getVolume()), (n.getPosition() + n.getDuration()));
      track.add(start);
      track.add(end);
    }
  }

  @Override
  public void display() throws InvalidMidiDataException {
    this.initializeNotes();
    seq.setTempoInMPQ(model.getTempo());
    seq.setSequence(sequence);
    this.isplaying = true;
    seq.start();
  }



}