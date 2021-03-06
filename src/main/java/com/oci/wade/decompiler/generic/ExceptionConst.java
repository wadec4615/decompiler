package com.oci.wade.decompiler.generic;

public class ExceptionConst {
  public enum EXCS {
    EXCS_CLASS_AND_INTERFACE_RESOLUTION, EXCS_FIELD_AND_METHOD_RESOLUTION, EXCS_INTERFACE_METHOD_RESOLUTION, EXCS_STRING_RESOLUTION, EXCS_ARRAY_EXCEPTION,
  }
  public static Class<Throwable> THROWABLE = Throwable.class;
  public static Class<RuntimeException> RUNTIME_EXCEPTION = RuntimeException.class;
  public static Class<LinkageError> LINKING_EXCEPTION = LinkageError.class;
  public static Class<ClassCircularityError> CLASS_CIRCULARITY_ERROR = ClassCircularityError.class;
  public static Class<ClassFormatError> CLASS_FORMAT_ERROR = ClassFormatError.class;
  public static Class<ExceptionInInitializerError> EXCEPTION_IN_INITIALIZER_ERROR = ExceptionInInitializerError.class;
  public static Class<IncompatibleClassChangeError> INCOMPATIBLE_CLASS_CHANGE_ERROR = IncompatibleClassChangeError.class;
  public static Class<AbstractMethodError> ABSTRACT_METHOD_ERROR = AbstractMethodError.class;
  public static Class<IllegalAccessError> ILLEGAL_ACCESS_ERROR = IllegalAccessError.class;
  public static Class<InstantiationError> INSTANTIATION_ERROR = InstantiationError.class;
  public static Class<NoSuchFieldError> NO_SUCH_FIELD_ERROR = NoSuchFieldError.class;
  public static Class<NoSuchMethodError> NO_SUCH_METHOD_ERROR = NoSuchMethodError.class;
  public static Class<NoClassDefFoundError> NO_CLASS_DEF_FOUND_ERROR = NoClassDefFoundError.class;
  public static Class<UnsatisfiedLinkError> UNSATISFIED_LINK_ERROR = UnsatisfiedLinkError.class;
  public static Class<VerifyError> VERIFY_ERROR = VerifyError.class;
  public static Class<NullPointerException> NULL_POINTER_EXCEPTION = NullPointerException.class;
  public static Class<ArrayIndexOutOfBoundsException> ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = ArrayIndexOutOfBoundsException.class;
  public static Class<ArithmeticException> ARITHMETIC_EXCEPTION = ArithmeticException.class;
  public static Class<NegativeArraySizeException> NEGATIVE_ARRAY_SIZE_EXCEPTION = NegativeArraySizeException.class;
  public static Class<ClassCastException> CLASS_CAST_EXCEPTION = ClassCastException.class;
  public static Class<IllegalMonitorStateException> ILLEGAL_MONITOR_STATE = IllegalMonitorStateException.class;
  private static Class<?>[] EXCS_CLASS_AND_INTERFACE_RESOLUTION = { NO_CLASS_DEF_FOUND_ERROR, CLASS_FORMAT_ERROR, VERIFY_ERROR, ABSTRACT_METHOD_ERROR, EXCEPTION_IN_INITIALIZER_ERROR, ILLEGAL_ACCESS_ERROR };
  private static Class<?>[] EXCS_FIELD_AND_METHOD_RESOLUTION = { NO_SUCH_FIELD_ERROR, ILLEGAL_ACCESS_ERROR, NO_SUCH_METHOD_ERROR };
  private static Class<?>[] EXCS_INTERFACE_METHOD_RESOLUTION = new Class[0];
  private static Class<?>[] EXCS_STRING_RESOLUTION = new Class[0];
  private static Class<?>[] EXCS_ARRAY_EXCEPTION = { NULL_POINTER_EXCEPTION, ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION };

  public static Class<?>[] createExceptions(EXCS type, Class<?>... extraClasses) {
    switch (type) {
      case EXCS_CLASS_AND_INTERFACE_RESOLUTION:
        return mergeExceptions(EXCS_CLASS_AND_INTERFACE_RESOLUTION, extraClasses);
      case EXCS_ARRAY_EXCEPTION:
        return mergeExceptions(EXCS_ARRAY_EXCEPTION, extraClasses);
      case EXCS_FIELD_AND_METHOD_RESOLUTION:
        return mergeExceptions(EXCS_FIELD_AND_METHOD_RESOLUTION, extraClasses);
      case EXCS_INTERFACE_METHOD_RESOLUTION:
        return mergeExceptions(EXCS_INTERFACE_METHOD_RESOLUTION, extraClasses);
      case EXCS_STRING_RESOLUTION:
        return mergeExceptions(EXCS_STRING_RESOLUTION, extraClasses);
      default:
        throw new AssertionError("Cannot happen; unexpected enum value: " + type);
    }
  }

  private static Class<?>[] mergeExceptions(Class<?>[] input, Class<?>... extraClasses) {
    int extraLen = extraClasses == null ? 0 : extraClasses.length;
    Class<?>[] excs = new Class<?>[input.length + extraLen];
    System.arraycopy(input, 0, excs, 0, input.length);
    if (extraLen > 0) {
      System.arraycopy(extraClasses, 0, excs, input.length, extraLen);
    }
    return excs;
  }
}
