package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.JScrollPane;

import cs3500.music.NoteColumn;

/**
 * A Frame in the window in which the GuiPanel is shown.
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {
  GuiPanel gpane;
  JScrollPane scrollPanel;
  /**
   * Creates new GuiView with a JScrollPane class around it in order to enable scrolling if needed.
   */
  public GuiViewFrame(List<NoteColumn> notes) {
    gpane = new GuiPanel(notes, 0);
    scrollPanel = new JScrollPane(gpane);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPanel);
    this.pack();
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Allows the window to be shown.
   */
  public void view() {
    this.setVisible(true);
  }

  /**
   * Allows the view to scroll forward
   */
  public void forward() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(Math.min(temp.getX() + 20, this.gpane.endpos()), temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  public void backward() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(Math.max(0, temp.getX() - 20), temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  public void toEnd() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(this.gpane.endpos(), temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  public void toStart() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(0, temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  /**
   * Method redraws the GuiPanel so that the red line is in the correct location
   * @param loc Current tick position of the sequencer.
   */
  public void refresh(long loc) {
    gpane.setLoc(loc);
    Dimension currDim = gpane.getSize();
    Point temp = scrollPanel.getViewport().getViewPosition();
      temp.setLocation(gpane.getLinePos(), temp.getY());
      scrollPanel.getViewport().setViewPosition(temp);
    gpane.revalidate();
    gpane.repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 1000);
  }

}
