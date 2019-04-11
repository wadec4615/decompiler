package com.oci.wade.decompiler.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ClassPath {
  public interface ClassFile {
    String getBase();

    InputStream getInputStream() throws IOException;

    String getPath();

    long getSize();

    long getTime();
  }

  private static class Dir extends PathEntry {
    private String dir;

    Dir(String d) {
      dir = d;
    }

    @Override
    ClassFile getClassFile(String name, String suffix) throws IOException {
      File file = new File(dir + File.separatorChar + name.replace('.', File.separatorChar) + suffix);
      return file.exists() ? new ClassFile() {
        @Override
        public String getBase() {
          return dir;
        }

        @Override
        public InputStream getInputStream() throws IOException {
          return new FileInputStream(file);
        }

        @Override
        public String getPath() {
          try {
            return file.getCanonicalPath();
          } catch (IOException e) {
            return null;
          }
        }

        @Override
        public long getSize() {
          return file.length();
        }

        @Override
        public long getTime() {
          return file.lastModified();
        }
      } : null;
    }

    @Override
    URL getResource(String name) {
      File file = new File(dir + File.separatorChar + name.replace('/', File.separatorChar));
      try {
        return file.exists() ? file.toURI().toURL() : null;
      } catch (MalformedURLException e) {
        return null;
      }
    }

    @Override
    InputStream getResourceAsStream(String name) {
      File file = new File(dir + File.separatorChar + name.replace('/', File.separatorChar));
      try {
        return file.exists() ? new FileInputStream(file) : null;
      } catch (IOException e) {
        return null;
      }
    }

    @Override
    public String toString() {
      return dir;
    }
  }

  private abstract static class PathEntry {
    abstract ClassFile getClassFile(String name, String suffix) throws IOException;

    abstract URL getResource(String name);

    abstract InputStream getResourceAsStream(String name);
  }

  private static class Zip extends PathEntry {
    private ZipFile zip;

    Zip(ZipFile z) {
      zip = z;
    }

    @Override
    ClassFile getClassFile(String name, String suffix) throws IOException {
      ZipEntry entry = zip.getEntry(name.replace('.', '/') + suffix);
      if (entry == null) {
        return null;
      }
      return new ClassFile() {
        @Override
        public String getBase() {
          return zip.getName();
        }

        @Override
        public InputStream getInputStream() throws IOException {
          return zip.getInputStream(entry);
        }

        @Override
        public String getPath() {
          return entry.toString();
        }

        @Override
        public long getSize() {
          return entry.getSize();
        }

        @Override
        public long getTime() {
          return entry.getTime();
        }
      };
    }

    @Override
    URL getResource(String name) {
      ZipEntry entry = zip.getEntry(name);
      try {
        return entry != null ? new URL("jar:file:" + zip.getName() + "!/" + name) : null;
      } catch (MalformedURLException e) {
        return null;
      }
    }

    @Override
    InputStream getResourceAsStream(String name) {
      ZipEntry entry = zip.getEntry(name);
      try {
        return entry != null ? zip.getInputStream(entry) : null;
      } catch (IOException e) {
        return null;
      }
    }
  }
  public static ClassPath SYSTEM_CLASS_PATH = new ClassPath(getClassPath());
  private static FilenameFilter ARCHIVE_FILTER = new FilenameFilter() {
    @Override
    public boolean accept(File dir, String name) {
      name = name.toLowerCase(Locale.ENGLISH);
      return name.endsWith(".zip") || name.endsWith(".jar");
    }
  };
  private PathEntry[] paths;
  private String class_path;
  private ClassPath parent;

  @Deprecated
  public ClassPath() {
    this(getClassPath());
  }

  public ClassPath(ClassPath parent, String class_path) {
    this(class_path);
    this.parent = parent;
  }

  public ClassPath(String class_path) {
    this.class_path = class_path;
    List<PathEntry> list = new ArrayList<>();
    for (StringTokenizer tok = new StringTokenizer(class_path, File.pathSeparator); tok.hasMoreTokens();) {
      String path = tok.nextToken();
      if (!path.isEmpty()) {
        File file = new File(path);
        try {
          if (file.exists()) {
            if (file.isDirectory()) {
              list.add(new Dir(path));
            } else {
              list.add(new Zip(new ZipFile(file)));
            }
          }
        } catch (IOException e) {
          if (path.endsWith(".zip") || path.endsWith(".jar")) {
            System.err.println("CLASSPATH component " + file + ": " + e);
          }
        }
      }
    }
    paths = new PathEntry[list.size()];
    list.toArray(paths);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ClassPath) {
      ClassPath cp = (ClassPath) o;
      return class_path.equals(cp.toString());
    }
    return false;
  }

  public byte[] getBytes(String name) throws IOException {
    return getBytes(name, ".class");
  }

  public byte[] getBytes(String name, String suffix) throws IOException {
    DataInputStream dis = null;
    try (InputStream is = getInputStream(name, suffix)) {
      if (is == null) {
        throw new IOException("Couldn't find: " + name + suffix);
      }
      dis = new DataInputStream(is);
      byte[] bytes = new byte[is.available()];
      dis.readFully(bytes);
      return bytes;
    } finally {
      if (dis != null) {
        dis.close();
      }
    }
  }

  public ClassFile getClassFile(String name) throws IOException {
    return getClassFile(name, ".class");
  }

  public ClassFile getClassFile(String name, String suffix) throws IOException {
    ClassFile cf = null;
    if (parent != null) {
      cf = parent.getClassFileInternal(name, suffix);
    }
    if (cf == null) {
      cf = getClassFileInternal(name, suffix);
    }
    if (cf != null) {
      return cf;
    }
    throw new IOException("Couldn't find: " + name + suffix);
  }

  private ClassFile getClassFileInternal(String name, String suffix) throws IOException {
    for (PathEntry path : paths) {
      ClassFile cf = path.getClassFile(name, suffix);
      if (cf != null) {
        return cf;
      }
    }
    return null;
  }

  public InputStream getInputStream(String name) throws IOException {
    return getInputStream(name.replace('.', '/'), ".class");
  }

  public InputStream getInputStream(String name, String suffix) throws IOException {
    InputStream is = null;
    try {
      is = getClass().getClassLoader().getResourceAsStream(name + suffix);
    } catch (Exception e) {
    }
    if (is != null) {
      return is;
    }
    return getClassFile(name, suffix).getInputStream();
  }

  public String getPath(String name) throws IOException {
    int index = name.lastIndexOf('.');
    String suffix = "";
    if (index > 0) {
      suffix = name.substring(index);
      name = name.substring(0, index);
    }
    return getPath(name, suffix);
  }

  public String getPath(String name, String suffix) throws IOException {
    return getClassFile(name, suffix).getPath();
  }

  public URL getResource(String name) {
    for (PathEntry path : paths) {
      URL url;
      if ((url = path.getResource(name)) != null) {
        return url;
      }
    }
    return null;
  }

  public InputStream getResourceAsStream(String name) {
    for (PathEntry path : paths) {
      InputStream is;
      if ((is = path.getResourceAsStream(name)) != null) {
        return is;
      }
    }
    return null;
  }

  public Enumeration<URL> getResources(String name) {
    Vector<URL> results = new Vector<>();
    for (PathEntry path : paths) {
      URL url;
      if ((url = path.getResource(name)) != null) {
        results.add(url);
      }
    }
    return results.elements();
  }

  @Override
  public int hashCode() {
    if (parent != null) {
      return class_path.hashCode() + parent.hashCode();
    }
    return class_path.hashCode();
  }

  @Override
  public String toString() {
    if (parent != null) {
      return parent + File.pathSeparator + class_path;
    }
    return class_path;
  }

  public static String getClassPath() {
    String class_path = System.getProperty("java.class.path");
    String boot_path = System.getProperty("sun.boot.class.path");
    String ext_path = System.getProperty("java.ext.dirs");
    List<String> list = new ArrayList<>();
    getPathComponents(class_path, list);
    getPathComponents(boot_path, list);
    List<String> dirs = new ArrayList<>();
    getPathComponents(ext_path, dirs);
    for (String d : dirs) {
      File ext_dir = new File(d);
      String[] extensions = ext_dir.list(ARCHIVE_FILTER);
      if (extensions != null) {
        for (String extension : extensions) {
          list.add(ext_dir.getPath() + File.separatorChar + extension);
        }
      }
    }
    StringBuilder buf = new StringBuilder();
    String separator = "";
    for (String path : list) {
      buf.append(separator);
      separator = File.pathSeparator;
      buf.append(path);
    }
    return buf.toString().intern();
  }

  private static void getPathComponents(String path, List<String> list) {
    if (path != null) {
      StringTokenizer tok = new StringTokenizer(path, File.pathSeparator);
      while (tok.hasMoreTokens()) {
        String name = tok.nextToken();
        File file = new File(name);
        if (file.exists()) {
          list.add(name);
        }
      }
    }
  }
}
