package com.imooc.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author hailin.tang
 * @date 2020/9/7 9:44 下午
 * @function
 */
public class AnnotationField {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.imooc.demo.reflect.ReflectTarget");

        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("所有方法");
        printFields(declaredFields);

        System.out.println("公有方法");
        Field[] fields = clazz.getFields();
        printFields(fields);

        Constructor<?> constructor = clazz.getConstructor();

        Object instance = constructor.newInstance();

        Field age = clazz.getField("age");

        age.set(instance,1);

        Field age4 = clazz.getDeclaredField("age4");
        age4.setAccessible(true);
        age4.set(instance,4);

    }


    private static <E> void printFields(E[] fields){
        for (E field : fields) {
            System.out.println(field);
        }
    }
}
