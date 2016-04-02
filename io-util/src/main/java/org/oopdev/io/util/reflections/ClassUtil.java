package org.oopdev.io.util.reflections;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamilbukum on 20/03/16.
 */
public class ClassUtil {

    /**
     * get super classes of class without Object.class list
     * @param clazz
     * @return
     */
    public static Set<Class<?>> getSuperClassesWithoutObject(Class<?> clazz){
        return getSuperClassesWithoutObject(clazz,new HashSet<Class<?>>());
    }

    /**
     * get super classes of class with own class and without Object.class list
     * @param clazz
     * @return
     */
    public static Set<Class<?>> getClassWithSuperClassesWithoutObject(Class<?> clazz){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(clazz);
        return getSuperClassesWithoutObject(clazz,classes);
    }

    /**
     * get super classes of class without Object.class with given list
     * @param clazz
     * @param classes
     * @return
     */
    private static Set<Class<?>> getSuperClassesWithoutObject(Class<?> clazz,Set<Class<?>> classes){
        while (!(clazz = clazz.getSuperclass()).equals(Object.class)){
            classes.add(clazz);
        }
        return classes;
    }
}
