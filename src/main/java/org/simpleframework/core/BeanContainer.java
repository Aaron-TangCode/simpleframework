package org.simpleframework.core;

import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: tanghailin
 * @Date: 2020/9/9 11:04 上午
 */
public class BeanContainer {
    //容器
    private final Map<Class<?>,Object> beanMap = new ConcurrentHashMap<>();


    //加载bean的注解列表.todo 为什么要用list，不用set.用set的话，时间复杂度可以降低
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATIO = Arrays.asList(Controller.class, Service.class, Repository.class, Component.class);

    public static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }
    //容器是否已经加载过bean
    private boolean loaded = false;

    public  boolean isLoaded(){
        return loaded;
    }
    public int size(){
        return beanMap.size();
    }
    public synchronized void loadBeans(String packageName){
        if(isLoaded()){
            System.out.println("beancontainer has bean loaded");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        //过滤，有@Controller的class存进容器中
        if(ValidationUtil.isEmpty(classSet)){
            System.out.println("extract nothing from packageName"+ packageName);
            return;
        }

        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATIO) {
                //如果类上面标记了定义的注解
                if (clazz.isAnnotationPresent(annotation)) {
                    beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
                }
            }
        }

        loaded = true;
    }

    /**
     * 删除bean
     * @param clazz
     * @return
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }
    /**
     * 添加bean
     * @param clazz
     * @return
     */
    public Object addBean(Class<?> clazz){
        return addBean(clazz,ClassUtil.newInstance(clazz,true));
    }
    public Object addBean(Class<?> clazz,Object object){
        return beanMap.put(clazz,object);
    }
    /**
     * 通过Class获取对应的实例
     * @return
     */
    public Object getBeanByClass(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取所有的Class
     * @return
     */
    public Set<Class<?>> getAllClass(){
       return beanMap.keySet();
    }

    /**
     * 获取所有的实例
     * @return
     */
    public Set<Object> getAllObject(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 通过注解来获取被注解标注的Class
     * @param annotation
     * @return
     */
    public Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotation){
        Set<Class<?>> res = new HashSet<>();
        Set<Class<?>> classes = beanMap.keySet();
        if(ValidationUtil.isEmpty(classes)){
           return null;
        }
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(annotation)) {
                res.add(clazz);
            }
        }
        return res.size()==0?null:res;
    }

    public Set<Class<?>> getClassBySuper(Class<?> superClass){
        Set<Class<?>> res = new HashSet<>();
        Set<Class<?>> classes = beanMap.keySet();
        if(ValidationUtil.isEmpty(classes)){
            return null;
        }
        for (Class<?> clazz : classes) {
            //父类isAssignableFrom（子类）
            if (superClass.isAssignableFrom(clazz)) {
                res.add(clazz);
            }
        }
        return res.size()==0?null:res;
    }
}
