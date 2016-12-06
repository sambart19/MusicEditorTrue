package cs3500.music.provider;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.provider.Note;

/**
 * Represents a general GUI view for a music player.
 */
public interface IGUIView extends IView {

  /**
   * Resets the panel for the view.
   *
   * @param x The width of the view.
   * @param y The height of the view.
   */
  void resetPanel(int x, int y);

  /**
   * Creates a note from a readable.
   *
   * @param rd The readable that the user can enter to create a note.
   * @return A created note.
   */
  Note createNote(Readable rd);

  /**
   * Sets the pop-up frame that allows the user to add and remove notes visible.
   */
  void setAddAndRemoveFrameVisible();

  /**'
   * Gets the text of the user input.
   *
   * @return The text.
   */
  String getText();

  /**
   * Clears the text of the user input.
   */
  void clearTextArea();

  /**
   * Plays or pauses depending on the state of the song (this only applies to CompositeView).
   */
  void playOrPause();

  /**
   * Moves the current view one beat in the given direction.
   *
   * @param direction The direction the view will move in.
   */
  void move(String direction);

  /**
   * Jumps to the beginning or the end of the piece.
   *
   * @param position Either the beginning or end.
   */
  void jump(String position);

  /**
   * Adds an action listener to the view.
   */
  void addActionListener(ActionListener actionListener);

  /**
   * Adds a key listener to the view.
   * This is inherited by the Swing-based implementation and is added to this interface
   * for controller access.
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds a mouse listener to the view.
   * This is inherited by the Swing-based implementation and is added to this interface
   * for controller access.
   */
  void addMouseListener(MouseListener listener);
}
