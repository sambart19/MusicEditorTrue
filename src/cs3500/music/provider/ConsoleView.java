package cs3500.music.provider;

import java.io.IOException;

import cs3500.music.provider.IMusicEditorModel;

/**
 * A view that shows the notes of a song in the console.
 */
public class ConsoleView implements IView {
  private ViewModel model;
  private Appendable ap;
  
  /**
   * Constructs a standard console view that outputs to System.out.
   */
  ConsoleView() {
    this.ap = System.out;
  }

  public ConsoleView(ViewModel model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void setViewModel(IMusicEditorModel model) {
    this.model = new ViewModel(model);
  }

  @Override
  public void display() {
    try {
      ap.append(model.getState());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
