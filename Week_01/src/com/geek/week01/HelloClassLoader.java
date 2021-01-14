package com.geek.week01;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 必做题1：自定义ClassLoader
 */
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try{
            //加载Hello.xlass
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");
            Method method = null;
            method = helloClass.getDeclaredMethod("hello");
            method.invoke(helloClass.newInstance());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //读取文件到byte数组
            byte[] bytes = toByteArray(name);
            //对byte数组进行解密处理
            decode(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将文件转化为字节数组
     * @param name
     * @return
     * @throws IOException
     */
    public byte[] toByteArray(String name) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name + ".xlass");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int n = 0;
        while ((n = inputStream.read(bytes)) != -1) {
            baos.write(bytes, 0, n);
        }
        inputStream.close();
        return baos.toByteArray();
    }

    /**
     * 对byte数组进行解密处理
     * @param bytes
     */
    private void decode(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
    }
}
