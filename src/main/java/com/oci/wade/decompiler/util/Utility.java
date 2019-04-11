package com.oci.wade.decompiler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.oci.wade.decompiler.attribute.LocalVariableTable;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.LocalVariable;
import com.oci.wade.decompiler.constant.Constant;
import com.oci.wade.decompiler.constants.AccessConstants;
import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.exception.ClassFormatException;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.instruction.*;

@SuppressWarnings({ "unused", "incomplete-switch" })
public abstract class Utility {
  private static ThreadLocal<Integer> consumed_chars = new ThreadLocal<Integer>() {
    @Override
    protected Integer initialValue() {
      return Integer.valueOf(0);
    }
  };
  private static boolean wide = false;

  public static String accessToString(int access_flags) {
    return accessToString(access_flags, false);
  }

  public static String accessToString(int access_flags, boolean for_class) {
    StringBuilder buf = new StringBuilder();
    short p = 0;
    for (short i = 0; p < AccessConstants.MAX_ACC_FLAG.getValue(); i++) {
      p = (short) pow2(i);
      if ((access_flags & p) != 0) {
        if (for_class && ((p == AccessConstants.ACC_SUPER.getValue()) || (p == AccessConstants.ACC_INTERFACE.getValue()))) {
          continue;
        }
        buf.append(AccessConstants.getAccessName(i)).append(" ");
      }
    }
    return buf.toString().trim();
  }

  public static String classOrInterface(int access_flags) {
    return (access_flags & AccessConstants.ACC_INTERFACE.getValue()) != 0 ? "interface" : "class";
  }

