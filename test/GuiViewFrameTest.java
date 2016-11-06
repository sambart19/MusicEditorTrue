import cs3500.music.NoteName;
import cs3500.music.model.MusicModel;
import cs3500.music.view.GuiViewFrame;

import static org.junit.Assert.*;

/**
 * Created by JakeCard on 11/5/2016.
 */
public class GuiViewFrameTest {


  public static void main(String[] args) {
    MusicModel a = new MusicModel();
    a.addBeat(NoteName.B, 1, 1, 10, 1, 1);
    a.addBeat(NoteName.A, 1, 5, 10, 1, 1);
    a.addBeat(NoteName.C, 3, 20, 30, 1, 1);
    a.addBeat(NoteName.A, 1, 100, 110, 1, 1);
    a.addBeat(NoteName.B, 1, 50, 70, 1, 1);
    a.addBeat(NoteName.D, 0, 1, 10, 1, 1);
    GuiViewFrame b = new GuiViewFrame(a.print());
    b.view();
  }
}