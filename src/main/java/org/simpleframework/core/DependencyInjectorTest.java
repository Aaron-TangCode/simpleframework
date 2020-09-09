package org.simpleframework.core;

import com.imooc.controller.MainPageController;
import org.junit.Test;

/**
 * @Description:
 * @Author: tanghailin
 * @Date: 2020/9/9 1:16 下午
 */
public class DependencyInjectorTest {
    @Test
    public void test1(){
        BeanContainer beanContainer = BeanContainer.getInstance();

        beanContainer.loadBeans("com.imooc");

        Object beanByClass = beanContainer.getBeanByClass(MainPageController.class);

        new DependencyInjector().doIoc();

        MainPageController mainPageController = (MainPageController)beanContainer.getBeanByClass(MainPageController.class);
        System.out.println(mainPageController.getHeadLineShopCategoryCombineService());
    }
}
