package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.ItemConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class StackMapType implements Cloneable {
  private ItemConstants type;
  private int index = -1;
  private ConstantPool constant_pool;

  public StackMapType(DataInput file, ConstantPool constant_pool) throws IOException {
    this(ItemConstants.convert(file.readByte()), -1, constant_pool);
    if (hasIndex()) {
      this.index = file.readShort();
    }
    this.constant_pool = constant_pool;
  }

  public StackMapType(ItemConstants type, int index, ConstantPool constant_pool) {
    if (type.getValue() < ItemConstants.ITEM_Bogus.getValue() || type.getValue() > ItemConstants.ITEM_NewObject.getValue()) {
      throw new RuntimeException("Illegal type for StackMapType: " + type);
    }
    this.type = type;
    this.index = index;
    this.constant_pool = constant_pool;
  }

  public StackMapType copy() {
    try {
      return (StackMapType) clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public int getIndex() {
    return index;
  }

  public ItemConstants getType() {
    return type;
  }

  public boolean hasIndex() {
    return type == ItemConstants.ITEM_Object || type == ItemConstants.ITEM_NewObject;
  }

  private String printIndex() {
    if (type == ItemConstants.ITEM_Object) {
      if (index < 0) {
        return ", class=<unknown>";
      }
      return ", class=" + constant_pool.constantToString(index, TypeConstants.CONSTANT_Class);
    } else if (type == ItemConstants.ITEM_NewObject) {
      return ", offset=" + index;
    } else {
      return "";
    }
  }

  public void setConstantPool(ConstantPool constant_pool) {
    this.constant_pool = constant_pool;
  }

  public void setIndex(int t) {
    index = t;
  }

  public void setType(ItemConstants t) {
    if (t.getValue() < ItemConstants.ITEM_Bogus.getValue() || t.getValue() > ItemConstants.ITEM_NewObject.getValue()) {
      throw new RuntimeException("Illegal type for StackMapType: " + t);
    }
    type = t;
  }

  @Override
  public String toString() {
    return "(type=" + ItemConstants.getItemName(type.getValue()) + printIndex() + ")";
  }
}
