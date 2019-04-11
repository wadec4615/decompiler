package com.oci.wade.decompiler.constants;

public enum ItemConstants {
  ITEM_Bogus(0), ITEM_Integer(1), ITEM_Float(2), ITEM_Double(3), ITEM_Long(4), ITEM_Null(5), ITEM_InitObject(6), ITEM_Object(7), ITEM_NewObject(8);
  private static String[] ITEM_NAMES = { "Bogus", "Integer", "Float", "Double", "Long", "Null", "InitObject", "Object", "NewObject" };
  private final byte value;

  private ItemConstants(int value) {
    this.value = (byte) value;
  }

  public byte getValue() {
    return value;
  }

  public static ItemConstants convert(byte opcode) {
    for (ItemConstants oc : values()) {
      if (oc.value == opcode) {
        return oc;
      }
    }
    return null;
  }

  public static String getItemName(byte index) {
    return ITEM_NAMES[index];
  }
}
