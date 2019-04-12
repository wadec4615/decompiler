package com.oci.wade.decompiler.classfile;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.PrintStream;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.attribute.Code;
import com.oci.wade.decompiler.attribute.ExceptionTable;
import com.oci.wade.decompiler.attribute.LineNumberTable;
import com.oci.wade.decompiler.attribute.LocalVariableTable;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.parsing.InstructionList;
import com.oci.wade.decompiler.type.Type;
import com.oci.wade.decompiler.util.Utility;

public class Method extends FieldOrMethod {
    public Method(DataInput file, ConstantPool constant_pool, int class_name_index, int superclass_name_index) throws Exception {
	super(file, constant_pool, class_name_index, superclass_name_index);
    }

    public Method(Method c) {
	super(c);
    }

    public void decompile(PrintStream out, String indent) throws Exception {
	String access = Utility.accessToString(super.getAccessFlags());
	ConstantPool constantPool = super.getConstantPool();
	String methodSignature = ((ConstantUtf8) constantPool.getConstant(super.getSignatureIndex(), TypeConstants.CONSTANT_Utf8)).getBytes();
	String name = ((ConstantUtf8) constantPool.getConstant(super.getNameIndex(), TypeConstants.CONSTANT_Utf8)).getBytes();
	String class_name = constant_pool.getConstantString(super.getClassNameIndex(), TypeConstants.CONSTANT_Class);
	String super_class_name = constant_pool.getConstantString(super.getSuperClassNameIndex(), TypeConstants.CONSTANT_Class);
	String signature;
	if ("<init>".equals(name)) {
	    name = Utility.getConstructorName(class_name, '/');
	    signature = Utility.methodSignatureToString(methodSignature, name, access, true, getLocalVariableTable(), true);
	} else {
	    signature = Utility.methodSignatureToString(methodSignature, name, access, true, getLocalVariableTable(), false);
	}
	ExceptionTable e = getExceptionTable();
	if (e != null) {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    e.decompile(new PrintStream(baos), indent);
	    String str = baos.toString();
	    signature += " throws " + str + " ";
	}
	out.println(indent + "/*");
	for (Attribute attribute : super.getAttributes()) {
	    out.print(attribute.toString());
	}
	out.println(indent + "*/");
	out.print(indent + signature + "{");
	decompileCode(out, indent, class_name, super_class_name);
	out.println(indent + "}\n");
    }

    private void decompileCode(PrintStream out, String indent, String className, String superClassName) throws Exception {
	byte[] code = getCode().getCode();
	Instruction[] instructions = Utility.codeToInstructions(code, getConstantPool(), code.length);
	InstructionList instructionList = new InstructionList(instructions, getConstant_pool(), getLocalVariableTable());
	instructionList.convertToExpressionList(className, superClassName.replace("/", "."));
	instructionList.decompile(out, indent);
    }

    public Type[] getArgumentTypes() {
	return Type.getArgumentTypes(getSignature());
    }

    public Code getCode() {
	for (Attribute attribute : super.getAttributes()) {
	    if (attribute instanceof Code) {
		return (Code) attribute;
	    }
	}
	return null;
    }

    public ExceptionTable getExceptionTable() {
	for (Attribute attribute : super.getAttributes()) {
	    if (attribute instanceof ExceptionTable) {
		return (ExceptionTable) attribute;
	    }
	}
	return null;
    }

    public LineNumberTable getLineNumberTable() {
	Code code = getCode();
	if (code == null) {
	    return null;
	}
	return code.getLineNumberTable();
    }

    public LocalVariableTable getLocalVariableTable() {
	Code code = getCode();
	if (code == null) {
	    return null;
	}
	return code.getLocalVariableTable();
    }

    public Type getReturnType() {
	return Type.getReturnType(getSignature());
    }

    @Override
    public String toString() {
	String access = Utility.accessToString(super.getAccessFlags());
	String signature = ((ConstantUtf8) super.getConstantPool().getConstant(super.getSignatureIndex(), TypeConstants.CONSTANT_Utf8)).getBytes();
	String name = ((ConstantUtf8) super.getConstantPool().getConstant(super.getNameIndex(), TypeConstants.CONSTANT_Utf8)).getBytes();
	signature = Utility.methodSignatureToString(signature, name, access, true, getLocalVariableTable(), false);
	StringBuilder buf = new StringBuilder(signature);
	for (Attribute attribute : super.getAttributes()) {
	    if (!((attribute instanceof Code) || (attribute instanceof ExceptionTable))) {
		buf.append(" [").append(attribute).append("]");
	    }
	}
	ExceptionTable e = getExceptionTable();
	if (e != null) {
	    String str = e.toString();
	    if (!str.isEmpty()) {
		buf.append("\n\t\tthrows ").append(str);
	    }
	}
	return buf.toString();
    }
}
