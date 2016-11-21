package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.music.NoteColumn;
import cs3500.music.NoteName;
import cs3500.music.controller.ActionHandler;

/**
 * A Frame in the window in which the GuiPanel is shown.
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {
  private GuiPanel gpane;
  private JScrollPane scrollPanel;
  private JComboBox pitch;
  private JTextField octave;
  private JTextField duration;
  private JTextField location;
  private JTextField volume;
  private JTextField instrument;
  private JButton addNote;
  private JButton remNote;

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

  @Override
  public void forward() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(Math.min(temp.getX() + 20, this.gpane.endpos()), temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  @Override
  public void backward() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(Math.max(0, temp.getX() - 20), temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  @Override
  public void toEnd() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(this.gpane.endpos(), temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  @Override
  public void toStart() {
    Point temp = scrollPanel.getViewport().getViewPosition();
    temp.setLocation(0, temp.getY());
    scrollPanel.getViewport().setViewPosition(temp);
  }

  /**
   * A getter for the octave field.
   * @return The octave field.
   */
  public String getOctave() {
    String oct = octave.getText();
    octave.setText("");
    return oct;
  }

  /**
   * A getter for the duration field.
   * @return The duration field.
   */
  public String getDuration() {
    String dur = duration.getText();
    duration.setText("");
    return dur;
  }

  /**
   * A getter for the start location field.
   * @return The start location field.
   */
  public String getLoc() {
    String loc = location.getText();
    location.setText("");
    return loc;
  }

  /**
   * A getter for the note name field.
   * @return The notename field.
   */
  public NoteName getNoteName() {
    NoteName nname = (NoteName) pitch.getSelectedItem();
    return nname;
  }

  /**
   * A getter for the volume field.
   * @return The volume field.
   */
  public String getVolume() {
    String vol = volume.getText();
    volume.setText("");
    return vol;
  }

  /**
   * A getter for the instrument field.
   * @return The instrument field.
   */
  public String getInstrument() {
    String ins = instrument.getText();
    instrument.setText("");
    return ins;
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    addNote.addActionListener(actionListener);
    remNote.addActionListener(actionListener);
  }

  @Override
  public void refresh(long loc) {
    gpane.setLoc(loc);
    Dimension currDim = scrollPanel.getSize();
    Point temp = scrollPanel.getViewport().getViewPosition();
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

  /**
   * Updates the gpane with a new note column
   * @param notes
   */
  public void update(List<NoteColumn> notes) {
    this.gpane.update(notes);
  }


}
