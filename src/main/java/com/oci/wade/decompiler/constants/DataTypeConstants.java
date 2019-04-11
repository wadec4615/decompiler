package com.oci.wade.decompiler.constants;

public enum DataTypeConstants {
  T_BOOLEAN(4), T_CHAR(5), T_FLOAT(6), T_DOUBLE(7), T_BYTE(8), T_SHORT(9), T_INT(10), T_LONG(11), T_VOID(12), T_ARRAY(13), T_OBJECT(14), T_REFERENCE(14), T_UNKNOWN(15), T_ADDRESS(16);
  private static String[] TYPE_NAMES = { Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, "boolean", "char", "float", "double", "byte", "short", "int", "long", "void", "array", "object", "unknown", "address" };
  private static short[][] TYPE_OF_OPERANDS = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, {}, {},
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { DataTypeConstants.T_BYTE.getTag(), DataTypeConstants.T_BYTE.getTag() }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() },
      { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, {}, {}, {}, {}, {}, {}, {}, {},
      { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag(), DataTypeConstants.T_BYTE.getTag(), DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_SHORT.getTag(), DataTypeConstants.T_BYTE.getTag(), DataTypeConstants.T_BYTE.getTag() },
      { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, {}, {}, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, {}, {}, { DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_SHORT.getTag(), DataTypeConstants.T_BYTE.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_SHORT.getTag() }, { DataTypeConstants.T_INT.getTag() }, { DataTypeConstants.T_INT.getTag() }, {}, {}, {}, {}, {},
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {} };
  private static String[] SHORT_TYPE_NAMES = { Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, "Z", "C", "F", "D", "B", "S", "I", "J", "V", Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE };
  private static String[] CLASS_TYPE_NAMES = { Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, "java.lang.Boolean", "java.lang.Character", "java.lang.Float", "java.lang.Double", "java.lang.Byte", "java.lang.Short", "java.lang.Integer", "java.lang.Long", "java.lang.Void", Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE, Const.ILLEGAL_TYPE };
  private byte tag;

  private DataTypeConstants(int value) {
    this.tag = (byte) value;
  }

  public byte getTag() {
    return tag;
  }

  public static DataTypeConstants convert(byte opcode) {
    for (DataTypeConstants oc : values()) {
      if (oc.tag == opcode) {
        return oc;
      }
    }
    return null;
  }

  public static String getClassTypeName(int index) {
    return CLASS_TYPE_NAMES[index];
  }

  public static short getOperandType(int opcode, int index) {
    return TYPE_OF_OPERANDS[opcode][index];
  }

  public static long getOperandTypeCount(int opcode) {
    return TYPE_OF_OPERANDS[opcode].length;
  }

  public static String getShortTypeName(int index) {
    return SHORT_TYPE_NAMES[index];
  }

  public static DataTypeConstants getTag(byte tag) {
    for (DataTypeConstants oc : values()) {
      if (oc.tag == tag) {
        return oc;
      }
    }
    return null;
  }

  public static String getTypeName(int index) {
    return TYPE_NAMES[index];
  }
}
