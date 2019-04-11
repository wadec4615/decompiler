package com.oci.wade.decompiler.classfile;

import com.oci.wade.decompiler.constants.AccessConstants;

public abstract class AccessFlags {
  protected int access_flags;

  public AccessFlags() {
  }

  public AccessFlags(int a) {
    access_flags = a;
  }

  public int getAccessFlags() {
    return access_flags;
  }

  public int getModifiers() {
    return access_flags;
  }

  public boolean is() {
    return (access_flags & AccessConstants.ACC_.getValue()) != 0;
  }

  public void is(boolean flag) {
    setFlag(AccessConstants.ACC_.getValue(), flag);
  }

  public boolean isAbstract() {
    return (access_flags & AccessConstants.ACC_ABSTRACT.getValue()) != 0;
  }

  public void isAbstract(boolean flag) {
    setFlag(AccessConstants.ACC_ABSTRACT.getValue(), flag);
  }

  public boolean isAnnotation() {
    return (access_flags & AccessConstants.ACC_ANNOTATION.getValue()) != 0;
  }

  public void isAnnotation(boolean flag) {
    setFlag(AccessConstants.ACC_ANNOTATION.getValue(), flag);
  }

  public boolean isEnum() {
    return (access_flags & AccessConstants.ACC_ENUM.getValue()) != 0;
  }

  public void isEnum(boolean flag) {
    setFlag(AccessConstants.ACC_ENUM.getValue(), flag);
  }

  public boolean isInterface() {
    return (access_flags & AccessConstants.ACC_INTERFACE.getValue()) != 0;
  }

  public void isInterface(boolean flag) {
    setFlag(AccessConstants.ACC_INTERFACE.getValue(), flag);
  }

  public boolean isNative() {
    return (access_flags & AccessConstants.ACC_NATIVE.getValue()) != 0;
  }

  public void isNative(boolean flag) {
    setFlag(AccessConstants.ACC_NATIVE.getValue(), flag);
  }

  public boolean isPrivate() {
    return (access_flags & AccessConstants.ACC_PRIVATE.getValue()) != 0;
  }

  public void isPrivate(boolean flag) {
    setFlag(AccessConstants.ACC_PRIVATE.getValue(), flag);
  }

  public boolean isProtected() {
    return (access_flags & AccessConstants.ACC_PROTECTED.getValue()) != 0;
  }

  public void isProtected(boolean flag) {
    setFlag(AccessConstants.ACC_PROTECTED.getValue(), flag);
  }

  public boolean isPublic() {
    return (access_flags & AccessConstants.ACC_PUBLIC.getValue()) != 0;
  }

  public void isPublic(boolean flag) {
    setFlag(AccessConstants.ACC_PUBLIC.getValue(), flag);
  }

  public boolean isStatic() {
    return (access_flags & AccessConstants.ACC_STATIC.getValue()) != 0;
  }

  public void isStatic(boolean flag) {
    setFlag(AccessConstants.ACC_STATIC.getValue(), flag);
  }

  public boolean isStrictfp() {
    return (access_flags & AccessConstants.ACC_STRICT.getValue()) != 0;
  }

  public void isStrictfp(boolean flag) {
    setFlag(AccessConstants.ACC_STRICT.getValue(), flag);
  }

  public boolean isSynchronized() {
    return (access_flags & AccessConstants.ACC_SYNCHRONIZED.getValue()) != 0;
  }

  public void isSynchronized(boolean flag) {
    setFlag(AccessConstants.ACC_SYNCHRONIZED.getValue(), flag);
  }

  public boolean isSynthetic() {
    return (access_flags & AccessConstants.ACC_SYNTHETIC.getValue()) != 0;
  }

  public void isSynthetic(boolean flag) {
    setFlag(AccessConstants.ACC_SYNTHETIC.getValue(), flag);
  }

  public boolean isTransient() {
    return (access_flags & AccessConstants.ACC_TRANSIENT.getValue()) != 0;
  }

  public void isTransient(boolean flag) {
    setFlag(AccessConstants.ACC_TRANSIENT.getValue(), flag);
  }

  public boolean isVarArgs() {
    return (access_flags & AccessConstants.ACC_VARARGS.getValue()) != 0;
  }

  public void isVarArgs(boolean flag) {
    setFlag(AccessConstants.ACC_VARARGS.getValue(), flag);
  }

  public boolean isVolatile() {
    return (access_flags & AccessConstants.ACC_VOLATILE.getValue()) != 0;
  }

  public void isVolatile(boolean flag) {
    setFlag(AccessConstants.ACC_VOLATILE.getValue(), flag);
  }

  public void setAccessFlags(int access_flags) {
    this.access_flags = access_flags;
  }

  private void setFlag(int flag, boolean set) {
    if ((access_flags & flag) != 0) {
      if (!set) {
        access_flags ^= flag;
      }
    } else {
      if (set) {
        access_flags |= flag;
      }
    }
  }

  public void setModifiers(int access_flags) {
    setAccessFlags(access_flags);
  }
}
