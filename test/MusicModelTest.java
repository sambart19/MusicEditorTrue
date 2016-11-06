import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.NoteColumn;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.Note;
import cs3500.music.NoteName;

import static org.junit.Assert.*;

public class MusicModelTest {

  @Test
  public void EmptyComposition() {
    MusicModel model = new MusicModel();
    assertEquals(model.print(), new ArrayList<>());
  }

  @Test
  public void addBeat1() {
    MusicModel model = new MusicModel();
    model.addBeat(NoteName.A, 1, 5, 5, 60, 2);
    model.addBeat(NoteName.C, 2, 20, 3, 60, 1);
    assertEquals(model.print().size() > 2, true); //Checks that at least 2 note columns were created
  }

  @Test
  public void testSim1() {
    MusicModel model = new MusicModel();
    model.addBeat(NoteName.A, 1, 5, 1, 60, 2);
    model.addBeat(NoteName.ASHARP, 1, 5, 5, 60, 2);

    List<NoteColumn> a = model.print();
    assertEquals(a.get(0).getBeats().size(), 1);
    assertEquals(a.get(1).getBeats().size(), 5);
  }

  @Test
  public void testSameNote1() {
    MusicModel model = new MusicModel();
    model.addBeat(NoteName.A, 1, 5, 5, 60, 1);
    model.addBeat(NoteName.A, 1, 7, 3, 80, 2);
    // adding notes that overlap each other should
    // cause the first note to be overwritten by other note
    List<NoteColumn> a = model.print();
    assertEquals(a.get(0).getBeats().size(), 5);
    assertEquals(a.get(0).getBeats().get(5).getVolume(), 60);
    assertEquals(a.get(0).getBeats().get(7).getVolume(), 80);
    assertEquals(a.get(0).getBeats().get(5).getHead(), true);
    assertEquals(a.get(0).getBeats().get(6).getHead(), false);
    assertEquals(a.get(0).getBeats().get(7).getHead(), true);
  }


}