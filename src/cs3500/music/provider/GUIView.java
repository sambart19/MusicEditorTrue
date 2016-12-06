package cs3500.music.provider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import cs3500.music.provider.IMusicEditorModel;
import cs3500.music.provider.Note;
import cs3500.music.provider.Pitch;

/**
 * Creates a visual view of the song within the model.
 */
public class GUIView extends JFrame implements IGUIView {
  protected ViewModel model;
  private JPanel panel;
  private JFrame addAndRemoveFrame;
  private JTextField input;
  private JButton addButton;
  private JButton removeButton;
  JScrollPane scroll;
  long currentBeat;

  public static final int CELL_SIZE = 20;

  GUIView() {
    super();
    this.createAddAndRemoveFrame();
  }

  @Override
  public void resetPanel(int x, int y) {
    panel.setPreferredSize(new Dimension(x, y));
    panel.revalidate();
    panel.repaint();
  }

  @Override
  public Note createNote(Readable rd) {
    Scanner scan = new Scanner(rd);
    Pitch pitch = null;
    Integer octave = null;
    Integer duration = null;
    Integer position = null;

    // Assigns the various parts of the note according to the input
    while (scan.hasNext()) {
      if (pitch == null) {
        pitch = Pitch.stringToPitch(scan.next());
      }
      if (octave == null) {
        octave = scan.nextInt();
      }
      if (duration == null) {
        duration = scan.nextInt();
      }
      if (position == null) {
        position = scan.nextInt();
        break;
      }
    }

    // Returns the constructed note
    return new Note(pitch, octave, duration, position);
  }

  @Override
  public void setAddAndRemoveFrameVisible() {
    addAndRemoveFrame.setVisible(true);
  }

  @Override
  public String getText() {
    return input.getText();
  }

  @Override
  public void clearTextArea() {
    input.setText("");
  }

  @Override
  public void playOrPause() {
    // Music will not be playing in the GUIView.
  }

  @Override
  public void move(String direction) {
    int hViewPosition = scroll.getHorizontalScrollBar().getValue();
    int hMin = scroll.getHorizontalScrollBar().getMinimum();
    int hMax = scroll.getHorizontalScrollBar().getMaximum();

    int vViewPosition = scroll.getVerticalScrollBar().getValue();
    int vMin = scroll.getVerticalScrollBar().getMinimum();
    int vMax = scroll.getVerticalScrollBar().getMaximum();

    // Sets the scroll bar position to the left
    if (direction.equals("Left")) {
      if (scroll.getHorizontalScrollBar().getValue() <= hMin + CELL_SIZE) {
        scroll.getHorizontalScrollBar().setValue(hMin);
      } else {
        scroll.getHorizontalScrollBar().setValue(hViewPosition - CELL_SIZE);
      }
    }

    // Sets the scroll bar position to the right
    if (direction.equals("Right")) {
      if (scroll.getHorizontalScrollBar().getValue() >= hMax - CELL_SIZE) {
        scroll.getHorizontalScrollBar().setValue(hMax);
      } else {
        scroll.getHorizontalScrollBar().setValue(hViewPosition + CELL_SIZE);
      }
    }

    // Sets the scroll bar position upward
    if (direction.equals("Up")) {
      if (scroll.getVerticalScrollBar().getValue() <= vMin + CELL_SIZE) {
        scroll.getVerticalScrollBar().setValue(vMin);
      } else {
        scroll.getVerticalScrollBar().setValue(vViewPosition - CELL_SIZE);
      }
    }

    // Sets the scroll bar position downward
    if (direction.equals("Down")) {
      if (scroll.getVerticalScrollBar().getValue() >= vMax - CELL_SIZE) {
        scroll.getVerticalScrollBar().setValue(vMax);
      } else {
        scroll.getVerticalScrollBar().setValue(vViewPosition + CELL_SIZE);
      }
    }
  }

  @Override
  public void jump(String position) {
    int min = scroll.getHorizontalScrollBar().getMinimum();
    int max = scroll.getHorizontalScrollBar().getMaximum();

    if (position.equals("Beginning")) {
      scroll.getHorizontalScrollBar().setValue(min);
    }
    if (position.equals("End")) {
      scroll.getHorizontalScrollBar().setValue(max);
    }
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
    removeButton.addActionListener(actionListener);
  }

