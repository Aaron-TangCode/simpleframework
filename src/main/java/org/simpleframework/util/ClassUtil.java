package org.simpleframework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hailin.tang
 * @date 2020/9/8 11:40 下午
 * @function
 */
public class ClassUtil {
    /**
     * 获取包下类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> extractPackageClass(String packageName){
        //1.获取类加载器
        ClassLoader contextClassLoader = getClassLoader();
        //2.通过类加载器获取到加载的资源
        URL url = contextClassLoader.getResource(packageName.replace(".","/"));
        if(url==null){
            System.out.println("unable to retrieve anything from package:"+packageName);
            return null;
        }
        //3.依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        if(url.getProtocol().equalsIgnoreCase("file")){
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            //提出class文件
            extractClassFile(classSet,packageDirectory,packageName);
        }
        return classSet;
    }

    private static void extractClassFile(Set<Class<?>> classSet, File fileSource, String packageName) {
        //如果不是文件夹
        if(!fileSource.isDirectory()){
            return;
        }
        //如果是一个文件夹，则调用其listFiles方法获取文件夹下的文件或文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()){
                    return true;
                }else{
                    //获取文件的绝对路径
                    String absolutePath = file.getAbsolutePath();
                    if(absolutePath.endsWith(".class")){
                        //若是class文件，则直接加载
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            private void addToClassSet(String absolutePath) {
                //1.从class文件的绝对路径里提取出包含了package的类名
                absolutePath = absolutePath.replace(File.separator,".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
                className = className.substring(0,className.lastIndexOf("."));
                //2.通过反射机制获取对应class对象并加入到classset中
                Class<?> aClass = loadClass(className);
                classSet.add(aClass);
            }
        });

        if(files!=null){
            for (File f : files) {
                //递归调用
                extractClassFile(classSet,f,packageName);
            }
        }
    }


    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
}
