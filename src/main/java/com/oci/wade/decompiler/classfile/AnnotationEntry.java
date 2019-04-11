package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.oci.wade.decompiler.attribute.Annotations;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;

public class AnnotationEntry {
  private int type_index;
  private ConstantPool constant_pool;
  private boolean isRuntimeVisible;
  private List<ElementValuePair> element_value_pairs;

  public AnnotationEntry(int type_index, ConstantPool constant_pool, boolean isRuntimeVisible) {
    this.type_index = type_index;
    this.constant_pool = constant_pool;
    this.isRuntimeVisible = isRuntimeVisible;
  }

  public void addElementNameValuePair(ElementValuePair elementNameValuePair) {
    element_value_pairs.add(elementNameValuePair);
  }

  public String getAnnotationType() {
    ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(type_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getAnnotationTypeIndex() {
    return type_index;
  }

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public ElementValuePair[] getElementValuePairs() {
    return element_value_pairs.toArray(new ElementValuePair[element_value_pairs.size()]);
  }

  public int getNumElementValuePairs() {
    return element_value_pairs.size();
  }

  public int getTypeIndex() {
    return type_index;
  }

  public boolean isRuntimeVisible() {
    return isRuntimeVisible;
  }

  public static AnnotationEntry[] createAnnotationEntries(Attribute[] attrs) {
    List<AnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
    for (Attribute attribute : attrs) {
      if (attribute instanceof Annotations) {
        Annotations runtimeAnnotations = (Annotations) attribute;
        Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getAnnotationEntries());
      }
    }
    return accumulatedAnnotations.toArray(new AnnotationEntry[accumulatedAnnotations.size()]);
  }

  public static AnnotationEntry read(DataInput input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
    AnnotationEntry annotationEntry = new AnnotationEntry(input.readUnsignedShort(), constant_pool, isRuntimeVisible);
    int num_element_value_pairs = input.readUnsignedShort();
    annotationEntry.element_value_pairs = new ArrayList<>();
    for (int i = 0; i < num_element_value_pairs; i++) {
      annotationEntry.element_value_pairs.add(new ElementValuePair(input.readUnsignedShort(), ElementValue.readElementValue(input, constant_pool), constant_pool));
    }
    return annotationEntry;
  }
}
