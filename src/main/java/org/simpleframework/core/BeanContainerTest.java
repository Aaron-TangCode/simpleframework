package org.simpleframework.core;

import org.junit.Before;
import org.junit.Test;

/**
 * @Description:
 * @Author: tanghailin
 * @Date: 2020/9/9 11:27 上午
 */
public class BeanContainerTest {
    private static BeanContainer beanContainer;

    @Before
    public void init(){
        beanContainer = BeanContainer.getInstance();
    }
    @Test
    public void beanContainerTest(){
        beanContainer.loadBeans("com.imooc");
        System.out.println(beanContainer.size());
    }
}