  public static Instruction[] codeToInstructions(byte[] bytes, ConstantPool constantPool, int size) throws Exception {
    List<Instruction> list = new ArrayList<>();
    ByteSequence stream = new ByteSequence(bytes);
    int i = 0, no_pad_bytes = 0, default_offset = 0;
    while (i < size) {
      Opcode opcode = Opcode.convert(stream.readUnsignedByte());
      Instruction instruction = null;
      int index = 0, nargs = 0;
      if ((opcode == Opcode.TABLESWITCH) || (opcode == Opcode.LOOKUPSWITCH)) {
        int remainder = stream.getIndex() % 4;
        no_pad_bytes = remainder == 0 ? 0 : 4 - remainder;
        for (int j = 0; j < no_pad_bytes; j++) {
          stream.readByte();
        }
        default_offset = stream.readInt();
      }
      switch (opcode) {
        case AALOAD:
          instruction = new AALOAD();
          break;
        case AASTORE:
          instruction = new AASTORE();
          break;
        case ACONST_NULL:
          instruction = new AASTORE();
          break;
        case ALOAD:
          instruction = new ALOAD(Opcode.ALOAD);
          break;
        case ALOAD_0:
          instruction = new ALOAD(Opcode.ALOAD_0, 0);
          break;
        case ALOAD_1:
          instruction = new ALOAD(Opcode.ALOAD_1, 1);
          break;
        case ALOAD_2:
          instruction = new ALOAD(Opcode.ALOAD_2, 2);
          break;
        case ALOAD_3:
          instruction = new ALOAD(Opcode.ALOAD_3, 3);
          break;
        case ANEWARRAY:
          index = stream.readUnsignedShort();
          instruction = new ANEWARRAY(index);
          break;
        case ARETURN:
          instruction = new ARETURN();
          break;
        case ARRAYLENGTH:
          instruction = new ARRAYLENGTH();
          break;
        case ASTORE:
          index = stream.readUnsignedByte();
          instruction = new ASTORE(Opcode.ASTORE, index);
          break;
        case ASTORE_0:
          instruction = new ASTORE(Opcode.ASTORE_0, 0);
          break;
        case ASTORE_1:
          instruction = new ASTORE(Opcode.ASTORE_1, 1);
          break;
        case ASTORE_2:
          instruction = new ASTORE(Opcode.ASTORE_2, 2);
          break;
        case ASTORE_3:
          instruction = new ASTORE(Opcode.ASTORE_3, 3);
          break;
        case ATHROW:
          instruction = new ATHROW();
          break;
        case BALOAD:
          instruction = new BALOAD();
          break;
        case BASTORE:
          instruction = new BASTORE();
          break;
        case BIPUSH:
          index = stream.readUnsignedByte();
          instruction = new BIPUSH((byte) index);
          break;
        case CALOAD:
          instruction = new CALOAD();
          break;
        case CASTORE:
          instruction = new CASTORE();
          break;
        case CHECKCAST:
          index = stream.readUnsignedShort();
          instruction = new CHECKCAST((short) index);
          break;
        case D2F:
          instruction = new D2F();
          break;
        case D2I:
          instruction = new D2I();
          break;
        case D2L:
          instruction = new D2L();
          break;
        case DADD:
          instruction = new DADD();
          break;
        case DALOAD:
          instruction = new DALOAD();
          break;
        case DASTORE:
          instruction = new DASTORE();
          break;
        case DCMPG:
          instruction = new DCMPG();
          break;
        case DCMPL:
          instruction = new DCMPL();
          break;
        case DCONST_0:
          instruction = new DCONST(0);
          break;
        case DCONST_1:
          instruction = new DCONST(1);
          break;
        case DDIV:
          instruction = new DDIV();
          break;
        case DLOAD:
          index = stream.readUnsignedByte();
          instruction = new DLOAD(Opcode.DLOAD, index);
          break;
        case DLOAD_0:
          instruction = new DLOAD(Opcode.DLOAD_0, 0);
          break;
        case DLOAD_1:
          instruction = new DLOAD(Opcode.DLOAD_1, 1);
          break;
        case DLOAD_2:
          instruction = new DLOAD(Opcode.DLOAD_2, 2);
          break;
        case DLOAD_3:
          instruction = new DLOAD(Opcode.DLOAD_3, 3);
          break;
        case DMUL:
          instruction = new DMUL();
          break;
        case DNEG:
          instruction = new DNEG();
          break;
        case DREM:
          instruction = new DREM();
          break;
        case DRETURN:
          instruction = new DRETURN();
          break;
        case DSTORE:
          index = stream.readUnsignedByte();
          instruction = new DSTORE(Opcode.DSTORE, index);
          break;
        case DSTORE_0:
          instruction = new DSTORE(Opcode.DSTORE_0, 0);
          break;
        case DSTORE_1:
          instruction = new DSTORE(Opcode.DSTORE_1, 1);
          break;
        case DSTORE_2:
          instruction = new DSTORE(Opcode.DSTORE_2, 2);
          break;
        case DSTORE_3:
          instruction = new DSTORE(Opcode.DSTORE_3, 3);
          break;
        case DSUB:
          instruction = new DSUB();
          break;
        case DUP:
          instruction = new DUP();
          break;
        case DUP_X1:
          instruction = new DUP_X1();
          break;
        case DUP_X2:
          instruction = new DUP_X2();
          break;
        case DUP2:
          instruction = new DUP2();
          break;
        case DUP2_X1:
          instruction = new DUP2_X1();
          break;
        case DUP2_X2:
          instruction = new DUP2_X2();
          break;
        case F2D:
          instruction = new F2D();
          break;
        case F2I:
          instruction = new F2I();
          break;
        case F2L:
          instruction = new F2L();
          break;
        case FADD:
          instruction = new FADD();
          break;
        case FALOAD:
          instruction = new FALOAD();
          break;
        case FASTORE:
          instruction = new FASTORE();
          break;
        case FCMPG:
          instruction = new FCMPG();
          break;
        case FCMPL:
          instruction = new FCMPL();
          break;
        case FCONST_0:
          instruction = new FCONST(0);
          break;
        case FCONST_1:
          instruction = new FCONST(1);
          break;
        case FCONST_2:
          instruction = new FCONST(2);
          break;
        case FDIV:
          instruction = new FDIV();
          break;
        case FLOAD:
          index = stream.readUnsignedByte();
          instruction = new FLOAD(Opcode.FLOAD, index);
          break;
        case FLOAD_0:
          instruction = new FLOAD(Opcode.FLOAD_0, 0);
          break;
        case FLOAD_1:
          instruction = new FLOAD(Opcode.FLOAD_1, 1);
          break;
        case FLOAD_2:
          instruction = new FLOAD(Opcode.FLOAD_2, 2);
          break;
        case FLOAD_3:
          instruction = new FLOAD(Opcode.FLOAD_3, 3);
          break;
        case FMUL:
          instruction = new FMUL();
          break;
        case FNEG:
          instruction = new FNEG();
          break;
        case FREM:
          instruction = new FREM();
          break;
        case FRETURN:
          instruction = new FRETURN();
          break;
        case FSTORE:
          index = stream.readUnsignedByte();
          instruction = new FSTORE(Opcode.FSTORE, index);
          break;
        case FSTORE_0:
          instruction = new FSTORE(Opcode.FSTORE_0, 0);
          break;
        case FSTORE_1:
          instruction = new FSTORE(Opcode.FSTORE_1, 1);
          break;
        case FSTORE_2:
          instruction = new FSTORE(Opcode.FSTORE_2, 2);
          break;
        case FSTORE_3:
          instruction = new FSTORE(Opcode.FSTORE_3, 3);
          break;
        case FSUB:
          instruction = new FSUB();
          break;
        case GETFIELD:
          index = stream.readUnsignedShort();
          instruction = new GETFIELD((short) index);
          break;
        case GETSTATIC:
          index = stream.readUnsignedShort();
          instruction = new GETSTATIC((short) index);
          break;
        case GOTO:
          index = stream.readUnsignedShort();
          instruction = new GOTO((short) index);
          break;
        case GOTO_W:
          index = stream.readInt();
          instruction = new GOTO_W(index);
          break;
        case I2B:
          instruction = new I2B();
          break;
        case I2C:
          instruction = new I2C();
          break;
        case I2D:
          instruction = new I2D();
          break;
        case I2F:
          instruction = new I2F();
          break;
        case I2L:
          instruction = new I2L();
          break;
        case I2S:
          instruction = new I2S();
          break;
        case IADD:
          instruction = new IADD();
          break;
        case IALOAD:
          instruction = new IALOAD();
          break;
        case IAND:
          instruction = new IAND();
          break;
        case IASTORE:
          instruction = new IASTORE();
          break;
        case ICONST_0:
          instruction = new ICONST(0);
          break;
        case ICONST_1:
          instruction = new ICONST(1);
          break;
        case ICONST_2:
          instruction = new ICONST(2);
          break;
        case ICONST_3:
          instruction = new ICONST(3);
          break;
        case ICONST_4:
          instruction = new ICONST(4);
          break;
        case ICONST_5:
          instruction = new ICONST(5);
          break;
        case IDIV:
          instruction = new IDIV();
          break;
        case IF_ACMPEQ:
          index = stream.readUnsignedShort();
          instruction = new IF_ACMPEQ(index);
          break;
        case IF_ACMPNE:
          index = stream.readUnsignedShort();
          instruction = new IF_ACMPNE(index);
          break;
        case IF_ICMPEQ:
          index = stream.readUnsignedShort();
          instruction = new IF_ICMPEQ(index);
          break;
        case IF_ICMPNE:
          index = stream.readUnsignedShort();
          instruction = new IF_ICMPNE(index);
          break;
        case IF_ICMPLT:
          index = stream.readUnsignedShort();
          instruction = new IF_ICMPLT(index);
          break;
        case IF_ICMPGE:
          index = stream.readUnsignedShort();
          instruction = new IF_ICMPGE(index);
          break;
        case IF_ICMPGT:
          index = stream.readUnsignedShort();
          instruction = new IF_ICMPGT(index);
          break;
        case IF_ICMPLE:
          index = stream.readUnsignedShort();
          instruction = new IF_ICMPLE(index);
          break;
        case IFEQ:
          index = stream.readUnsignedShort();
          instruction = new IFEQ(index);
          break;
        case IFNE:
          index = stream.readUnsignedShort();
          instruction = new IFNE(index);
          break;
        case IFLT:
          index = stream.readUnsignedShort();
          instruction = new IFLT(index);
          break;
        case IFGE:
          index = stream.readUnsignedShort();
          instruction = new IFGE(index);
          break;
        case IFGT:
          index = stream.readUnsignedShort();
          instruction = new IFGT(index);
          break;
        case IFLE:
          index = stream.readUnsignedShort();
          instruction = new IFLE(index);
          break;
        case IFNONNULL:
          index = stream.readUnsignedShort();
          instruction = new IFNONNULL(index);
          break;
        case IFNULL:
          index = stream.readUnsignedShort();
          instruction = new IFNULL(index);
          break;
        case IINC:
          index = stream.readUnsignedByte();
          int c = stream.readUnsignedByte();
          instruction = new IINC(index, c);
          break;
        case ILOAD:
          index = stream.readUnsignedByte();
          instruction = new ILOAD(Opcode.ILOAD, index, false);
          break;
        case ILOAD_0:
          instruction = new ILOAD(Opcode.ILOAD_0, 0, false);
          break;
        case ILOAD_1:
          instruction = new ILOAD(Opcode.ILOAD_1, 1, false);
          break;
        case ILOAD_2:
          instruction = new ILOAD(Opcode.ILOAD_2, 2, false);
          break;
        case ILOAD_3:
          instruction = new ILOAD(Opcode.ILOAD_3, 3, false);
          break;
        case IMUL:
          instruction = new IMUL();
          break;
        case INEG:
          instruction = new INEG();
          break;
        case INSTANCEOF:
          index = stream.readUnsignedShort();
          instruction = new INSTANCEOF(index);
          break;
        case INVOKEDYNAMIC:
          index = stream.readUnsignedShort();
          stream.readUnsignedByte();
          stream.readUnsignedByte();
          instruction = new INVOKEDYNAMIC(constantPool, index);
          break;
        case INVOKEINTERFACE:
          index = stream.readUnsignedShort();
          nargs = stream.readUnsignedByte();
          stream.readUnsignedByte();
          instruction = new INVOKEINTERFACE(constantPool, index, nargs);
          break;
        case INVOKESPECIAL:
          index = stream.readUnsignedShort();
          instruction = new INVOKESPECIAL(constantPool, index);
          break;
        case INVOKESTATIC:
          index = stream.readUnsignedShort();
          instruction = new INVOKESTATIC(constantPool, index);
          break;
        case INVOKEVIRTUAL:
          index = stream.readUnsignedShort();
          instruction = new INVOKEVIRTUAL(constantPool, index);
          break;
        case IOR:
          instruction = new IOR();
          break;
        case IREM:
          instruction = new IREM();
          break;
        case IRETURN:
          instruction = new IRETURN();
          break;
        case ISHL:
          instruction = new ISHL();
          break;
        case ISHR:
          instruction = new ISHR();
          break;
        case ISTORE:
          index = stream.readUnsignedByte();
          instruction = new ISTORE(Opcode.ISTORE, index);
          break;
        case ISTORE_0:
          instruction = new ISTORE(Opcode.ISTORE_0, 0);
          break;
        case ISTORE_1:
          instruction = new ISTORE(Opcode.ISTORE_1, 1);
          break;
        case ISTORE_2:
          instruction = new ISTORE(Opcode.ISTORE_2, 2);
          break;
        case ISTORE_3:
          instruction = new ISTORE(Opcode.ISTORE_3, 3);
          break;
        case ISUB:
          instruction = new ISUB();
          break;
        case IUSHR:
          instruction = new IUSHR();
          break;
        case IXOR:
          instruction = new IXOR();
          break;
        case JSR:
          index = stream.readUnsignedShort();
          instruction = new JSR(index);
          break;
        case JSR_W:
          index = stream.readInt();
          instruction = new JSR_W(index);
          break;
        case L2D:
          instruction = new L2D();
          break;
        case L2F:
          instruction = new L2F();
          break;
        case L2I:
          instruction = new L2I();
          break;
        case LADD:
          instruction = new LADD();
          break;
        case LALOAD:
          instruction = new LALOAD();
          break;
        case LAND:
          instruction = new LAND();
          break;
        case LASTORE:
          instruction = new LASTORE();
          break;
        case LCMP:
          instruction = new LCMP();
          break;
        case LCONST_0:
          instruction = new LCONST(0);
          break;
        case LCONST_1:
          instruction = new LCONST(1);
          break;
        case LDC:
          index = stream.readUnsignedByte();
          instruction = new LDC(index);
          break;
        case LDC_W:
          index = stream.readUnsignedShort();
          instruction = new LDC_W(index);
          break;
        case LDC2_W:
          index = stream.readUnsignedShort();
          instruction = new LDC2_W(index);
          break;
        case LDIV:
          instruction = new LDIV();
          break;
        case LLOAD:
          index = stream.readUnsignedByte();
          instruction = new LLOAD(Opcode.LLOAD, index);
          break;
        case LLOAD_0:
          instruction = new LLOAD(Opcode.LLOAD_0, 0);
          break;
        case LLOAD_1:
          instruction = new LLOAD(Opcode.LLOAD_1, 1);
          break;
        case LLOAD_2:
          instruction = new LLOAD(Opcode.LLOAD_2, 2);
          break;
        case LLOAD_3:
          instruction = new LLOAD(Opcode.LLOAD_3, 3);
          break;
        case LMUL:
          instruction = new LMUL();
          break;
        case LNEG:
          instruction = new LNEG();
          break;
        case LOOKUPSWITCH:
          int npairs = stream.readInt();
          int offset = stream.getIndex() - 8 - no_pad_bytes - 1;
          int[] match = new int[npairs];
          int[] jump_table = new int[npairs];
          for (int j = 0; j < npairs; j++) {
            match[i] = stream.readInt();
            jump_table[i] = offset + stream.readInt();
          }
          instruction = new LOOKUPSWITCH(npairs, offset, match, jump_table, no_pad_bytes);
          break;
        case LOR:
          instruction = new LOR();
          break;
        case LREM:
          instruction = new LREM();
          break;
        case LRETURN:
          instruction = new LRETURN();
          break;
        case LSHL:
          instruction = new LSHL();
          break;
        case LSHR:
          instruction = new LSHR();
          break;
        case LSTORE:
          index = stream.readUnsignedByte();
          instruction = new LSTORE(Opcode.LSTORE, index);
          break;
        case LSTORE_0:
          instruction = new LSTORE(Opcode.LSTORE_0, 0);
          break;
        case LSTORE_1:
          instruction = new LSTORE(Opcode.LSTORE_1, 1);
          break;
        case LSTORE_2:
          instruction = new LSTORE(Opcode.LSTORE_2, 2);
          break;
        case LSTORE_3:
          instruction = new LSTORE(Opcode.LSTORE_3, 3);
          break;
        case LSUB:
          instruction = new LSUB();
          break;
        case LUSHR:
          instruction = new LUSHR();
          break;
        case LXOR:
          instruction = new LXOR();
          break;
        case MONITORENTER:
          instruction = new MONITORENTER();
          break;
        case MONITOREXIT:
          instruction = new MONITOREXIT();
          break;
        case MULTIANEWARRAY:
          index = stream.readUnsignedShort();
          int dimensions = stream.readUnsignedByte();
          instruction = new MULTIANEWARRAY(index, dimensions);
          break;
        case NEW:
          index = stream.readUnsignedShort();
          instruction = new NEW(index);
          break;
        case NEWARRAY:
          index = stream.readUnsignedByte();
          instruction = new NEWARRAY(DataTypeConstants.convert((byte) index));
          break;
        case NOP:
          instruction = new NOP();
          break;
        case POP:
          instruction = new POP();
          break;
        case POP2:
          instruction = new POP2();
          break;
        case PUTFIELD:
          index = stream.readUnsignedShort();
          instruction = new PUTFIELD(index);
          break;
        case PUTSTATIC:
          index = stream.readUnsignedShort();
          instruction = new PUTSTATIC(index);
          break;
        case RET:
          index = stream.readUnsignedByte();
          instruction = new RET(index);
          break;
        case RETURN:
          instruction = new RETURN();
          break;
        case SALOAD:
          instruction = new SALOAD();
          break;
        case SASTORE:
          instruction = new SASTORE();
          break;
        case SIPUSH:
          index = stream.readUnsignedShort();
          instruction = new SIPUSH(index);
          break;
        case SWAP:
          instruction = new SWAP();
          break;
        case TABLESWITCH:
          int low = stream.readInt();
          int high = stream.readInt();
          offset = stream.getIndex() - 12 - no_pad_bytes - 1;
          default_offset += offset;
          jump_table = new int[(high - low) + 1];
          for (int j = 0; j < jump_table.length; j++) {
            jump_table[i] = offset + stream.readInt();
          }
          instruction = new TABLESWITCH(low, high, offset, jump_table);
          break;
        case WIDE:
          Opcode opcode2 = Opcode.convert(stream.readUnsignedByte());
          switch (opcode2) {
            case ILOAD:
              index = stream.readUnsignedShort();
              instruction = new ILOAD(Opcode.ILOAD, index, true);
              break;
            case FLOAD:
              index = stream.readUnsignedShort();
              instruction = new FLOAD(Opcode.FLOAD, index, true);
              break;
            case ALOAD:
              index = stream.readUnsignedShort();
              instruction = new ALOAD(Opcode.ALOAD, index, true);
              break;
            case LLOAD:
              index = stream.readUnsignedShort();
              instruction = new LLOAD(Opcode.LLOAD, index, true);
              break;
            case DLOAD:
              index = stream.readUnsignedShort();
              instruction = new DLOAD(Opcode.DLOAD, index, true);
              break;
            case ISTORE:
              index = stream.readUnsignedShort();
              instruction = new ISTORE(Opcode.ISTORE, index, true);
              break;
            case FSTORE:
              index = stream.readUnsignedShort();
              instruction = new FSTORE(Opcode.FSTORE, index, true);
              break;
            case ASTORE:
              index = stream.readUnsignedShort();
              instruction = new ASTORE(Opcode.ASTORE, index, true);
              break;
            case LSTORE:
              index = stream.readUnsignedShort();
              instruction = new LSTORE(Opcode.LSTORE, index, true);
              break;
            case DSTORE:
              index = stream.readUnsignedShort();
              instruction = new DSTORE(Opcode.DSTORE, index, true);
              break;
            case RET:
              index = stream.readUnsignedShort();
              instruction = new RET(index, true);
              break;
            case IINC:
              index = stream.readUnsignedShort();
              c = stream.readUnsignedShort();
              instruction = new IINC(index, c, true);
              break;
          }
          break;
        default:
          instruction = new NOP();
          break;
      }
      list.add(instruction);
      i = stream.getIndex();
    }
    stream.close();
    return list.toArray(new Instruction[list.size()]);
  }

