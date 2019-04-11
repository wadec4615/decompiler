package com.oci.wade.decompiler;

import com.oci.wade.decompiler.classfile.JavaClass;
import com.oci.wade.decompiler.util.RepositoryImpl;
import test.FurstClass;

public class decompiler {
  public static void main(String[] args) throws Exception {
    JavaClass clazz1 = RepositoryImpl.lookupClass(FurstClass.class.getName());
    Decompile dc1 = new Decompile(clazz1);
    dc1.decompile(System.out, "");
//        System.out.println("**************************************************");
//        JavaClass clazz2 = RepositoryImpl.lookupClass(FurstEnum.class.getName());
//        Decompile dc2 = new Decompile(clazz2);
//        dc2.decompile(System.out, "");
  }
}
