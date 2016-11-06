package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javax.swing.*;
import java.util.List;
import cs3500.music.NoteColumn;

/**
 * A Frame in the window in which the GuiPanel is shown.
 */
public class GuiViewFrame extends javax.swing.JFrame implements IView {

  private List<NoteColumn> notes;
  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView with a JScrollPane class around it in order to enable scrolling if needed.
   */
  public GuiViewFrame(List<NoteColumn> notes) {
    this.notes = notes;
    this.displayPanel = new GuiPanel(notes);
    JScrollPane scrollPanel = new JScrollPane(displayPanel);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPanel);
    this.pack();
  }

  /**
   * Allows the window to be shown.
   */
  public void view(){
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(1000, 1000);
  }

}
