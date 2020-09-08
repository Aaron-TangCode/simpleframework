package com.imooc.demo.pattern.factory.abstractf;

import com.imooc.demo.pattern.factory.entity.Keyboard;
import com.imooc.demo.pattern.factory.entity.Mouse;

/**
 * @author hailin.tang
 * @date 2020/9/6 6:05 下午
 * @function
 */
public interface ComputeFactory {
    Mouse createMouse();
    Keyboard createKeyboard();
}
