package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.NoteName;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.provider.CompositeView;
import cs3500.music.provider.IMusicEditorModel;
import cs3500.music.provider.Note;

/**
 * Created by JakeCard on 12/3/2016.
 */
public class AdaptController implements IController, ActionListener {
  private IMusicModel model;
  private CompositeView view;
  private KeyHandler keys;

  public AdaptController(IMusicModel model) {
    this.model = model;
    ((MusicModel)this.model).update();
    this.keys = new KeyHandler();
    this.buildKeys();
    try {
      this.view = new CompositeView();
      this.view.setViewModel((IMusicEditorModel) this.model);
      this.view.addActionListener(this);
      this.view.addKeyListener(this.keys);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * Builds the keyHandler for this controller.
   */
  private void buildKeys() {
    Runnable space = () -> this.view.playOrPause();
    Runnable forward = () -> this.view.move("Right");
    Runnable backward = () -> this.view.move("Left");
    Runnable up = () -> this.view.move("Up");
    Runnable down = () -> this.view.move("Down");
    Runnable end = () -> this.view.jump("End");
    Runnable start = () -> this.view.jump("Beginning");
    this.keys.addPressed(32, space);
    this.keys.addPressed(37, backward);
    this.keys.addPressed(36, start);
    this.keys.addPressed(35, end);
    this.keys.addPressed(39, forward);
    this.keys.addPressed(38, up);
    this.keys.addPressed(40, down);
  }

  public void play() {
    this.view.display();
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Add Note":
          Note res = view.createNote(new StringReader(view.getText()));
          int octave = (res.trueValue() / 12) - 1;
          NoteName name = NoteName.values()[res.trueValue() % 12];
          model.addBeat(name, octave, res.getPosition(), res.getDuration(), res.getVolume(),
                  res.getInst());
        ((MusicModel)this.model).update();
        break;

      case "Remove Note":
          Note res1 = view.createNote(new StringReader(view.getText()));
          int octave1 = (res1.trueValue() / 12) - 1;
          NoteName name1 = NoteName.values()[res1.trueValue() % 12];
          model.removeBeat(name1, octave1, res1.getPosition());
        ((MusicModel)this.model).update();
        break;
      default: //Does nothing
    };
  }
}
