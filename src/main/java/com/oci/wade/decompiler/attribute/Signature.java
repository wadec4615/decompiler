package com.oci.wade.decompiler.attribute;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class Signature extends Attribute {
  private static class MyByteArrayInputStream extends ByteArrayInputStream {
    public MyByteArrayInputStream(String data) {
      super(data.getBytes());
    }

    String getData() {
      return new String(buf);
    }

    void unread() {
      if (pos > 0) {
        pos--;
      }
    }
  }
  private int signature_index;

  public Signature(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, input.readUnsignedShort(), constant_pool);
  }

  public Signature(int name_index, int length, int signature_index, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_SIGNATURE, name_index, length, constant_pool);
    this.signature_index = signature_index;
  }

  public Signature(Signature c) {
    this(c.getNameIndex(), c.getLength(), c.getSignatureIndex(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public String getSignature() {
    ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(signature_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getSignatureIndex() {
    return signature_index;
  }

  public void setSignatureIndex(int signature_index) {
    this.signature_index = signature_index;
  }

  @Override
  public final String toString() {
    final String s = getSignature();
    return "  Signature: " + s + "\n";
  }

  public static boolean isActualParameterList(String s) {
    return s.startsWith("L") && s.endsWith(">;");
  }

  public static boolean isFormalParameterList(String s) {
    return s.startsWith("<") && (s.indexOf(':') > 0);
  }

  public static String translate(String s) {
    StringBuilder buf = new StringBuilder();
    matchGJIdent(new MyByteArrayInputStream(s), buf);
    return buf.toString();
  }

  private static boolean identStart(int ch) {
    return (ch == 'T') || (ch == 'L');
  }

  private static void matchGJIdent(MyByteArrayInputStream in, StringBuilder buf) {
    int ch;
    matchIdent(in, buf);
    ch = in.read();
    if ((ch == '<') || (ch == '(')) {
      buf.append((char) ch);
      matchGJIdent(in, buf);
      while (((ch = in.read()) != '>') && (ch != ')')) {
        if (ch == -1) {
          throw new RuntimeException("Illegal signature: " + in.getData() + " reaching EOF");
        }
        buf.append(", ");
        in.unread();
        matchGJIdent(in, buf);
      }
      buf.append((char) ch);
    } else {
      in.unread();
    }
    ch = in.read();
    if (identStart(ch)) {
      in.unread();
      matchGJIdent(in, buf);
    } else if (ch == ')') {
      in.unread();
      return;
    } else if (ch != ';') {
      throw new RuntimeException("Illegal signature: " + in.getData() + " read " + (char) ch);
    }
  }

  private static void matchIdent(MyByteArrayInputStream in, StringBuilder buf) {
    int ch;
    if ((ch = in.read()) == -1) {
      throw new RuntimeException("Illegal signature: " + in.getData() + " no ident, reaching EOF");
    }
    if (!identStart(ch)) {
      StringBuilder buf2 = new StringBuilder();
      int count = 1;
      while (Character.isJavaIdentifierPart((char) ch)) {
        buf2.append((char) ch);
        count++;
        ch = in.read();
      }
      if (ch == ':') {
        in.skip("Ljava/lang/Object".length());
        buf.append(buf2);
        ch = in.read();
        in.unread();
      } else {
        for (int i = 0; i < count; i++) {
          in.unread();
        }
      }
      return;
    }
    StringBuilder buf2 = new StringBuilder();
    ch = in.read();
    do {
      buf2.append((char) ch);
      ch = in.read();
    } while ((ch != -1) && (Character.isJavaIdentifierPart((char) ch) || (ch == '/')));
    buf.append(buf2.toString().replace('/', '.'));
    if (ch != -1) {
      in.unread();
    }
  }
}
