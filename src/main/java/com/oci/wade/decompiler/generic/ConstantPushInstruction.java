package com.oci.wade.decompiler.generic;

public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {
  Number getValue();
}
