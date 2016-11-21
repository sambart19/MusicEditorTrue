package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.music.model.IMusicModel;
import cs3500.music.view.CombinedView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

/**
 * Controller for the music model.
 */
public class Controller implements IController, ActionListener{

  private IMusicModel model;
  private CombinedView view;
  private KeyHandler keys;

  /**
   * Basic constructor for this controller.
   * @param model The model to control.
   */
  public Controller(IMusicModel model) {
    this.model = model;
    this.view = new CombinedView(new GuiViewFrame(this.model.print()),
            new MidiViewImpl(this.model.print(), this.model.getTempo()));
    this.keys = new KeyHandler();
    this.buildKeys();
  }

  /**
   * Builds the keyHandler for this controller.
   */
  private void buildKeys() {
    Runnable space = () -> this.view.togglePlay();
    Runnable forward = () -> this.view.parse("forward");
    Runnable backward = () -> this.view.parse("backward");
    Runnable end = () -> this.view.parse("to end");
    Runnable start = () -> this.view.parse("to start");
    this.keys.addPressed(32, space);
    this.keys.addPressed(37, backward);
    this.keys.addPressed(36, start);
    this.keys.addPressed(35, end);
    this.keys.addPressed(39, forward);
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Add Note":
        try {
          model.addBeat(view.guiNoteName(), Integer.parseInt(view.guiAccess("octave")),
                  Integer.parseInt(view.guiAccess("location")),
                  Integer.parseInt(view.guiAccess("duration")),
                  Integer.parseInt(view.guiAccess("volume")),
                  Integer.parseInt(view.guiAccess("instrument")));
        } catch (NumberFormatException f) {
          // Do nothing
        }
        break;

      case "Remove Note":
        try {
          model.removeBeat(view.guiNoteName(), Integer.parseInt(view.guiAccess("octave")),
                  Integer.parseInt(view.guiAccess("location")));
        } catch (NumberFormatException f) {
          // Do nothing
        }
        break;
      default: //Does nothing
    }
    this.view.parse("rebuild");
    view.resetFocus();
  }

  @Override
  public void play() {
    this.view.view();
    this.view.setListeners(this.keys, this);
    while (true) {
      /**
       * This print is needed due to a possible bug in java. When the print isn't included the
       * if statement does not get evaluated.
       */
      System.out.print("");
     if (this.view.getIsPlaying()) {
        this.view.parse("refresh");
      }
    }
  }

}
