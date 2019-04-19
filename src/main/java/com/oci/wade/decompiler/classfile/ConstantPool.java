package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constant.Constant;
import com.oci.wade.decompiler.constant.ConstantCP;
import com.oci.wade.decompiler.constant.ConstantClass;
import com.oci.wade.decompiler.constant.ConstantDouble;
import com.oci.wade.decompiler.constant.ConstantFloat;
import com.oci.wade.decompiler.constant.ConstantInteger;
import com.oci.wade.decompiler.constant.ConstantInvokeDynamic;
import com.oci.wade.decompiler.constant.ConstantLong;
import com.oci.wade.decompiler.constant.ConstantMethodHandle;
import com.oci.wade.decompiler.constant.ConstantMethodType;
import com.oci.wade.decompiler.constant.ConstantNameAndType;
import com.oci.wade.decompiler.constant.ConstantString;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.ReferenceConstants;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.exception.ClassFormatException;
import com.oci.wade.decompiler.util.Utility;

public class ConstantPool implements Cloneable {
    private Constant[] constant_pool;

    public ConstantPool(Constant[] constant_pool) {
	this.constant_pool = constant_pool;
    }

    public ConstantPool(DataInput input) throws IOException, ClassFormatException {
	int constant_pool_count = input.readUnsignedShort();
	constant_pool = new Constant[constant_pool_count];
	for (int i = 1; i < constant_pool_count; i++) {
	    constant_pool[i] = Constant.readConstant(input);
	    TypeConstants tag = constant_pool[i].getTag();
	    if ((tag == TypeConstants.CONSTANT_Double) || (tag == TypeConstants.CONSTANT_Long)) {
		i++;
	    }
	}
    }

    public Long constantToLong(int index, TypeConstants tag) throws ClassFormatException {
	Constant c = getConstant(index, tag);
	return ((ConstantLong) c).getBytes();
    }

    public String constantToString(Constant c) throws ClassFormatException {
	String str;
	int i;
	TypeConstants tag = c.getTag();
	switch (tag) {
	    case CONSTANT_Class:
		i = ((ConstantClass) c).getNameIndex();
		c = getConstant(i, TypeConstants.CONSTANT_Utf8);
		str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
		break;
	    case CONSTANT_String:
		i = ((ConstantString) c).getStringIndex();
		c = getConstant(i, TypeConstants.CONSTANT_Utf8);
		str = "\"" + escape(((ConstantUtf8) c).getBytes()) + "\"";
		break;
	    case CONSTANT_Utf8:
		str = ((ConstantUtf8) c).getBytes();
		break;
	    case CONSTANT_Double:
		str = String.valueOf(((ConstantDouble) c).getBytes());
		break;
	    case CONSTANT_Float:
		str = String.valueOf(((ConstantFloat) c).getBytes());
		break;
	    case CONSTANT_Long:
		str = String.valueOf(((ConstantLong) c).getBytes());
		break;
	    case CONSTANT_Integer:
		str = String.valueOf(((ConstantInteger) c).getBytes());
		break;
	    case CONSTANT_NameAndType:
		str = constantToString(((ConstantNameAndType) c).getNameIndex(), TypeConstants.CONSTANT_Utf8) + ":" + constantToString(((ConstantNameAndType) c).getSignatureIndex(), TypeConstants.CONSTANT_Utf8);
		break;
	    case CONSTANT_InterfaceMethodref:
	    case CONSTANT_Methodref:
	    case CONSTANT_Fieldref:
		str = constantToString(((ConstantCP) c).getClassIndex(), TypeConstants.CONSTANT_Class) + "." + constantToString(((ConstantCP) c).getNameAndTypeIndex(), TypeConstants.CONSTANT_NameAndType);
		break;
	    case CONSTANT_MethodHandle:
		ConstantMethodHandle cmh = (ConstantMethodHandle) c;
		str = ReferenceConstants.getMethodHandleName(cmh.getReferenceKind()) + " " + constantToString(cmh.getReferenceIndex(), getConstant(cmh.getReferenceIndex()).getTag());
		break;
	    case CONSTANT_MethodType:
		ConstantMethodType cmt = (ConstantMethodType) c;
		str = constantToString(cmt.getDescriptorIndex(), TypeConstants.CONSTANT_Utf8);
		break;
	    case CONSTANT_InvokeDynamic:
		ConstantInvokeDynamic cid = (ConstantInvokeDynamic) c;
		str = cid.getBootstrapMethodAttrIndex() + ":" + constantToString(cid.getNameAndTypeIndex(), TypeConstants.CONSTANT_NameAndType);
		break;
	    default:
		throw new RuntimeException("Unknown constant type " + tag);
	}
	return str;
    }

