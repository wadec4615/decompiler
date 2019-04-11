package com.oci.wade.decompiler.classfile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.constants.AccessConstants;
import com.oci.wade.decompiler.constants.Const;
import com.oci.wade.decompiler.exception.ClassFormatException;

public class ClassParser {
  private static int BUFSIZE = 8192;
  private DataInputStream dataInputStream;
  private boolean fileOwned;
  private String file_name;
  private String zip_file;
  private int class_name_index;
  private int superclass_name_index;
  private int major;
  private int minor;
  private int access_flags;
  private int[] interfaces;
  private ConstantPool constant_pool;
  private Field[] fields;
  private Method[] methods;
  private Attribute[] attributes;
  private boolean is_zip;

  public ClassParser(InputStream inputStream, String file_name) {
    this.file_name = file_name;
    fileOwned = false;
    String clazz = inputStream.getClass().getName();
    is_zip = clazz.startsWith("java.util.zip.") || clazz.startsWith("java.util.jar.");
    if (inputStream instanceof DataInputStream) {
      dataInputStream = (DataInputStream) inputStream;
    } else {
      dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, BUFSIZE));
    }
  }

  public ClassParser(String file_name) {
    is_zip = false;
    this.file_name = file_name;
    fileOwned = true;
  }

  public ClassParser(String zip_file, String file_name) {
    is_zip = true;
    fileOwned = true;
    this.zip_file = zip_file;
    this.file_name = file_name;
  }

  public JavaClass parse() throws Exception {
    ZipFile zip = null;
    try {
      if (fileOwned) {
        if (is_zip) {
          zip = new ZipFile(zip_file);
          ZipEntry entry = zip.getEntry(file_name);
          if (entry == null) {
            throw new IOException("File " + file_name + " not found");
          }
          dataInputStream = new DataInputStream(new BufferedInputStream(zip.getInputStream(entry), BUFSIZE));
        } else {
          dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file_name), BUFSIZE));
        }
      }
      readID();
      readVersion();
      readConstantPool();
      readClassInfo();
      readInterfaces();
      readFields();
      readMethods();
      readAttributes();
    } finally {
      if (fileOwned) {
        try {
          if (dataInputStream != null) {
            dataInputStream.close();
          }
        } catch (IOException ioe) {
        }
      }
      try {
        if (zip != null) {
          zip.close();
        }
      } catch (IOException ioe) {
      }
    }
    return new JavaClass(class_name_index, superclass_name_index, file_name, major, minor, access_flags, constant_pool, interfaces, fields, methods, attributes, is_zip ? JavaClass.ZIP : JavaClass.FILE);
  }

  private void readAttributes() throws Exception {
    int attributes_count = dataInputStream.readUnsignedShort();
    attributes = new Attribute[attributes_count];
    for (int i = 0; i < attributes_count; i++) {
      attributes[i] = Attribute.readAttribute(dataInputStream, constant_pool);
    }
  }

  private void readClassInfo() throws IOException, ClassFormatException {
    access_flags = dataInputStream.readUnsignedShort();
    if ((access_flags & AccessConstants.ACC_INTERFACE.getValue()) != 0) {
      access_flags |= AccessConstants.ACC_ABSTRACT.getValue();
    }
    if (((access_flags & AccessConstants.ACC_ABSTRACT.getValue()) != 0) && ((access_flags & AccessConstants.ACC_.getValue()) != 0)) {
      throw new ClassFormatException("Class " + file_name + " can't be both  and abstract");
    }
    class_name_index = dataInputStream.readUnsignedShort();
    superclass_name_index = dataInputStream.readUnsignedShort();
  }

  private void readConstantPool() throws IOException, ClassFormatException {
    constant_pool = new ConstantPool(dataInputStream);
  }

  private void readFields() throws Exception {
    int fields_count = dataInputStream.readUnsignedShort();
    fields = new Field[fields_count];
    for (int i = 0; i < fields_count; i++) {
      fields[i] = new Field(dataInputStream, constant_pool, class_name_index, superclass_name_index);
    }
  }

  private void readID() throws Exception {
    if (dataInputStream.readInt() != Const.JVM_CLASSFILE_MAGIC) {
      throw new ClassFormatException(file_name + " is not a Java .class file");
    }
  }

  private void readInterfaces() throws IOException, ClassFormatException {
    int interfaces_count = dataInputStream.readUnsignedShort();
    interfaces = new int[interfaces_count];
    for (int i = 0; i < interfaces_count; i++) {
      interfaces[i] = dataInputStream.readUnsignedShort();
    }
  }

  private void readMethods() throws Exception {
    int methods_count = dataInputStream.readUnsignedShort();
    methods = new Method[methods_count];
    for (int i = 0; i < methods_count; i++) {
      methods[i] = new Method(dataInputStream, constant_pool, class_name_index, superclass_name_index);
    }
  }

  private void readVersion() throws IOException, ClassFormatException {
    minor = dataInputStream.readUnsignedShort();
    major = dataInputStream.readUnsignedShort();
  }
}
