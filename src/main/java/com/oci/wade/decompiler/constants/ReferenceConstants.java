package com.oci.wade.decompiler.constants;

public enum ReferenceConstants {
  REF_getField(1), REF_getStatic(2), REF_putField(3), REF_putStatic(4), REF_invokeVirtual(5), REF_invokeStatic(6), REF_invokeSpecial(7), REF_newInvokeSpecial(8), REF_invokeInterface(9);
  private static String[] METHODHANDLE_NAMES = { "", "getField", "getStatic", "putField", "putStatic", "invokeVirtual", "invokeStatic", "invokeSpecial", "newInvokeSpecial", "invokeInterface" };
  private byte tag;

  private ReferenceConstants(int value) {
    this.tag = (byte) value;
  }

  public byte getTag() {
    return tag;
  }

  public static String getMethodHandleName(int index) {
    return METHODHANDLE_NAMES[index];
  }

  public static ReferenceConstants getTag(byte tag) {
    for (ReferenceConstants oc : values()) {
      if (oc.tag == tag) {
        return oc;
      }
    }
    return null;
  }
}
