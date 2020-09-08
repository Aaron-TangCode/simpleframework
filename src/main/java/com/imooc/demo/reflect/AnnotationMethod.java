package com.imooc.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hailin.tang
 * @date 2020/9/7 9:17 下午
 * @function
 */
public class AnnotationMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.imooc.demo.reflect.ReflectTarget");

        //创建对象
        Constructor constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();
        Method[] methods = clazz.getMethods();
        System.out.println("公有方法。包括继承的公有方法");
        printArray(methods);

        Method[] declaredMethods = clazz.getDeclaredMethods();

        System.out.println("所有方法。不包括继承");
        printArray(declaredMethods);

        System.out.println("一个方法");
        Method method2 = clazz.getMethod("method1");

        method2.invoke(instance);
        Method method4 = clazz.getDeclaredMethod("method4");
        method4.setAccessible(true);
        method4.invoke(instance);


    }


    private static <E> void printArray(E[] arr){
        for (E e : arr) {
            System.out.println(e);
        }
    }
}
