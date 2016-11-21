package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.NoteName;

/**
 * A view that combines the gui view and midi view. It syncs so that a red bar will represent which
 * beat the midi view is currently representing with audio.
 */
public class CombinedView implements GuiView{
  private GuiViewFrame gui;
  private MidiViewImpl midi;
  private boolean isPlaying;

  /**
   * A basic constructor for the combined view. By default the midi is not playing.
   * @param gui The gui view to sync.
   * @param midi The midi view to sync.
   */
  public CombinedView(GuiViewFrame gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
    this.isPlaying = false;
  }

  /**
   * Displays the gui portion without starting the midi.
   */
  public void view() {
    gui.view();
  }

  /**
   * Toggles the playing of the midi view. If it is already playing it stops.
   * If it is not playing it starts.
   */
  public void togglePlay() {
    if (!isPlaying) {
      midi.view();
      this.isPlaying = true;
    }
    else {
      midi.pause();
      this.isPlaying = false;
    }
  }

  /**
   * Sets the listener for the gui.
   * @param listener The listener for the gui.
   */
  public void setListeners(KeyListener listener, ActionListener actionListener) {
    this.gui.addKeyListener(listener);
    this.gui.addActionListener(actionListener);
  }

  /**
   * Parses the given command, and passes it to the gui view.
   * @param s The command to be parsed.
   */
  public void parse(String s) {
    switch (s) {
      case "refresh" : this.gui.refresh(this.midi.getLoc());
        break;
      case "forward" : this.gui.forward();
        break;
      case "backward" : this.gui.backward();
        break;
      case "to end" : this.gui.toEnd();
        break;
      case "to start" : this.gui.toStart();
        break;
      case "rebuild" : this.gui.refresh(this.midi.getLoc());
        try {
          this.midi.build();
          System.out.println("MIDI JUST GOT REBUILT");
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
        break;
      default: //Doesn't do anything
    }
  }

  public String guiAccess(String s) {
    switch (s) {
      case "octave" : return this.gui.getOctave();
      case "duration" : return this.gui.getDuration();
      case "location" : return this.gui.getLoc();
      case "instrument" : return this.gui.getInstrument();
      case "volume" : return this.gui.getVolume();
      default:
        return "";
    }
  }

  public NoteName guiNoteName() {
    return this.gui.getNoteName();
  }

  public void resetFocus() {
    this.gui.requestFocus();
  }

  /**
   * A getter for the isPlaying variable.
   * @return The isPlaying variable.
   */
  public boolean getIsPlaying() {
    return this.isPlaying;
  }
}
