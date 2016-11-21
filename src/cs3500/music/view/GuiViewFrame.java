package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.music.NoteColumn;
import cs3500.music.NoteName;

/**
 * A Frame in the window in which the GuiPanel is shown.
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {
  GuiPanel gpane;
  JScrollPane scrollPanel;
  JComboBox pitch;
  JTextField octave;
  JTextField duration;
  JTextField location;
  JTextField volume;
  JTextField instrument;
  JButton addNote;
  JButton remNote;

  /**
   * Creates new GuiView with a JScrollPane class around it in order to enable scrolling if needed.
   */
  public GuiViewFrame(List<NoteColumn> notes) {
    //this.setLayout(new FlowLayout());

    //Add scrollable visual representation.
    gpane = new GuiPanel(notes, 0);
    gpane.setLayout(new BorderLayout());
    scrollPanel = new JScrollPane(gpane);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPanel, BorderLayout.CENTER);


    JPanel subpanel = new JPanel();

    //Add DropDown list to select the Pitch.
    NoteName[] pitchs = {NoteName.C, NoteName.CSHARP, NoteName.D, NoteName.DSHARP, NoteName.E,
            NoteName.F, NoteName.FSHARP, NoteName.G, NoteName.GSHARP, NoteName.A, NoteName.ASHARP,
            NoteName.B};
    pitch = new JComboBox(pitchs);
    subpanel.add(pitch);

    //Add TextField to input octave.
    octave = new JTextField(5);
    JLabel octaveLab = new JLabel("Enter octave");
    subpanel.add(octaveLab);
    subpanel.add(octave);

    //Add TextField to input duration.
    duration = new JTextField(5);
    JLabel durationLab = new JLabel("Enter duration");
    subpanel.add(durationLab);
    subpanel.add(duration);

    //Add TextField to input location.
    location = new JTextField(5);
    JLabel locationLab = new JLabel("Enter location");
    subpanel.add(locationLab);
    subpanel.add(location);

    //Add TextField to input volume
    volume = new JTextField(5);
    JLabel volLab = new JLabel("Enter volume");
    subpanel.add(volLab);
    subpanel.add(volume);

    //Add TextField to input instrument
    instrument = new JTextField(5);
    JLabel instrumentLab = new JLabel("Enter instrument");
    subpanel.add(instrumentLab);
    subpanel.add(instrument);

    //Add button to add note with specified information
    addNote = new JButton("Add note");
    addNote.setActionCommand("Add Note");
    subpanel.add(addNote);

    //Add button to remove note with specified information
    remNote = new JButton("Remove Note");
    remNote.setActionCommand("Remove Note");
    subpanel.add(remNote);

    this.getContentPane().add(subpanel, BorderLayout.SOUTH);
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

  public String getOctave() {
    String oct = octave.getText();
    octave.setText("");
    return oct;
  }

  public String getDuration() {
    String dur = duration.getText();
    duration.setText("");
    return dur;
  }

  public String getLoc() {
    String loc = location.getText();
    location.setText("");
    return loc;
  }

  public NoteName getNoteName() {
    NoteName nname = (NoteName) pitch.getSelectedItem();
    return nname;
  }

  public String getVolume() {
    String vol = volume.getText();
    volume.setText("");
    return vol;
  }

  public String getInstrument() {
    String ins = instrument.getText();
    instrument.setText("");
    return ins;
  }

  public void addActionListener(ActionListener actionListener) {
    addNote.addActionListener(actionListener);
    remNote.addActionListener(actionListener);
  }
  /**
   * Method redraws the GuiPanel so that the red line is in the correct location
   * @param loc Current tick position of the sequencer.
   */
  public void refresh(long loc) {
    gpane.setLoc(loc);
    Dimension currDim = scrollPanel.getSize();
    Point temp = scrollPanel.getViewport().getViewPosition();
    System.out.println(temp.getX() + " linepos: " + gpane.getLinePos()+ " currDim width: " + currDim.width);
    if (temp.getX() + currDim.width < gpane.getLinePos()) {
      temp.setLocation(gpane.getLinePos(), temp.getY());
      scrollPanel.getViewport().setViewPosition(temp);
    }
    gpane.revalidate();
    gpane.repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 1000);
  }



}
