package com.imooc.demo;

/**
 * @author hailin.tang
 * @date 2020/9/6 12:31 下午
 * @function
 */
public class GenericDemo {
    public static void main(String[] args) {
        GenericClassExample<String> stringGenericClassExample = new GenericClassExample<>("123");
        GenericClassExample<Number> integerGenericClassExample = new GenericClassExample<>(123);

        System.out.println(stringGenericClassExample.getClass());
        System.out.println(integerGenericClassExample.getClass());

        handleMember(integerGenericClassExample);
    }

    public static void handleMember(GenericClassExample<? super Integer> stringGenericClassExample){
        System.out.println(stringGenericClassExample.getMember());
    }

}
