package cs3500.music.util;

import cs3500.music.NoteName;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
//
/**
 * The implementation of Composition Builder for Music Model.
 */
public class MusicModelComposition implements CompositionBuilder<IMusicModel> {

  private IMusicModel model;

  /**
   * A basic constructor.
   */
  public MusicModelComposition() {
    this.model = new MusicModel();
  }

  @Override
  public CompositionBuilder<IMusicModel> setTempo(int tempo) {
    this.model.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<IMusicModel> addNote(int start, int end, int instrument,
                                                int pitch, int volume) {
    int octave = (pitch / 12) - 1;
    NoteName name = NoteName.values()[pitch % 12];
    this.model.addBeat(name, octave, start, (end - start), volume, instrument);
    return this;
  }

  @Override
  public IMusicModel build() {
    return this.model;
  }

}
