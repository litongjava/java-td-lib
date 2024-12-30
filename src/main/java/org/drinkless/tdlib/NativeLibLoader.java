package org.drinkless.tdlib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class NativeLibLoader {

  public static void loadNativeLibrary(String libName) throws IOException {
    String libFileName = System.mapLibraryName(libName);
    InputStream libStream = NativeLibLoader.class.getResourceAsStream("/libs/" + libFileName);
    if (libStream == null) {
      throw new FileNotFoundException("Native library " + libFileName + " not found in classpath.");
    }

    // 创建临时文件
    Path tempLib = Files.createTempFile("native_lib_", libFileName);
    tempLib.toFile().deleteOnExit();
    Files.copy(libStream, tempLib, StandardCopyOption.REPLACE_EXISTING);

    // 加载库
    //String libFile = tempLib.toAbsolutePath().toString();
    String libFile = "D:\\dev_workspace\\eclipse-jee-2022-6\\java-td-lib\\src\\main\\resources\\libs\\win_x64\\tdjni.dll";
    System.out.println("load file:" + libFile);
    System.load(libFile);
  }

  public static void main(String[] args) {
    try {
      NativeLibLoader.loadNativeLibrary("tdjni");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
