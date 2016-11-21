package cs3500.music.view;

import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * A view that combines the gui view and midi view. It syncs so that a red bar will represent which
 * beat the midi view is currently representing with audio.
 */
public class CombinedView implements GuiView{
  private GuiViewFrame gui;
  private MidiViewImpl midi;
  private boolean isPlaying;

  public CombinedView(GuiViewFrame gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
    this.isPlaying = false;
  }

  public void view() {
    gui.view();
  }

  public void togglePlay() {
    if (!isPlaying) {
      midi.view();
      this.isPlaying = true;
      //System.out.println(this.getIsPlaying());
    }
    else {
      midi.pause();
      this.isPlaying = false;
     // System.out.println(this.getIsPlaying());
    }

  }

  public void setListener(KeyListener listener) {
    this.gui.addKeyListener(listener);
  }

  public void refresh() {
    this.gui.refresh(this.midi.getLoc());
  }

  public void forward() {
    this.gui.forward();
  }

  public void backward() {
    this.gui.backward();
  }

  public void toEnd() {
    this.gui.toEnd();
  }

  public void toStart() {
    this.gui.toStart();
  }

  public boolean getIsPlaying() {
    return this.isPlaying;
  }
}