  @Override
  public void setViewModel(IMusicEditorModel model) {
    this.model = new ViewModel(model);
  }

  @Override
  public void display() {
    // Initializes the frame
    this.setTitle("Music Editor");
    this.setSize(new Dimension(1536, 864));
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Initializes the panel
    this.panel = new MyPanel();
    this.scroll = new JScrollPane(panel);
    this.panel.setPreferredSize(new Dimension(80 + model.length() * CELL_SIZE,
            80 + model.range().size() * CELL_SIZE));
    this.add(scroll, BorderLayout.CENTER);

    // Appropriately sizes the frame and makes it visible
    this.pack();
    this.setVisible(true);
  }

  private void createAddAndRemoveFrame() {
    // Initializes the adding frame
    addAndRemoveFrame = new JFrame();
    addAndRemoveFrame.setTitle("Add/Remove");
    addAndRemoveFrame.setSize(300, 100);
    addAndRemoveFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    addAndRemoveFrame.setLayout(new FlowLayout());

    // Adds the text field
    input = new JTextField(10);
    addAndRemoveFrame.add(input);

    // Adds the adding button
    addButton = new JButton("Add");
    addButton.setActionCommand("Add Note");
    addAndRemoveFrame.add(addButton);

    // Adds the removing button
    removeButton = new JButton("Remove");
    removeButton.setActionCommand("Remove Note");
    addAndRemoveFrame.add(removeButton);
  }


  class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Draws the pitch for each row
      this.drawPitches(g);

      // Draws the rectangles for each measure in each row
      this.drawMeasures(g);

      // Draws the squares for each note
      this.drawNotes(g);

      //Draws the red line of the current beat
      this.drawRedLine(g);
    }

    private void drawPitches(Graphics g) {
      int rowPosition = 40 - CELL_SIZE * 3 / 8;
      ArrayList<Note> range = model.range();
      Collections.reverse(range);

      for (Note n : range) {
        g.drawString(n.toString(), 0, rowPosition);
        rowPosition = rowPosition + CELL_SIZE;
      }
    }

    private void drawMeasures(Graphics g) {
      g.setColor(Color.BLACK);
      for (Note n : model.range()) {
        int y = n.trueValue() - model.lowestNote().trueValue();
        for (int x = 0; x < model.length(); x++) {

          // Draws the measure references every four measures
          if (x % 16 == 0) {
            g.drawString(Integer.toString(x), 40 + CELL_SIZE * x, 20);
          }

          // Draws the rectangles
          if (x % 4 == 0) {
            g.drawRect(40 + CELL_SIZE * x, 40 + CELL_SIZE * (y - 1), CELL_SIZE * 4, CELL_SIZE);
          }
        }
      }
    }


    private void drawNotes(Graphics g) {
      for (Note n : model.range()) {
        int y = model.highestNote().trueValue() - n.trueValue();
        for (Note m : model.getNotes()) {
          // Draws a solid square if there is a note in the beat
          if (n.trueValue() == m.trueValue()) {
            g.setColor(Color.RED);
            g.fillRect(40 + CELL_SIZE * m.getPosition(), 40 + CELL_SIZE * (y - 1),
                    CELL_SIZE, CELL_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(40 + CELL_SIZE * m.getPosition(), 40 + CELL_SIZE * (y - 1),
                    CELL_SIZE, CELL_SIZE);

            // Draws the extended notes for the given note's duration
            for (int i = 1; i < m.getDuration(); i++) {
              g.setColor(Color.GREEN);
              g.fillRect(40 + CELL_SIZE * (m.getPosition() + i), 40 + CELL_SIZE * (y - 1),
                      CELL_SIZE, CELL_SIZE);
            }
          }
        }
      }
    }

    private void drawRedLine(Graphics g) {
      g.setColor(Color.RED);
      g.fillRect(40 + CELL_SIZE * (int) currentBeat, CELL_SIZE,
              3, model.range().size() * CELL_SIZE);
    }
  }
}