  public static String codeToString(byte[] code, ConstantPool constant_pool, int index, int length, boolean verbose, String indent) {
    StringBuilder buf = new StringBuilder(code.length * 20);
    try (ByteSequence stream = new ByteSequence(code)) {
      for (int i = 0; i < index; i++) {
        codeToString(stream, constant_pool, verbose);
      }
      for (int i = 0; stream.available() > 0; i++) {
        if ((length < 0) || (i < length)) {
          String indices = fillup(stream.getIndex() + ":", 6, true, ' ');
          buf.append(indent).append(indices).append(codeToString(stream, constant_pool, verbose)).append('\n');
        }
      }
    } catch (IOException e) {
      throw new ClassFormatException("Byte code error: " + buf.toString(), e);
    }
    return buf.toString();
  }

  public static String codeToString(ByteSequence bytes, ConstantPool constant_pool, boolean verbose) throws IOException {
    Opcode opcode = Opcode.convert((short) bytes.readUnsignedByte());
    int default_offset = 0;
    int low;
    int high;
    int npairs;
    int index;
    int vindex;
    int constant;
    int[] match;
    int[] jump_table;
    int no_pad_bytes = 0;
    int offset;
    StringBuilder buf = new StringBuilder(Opcode.getOpcodeName(opcode.getValue()));
    if ((opcode == Opcode.TABLESWITCH) || (opcode == Opcode.LOOKUPSWITCH)) {
      int remainder = bytes.getIndex() % 4;
      no_pad_bytes = remainder == 0 ? 0 : 4 - remainder;
      for (int i = 0; i < no_pad_bytes; i++) {
        byte b;
        if ((b = bytes.readByte()) != 0) {
          System.err.println("Warning: Padding byte != 0 in " + Opcode.getOpcodeName(opcode.getValue()) + ":" + b);
        }
      }
      default_offset = bytes.readInt();
    }
    switch (opcode) {
      case TABLESWITCH:
        low = bytes.readInt();
        high = bytes.readInt();
        offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
        default_offset += offset;
        buf.append("\tdefault = ").append(default_offset).append(", low = ").append(low).append(", high = ").append(high).append("(");
        jump_table = new int[(high - low) + 1];
        for (int i = 0; i < jump_table.length; i++) {
          jump_table[i] = offset + bytes.readInt();
          buf.append(jump_table[i]);
          if (i < (jump_table.length - 1)) {
            buf.append(", ");
          }
        }
        buf.append(")");
        break;
      case LOOKUPSWITCH: {
        npairs = bytes.readInt();
        offset = bytes.getIndex() - 8 - no_pad_bytes - 1;
        match = new int[npairs];
        jump_table = new int[npairs];
        default_offset += offset;
        buf.append("\tdefault = ").append(default_offset).append(", npairs = ").append(npairs).append(" (");
        for (int i = 0; i < npairs; i++) {
          match[i] = bytes.readInt();
          jump_table[i] = offset + bytes.readInt();
          buf.append("(").append(match[i]).append(", ").append(jump_table[i]).append(")");
          if (i < (npairs - 1)) {
            buf.append(", ");
          }
        }
        buf.append(")");
      }
        break;
      case GOTO:
      case IFEQ:
      case IFGE:
      case IFGT:
      case IFLE:
      case IFLT:
      case JSR:
      case IFNE:
      case IFNONNULL:
      case IFNULL:
      case IF_ACMPEQ:
      case IF_ACMPNE:
      case IF_ICMPEQ:
      case IF_ICMPGE:
      case IF_ICMPGT:
      case IF_ICMPLE:
      case IF_ICMPLT:
      case IF_ICMPNE:
        buf.append("\t\t#").append((bytes.getIndex() - 1) + bytes.readShort());
        break;
      case GOTO_W:
      case JSR_W:
        buf.append("\t\t#").append((bytes.getIndex() - 1) + bytes.readInt());
        break;
      case ALOAD:
      case ASTORE:
      case DLOAD:
      case DSTORE:
      case FLOAD:
      case FSTORE:
      case ILOAD:
      case ISTORE:
      case LLOAD:
      case LSTORE:
      case RET:
        if (wide) {
          vindex = bytes.readUnsignedShort();
          wide = false;
        } else {
          vindex = bytes.readUnsignedByte();
        }
        buf.append("\t\t%").append(vindex);
        break;
      case WIDE:
        wide = true;
        buf.append("\t(wide)");
        break;
      case NEWARRAY:
        buf.append("\t\t<").append(DataTypeConstants.getTypeName(bytes.readByte())).append(">");
        break;
      case GETFIELD:
      case GETSTATIC:
      case PUTFIELD:
      case PUTSTATIC:
        index = bytes.readUnsignedShort();
        buf.append("\t\t").append(constant_pool.constantToString(index, TypeConstants.CONSTANT_Fieldref)).append(verbose ? " (" + index + ")" : "");
        break;
      case NEW:
      case CHECKCAST:
        buf.append("\t");
      case INSTANCEOF:
        index = bytes.readUnsignedShort();
        buf.append("\t<").append(constant_pool.constantToString(index, TypeConstants.CONSTANT_Class)).append(">").append(verbose ? " (" + index + ")" : "");
        break;
      case INVOKESPECIAL:
      case INVOKESTATIC:
        index = bytes.readUnsignedShort();
        Constant c = constant_pool.getConstant(index);
        buf.append("\t").append(constant_pool.constantToString(index, c.getTag())).append(verbose ? " (" + index + ")" : "");
        break;
      case INVOKEVIRTUAL:
        index = bytes.readUnsignedShort();
        buf.append("\t").append(constant_pool.constantToString(index, TypeConstants.CONSTANT_Methodref)).append(verbose ? " (" + index + ")" : "");
        break;
      case INVOKEINTERFACE:
        index = bytes.readUnsignedShort();
        int nargs = bytes.readUnsignedByte();
        buf.append("\t").append(constant_pool.constantToString(index, TypeConstants.CONSTANT_InterfaceMethodref)).append(verbose ? " (" + index + ")\t" : "").append(nargs).append("\t").append(bytes.readUnsignedByte());
        break;
      case INVOKEDYNAMIC:
        index = bytes.readUnsignedShort();
        buf.append("\t").append(constant_pool.constantToString(index, TypeConstants.CONSTANT_InvokeDynamic)).append(verbose ? " (" + index + ")\t" : "").append(bytes.readUnsignedByte()).append(bytes.readUnsignedByte());
        break;
      case LDC_W:
      case LDC2_W:
        index = bytes.readUnsignedShort();
        buf.append("\t\t").append(constant_pool.constantToString(index, constant_pool.getConstant(index).getTag())).append(verbose ? " (" + index + ")" : "");
        break;
      case LDC:
        index = bytes.readUnsignedByte();
        buf.append("\t\t").append(constant_pool.constantToString(index, constant_pool.getConstant(index).getTag())).append(verbose ? " (" + index + ")" : "");
        break;
      case ANEWARRAY:
        index = bytes.readUnsignedShort();
        buf.append("\t\t<").append(compactClassName(constant_pool.getConstantString(index, TypeConstants.CONSTANT_Class), false)).append(">").append(verbose ? " (" + index + ")" : "");
        break;
      case MULTIANEWARRAY: {
        index = bytes.readUnsignedShort();
        int dimensions = bytes.readUnsignedByte();
        buf.append("\t<").append(compactClassName(constant_pool.getConstantString(index, TypeConstants.CONSTANT_Class), false)).append(">\t").append(dimensions).append(verbose ? " (" + index + ")" : "");
      }
        break;
      case IINC:
        if (wide) {
          vindex = bytes.readUnsignedShort();
          constant = bytes.readShort();
          wide = false;
        } else {
          vindex = bytes.readUnsignedByte();
          constant = bytes.readByte();
        }
        buf.append("\t\t%").append(vindex).append("\t").append(constant);
        break;
      default:
        if (Opcode.getNoOfOperands(opcode.getValue()) > 0) {
          for (int i = 0; i < DataTypeConstants.getOperandTypeCount(opcode.getValue()); i++) {
            buf.append("\t\t");
            switch (DataTypeConstants.getOperandType(opcode.getValue(), i)) {
              case 8:
                buf.append(bytes.readByte());
                break;
              case 9:
                buf.append(bytes.readShort());
                break;
              case 10:
                buf.append(bytes.readInt());
                break;
              default:
                throw new IllegalStateException("Unreachable default case reached!");
            }
          }
        }
    }
    return buf.toString();
  }

