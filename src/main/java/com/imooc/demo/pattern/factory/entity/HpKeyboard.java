package com.imooc.demo.pattern.factory.entity;

/**
 * @author hailin.tang
 * @date 2020/9/6 6:04 下午
 * @function
 */
public class HpKeyboard implements Keyboard {
    @Override
    public void sayHello() {
        System.out.println("我是惠普键盘");
    }
}
