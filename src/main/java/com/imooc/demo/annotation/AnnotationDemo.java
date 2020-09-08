package com.imooc.demo.annotation;

import java.lang.reflect.Field;

/**
 * @author hailin.tang
 * @date 2020/9/7 9:56 下午
 * @function
 */
public class AnnotationDemo {
    @CustomAnnotation(name = "tanghailin",language = {"JAVA","C++"})
    AnnotationDemo annotationDemo;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {

        Class<?> clazz = Class.forName("com.imooc.demo.annotation.AnnotationDemo");

        Field declaredField = clazz.getDeclaredField("annotationDemo");

        CustomAnnotation annotation = declaredField.getAnnotation(CustomAnnotation.class);

        System.out.println(annotation);
    }
}
