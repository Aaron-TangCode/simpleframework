package com.imooc.demo.pattern.factory.abstractf;

import com.imooc.demo.pattern.factory.entity.DellKeyboard;
import com.imooc.demo.pattern.factory.entity.DellMouse;
import com.imooc.demo.pattern.factory.entity.Keyboard;
import com.imooc.demo.pattern.factory.entity.Mouse;

/**
 * @author hailin.tang
 * @date 2020/9/6 6:06 下午
 * @function
 */
public class DellComputerFactory implements ComputeFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
