package cs3500.music.controller;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.view.CombinedView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IView;
import cs3500.music.view.MidiViewImpl;

/**
 * Controller for the music model.
 */
public class Controller implements IController{

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
    Runnable forward = () -> this.view.forward();
    Runnable backward = () -> this.view.backward();
    Runnable end = () -> this.view.toEnd();
    Runnable start = () -> this.view.toStart();
    this.keys.addPressed(32, space);
    this.keys.addPressed(37, backward);
    this.keys.addPressed(36, start);
    this.keys.addPressed(35, end);
    this.keys.addPressed(39, forward);
  }

  @Override
  public void play() {
    this.view.view();
    this.view.setListener(this.keys);
    while (true) {
      /**
       * This print is needed due to a possible bug. When the print isn't included the
       * if statement does not get evaluated.
       */
      System.out.print("");
     if (this.view.getIsPlaying()) {
        this.view.refresh();
      }
    }
  }

}
