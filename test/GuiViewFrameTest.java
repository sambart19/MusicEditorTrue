import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicModelComposition;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.TextView;

/**
 * Created by JakeCard on 11/5/2016.
 */
public class GuiViewFrameTest {


  public static void main(String[] args) {
    CompositionBuilder comp = new MusicModelComposition();

    File f = new File("df-ttfaf.txt");
    try {
      Readable file = new FileReader(f);
      MusicModel m = (MusicModel)MusicReader.parseFile(file, comp);
      MidiViewImpl midi = new MidiViewImpl(m.print(), m.getTempo());
      //GuiViewFrame b = new GuiViewFrame(m.print());
      //TextView t = new TextView(m.print());
      //t.view();
      //b.view();
      midi.view();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}