  public static String compactClassName(String str) {
    return compactClassName(str, true);
  }

  public static String compactClassName(String str, boolean chopit) {
    return compactClassName(str, "java.lang.", chopit);
  }

  public static String compactClassName(String str, String prefix, boolean chopit) {
    int len = prefix.length();
    str = str.replace('/', '.');
    if (chopit) {
      if (str.startsWith(prefix) && (str.substring(len).indexOf('.') == -1)) {
        str = str.substring(len);
      }
    }
    return str;
  }

  public static String convertString(String label) {
    char[] ch = label.toCharArray();
    StringBuilder buf = new StringBuilder();
    for (char element : ch) {
      switch (element) {
        case '\n':
          buf.append("\\n");
          break;
        case '\r':
          buf.append("\\r");
          break;
        case '\"':
          buf.append("\\\"");
          break;
        case '\'':
          buf.append("\\'");
          break;
        case '\\':
          buf.append("\\\\");
          break;
        default:
          buf.append(element);
          break;
      }
    }
    return buf.toString();
  }

  public static String fillup(String str, int length, boolean left_justify, char fill) {
    int len = length - str.length();
    char[] buf = new char[len < 0 ? 0 : len];
    for (int j = 0; j < buf.length; j++) {
      buf[j] = fill;
    }
    if (left_justify) {
      return str + new String(buf);
    }
    return new String(buf) + str;
  }

