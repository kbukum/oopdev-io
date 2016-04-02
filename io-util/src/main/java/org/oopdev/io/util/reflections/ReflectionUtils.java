package org.oopdev.io.util.reflections;


import com.google.common.base.Predicate;
import org.oopdev.io.util.bean.IOField;
import org.oopdev.io.util.bean.field.IOFieldPrivateImpl;
import org.oopdev.io.util.bean.field.IOFieldPublicImpl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by kamilbukum on 21/03/16.
 */
public class ReflectionUtils extends org.reflections.ReflectionUtils {

    /**
     *
     * @param clazz
     * @param predicate
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public  static Map<String,IOField> getIOFieldMapOfClass(Class<?> clazz, Predicate<Field> predicate) throws IntrospectionException, NoSuchFieldException {
        return set(clazz).getIOFieldMap(predicate);
    }

    /**
     *
     * @param clazz
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public  static Map<String,IOField> getIOFieldMapOfClass(Class<?> clazz) throws IntrospectionException, NoSuchFieldException {
        return set(clazz).getIOFieldMap();
    }

    /**
     *
     * @param clazz
     * @return
     * @throws IntrospectionException
     */
    public static ReflectionUtilsClass set(Class<?> clazz) throws IntrospectionException {
        return new ReflectionUtilsClass(clazz);
    }
}
