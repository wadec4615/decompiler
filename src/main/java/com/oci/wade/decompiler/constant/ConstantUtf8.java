package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.util.Utility;

public class ConstantUtf8 extends Constant {
  private static class CACHE_HOLDER {
    private static int MAX_CACHE_ENTRIES = 20000;
    private static int INITIAL_CACHE_CAPACITY = (int) (MAX_CACHE_ENTRIES / 0.75);
    private static HashMap<String, ConstantUtf8> CACHE = new LinkedHashMap<String, ConstantUtf8>(INITIAL_CACHE_CAPACITY, 0.75f, true) {
      private static final long serialVersionUID = -8506975356158971766L;

      @Override
      protected boolean removeEldestEntry(Map.Entry<String, ConstantUtf8> eldest) {
        return size() > MAX_CACHE_ENTRIES;
      }
    };
  }
  private static volatile int considered = 0;
  private static volatile int hits = 0;
  private static volatile int skipped = 0;
  private static volatile int created = 0;
  private static int MAX_CACHED_SIZE = Integer.getInteger("bcel.maxcached.size", 200).intValue();
  private static boolean BCEL_STATISTICS = Boolean.getBoolean("bcel.statistics");
  static {
    if (BCEL_STATISTICS) {
      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
          printStats();
        }
      });
    }
  }
  private String bytes;

  public ConstantUtf8(ConstantUtf8 c) {
    this(c.getBytes());
  }

  public ConstantUtf8(DataInput file) throws IOException {
    super(TypeConstants.CONSTANT_Utf8);
    bytes = file.readUTF();
    created++;
  }

  public ConstantUtf8(String bytes) {
    super(TypeConstants.CONSTANT_Utf8);
    if (bytes == null) {
      throw new IllegalArgumentException("bytes must not be null!");
    }
    this.bytes = bytes;
    created++;
  }

  public String getBytes() {
    return bytes;
  }

  @Override
  public String toString() {
    return super.toString() + "(\"" + Utility.replace(bytes, "\n", "\\n") + "\")";
  }

  static void clearStats() {
    hits = considered = skipped = created = 0;
  }

  public static ConstantUtf8 getCachedInstance(String s) {
    if (s.length() > MAX_CACHED_SIZE) {
      skipped++;
      return new ConstantUtf8(s);
    }
    considered++;
    synchronized (ConstantUtf8.class) {
      ConstantUtf8 result = CACHE_HOLDER.CACHE.get(s);
      if (result != null) {
        hits++;
        return result;
      }
      result = new ConstantUtf8(s);
      CACHE_HOLDER.CACHE.put(s, result);
      return result;
    }
  }

  public static ConstantUtf8 getInstance(DataInput input) throws IOException {
    return getInstance(input.readUTF());
  }

  public static ConstantUtf8 getInstance(String s) {
    return new ConstantUtf8(s);
  }

  static void printStats() {
    System.err.println("Cache hit " + hits + "/" + considered + ", " + skipped + " skipped");
    System.err.println("Total of " + created + " ConstantUtf8 objects created");
  }
}
