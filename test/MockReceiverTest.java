import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.NoteName;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicModelComposition;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;
/**
public class MockReceiverTest {

  @Test
  public void Test1() {
    IMusicModel model = new MusicModel();
    StringBuilder s = new StringBuilder();
    IView m = new MidiViewImpl(model.print(), model.getTempo(), s);
    m.view();
    assertEquals("", s.toString());
  }

  @Test
  public void Test2() {
    IMusicModel model = new MusicModel();
    model.setTempo(1);

    model.addBeat(NoteName.CSHARP, 4, 3, 10, 60, 1);

    StringBuilder s = new StringBuilder();
    IView m = new MidiViewImpl(model.print(), model.getTempo(), s);
    m.view();
    assertEquals("-112 49 60 1000003\n" +
            "-128 49 60 1000013\n", s.toString());
  }

  @Test
  public void Test3() {
    CompositionBuilder comp = new MusicModelComposition();

    File f = new File("Mary.txt");
    try {
      Readable file = new FileReader(f);
      MusicModel m = (MusicModel) MusicReader.parseFile(file, comp);
      StringBuilder s = new StringBuilder();
      IView v = new MidiViewImpl(m.print(), m.getTempo(), s);
      v.view();
      assertEquals("-112 40 72 12200000\n" +
              "-128 40 72 13800000\n" +
              "-112 43 70 1000000\n" +
              "-128 43 70 2400000\n" +
              "-112 43 79 2600000\n" +
              "-128 43 79 4000000\n" +
              "-112 43 77 4200000\n" +
              "-128 43 77 5800000\n" +
              "-112 43 79 5800000\n" +
              "-128 43 79 6200000\n" +
              "-112 43 78 7400000\n" +
              "-128 43 78 9000000\n" +
              "-112 43 79 9000000\n" +
              "-128 43 79 10600000\n" +
              "-112 43 78 10600000\n" +
              "-128 43 78 12200000\n" +
              "-112 48 71 1800000\n" +
              "-128 48 71 2200000\n" +
              "-112 48 71 8200000\n" +
              "-128 48 71 8600000\n" +
              "-112 48 73 12200000\n" +
              "-128 48 73 13800000\n" +
              "-112 50 72 1400000\n" +
              "-128 50 72 1800000\n" +
              "-112 50 69 7800000\n" +
              "-128 50 69 8200000\n" +
              "-112 50 79 2200000\n" +
              "-128 50 79 2600000\n" +
              "-112 50 80 8600000\n" +
              "-128 50 80 9000000\n" +
              "-112 50 75 4200000\n" +
              "-128 50 75 4600000\n" +
              "-112 50 75 10600000\n" +
              "-128 50 75 11000000\n" +
              "-112 50 77 4600000\n" +
              "-128 50 77 5000000\n" +
              "-112 50 74 11000000\n" +
              "-128 50 74 11400000\n" +
              "-112 50 75 5000000\n" +
              "-128 50 75 5800000\n" +
              "-112 50 70 11800000\n" +
              "-128 50 70 12200000\n" +
              "-112 52 72 1000000\n" +
              "-128 52 72 1400000\n" +
              "-112 52 73 7400000\n" +
              "-128 52 73 7800000\n" +
              "-112 52 85 2600000\n" +
              "-128 52 85 3000000\n" +
              "-112 52 84 9000000\n" +
              "-128 52 84 9400000\n" +
              "-112 52 78 3000000\n" +
              "-128 52 78 3400000\n" +
              "-112 52 76 9400000\n" +
              "-128 52 76 9800000\n" +
              "-112 52 74 3400000\n" +
              "-128 52 74 4000000\n" +
              "-112 52 74 9800000\n" +
              "-128 52 74 10200000\n" +
              "-112 52 77 10200000\n" +
              "-128 52 77 10600000\n" +
              "-112 52 81 11400000\n" +
              "-128 52 81 11800000\n" +
              "-112 52 82 5800000\n" +
              "-128 52 82 6200000\n" +
              "-112 55 84 6200000\n" +
              "-128 55 84 6600000\n" +
              "-112 55 75 6600000\n" +
              "-128 55 75 7400000\n", s.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}
*/