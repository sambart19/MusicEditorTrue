/*import org.junit.Test;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.Note;
import cs3500.music.NoteName;

import static org.junit.Assert.*;

public class MusicModelTest {
  @Test (expected = IllegalArgumentException.class)
  public void NoteExcept1() {
    Note n = new Note(null, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void NoteExcept2() {
    Note n = new Note(NoteName.A, 2);
    n.addBeat(-1, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void NoteExcept3() {
    Note n = new Note(NoteName.A, 2);
    n.addBeat(3, -1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void NoteExcept4() {
    Note n = new Note(NoteName.A, 2);
    n.addBeat(3, 2);
    n.removeBeat(4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void NoteExcept5() {
    Note n = new Note(NoteName.A, 2);
    n.addBeat(3, 2);
    n.removeBeat(2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void NoteExcept6() {
    Note n = new Note(NoteName.A, 2);
    n.addBeat(3, 2);
    n.removeBeat(-1);
  }

  @Test
  public void MusicTest() {
    Note cSharp3 = new Note(NoteName.CSHARP, 3);
    cSharp3.addBeat(2, 4);
    Note f4 = new Note(NoteName.F, 4);
    f4.addBeat(4, 2);
    Note aSharp10 = new Note(NoteName.ASHARP, 10);
    aSharp10.addBeat(1, 1);
    aSharp10.addBeat(3, 3);
    aSharp10.addBeat(2, 1);
    IMusicModel model = new MusicModel();
    model.addNote(aSharp10);
    model.addNote(cSharp3);
    model.addNote(f4);
    assertEquals("  C#3   F4  A#10\n" +
            "0               \n" +
            "1            X  \n" +
            "2  X         X  \n" +
            "3  |         X  \n" +
            "4  |    X    |  \n" +
            "5  |    |    |  \n", model.print());
    model.addBeat(NoteName.CSHARP, 3, 15, 10);
    assertEquals("   C#3   F4  A#10\n" +
            " 0               \n" +
            " 1            X  \n" +
            " 2  X         X  \n" +
            " 3  |         X  \n" +
            " 4  |    X    |  \n" +
            " 5  |    |    |  \n" +
            " 6               \n" +
            " 7               \n" +
            " 8               \n" +
            " 9               \n" +
            "10               \n" +
            "11               \n" +
            "12               \n" +
            "13               \n" +
            "14               \n" +
            "15  X            \n" +
            "16  |            \n" +
            "17  |            \n" +
            "18  |            \n" +
            "19  |            \n" +
            "20  |            \n" +
            "21  |            \n" +
            "22  |            \n" +
            "23  |            \n" +
            "24  |            \n", model.print());
    model.addBeat(NoteName.FSHARP, 4, 7, 6);
    assertEquals("   C#3   F4  F#4  A#10\n" +
            " 0                    \n" +
            " 1                 X  \n" +
            " 2  X              X  \n" +
            " 3  |              X  \n" +
            " 4  |    X         |  \n" +
            " 5  |    |         |  \n" +
            " 6                    \n" +
            " 7            X       \n" +
            " 8            |       \n" +
            " 9            |       \n" +
            "10            |       \n" +
            "11            |       \n" +
            "12            |       \n" +
            "13                    \n" +
            "14                    \n" +
            "15  X                 \n" +
            "16  |                 \n" +
            "17  |                 \n" +
            "18  |                 \n" +
            "19  |                 \n" +
            "20  |                 \n" +
            "21  |                 \n" +
            "22  |                 \n" +
            "23  |                 \n" +
            "24  |                 \n", model.print());
    model.removeBeat(NoteName.CSHARP, 3, 2);
    assertEquals("   C#3   F4  F#4  A#10\n" +
            " 0                    \n" +
            " 1                 X  \n" +
            " 2                 X  \n" +
            " 3                 X  \n" +
            " 4       X         |  \n" +
            " 5       |         |  \n" +
            " 6                    \n" +
            " 7            X       \n" +
            " 8            |       \n" +
            " 9            |       \n" +
            "10            |       \n" +
            "11            |       \n" +
            "12            |       \n" +
            "13                    \n" +
            "14                    \n" +
            "15  X                 \n" +
            "16  |                 \n" +
            "17  |                 \n" +
            "18  |                 \n" +
            "19  |                 \n" +
            "20  |                 \n" +
            "21  |                 \n" +
            "22  |                 \n" +
            "23  |                 \n" +
            "24  |                 \n", model.print());
    model.removeBeat(NoteName.ASHARP, 10, 1);
    assertEquals("   C#3   F4  F#4  A#10\n" +
            " 0                    \n" +
            " 1                    \n" +
            " 2                 X  \n" +
            " 3                 X  \n" +
            " 4       X         |  \n" +
            " 5       |         |  \n" +
            " 6                    \n" +
            " 7            X       \n" +
            " 8            |       \n" +
            " 9            |       \n" +
            "10            |       \n" +
            "11            |       \n" +
            "12            |       \n" +
            "13                    \n" +
            "14                    \n" +
            "15  X                 \n" +
            "16  |                 \n" +
            "17  |                 \n" +
            "18  |                 \n" +
            "19  |                 \n" +
            "20  |                 \n" +
            "21  |                 \n" +
            "22  |                 \n" +
            "23  |                 \n" +
            "24  |                 \n", model.print());
    model.removeBeat(NoteName.A, 5, 3);
    assertEquals("   C#3   F4  F#4  A#10\n" +
            " 0                    \n" +
            " 1                    \n" +
            " 2                 X  \n" +
            " 3                 X  \n" +
            " 4       X         |  \n" +
            " 5       |         |  \n" +
            " 6                    \n" +
            " 7            X       \n" +
            " 8            |       \n" +
            " 9            |       \n" +
            "10            |       \n" +
            "11            |       \n" +
            "12            |       \n" +
            "13                    \n" +
            "14                    \n" +
            "15  X                 \n" +
            "16  |                 \n" +
            "17  |                 \n" +
            "18  |                 \n" +
            "19  |                 \n" +
            "20  |                 \n" +
            "21  |                 \n" +
            "22  |                 \n" +
            "23  |                 \n" +
            "24  |                 \n", model.print());
  }
}*/