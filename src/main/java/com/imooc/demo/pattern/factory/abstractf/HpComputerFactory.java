package com.imooc.demo.pattern.factory.abstractf;

import com.imooc.demo.pattern.factory.entity.HpKeyboard;
import com.imooc.demo.pattern.factory.entity.HpMouse;
import com.imooc.demo.pattern.factory.entity.Keyboard;
import com.imooc.demo.pattern.factory.entity.Mouse;

/**
 * @author hailin.tang
 * @date 2020/9/6 6:06 下午
 * @function
 */
public class HpComputerFactory implements ComputeFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HpKeyboard();
    }
}
