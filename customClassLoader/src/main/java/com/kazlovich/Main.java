package com.kazlovich;

public class Main {
    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = customClassLoader.findClass("com.kazlovich.Person");
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }
}
