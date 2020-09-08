package com.imooc.demo;

import lombok.Data;

/**
 * @author hailin.tang
 * @date 2020/9/6 12:27 下午
 * @function
 */
@Data
public class GenericClassExample<T> {

    private T member;

    public GenericClassExample(T member){this.member = member;}

    public T handleSomething(T target){
        return target;
    }

    public String sayHello(String name){
        return "hello"+name;
    }

    /**
     * 泛型方法是独立于泛型类的
     * @param inputArray
     * @param <E>
     */
    public static <E> void printArray(E[] inputArray){
        for (E e : inputArray) {
            System.out.println(e);
        }
    }
}
