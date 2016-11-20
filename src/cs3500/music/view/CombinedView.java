package cs3500.music.view;

/**
 * A view that combines gui and midi.
 */
public class CombinedView implements GuiView{
  private GuiViewFrame gui;
  private MidiViewImpl midi;

  public CombinedView(GuiViewFrame gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
  }

  public void view() {

    gui.view(midi.getLine());

  }
}
