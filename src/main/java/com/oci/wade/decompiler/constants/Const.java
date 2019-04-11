package com.oci.wade.decompiler.constants;

import java.util.Arrays;
import java.util.Collections;

public class Const {
  public static final int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;
  public static final short MAJOR_1_1 = 45;
  public static final short MINOR_1_1 = 3;
  public static final short MAJOR_1_2 = 46;
  public static final short MINOR_1_2 = 0;
  public static final short MAJOR_1_3 = 47;
  public static final short MINOR_1_3 = 0;
  public static final short MAJOR_1_4 = 48;
  public static final short MINOR_1_4 = 0;
  public static final short MAJOR_1_5 = 49;
  public static final short MINOR_1_5 = 0;
  public static final short MAJOR_1_6 = 50;
  public static final short MINOR_1_6 = 0;
  public static final short MAJOR_1_7 = 51;
  public static final short MINOR_1_7 = 0;
  public static final short MAJOR_1_8 = 52;
  public static final short MAJOR_1_9 = 53;
  public static final short MINOR_1_8 = 0;
  public static final short MINOR_1_9 = 0;
  public static final short MAJOR = MAJOR_1_1;
  public static final short MINOR = MINOR_1_1;
  public static int MAX_SHORT = 65535;
  public static int MAX_BYTE = 255;
  public static String STATIC_INITIALIZER_NAME = "<clinit>";
  public static String CONSTRUCTOR_NAME = "<init>";
  private static String[] INTERFACES_IMPLEMENTED_BY_ARRAYS = { "java.lang.Cloneable", "java.io.Serializable" };
  public static int MAX_CP_ENTRIES = 65535;
  public static int MAX_CODE_SIZE = 65536;
  public static int MAX_ARRAY_DIMENSIONS = 255;
  public static String ILLEGAL_OPCODE = "<illegal opcode>";
  public static String ILLEGAL_TYPE = "<illegal type>";
  private static int[] CONSUME_STACK = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 3, 4, 3, 4, 3, 3, 3, 3, 1, 2, 1, 2, 3, 2, 3, 4, 2, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 1, 2, 1, 2, 2, 3, 2, 3, 2, 3, 2, 4, 2, 4, 2, 4, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4, 2, 2, 4, 4, 1, 1, 1, 1,
      1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1, 1, 2, 1, 2, 1, 0, 0, Opcode.UNPREDICTABLE.getValue(), 1, Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, Opcode.UNPREDICTABLE.getValue(), 1, 1, 0, 0, 0, Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(),
      Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(),
      Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(),
      Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue() };
  private static int[] PRODUCE_STACK = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 4, 4, 5, 6, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, Opcode.UNPREDICTABLE.getValue(), 0, Opcode.UNPREDICTABLE.getValue(), 0, Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue(), 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(),
      Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(),
      Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(),
      Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNDEFINED.getValue(), Opcode.UNPREDICTABLE.getValue(), Opcode.UNPREDICTABLE.getValue() };
  public static int SAME_FRAME = 0;
  public static int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
  public static int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
  public static int CHOP_FRAME = 248;
  public static int SAME_FRAME_EXTENDED = 251;
  public static int APPEND_FRAME = 252;
  public static int FULL_FRAME = 255;
  public static int SAME_FRAME_MAX = 63;
  public static int SAME_LOCALS_1_STACK_ITEM_FRAME_MAX = 127;
  public static int CHOP_FRAME_MAX = 250;
  public static int APPEND_FRAME_MAX = 254;

  private Const() {
  }

  public static int getConsumeStack(int index) {
    return CONSUME_STACK[index];
  }

  public static Iterable<String> getInterfacesImplementedByArrays() {
    return Collections.unmodifiableList(Arrays.asList(INTERFACES_IMPLEMENTED_BY_ARRAYS));
  }

  public static int getProduceStack(int index) {
    return PRODUCE_STACK[index];
  }
}
