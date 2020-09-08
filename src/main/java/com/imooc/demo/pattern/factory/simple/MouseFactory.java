package com.imooc.demo.pattern.factory.simple;

import com.imooc.demo.pattern.factory.entity.DellMouse;
import com.imooc.demo.pattern.factory.entity.HpMouse;
import com.imooc.demo.pattern.factory.entity.Mouse;

/**
 * @author hailin.tang
 * @date 2020/9/6 5:33 下午
 * @function
 */
public class MouseFactory {
    public static Mouse createMouse(int type){
        switch (type) {
            case 0: return new DellMouse();
            case 1: return new HpMouse();
            default: return new DellMouse();
        }
    }

    public static void main(String[] args) {
        Mouse mouse = createMouse(0);
        mouse.sayHi();
    }
}
