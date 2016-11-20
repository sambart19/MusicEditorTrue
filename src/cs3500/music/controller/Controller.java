package cs3500.music.controller;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.view.IView;

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
   * @param view The view to display the model in.
   */
  public Controller(IMusicModel model) {
    this.model = model;
    this.view = new CombinedView(model);
    this.keys = new KeyHandler();
    this.buildKeys();
  }

  /**
   * Builds the keyHandler for this controller.
   */
  private void buildKeys() {
    Runnable space = () -> this.view.space();
    Runnable forward = () -> this.view.foreward();
    Runnable backward = () -> this.view.backward();
    Runnable end = () -> this.view.toEnd();
    Runnable start = () -> this.view.toStart();

    this.keys.addTyped(32, space);
    this.keys.addPressed(36, start);
    this.keys.addPressed(35, end);
    this.keys.addPressed(39, forward);
    this.keys.addPressed(37, backward);
  }

  @Override
  public void play() {

  }

}
