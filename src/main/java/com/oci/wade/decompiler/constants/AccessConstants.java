package com.oci.wade.decompiler.constants;

public enum AccessConstants {
  ACC_PUBLIC(0x0001), ACC_PRIVATE(0x0002), ACC_PROTECTED(0x0004), ACC_STATIC(0x0008), ACC_(0x0010), ACC_SYNCHRONIZED(0x0020), ACC_VOLATILE(0x0040), ACC_BRIDGE(0x0040), ACC_TRANSIENT(0x0080), ACC_VARARGS(0x0080), ACC_NATIVE(0x0100), ACC_INTERFACE(0x0200), ACC_ABSTRACT(0x0400), ACC_STRICT(0x0800), ACC_SYNTHETIC(0x1000), ACC_ANNOTATION(0x2000), ACC_ENUM(0x4000), ACC_MANDATED(0x8000), ACC_SUPER(0x0020), MAX_ACC_FLAG(ACC_ENUM.getValue());
  private static String[] ACCESS_NAMES = { "public", "private", "protected", "static", "", "synchronized", "volatile", "transient", "native", "interface", "abstract", "strictfp", "synthetic", "annotation", "enum" };
  private static int ACCESS_NAMES_LENGTH = ACCESS_NAMES.length;
  private final short value;

  private AccessConstants(int value) {
    this.value = (short) value;
  }

  public short getValue() {
    return value;
  }

  public static AccessConstants convert(short opcode) {
    for (AccessConstants oc : values()) {
      if (oc.value == opcode) {
        return oc;
      }
    }
    return null;
  }

  public static String getAccessName(AccessConstants c) {
    return ACCESS_NAMES[c.getValue()];
  }

  public static String getAccessName(int index) {
    return ACCESS_NAMES[index];
  }

  public static int getAccessNamesLength() {
    return ACCESS_NAMES_LENGTH;
  }
}
