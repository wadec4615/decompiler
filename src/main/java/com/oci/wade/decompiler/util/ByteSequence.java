package com.oci.wade.decompiler.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ByteSequence extends DataInputStream {
  private ByteArrayStream byteStream;

  public ByteSequence(byte[] bytes) {
    super(new ByteArrayStream(bytes));
    byteStream = (ByteArrayStream) in;
  }

  public int getIndex() {
    return byteStream.getPosition();
  }

  void unreadByte() {
    byteStream.unreadByte();
  }

  private static class ByteArrayStream extends ByteArrayInputStream {
    public ByteArrayStream(byte[] bytes) {
      super(bytes);
    }

    int getPosition() {
      return pos;
    }

    void unreadByte() {
      if (pos > 0) {
        pos--;
      }
    }
  }
}
