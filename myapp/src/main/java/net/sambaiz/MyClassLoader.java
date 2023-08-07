package net.sambaiz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader {
    private String jarFilePath;

    public MyClassLoader(ClassLoader parent, String jarFilePath) {
        super(parent);
        this.jarFilePath = jarFilePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.printf("findClass %s\n", className);
        try {
            JarFile jarFile = new JarFile(this.jarFilePath);
            JarEntry entry = jarFile.getJarEntry(className.replace('.', '/') + ".class");
            if (entry == null) {
                throw new ClassNotFoundException(className);
            }

            InputStream inputStream = jarFile.getInputStream(entry);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] bytecode = outputStream.toByteArray();
            return defineClass(className, bytecode, 0, bytecode.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(className);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println(ClassLoader.getSystemClassLoader());

        MyClassLoader legacyClassLoader = new MyClassLoader(ClassLoader.getSystemClassLoader(), "legacylib/target/legacylib-1.0.0.jar");
        Class<?> legacyClass = legacyClassLoader.loadClass("net.sambaiz.legacylib.Legacy");
        Object legacyInstance = legacyClass.getDeclaredConstructor().newInstance();
        legacyClass.getMethod("foo").invoke(legacyInstance);;

        MyClassLoader modernClassLoader = new MyClassLoader(ClassLoader.getSystemClassLoader(), "modernlib/target/modernlib-1.0.0.jar");
        Class<?> modernClass = modernClassLoader.loadClass("net.sambaiz.modernlib.Modern");
        Object modernInstance = modernClass.getDeclaredConstructor().newInstance();
        modernClass.getMethod("bar").invoke(modernInstance);;
    }
}

