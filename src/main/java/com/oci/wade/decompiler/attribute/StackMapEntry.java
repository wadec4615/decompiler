package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.Const;
import com.oci.wade.decompiler.exception.ClassFormatException;

public class StackMapEntry implements Cloneable {
  private int frame_type;
  private int byte_code_offset;
  private StackMapType[] types_of_locals;
  private StackMapType[] types_of_stack_items;
  private ConstantPool constant_pool;

  public StackMapEntry(DataInput input, ConstantPool constant_pool) throws IOException {
    this(input.readByte() & 0xFF, -1, null, null, constant_pool);
    if (frame_type >= Const.SAME_FRAME && frame_type <= Const.SAME_FRAME_MAX) {
      byte_code_offset = frame_type - Const.SAME_FRAME;
    } else if (frame_type >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frame_type <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
      byte_code_offset = frame_type - Const.SAME_LOCALS_1_STACK_ITEM_FRAME;
      types_of_stack_items = new StackMapType[1];
      types_of_stack_items[0] = new StackMapType(input, constant_pool);
    } else if (frame_type == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
      byte_code_offset = input.readShort();
      types_of_stack_items = new StackMapType[1];
      types_of_stack_items[0] = new StackMapType(input, constant_pool);
    } else if (frame_type >= Const.CHOP_FRAME && frame_type <= Const.CHOP_FRAME_MAX) {
      byte_code_offset = input.readShort();
    } else if (frame_type == Const.SAME_FRAME_EXTENDED) {
      byte_code_offset = input.readShort();
    } else if (frame_type >= Const.APPEND_FRAME && frame_type <= Const.APPEND_FRAME_MAX) {
      byte_code_offset = input.readShort();
      int number_of_locals = frame_type - 251;
      types_of_locals = new StackMapType[number_of_locals];
      for (int i = 0; i < number_of_locals; i++) {
        types_of_locals[i] = new StackMapType(input, constant_pool);
      }
    } else if (frame_type == Const.FULL_FRAME) {
      byte_code_offset = input.readShort();
      int number_of_locals = input.readShort();
      types_of_locals = new StackMapType[number_of_locals];
      for (int i = 0; i < number_of_locals; i++) {
        types_of_locals[i] = new StackMapType(input, constant_pool);
      }
      int number_of_stack_items = input.readShort();
      types_of_stack_items = new StackMapType[number_of_stack_items];
      for (int i = 0; i < number_of_stack_items; i++) {
        types_of_stack_items[i] = new StackMapType(input, constant_pool);
      }
    } else {
      throw new ClassFormatException("Invalid frame type found while parsing stack map table: " + frame_type);
    }
  }

  public StackMapEntry(int byte_code_offset, int number_of_locals, StackMapType[] types_of_locals, int number_of_stack_items, StackMapType[] types_of_stack_items, ConstantPool constant_pool) {
    this.byte_code_offset = byte_code_offset;
    this.types_of_locals = types_of_locals != null ? types_of_locals : new StackMapType[0];
    this.types_of_stack_items = types_of_stack_items != null ? types_of_stack_items : new StackMapType[0];
    this.constant_pool = constant_pool;
  }

  public StackMapEntry(int tag, int byte_code_offset, StackMapType[] types_of_locals, StackMapType[] types_of_stack_items, ConstantPool constant_pool) {
    this.frame_type = tag;
    this.byte_code_offset = byte_code_offset;
    this.types_of_locals = types_of_locals != null ? types_of_locals : new StackMapType[0];
    this.types_of_stack_items = types_of_stack_items != null ? types_of_stack_items : new StackMapType[0];
    this.constant_pool = constant_pool;
  }

  public StackMapEntry copy() {
    StackMapEntry e;
    try {
      e = (StackMapEntry) clone();
    } catch (CloneNotSupportedException ex) {
      throw new Error("Clone Not Supported");
    }
    e.types_of_locals = new StackMapType[types_of_locals.length];
    for (int i = 0; i < types_of_locals.length; i++) {
      e.types_of_locals[i] = types_of_locals[i].copy();
    }
    e.types_of_stack_items = new StackMapType[types_of_stack_items.length];
    for (int i = 0; i < types_of_stack_items.length; i++) {
      e.types_of_stack_items[i] = types_of_stack_items[i].copy();
    }
    return e;
  }

  public int getByteCodeOffset() {
    return byte_code_offset;
  }

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public int getFrameType() {
    return frame_type;
  }

