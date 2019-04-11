package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantMethodHandle extends Constant {
  private int reference_kind;
  private int reference_index;

  public ConstantMethodHandle(ConstantMethodHandle c) {
    this(c.getReferenceKind(), c.getReferenceIndex());
  }

  public ConstantMethodHandle(DataInput file) throws IOException {
    this(file.readUnsignedByte(), file.readUnsignedShort());
  }

  public ConstantMethodHandle(int reference_kind, int reference_index) {
    super(TypeConstants.CONSTANT_MethodHandle);
    this.reference_kind = reference_kind;
    this.reference_index = reference_index;
  }

  public int getReferenceIndex() {
    return reference_index;
  }

  public int getReferenceKind() {
    return reference_kind;
  }

  public void setReferenceIndex(int reference_index) {
    this.reference_index = reference_index;
  }

  public void setReferenceKind(int reference_kind) {
    this.reference_kind = reference_kind;
  }

  @Override
  public String toString() {
    return super.toString() + "(reference_kind = " + reference_kind + ", reference_index = " + reference_index + ")";
  }
}
