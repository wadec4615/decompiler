package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataInputStream;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.attribute.Signature;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;

public abstract class FieldOrMethod extends AccessFlags implements Cloneable {
  protected int name_index;
  protected int signature_index;
  protected Attribute[] attributes;
  protected int attributes_count;
  private AnnotationEntry[] annotationEntries;
  protected ConstantPool constant_pool;
  private String signatureAttributeString = null;
  private boolean searchedForSignatureAttribute = false;
  private int classNameIndex;
  private int superClassNameIndex;

  public FieldOrMethod() {
  }

  protected FieldOrMethod(DataInput file, ConstantPool constant_pool, int class_name_index, int superclass_name_index) throws Exception {
    this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool, class_name_index, superclass_name_index);
    int attributes_count = file.readUnsignedShort();
    attributes = new Attribute[attributes_count];
    for (int i = 0; i < attributes_count; i++) {
      attributes[i] = Attribute.readAttribute(file, constant_pool);
    }
    this.attributes_count = attributes_count;
  }

  protected FieldOrMethod(DataInputStream file, ConstantPool constant_pool, int class_name_index, int superclass_name_index) throws Exception {
    this((DataInput) file, constant_pool, class_name_index, superclass_name_index);
  }

  protected FieldOrMethod(FieldOrMethod c) {
    this(c.getAccessFlags(), c.getNameIndex(), c.getSignatureIndex(), c.getAttributes(), c.getConstantPool(), c.getClassNameIndex(), c.getSuperClassNameIndex());
  }

  protected FieldOrMethod(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool, int class_name_index, int superclass_name_index) {
    super(access_flags);
    this.name_index = name_index;
    this.signature_index = signature_index;
    this.constant_pool = constant_pool;
    classNameIndex = class_name_index;
    superClassNameIndex = superclass_name_index;
    setAttributes(attributes);
  }

  public AnnotationEntry[] getAnnotationEntries() {
    if (annotationEntries == null) {
      annotationEntries = AnnotationEntry.createAnnotationEntries(getAttributes());
    }
    return annotationEntries;
  }

  public Attribute[] getAttributes() {
    return attributes;
  }

  public int getAttributes_count() {
    return attributes_count;
  }

  public int getClassNameIndex() {
    return classNameIndex;
  }

  public ConstantPool getConstant_pool() {
    return constant_pool;
  }

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public String getGenericSignature() {
    if (!searchedForSignatureAttribute) {
      boolean found = false;
      for (int i = 0; !found && (i < attributes.length); i++) {
        if (attributes[i] instanceof Signature) {
          signatureAttributeString = ((Signature) attributes[i]).getSignature();
          found = true;
        }
      }
      searchedForSignatureAttribute = true;
    }
    return signatureAttributeString;
  }

  public String getName() {
    ConstantUtf8 c;
    c = (ConstantUtf8) constant_pool.getConstant(name_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getName_index() {
    return name_index;
  }

  public int getNameIndex() {
    return name_index;
  }

  public String getSignature() {
    ConstantUtf8 c;
    c = (ConstantUtf8) constant_pool.getConstant(signature_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getSignature_index() {
    return signature_index;
  }

  public String getSignatureAttributeString() {
    return signatureAttributeString;
  }

  public int getSignatureIndex() {
    return signature_index;
  }

  public int getSuperClassNameIndex() {
    return superClassNameIndex;
  }

  public boolean isSearchedForSignatureAttribute() {
    return searchedForSignatureAttribute;
  }

  public void setAttributes(Attribute[] attributes) {
    this.attributes = attributes;
    attributes_count = attributes != null ? attributes.length : 0;
  }

  public void setConstantPool(ConstantPool constant_pool) {
    this.constant_pool = constant_pool;
  }

  public void setNameIndex(int name_index) {
    this.name_index = name_index;
  }

  public void setSignatureIndex(int signature_index) {
    this.signature_index = signature_index;
  }
}
