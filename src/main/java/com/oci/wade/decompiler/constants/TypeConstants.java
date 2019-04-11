package com.oci.wade.decompiler.constants;

public enum TypeConstants {
  CONSTANT_Utf8(1), CONSTANT_Integer(3), CONSTANT_Float(4), CONSTANT_Long(5), CONSTANT_Double(6), CONSTANT_Class(7), CONSTANT_Fieldref(9), CONSTANT_String(8), CONSTANT_Methodref(10), CONSTANT_InterfaceMethodref(11), CONSTANT_NameAndType(12), CONSTANT_MethodHandle(15), CONSTANT_MethodType(16), CONSTANT_InvokeDynamic(18);
  private static String[] CONSTANT_NAMES = { "", "CONSTANT_Utf8", "", "CONSTANT_Integer", "CONSTANT_Float", "CONSTANT_Long", "CONSTANT_Double", "CONSTANT_Class", "CONSTANT_String", "CONSTANT_Fieldref", "CONSTANT_Methodref", "CONSTANT_InterfaceMethodref", "CONSTANT_NameAndType", "", "", "CONSTANT_MethodHandle", "CONSTANT_MethodType", "", "CONSTANT_InvokeDynamic" };
  private byte tag;

  private TypeConstants(int value) {
    this.tag = (byte) value;
  }

  public byte getTag() {
    return tag;
  }

  public static String getConstantName(TypeConstants index) {
    return CONSTANT_NAMES[index.getTag()];
  }

  public static TypeConstants getTag(byte tag) {
    for (TypeConstants oc : values()) {
      if (oc.tag == tag) {
        return oc;
      }
    }
    return null;
  }
}