    public String constantToString(int index, TypeConstants tag) throws ClassFormatException {
	Constant c = getConstant(index, tag);
	return constantToString(c);
    }

    public ConstantPool copy() {
	ConstantPool c = null;
	try {
	    c = (ConstantPool) clone();
	    c.constant_pool = new Constant[constant_pool.length];
	    for (int i = 1; i < constant_pool.length; i++) {
		if (constant_pool[i] != null) {
		    c.constant_pool[i] = constant_pool[i].copy();
		}
	    }
	} catch (CloneNotSupportedException e) {
	}
	return c;
    }

    public Constant getConstant(int index) {
	if ((index >= constant_pool.length) || (index < 0)) {
	    throw new ClassFormatException("Invalid constant pool reference: " + index + ". Constant pool size is: " + constant_pool.length);
	}
	return constant_pool[index];
    }

    public Constant getConstant(int index, TypeConstants tag) throws ClassFormatException {
	Constant c = getConstant(index);
	if (c == null) {
	    throw new ClassFormatException("Constant pool at index " + index + " is null.");
	}
	if (c.getTag() != tag) {
	    throw new ClassFormatException("Expected class `" + TypeConstants.getConstantName(tag) + "' at index " + index + " and got " + c);
	}
	return c;
    }

    public Constant[] getConstantPool() {
	return constant_pool;
    }

    public String getConstantString(int index, TypeConstants tag) throws ClassFormatException {
	int i;
	Constant c = getConstant(index, tag);
	switch (tag) {
	    case CONSTANT_Class:
		i = ((ConstantClass) c).getNameIndex();
		break;
	    case CONSTANT_String:
		i = ((ConstantString) c).getStringIndex();
		break;
	    default:
		throw new RuntimeException("getConstantString called with illegal tag " + tag);
	}
	c = getConstant(i, TypeConstants.CONSTANT_Utf8);
	return ((ConstantUtf8) c).getBytes();
    }

    public int getLength() {
	return constant_pool == null ? 0 : constant_pool.length;
    }

    public void setConstant(int index, Constant constant) {
	constant_pool[index] = constant;
    }

    public void setConstantPool(Constant[] constant_pool) {
	this.constant_pool = constant_pool;
    }

    @Override
    public String toString() {
	StringBuilder buf = new StringBuilder();
	for (int i = 1; i < constant_pool.length; i++) {
	    buf.append(i).append(")").append(constant_pool[i]).append("\n");
	}
	return buf.toString();
    }

    private static String escape(String str) {
	int len = str.length();
	StringBuilder buf = new StringBuilder(len + 5);
	char[] ch = str.toCharArray();
	for (int i = 0; i < len; i++) {
	    switch (ch[i]) {
		case '\n':
		    buf.append("\\n");
		    break;
		case '\r':
		    buf.append("\\r");
		    break;
		case '\t':
		    buf.append("\\t");
		    break;
		case '\b':
		    buf.append("\\b");
		    break;
		case '"':
		    buf.append("\\\"");
		    break;
		default:
		    buf.append(ch[i]);
	    }
	}
	return buf.toString();
    }
}