  public int getMapEntrySize() {
    if (frame_type >= Const.SAME_FRAME && frame_type <= Const.SAME_FRAME_MAX) {
      return 1;
    } else if (frame_type >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frame_type <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
      return 1 + (types_of_stack_items[0].hasIndex() ? 3 : 1);
    } else if (frame_type == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
      return 3 + (types_of_stack_items[0].hasIndex() ? 3 : 1);
    } else if (frame_type >= Const.CHOP_FRAME && frame_type <= Const.CHOP_FRAME_MAX) {
      return 3;
    } else if (frame_type == Const.SAME_FRAME_EXTENDED) {
      return 3;
    } else if (frame_type >= Const.APPEND_FRAME && frame_type <= Const.APPEND_FRAME_MAX) {
      int len = 3;
      for (StackMapType types_of_local : types_of_locals) {
        len += types_of_local.hasIndex() ? 3 : 1;
      }
      return len;
    } else if (frame_type == Const.FULL_FRAME) {
      int len = 7;
      for (StackMapType types_of_local : types_of_locals) {
        len += types_of_local.hasIndex() ? 3 : 1;
      }
      for (StackMapType types_of_stack_item : types_of_stack_items) {
        len += types_of_stack_item.hasIndex() ? 3 : 1;
      }
      return len;
    } else {
      throw new RuntimeException("Invalid StackMap frame_type: " + frame_type);
    }
  }

  public int getNumberOfLocals() {
    return types_of_locals.length;
  }

  public int getNumberOfStackItems() {
    return types_of_stack_items.length;
  }

  public StackMapType[] getTypesOfLocals() {
    return types_of_locals;
  }

  public StackMapType[] getTypesOfStackItems() {
    return types_of_stack_items;
  }

  public void setByteCodeOffset(int new_offset) {
    if (new_offset < 0 || new_offset > 32767) {
      throw new RuntimeException("Invalid StackMap offset: " + new_offset);
    }
    if (frame_type >= Const.SAME_FRAME && frame_type <= Const.SAME_FRAME_MAX) {
      if (new_offset > Const.SAME_FRAME_MAX) {
        frame_type = Const.SAME_FRAME_EXTENDED;
      } else {
        frame_type = new_offset;
      }
    } else if (frame_type >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frame_type <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
      if (new_offset > Const.SAME_FRAME_MAX) {
        frame_type = Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED;
      } else {
        frame_type = Const.SAME_LOCALS_1_STACK_ITEM_FRAME + new_offset;
      }
    } else if (frame_type == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
    } else if (frame_type >= Const.CHOP_FRAME && frame_type <= Const.CHOP_FRAME_MAX) {
    } else if (frame_type == Const.SAME_FRAME_EXTENDED) {
    } else if (frame_type >= Const.APPEND_FRAME && frame_type <= Const.APPEND_FRAME_MAX) {
    } else if (frame_type == Const.FULL_FRAME) {
    } else {
      throw new RuntimeException("Invalid StackMap frame_type: " + frame_type);
    }
    byte_code_offset = new_offset;
  }

  public void setConstantPool(ConstantPool constant_pool) {
    this.constant_pool = constant_pool;
  }

  public void setFrameType(int f) {
    if (f >= Const.SAME_FRAME && f <= Const.SAME_FRAME_MAX) {
      byte_code_offset = f - Const.SAME_FRAME;
    } else if (f >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && f <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
      byte_code_offset = f - Const.SAME_LOCALS_1_STACK_ITEM_FRAME;
    } else if (f == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
    } else if (f >= Const.CHOP_FRAME && f <= Const.CHOP_FRAME_MAX) {
    } else if (f == Const.SAME_FRAME_EXTENDED) {
    } else if (f >= Const.APPEND_FRAME && f <= Const.APPEND_FRAME_MAX) {
    } else if (f == Const.FULL_FRAME) {
    } else {
      throw new RuntimeException("Invalid StackMap frame_type");
    }
    frame_type = f;
  }

  @java.lang.Deprecated
  public void setNumberOfLocals(int n) {
  }

  @java.lang.Deprecated
  public void setNumberOfStackItems(int n) {
  }

  public void setTypesOfLocals(StackMapType[] types) {
    types_of_locals = types != null ? types : new StackMapType[0];
  }

  public void setTypesOfStackItems(StackMapType[] types) {
    types_of_stack_items = types != null ? types : new StackMapType[0];
  }

  public void updateByteCodeOffset(int delta) {
    setByteCodeOffset(byte_code_offset + delta);
  }
}
