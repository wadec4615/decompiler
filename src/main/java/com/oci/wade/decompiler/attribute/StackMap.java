package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class StackMap extends Attribute {
  private StackMapEntry[] map;

  public StackMap(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (StackMapEntry[]) null, constant_pool);
    int map_length = input.readUnsignedShort();
    map = new StackMapEntry[map_length];
    for (int i = 0; i < map_length; i++) {
      map[i] = new StackMapEntry(input, constant_pool);
    }
  }

  public StackMap(int name_index, int length, StackMapEntry[] map, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_STACK_MAP, name_index, length, constant_pool);
    this.map = map;
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public int getMapLength() {
    return map == null ? 0 : map.length;
  }

  public StackMapEntry[] getStackMap() {
    return map;
  }

  public void setStackMap(StackMapEntry[] map) {
    this.map = map;
    int len = 2;
    for (StackMapEntry element : map) {
      len += element.getMapEntrySize();
    }
    setLength(len);
  }
}
