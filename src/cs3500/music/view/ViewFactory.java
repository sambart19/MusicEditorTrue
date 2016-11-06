package cs3500.music.view;

import cs3500.music.model.IMusicModel;

/**
 * A factory for the carious view classes.
 */
public class ViewFactory {

  /**
   * A factory method for views.
   * @param view The string to convert into a view.
   * @param model The model to view.
   * @return The view of the given model.
   */
  public static IView makeView(String view, IMusicModel model) {
    switch (view) {
      case "midi" :
        return new MidiViewImpl(model.print(), model.getTempo());
      case "visual" :
        return new GuiViewFrame(model.print());
      case "console" :
        return new TextView(model.print());
      default : throw new IllegalArgumentException("Invalid view type");
    }
  }
}
