package com.imooc.demo.reflect;

/**
 * @author hailin.tang
 * @date 2020/9/6 6:21 下午
 * @function
 */
public class ReflectTarget {
    public void method1(){
        System.out.println("public 方法");
    }

    protected void method2(String name){
        System.out.println("protect方法:"+name);
    }

    void method3(){
        System.out.println("默认方法：");
    }

    private void method4(){
        System.out.println("private 方法");
    }

    public int age;
    protected int age2;
    int age3;
    private int age4;

    public static void main(String[] args) throws ClassNotFoundException {
        //1.getClass()
        ReflectTarget reflectTarget = new ReflectTarget();
        Class<? extends ReflectTarget> class1 = reflectTarget.getClass();
        System.out.println(class1.getName());

        //2.class
        Class<ReflectTarget> class2 = ReflectTarget.class;

        System.out.println(class2.getName());

        System.out.println(class1 == class2);
        //3.

        Class<?> class3 = Class.forName("com.imooc.demo.reflect.ReflectTarget");

        System.out.println(class3.getName());

        System.out.println(class2 == class3);//证明class对象有且仅有一个

    }

}
