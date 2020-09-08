package com.imooc.demo.pattern.factory.method;

import com.imooc.demo.pattern.factory.entity.DellMouse;
import com.imooc.demo.pattern.factory.entity.Mouse;

/**
 * @author hailin.tang
 * @date 2020/9/6 5:50 下午
 * @function
 */
public class DellMouseFactory implements MouseFactory{
    @Override
    public Mouse createMouseFactory() {
        return new DellMouse();
    }
}
