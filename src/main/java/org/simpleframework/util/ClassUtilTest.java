package org.simpleframework.util;

import org.junit.Test;

import java.util.Set;

/**
 * @author hailin.tang
 * @date 2020/9/9 12:06 上午
 * @function
 */
public class ClassUtilTest {
    @Test
    public void extractPackageTest(){
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.imooc.entity");
        System.out.println(classSet);
    }
}
