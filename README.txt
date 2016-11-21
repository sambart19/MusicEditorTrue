IMusicModel
- A basic interface for the model.
- includes four methods, addNote, addBeat, removeBeat, print
- addNote adds a full pitch, e.g A#3
- addBeat adds one note to the pitch indicated
- removeBeat removes one note from the pitch indicated
- print prints out the model

MusicModel
- The implementation of IMusicModel
- contains a List<NoteColumn>
    Design change now has the model represented by a list of NoteColumns


NoteColumn
- 3 fields
- NoteName represents the pitch
- octave represents the octave
- HashMap<Integer, Note>
- By having the notename and octave here, indivudal notes dont have fields for notenames or
octaves. This design makes it easier to represent the range of octaves a piece of music
encompasses.

Note
- Represents a whole pitch e.g. A#3
- has a NoteName, a int for the octave, and an ArrayList<String> to represent the beats
- includes four methods, addBeat, removeBeat, toString, and CompareTo
- addBeat adds a single note to this pitch
- removeBeat removes a note from this pitch
- toString converts this pitch to a string
- compareTo is used to compare notes, for Collections.sort

NoteName
- Enumeration for note
- does not contain an octave

IView
- Has one method view which is called to show the view implementation

GuiPanel
- Represents a JPanel that has the GUIView as described in the assignment drawn onto it

GuiViewFrame
- Represents a JFrame that has the GuiPanel wrapped by a JScrollPane (to allow scrolling)

TextView
- Pretty much the same as the previous model.print() method, represents the model as a consoleview

MidiViewImpl
- Represents the model in actual sound using MIDI library

ViewFactory
- Takes in a string and returns the corresponding IView.

MockReciever
- used for testing

MockSynth
- used for testing

MusicEditor
- The main jar file that runs the program with two arguments, music file and view type.

Controller
- Takes in a view and a model, as well as creating its own keyhandler events. The only keystrokes
handled right now are home/end to jump to the start/end of a composition, space to pause/play, and
arrow keys to scroll through the gui portion. The Controller adds a keylistener as well as an
action listener to the view.

KeyHandler
- A class that implements keylistener, binds actions to key events through the use of three maps.
Provides methods for adding bindings to maps.

CombinedView
- A class that combines MidiViewImpl as well as GuiFrameView. It allows users to interact with
keyboard or buttons and text fields. Users can add/remove notes as well as pause the composition
or scroll through the gui.


MidiViewImpl
 - Changed the way it plays sound, instead of using sending messages to a MIDI reciever it now
 uses a Sequence and a sequencer.
 - Added method to pause playback/resume playback.
 - Added method to retrieve current tick position. This is used to sync up other views.

 GuiViewFrame
 - Added methods to scroll through the view.
 - Added methods to paint a red line based on tick position.
 - Added method to set listeners.
 - Added method to refresh its drawn components.

 GuiPanel
 - Changed the jframe layout.
 - Added several labels, text fields, and buttons to correspond with user input.
