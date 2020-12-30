package com.kazlovich;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    public CustomClassLoader() {
        super(ClassLoader.getSystemClassLoader());
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(className);
        if (clazz != null){return  clazz;}

        byte[] b = new byte[0];
        try {
            b = loadClassData(className);
        } catch (IOException e) {
            e.getMessage();
        }
        return defineClass(className, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/" + name.replace(".", "/") + ".class");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer, 0 , len);
        }
        return byteArrayOutputStream.toByteArray();
    }

}
