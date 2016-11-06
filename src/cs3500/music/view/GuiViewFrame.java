package cs3500.music.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;

import cs3500.music.NoteColumn;

/**
 * A Frame in the window in which the GuiPanel is shown.
 */
public class GuiViewFrame extends javax.swing.JFrame implements IView {


  /**
   * Creates new GuiView with a JScrollPane class around it in order to enable scrolling if needed.
   */
  public GuiViewFrame(List<NoteColumn> notes) {
    JScrollPane scrollPanel = new JScrollPane(new GuiPanel(notes));
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPanel);
    this.pack();
  }

  /**
   * Allows the window to be shown.
   */
  public void view() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 1000);
  }

}
