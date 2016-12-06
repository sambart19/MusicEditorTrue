package cs3500.music.provider;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.provider.Note;

/**
 * A composite view which shows the notes of a song and plays them back simultaneously.
 */
public class CompositeView extends GUIView {
  private Sequencer seq;
  private Sequence sequence;
  private boolean isPlaying;

  /**
   * Creates a composite view that displays the song as it is being played.
   */
  public CompositeView() throws InvalidMidiDataException {
    super();
    try {
      this.seq = MidiSystem.getSequencer();
      this.seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequence = new Sequence(Sequence.PPQ, 1);
    this.isPlaying = false;
    this.currentBeat = 0;
    seq.addMetaEventListener(meta -> this.updateBeat());
  }

  private void updateBeat() {
    currentBeat = seq.getTickPosition();
    if (currentBeat * CELL_SIZE >= scroll.getHorizontalScrollBar().getValue()
            + scroll.getWidth() - (2 * CELL_SIZE)) {
      scroll.getHorizontalScrollBar().setValue((int)currentBeat * CELL_SIZE);
    }
    this.repaint();
  }

  @Override
  public void resetPanel(int x, int y) {
    super.resetPanel(x, y);

    // Re-initializes the midi to prepare to be played
    this.initializeMidi();
  }

  @Override
  public void playOrPause() {
    if (this.isPlaying) {
      seq.stop();
      this.isPlaying = false;
    } else {
      seq.start();
      seq.setTempoInMPQ(model.getTempo());
      this.isPlaying = true;
    }
  }

  @Override
  public void move(String direction) {
    if (!this.isPlaying) {
      super.move(direction);
    }
  }

  @Override
  public void jump(String position) {
    if (!this.isPlaying) {
      super.jump(position);

      // Moves to the beginning of the song
      if (position.equals("Beginning")) {
        this.currentBeat = 0;
        seq.setTickPosition(currentBeat);
      }

      // Moves to the end of the song
      if (position.equals("End")) {
        this.currentBeat = model.length();
        seq.setTickPosition(currentBeat * model.getTempo());
      }
    }
  }

  @Override
  public void display() {
    super.display();

    // Initializes the midi to prepare to be played
    this.initializeMidi();
  }

  private void initializeMidi() {
    try {
      this.initializeNotes();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    seq.setTempoInMPQ(model.getTempo());
    try {
      seq.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  private void initializeNotes() throws InvalidMidiDataException {
    List<Note> notes = model.getNotes();
    Track track = sequence.createTrack();
    MetaMessage m = new MetaMessage();
    for (Note n : notes) {
      MidiEvent start = new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON,
              n.getInst() - 1, n.trueValue(), n.getVolume()), n.getPosition());
      MidiEvent end = new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF,
              n.getInst() - 1, n.trueValue(), n.getVolume()), (n.getPosition() + n.getDuration()));
      track.add(start);
      track.add(end);
    }
    for (int i = 0; i < model.length(); i++) {
      MidiEvent event = new MidiEvent(m, i);
      track.add(event);
    }
  }

}