  public static String getConstructorName(String str, int ch) {
    if (str.lastIndexOf(ch) != -1) {
      str = str.substring(str.lastIndexOf(ch) + 1, str.length());
    }
    return str;
  }

  public static String methodSignatureToString(String signature, String name, String access, boolean chopit, LocalVariableTable vars, boolean isConstructor) throws ClassFormatException {
    StringBuilder buf = new StringBuilder("(");
    String type;
    int index;
    int var_index = access.contains("static") ? 0 : 1;
    try {
      if (signature.charAt(0) != '(') {
        throw new ClassFormatException("Invalid method signature: " + signature);
      }
      index = 1;
      while (signature.charAt(index) != ')') {
        String param_type = signatureToString(signature.substring(index), chopit);
        buf.append(param_type);
        if (vars != null) {
          LocalVariable l = vars.getLocalVariable(var_index, 0);
          if (l != null) {
            buf.append(" ").append(l.getName());
          }
        } else {
          buf.append(" arg").append(var_index);
        }
        if ("double".equals(param_type) || "long".equals(param_type)) {
          var_index += 2;
        } else {
          var_index++;
        }
        buf.append(", ");
        index += unwrap(consumed_chars);
      }
      index++;
      if (!isConstructor) {
        type = signatureToString(signature.substring(index), chopit);
      } else {
        type = "";
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ClassFormatException("Invalid method signature: " + signature, e);
    }
    if (buf.length() > 1) {
      buf.setLength(buf.length() - 2);
    }
    buf.append(")");
    if (!isConstructor) {
      return access + (access.length() > 0 ? " " : "") + type + " " + name + buf.toString();
    } else {
      return access + (access.length() > 0 ? " " : "") + type + "" + name + buf.toString();
    }
  }

  public static String replace(String str, String old, String new_) {
    int index;
    int old_index;
    try {
      if (str.contains(old)) {
        StringBuilder buf = new StringBuilder();
        old_index = 0;
        while ((index = str.indexOf(old, old_index)) != -1) {
          buf.append(str.substring(old_index, index));
          buf.append(new_);
          old_index = index + old.length();
        }
        buf.append(str.substring(old_index));
        str = buf.toString();
      }
    } catch (StringIndexOutOfBoundsException e) {
      System.err.println(e);
    }
    return str;
  }

  public static String signatureToString(String signature) {
    return signatureToString(signature, true);
  }

  public static String signatureToString(String signature, boolean chopit) {
    wrap(consumed_chars, 1);
    try {
      switch (signature.charAt(0)) {
        case 'B':
          return "byte";
        case 'C':
          return "char";
        case 'D':
          return "double";
        case 'F':
          return "float";
        case 'I':
          return "int";
        case 'J':
          return "long";
        case 'T': {
          int index = signature.indexOf(';');
          if (index < 0) {
            throw new ClassFormatException("Invalid signature: " + signature);
          }
          wrap(consumed_chars, index + 1);
          return compactClassName(signature.substring(1, index), chopit);
        }
        case 'L': {
          int fromIndex = signature.indexOf('<');
          if (fromIndex < 0) {
            fromIndex = 0;
          } else {
            fromIndex = signature.indexOf('>', fromIndex);
            if (fromIndex < 0) {
              throw new ClassFormatException("Invalid signature: " + signature);
            }
          }
          int index = signature.indexOf(';', fromIndex);
          if (index < 0) {
            throw new ClassFormatException("Invalid signature: " + signature);
          }
          int bracketIndex = signature.substring(0, index).indexOf('<');
          if (bracketIndex < 0) {
            wrap(consumed_chars, index + 1);
            return compactClassName(signature.substring(1, index), chopit);
          }
          StringBuilder type = new StringBuilder(compactClassName(signature.substring(1, bracketIndex), chopit)).append("<");
          int consumed_chars = bracketIndex + 1;
          if (signature.charAt(consumed_chars) == '+') {
            type.append("? extends ");
            consumed_chars++;
          } else if (signature.charAt(consumed_chars) == '-') {
            type.append("? super ");
            consumed_chars++;
          } else if (signature.charAt(consumed_chars) == '*') {
            if (signature.charAt(consumed_chars + 1) != '>') {
              throw new ClassFormatException("Invalid signature: " + signature);
            }
            if (signature.charAt(consumed_chars + 2) != ';') {
              throw new ClassFormatException("Invalid signature: " + signature);
            }
            wrap(Utility.consumed_chars, consumed_chars + 3);
            return type + "?>...";
          }
          type.append(signatureToString(signature.substring(consumed_chars), chopit));
          consumed_chars = unwrap(Utility.consumed_chars) + consumed_chars;
          wrap(Utility.consumed_chars, consumed_chars);
          while (signature.charAt(consumed_chars) != '>') {
            type.append(", ").append(signatureToString(signature.substring(consumed_chars), chopit));
            consumed_chars = unwrap(Utility.consumed_chars) + consumed_chars;
            wrap(Utility.consumed_chars, consumed_chars);
          }
          if (signature.charAt(consumed_chars + 1) != ';') {
            throw new ClassFormatException("Invalid signature: " + signature);
          }
          wrap(Utility.consumed_chars, consumed_chars + 2);
          return type.append(">").toString();
        }
        case 'S':
          return "short";
        case 'Z':
          return "boolean";
        case '[': {
          int n;
          StringBuilder brackets;
          String type;
          int consumed_chars;
          brackets = new StringBuilder();
          for (n = 0; signature.charAt(n) == '['; n++) {
            brackets.append("[]");
          }
          consumed_chars = n;
          type = signatureToString(signature.substring(n), chopit);
          int _temp = unwrap(Utility.consumed_chars) + consumed_chars;
          wrap(Utility.consumed_chars, _temp);
          return type + brackets.toString();
        }
        case 'V':
          return "void";
        default:
          throw new ClassFormatException("Invalid signature: `" + signature + "'");
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ClassFormatException("Invalid signature: " + signature, e);
    }
  }

  public static DataTypeConstants typeOfSignature(String signature) throws ClassFormatException {
    try {
      switch (signature.charAt(0)) {
        case 'B':
          return DataTypeConstants.T_BYTE;
        case 'C':
          return DataTypeConstants.T_CHAR;
        case 'D':
          return DataTypeConstants.T_DOUBLE;
        case 'F':
          return DataTypeConstants.T_FLOAT;
        case 'I':
          return DataTypeConstants.T_INT;
        case 'J':
          return DataTypeConstants.T_LONG;
        case 'L':
        case 'T':
          return DataTypeConstants.T_REFERENCE;
        case '[':
          return DataTypeConstants.T_ARRAY;
        case 'V':
          return DataTypeConstants.T_VOID;
        case 'Z':
          return DataTypeConstants.T_BOOLEAN;
        case 'S':
          return DataTypeConstants.T_SHORT;
        case '!':
        case '+':
        case '*':
          return typeOfSignature(signature.substring(1));
        default:
          throw new ClassFormatException("Invalid method signature: " + signature);
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ClassFormatException("Invalid method signature: " + signature, e);
    }
  }

  private static int pow2(int n) {
    return 1 << n;
  }

  private static int unwrap(ThreadLocal<Integer> tl) {
    return tl.get().intValue();
  }

  private static void wrap(ThreadLocal<Integer> tl, int value) {
    tl.set(Integer.valueOf(value));
  }
}
