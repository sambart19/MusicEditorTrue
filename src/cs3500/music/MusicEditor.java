package cs3500.music;

import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicModelComposition;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    String s = args[0];
    File f = new File(s);
    Readable file = new FileReader(f);
    CompositionBuilder comp = new MusicModelComposition();
    MusicModel m = (MusicModel) MusicReader.parseFile(file, comp);
    IView view  = ViewFactory.makeView(args[1], m);
    view.view();
  }
}
