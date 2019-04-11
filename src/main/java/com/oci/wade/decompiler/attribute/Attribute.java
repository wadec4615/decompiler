package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.UnknownAttributeReader;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public abstract class Attribute implements Cloneable {
  private static Map<String, Object> readers = new HashMap<>();
  protected int name_index;
  protected int length;
  protected AttributeConstants tag;
  protected ConstantPool constant_pool;

  protected Attribute(AttributeConstants tag, int name_index, int length, ConstantPool constant_pool) {
    this.tag = tag;
    this.name_index = name_index;
    this.length = length;
    this.constant_pool = constant_pool;
  }

  public abstract void decompile(PrintStream out, String indent);

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public int getLength() {
    return length;
  }

  public String getName() {
    ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getNameIndex() {
    return name_index;
  }

  public AttributeConstants getTag() {
    return tag;
  }

  public void setConstantPool(ConstantPool constant_pool) {
    this.constant_pool = constant_pool;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public void setNameIndex(int name_index) {
    this.name_index = name_index;
  }

  public static void addAttributeReader(String name, AttributeReader r) {
    readers.put(name, r);
  }

  public static void addAttributeReader(String name, UnknownAttributeReader r) {
    readers.put(name, r);
  }

  public static Attribute readAttribute(DataInput file, ConstantPool constant_pool) throws Exception {
    AttributeConstants tag = AttributeConstants.ATTR_UNKNOWN;
    int name_index = file.readUnsignedShort();
    ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, TypeConstants.CONSTANT_Utf8);
    String name = c.getBytes();
    int length = file.readInt();
    for (byte i = 0; i < AttributeConstants.KNOWN_ATTRIBUTES.getValue(); i++) {
      if (name.equals(AttributeConstants.getAttributeName(i))) {
        tag = AttributeConstants.convert(i);
        break;
      }
    }
    switch (tag) {
      case ATTR_UNKNOWN:
        Object r = readers.get(name);
        if (r instanceof UnknownAttributeReader) {
          return ((UnknownAttributeReader) r).createAttribute(name_index, length, file, constant_pool);
        }
        return new Unknown(name_index, length, file, constant_pool);
      case ATTR_CONSTANT_VALUE:
        return new ConstantValue(name_index, length, file, constant_pool);
      case ATTR_SOURCE_FILE:
        return new SourceFile(name_index, length, file, constant_pool);
      case ATTR_CODE:
        return new Code(name_index, length, file, constant_pool);
      case ATTR_EXCEPTIONS:
        return new ExceptionTable(name_index, length, file, constant_pool);
      case ATTR_LINE_NUMBER_TABLE:
        return new LineNumberTable(name_index, length, file, constant_pool);
      case ATTR_LOCAL_VARIABLE_TABLE:
        return new LocalVariableTable(name_index, length, file, constant_pool);
      case ATTR_INNER_CLASSES:
        return new InnerClasses(name_index, length, file, constant_pool);
      case ATTR_SYNTHETIC:
        return new Synthetic(name_index, length, file, constant_pool);
      case ATTR_DEPRECATED:
        return new Deprecated(name_index, length, file, constant_pool);
      case ATTR_PMG:
        return new PMGClass(name_index, length, file, constant_pool);
      case ATTR_SIGNATURE:
        return new Signature(name_index, length, file, constant_pool);
      case ATTR_STACK_MAP:
        return new StackMap(name_index, length, file, constant_pool);
      case ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
        return new RuntimeVisibleAnnotations(name_index, length, file, constant_pool);
      case ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
        return new RuntimeInvisibleAnnotations(name_index, length, file, constant_pool);
      case ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
        return new RuntimeVisibleParameterAnnotations(name_index, length, file, constant_pool);
      case ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
        return new RuntimeInvisibleParameterAnnotations(name_index, length, file, constant_pool);
      case ATTR_ANNOTATION_DEFAULT:
        return new AnnotationDefault(name_index, length, file, constant_pool);
      case ATTR_LOCAL_VARIABLE_TYPE_TABLE:
        return new LocalVariableTypeTable(name_index, length, file, constant_pool);
      case ATTR_ENCLOSING_METHOD:
        return new EnclosingMethod(name_index, length, file, constant_pool);
      case ATTR_STACK_MAP_TABLE:
        return new StackMap(name_index, length, file, constant_pool);
      case ATTR_BOOTSTRAP_METHODS:
        return new BootstrapMethods(name_index, length, file, constant_pool);
      case ATTR_METHOD_PARAMETERS:
        return new MethodParameters(name_index, length, file, constant_pool);
      default:
        throw new IllegalStateException("Unrecognized attribute type tag parsed: " + tag);
    }
  }

  public static Attribute readAttribute(DataInputStream file, ConstantPool constant_pool) throws Exception {
    return readAttribute((DataInput) file, constant_pool);
  }

  public static void removeAttributeReader(String name) {
    readers.remove(name);
  }
}
