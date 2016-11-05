IMusicModel
- A basic interface for the model.
- includes four methods, addNote, addBeat, removeBeat, print
- addNote adds a full pitch, e.g A#3
- addBeat adds one note to the pitch indicated
- removeBeat removes one note from the pitch indicated
- print prints out the model

MusicModel
- The implementation of IMusicModel
- contains a List<Note>

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