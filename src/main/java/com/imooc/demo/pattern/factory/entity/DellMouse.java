package com.imooc.demo.pattern.factory.entity;

/**
 * @author hailin.tang
 * @date 2020/9/6 5:32 下午
 * @function
 */
public class DellMouse  implements Mouse {
    @Override
    public void sayHi() {
        System.out.println("我是dell鼠标");
    }
}
