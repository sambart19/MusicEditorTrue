package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.controller.IController;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicModelComposition;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MusicEditor {
  /**
   * This is the main method.
   * @param args The arguments, a string for file, and a string for view.
   * @throws IOException if no file is found.
   */
  public static void main(String[] args) throws IOException {
    String s = args[0];
    File f = new File(s);
    Readable file = new FileReader(f);
    CompositionBuilder comp = new MusicModelComposition();
    MusicModel m = (MusicModel) MusicReader.parseFile(file, comp);
    if (args[1].equals("combined")) {
      IController controller = new Controller(m);
      controller.play();
    } else {
      IView view = ViewFactory.makeView(args[1], m);
      view.view();
    }
  }
}
