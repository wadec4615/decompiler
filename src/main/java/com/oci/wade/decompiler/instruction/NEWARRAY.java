package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.AllocationInstruction;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.StackProducer;
import com.oci.wade.decompiler.type.ArrayType;
import com.oci.wade.decompiler.type.BasicType;
import com.oci.wade.decompiler.type.Type;

public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {
  private DataTypeConstants type;

  public NEWARRAY(BasicType type) {
    this(type.getType());
  }

  public NEWARRAY(DataTypeConstants type) {
    super(Opcode.NEWARRAY, (short) 2);
    this.type = type;
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION };
  }

  public Type getType() {
    return new ArrayType(BasicType.getType(type), 1);
  }

  public DataTypeConstants getTypecode() {
    return type;
  }
}
