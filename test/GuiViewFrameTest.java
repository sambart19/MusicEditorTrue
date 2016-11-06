import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.NoteName;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicModelComposition;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

/**
 * Created by JakeCard on 11/5/2016.
 */
public class GuiViewFrameTest {


  public static void main(String[] args) {
    CompositionBuilder comp = new MusicModelComposition();
    /**File mary = new File("Mary.txt");
    try {
      Readable file = new FileReader(mary);
      MusicModel m = (MusicModel)MusicReader.parseFile(file, comp);
      MidiViewImpl midi = new MidiViewImpl(m.print(), m.getTempo());
      GuiViewFrame b = new GuiViewFrame(m.print());
      b.view();
      try {
        midi.view();
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }*/

    File luck = new File("zoot-lw.txt");
    try {
      Readable file = new FileReader(luck);
      MusicModel m = (MusicModel)MusicReader.parseFile(file, comp);
      MidiViewImpl midi = new MidiViewImpl(m.print(), m.getTempo());
      GuiViewFrame b = new GuiViewFrame(m.print());
      b.view();
      midi.view();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}