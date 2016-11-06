package cs3500.music;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * A Mock receiver for testing.
 */
public class MockReceiver implements Receiver {
  public StringBuilder log;

  public MockReceiver(StringBuilder s) {
    this.log = s;
  }

  @Override
  public void send(MidiMessage s, long time) {
    byte[] b = s.getMessage();
    for (byte by : b) {
      this.log.append(Byte.toString(by) + " ");
    }
    this.log.append(Long.toString(time) + "\n");
  }

  @Override
  public void close() {
    //this is empty
  }
}
