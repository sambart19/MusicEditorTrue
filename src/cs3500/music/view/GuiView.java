package cs3500.music.view;

import java.awt.event.ActionListener;

/**
 * Interface for gui-only methods.
 */
public interface GuiView extends IView {

  /**
   * Allows the view to scroll forward
   */
  public void forward();

  /**
   * Allows the view to scroll backwards.
   */
  public void backward();

  /**
   * Jumps to the end of the view.
   */
  public void toEnd();

  /**
   * Jumps to the start of the view.
   */
  public void toStart();

  /**
   * Adds the given action listener to the add and remove buttons.
   * @param actionListener The action listener to add to the buttons.
   */
  public void addActionListener(ActionListener actionListener);

  /**
   * Method redraws the GuiPanel so that the red line is in the correct location
   * @param loc Current tick position of the sequencer.
   */
  public void refresh(long loc);

}
