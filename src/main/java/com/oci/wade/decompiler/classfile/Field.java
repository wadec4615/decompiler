package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.PrintStream;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.attribute.ConstantValue;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.type.Type;
import com.oci.wade.decompiler.util.Utility;

public class Field extends FieldOrMethod {
  public Field(DataInput file, ConstantPool constant_pool, int class_name_index, int superclass_name_index) throws Exception {
    super(file, constant_pool, class_name_index, superclass_name_index);
  }

  public Field(Field c) {
    super(c);
  }

  public Field(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool, int class_name_index, int superclass_name_index) {
    super(access_flags, name_index, signature_index, attributes, constant_pool, class_name_index, superclass_name_index);
  }

  public void decompile(PrintStream out, String indent) {
    String access = Utility.accessToString(super.getAccessFlags());
    access = access.isEmpty() ? "" : access + " ";
    String signature = Utility.signatureToString(getSignature());
    signature = signature.isEmpty() ? "" : signature + " ";
    String name = getName();
    out.print(indent);
    out.print(access);
    out.print(signature);
    out.print(name);
    ConstantValue cv = getConstantValue();
    if (cv != null) {
      out.print(" = ");
      cv.decompile(out, indent);
      out.print(";");
    }
    out.println();
  }

  public ConstantValue getConstantValue() {
    for (Attribute attribute : super.getAttributes()) {
      if (attribute.getTag() == AttributeConstants.ATTR_CONSTANT_VALUE) {
        return (ConstantValue) attribute;
      }
    }
    return null;
  }

  public Type getType() {
    return Type.getReturnType(getSignature());
  }
//    @Override
//    public String toString() {
//        String name;
//        String signature;
//        String access;
//
//        access = Utility.accessToString(super.getAccessFlags());
//        access = access.isEmpty() ? "" : access + " ";
//        signature = Utility.signatureToString(getSignature());
//        name = getName();
//        StringBuilder buf = new StringBuilder(64);
//        buf.append(access).append(signature).append(" ").append(name);
//        ConstantValue cv = getConstantValue();
//        if (cv != null) {
//            buf.append(" = ").append(cv);
//        }
//        for (Attribute attribute : super.getAttributes()) {
//            if (!(attribute instanceof ConstantValue)) {
//                buf.append(" [").append(attribute).append("]");
//            }
//        }
//        return buf.toString();
//    }
}
