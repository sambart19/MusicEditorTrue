package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles keypresses
 */
public class KeyHandler implements KeyListener{

  public Map<Integer, Runnable> typed = new HashMap<>();
  public Map<Integer, Runnable> pressed = new HashMap<>();
  public Map<Integer, Runnable> released = new HashMap<>();

  @Override
  public void keyPressed(KeyEvent e) {
    this.pressed.get(e.getKeyCode()).run();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.released.get(e.getKeyCode()).run();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    this.typed.get(e.getKeyCode()).run();
  }

  /**
   * Adds a new key map to the Pressed map.
   * @param i The key that will cause the event.
   * @param r The function to run.
   */
  public void addPressed(Integer i, Runnable r) {
    this.pressed.put(i, r);
  }

  /**
   * Adds a new key map to the Released map.
   * @param i The key that will cause the event.
   * @param r The function to run.
   */
  public void addReleased(Integer i, Runnable r) {
    this.released.put(i, r);
  }

  /**
   * Adds a new key map to the Typed map.
   * @param i The key that will cause the event.
   * @param r The function to run.
   */
  public void addTyped(Integer i, Runnable r) {
    this.typed.put(i, r);
  }

}
