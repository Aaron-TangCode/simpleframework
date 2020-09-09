package org.simpleframework.core;

import org.simpleframework.core.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @Description:
 * @Author: tanghailin
 * @Date: 2020/9/9 12:03 下午
 */
public class DependencyInjector {
    private static BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 依赖注入的思路：
     * 1、获取每个类中的每个字段
     * 2、用@Autowired来过滤字段
     * 3、通过字段，在容器查找object，有，就返回，没，就先创建，后返回
     * 4、特别注意下，接口的注入，因为如果有多个实现类，会报错
     */
    public void doIoc() {
        Set<Class<?>> classes = beanContainer.getAllClass();
        if (ValidationUtil.isEmpty(classes)) {
            System.out.println("emplt classes in beanContainer");
            return;
        }
        for (Class<?> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    Class<?> fieldClass = field.getType();
                    //获取这些成员变量的类型在容器对应的实例
                    Object fieldValue = getFieldInstance(fieldClass,autowiredValue);
                    if (fieldValue == null) {
                        throw new RuntimeException("unable to inject relevant type,target fieldclass is " + fieldClass.getName());
                    } else {
                        //通过反射将值，注入成员变量
                        Object instance = beanContainer.getBeanByClass(clazz);
                        ClassUtil.setField(field, instance, fieldValue, true);
                    }
                }
            }
        }
    }


    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBeanByClass(fieldClass);
        if (fieldValue != null) {
            return fieldValue;
        } else {
            Class<?> implementClass = getImplementClass(fieldClass,autowiredValue);
            if (implementClass != null) {
                return beanContainer.getBeanByClass(implementClass);
            } else {
                return null;
            }
        }
    }

    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        //通过父类获取实现类
        Set<Class<?>> classSet = beanContainer.getClassBySuper(fieldClass);

        if (ValidationUtil.isEmpty(classSet)) {
            return null;
        }

        if(ValidationUtil.isEmpty(autowiredValue)){
            if(classSet.size()==1){
                return classSet.iterator().next();
            }else{
                //如果多于两个实现类且用户未指定其中一个实现类，抛出异常
                throw new RuntimeException("multiple implementd classes for"+fieldClass.getName());
            }
        }else {
            for (Class<?> clazz : classSet) {
                if(clazz.getSimpleName().equals(autowiredValue)){
                    return clazz;
                }
            }
        }
        return null;
    }
}
