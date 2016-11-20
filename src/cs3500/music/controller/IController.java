package cs3500.music.controller;

/**
 * A basic controller interface.
 */
public interface IController {

  /**
   * Sets up asynchronous controller.
   */
  public void play();

  public void removeNote();

  public void addNote();

  public void scroll();

  public void jumpEnd();

  public void jumpStart();
}
