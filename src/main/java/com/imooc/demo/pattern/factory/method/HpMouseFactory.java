package com.imooc.demo.pattern.factory.method;

import com.imooc.demo.pattern.factory.entity.HpMouse;
import com.imooc.demo.pattern.factory.entity.Mouse;

/**
 * @author hailin.tang
 * @date 2020/9/6 5:49 下午
 * @function
 */
public class HpMouseFactory implements MouseFactory {

    @Override
    public Mouse createMouseFactory() {
        return new HpMouse();
    }
}
