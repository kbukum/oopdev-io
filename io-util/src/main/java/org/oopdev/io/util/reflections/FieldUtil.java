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
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamilbukum on 20/03/16.
 */
public class FieldUtil {

    /**
     * get all declared field in class and its super classes without Object.class
     * @param clazz
     * @return
     */
    public static Set<Field> getDeclaredFieldOfClass(Class<?> clazz){
        return findDeclaredFieldOfClass(clazz,null);
    }


    /**
     * get all declared field in class and its super classes without Object.class by condition
     * @param clazz
     * @param predicate
     * @return
     */
    public static Set<Field> getDeclaredFieldOfClass(Class<?> clazz, Predicate<Field> predicate){
        if(predicate == null){
            throw new RuntimeException("Condition is null! ");
        }
        return findDeclaredFieldOfClass(clazz,predicate);
    }
    /**
     * get all declared field in class and its super classes without Object.class by condition
     * @param clazz
     * @param predicate
     * @return
     */
    private static Set<Field> findDeclaredFieldOfClass(Class<?> clazz,  Predicate<Field> predicate){
        if(clazz == null){
            throw new RuntimeException("Class is null ! ");
        }

        Set<Field> fields = new HashSet<>();
        Set<Class<?>> classes = ClassUtil.getClassWithSuperClassesWithoutObject(clazz);

        if(predicate != null){
            for(Class<?> getClass : classes ){
                for(Field field : getClass.getDeclaredFields()){
                    if(predicate.apply(field)){
                        fields.add(field);
                    }
                }
            }
        }else{
            for(Class<?> getClass : classes ){
                for(Field field : getClass.getDeclaredFields()){
                        fields.add(field);
                }
            }
        }
        return fields;
    }


    /**
     *
     * @param clazz
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Set<IOField> getIOFields(Class<?> clazz) throws IntrospectionException, NoSuchFieldException {
       Set<Field> fieldSet =  getDeclaredFieldOfClass(clazz);
        BeanInfo beanInfo  = Introspector.getBeanInfo(clazz);
        return getIOFields(beanInfo,fieldSet);
    }

    /**
     *
     * @param clazz
     * @param predicate
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Set<IOField> getIOFields(Class<?> clazz,Predicate<Field> predicate) throws IntrospectionException, NoSuchFieldException {
        Set<Field> fieldSet =  getDeclaredFieldOfClass(clazz,predicate);
        BeanInfo beanInfo  = Introspector.getBeanInfo(clazz);
        return getIOFields(beanInfo,fieldSet);
    }

    /**
     *
     * @param info
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Set<IOField> getIOFields(BeanInfo info) throws IntrospectionException, NoSuchFieldException {
        Set<Field> fieldSet =  getDeclaredFieldOfClass(info.getBeanDescriptor().getBeanClass());
        return getIOFields(info,fieldSet);
    }

    /**
     *
     * @param beanInfo
     * @param predicate
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Set<IOField> getIOFields(BeanInfo beanInfo,Predicate<Field> predicate) throws IntrospectionException, NoSuchFieldException {
        Set<Field> fieldSet =  getDeclaredFieldOfClass(beanInfo.getBeanDescriptor().getBeanClass(),predicate);
        return getIOFields(beanInfo,fieldSet);
    }

    /**
     *
     * @param info
     * @param fields
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Set<IOField> getIOFields(BeanInfo info,Set<Field> fields) throws IntrospectionException, NoSuchFieldException {
        Set<IOField> ioFields = new HashSet<>();
        for(Field field : fields){
            ioFields.add(getIOField(info,field));
        }
        return ioFields;
    }

    /**
     *
     * @param info
     * @param field
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static IOField getIOField(BeanInfo info,Field field) throws IntrospectionException, NoSuchFieldException {
        if(field.getModifiers() == Modifier.PUBLIC){
            return createPublicIOFieldB(field);
        }else{
            return createPrivateIOFieldB(info,field);
        }
    }

    /**
     *
     * @param field
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    private static IOField createPublicIOFieldB(Field field) throws IntrospectionException, NoSuchFieldException {
        return new IOFieldPublicImpl(field);
    }

    /**
     *
     * @param info
     * @param field
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    private static IOField createPrivateIOFieldB(BeanInfo info,Field field) throws IntrospectionException, NoSuchFieldException {
        Method setter = findSetter(info,field.getName());
        Method getter = findGetter(info,field.getName());
        return new IOFieldPrivateImpl(field.getName(),setter,getter);
    }

    /**
     *
     * @param info
     * @param fieldName
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Method findGetter(BeanInfo info, String fieldName) throws IntrospectionException, NoSuchFieldException {
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() )
            if (fieldName.equals(pd.getName())) return pd.getReadMethod();
        throw new NoSuchFieldException(info.getBeanDescriptor().getBeanClass().getName() + " has no field " + fieldName);
    }

    /**
     *
     * @param clazz
     * @param fieldName
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Method findGetter(Class clazz, String fieldName) throws IntrospectionException, NoSuchFieldException {
        return findGetter(Introspector.getBeanInfo(clazz),fieldName);
    }

    /**
     *
     * @param info
     * @param fieldName
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Method findSetter(BeanInfo info, String fieldName) throws IntrospectionException, NoSuchFieldException {
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() )
            if (fieldName.equals(pd.getName())) return pd.getWriteMethod();
        throw new NoSuchFieldException(info.getBeanDescriptor().getBeanClass().getName() + " has no field " + fieldName);
    }

    /**
     *
     * @param clazz
     * @param fieldName
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public static Method findSetter(Class clazz, String fieldName) throws IntrospectionException, NoSuchFieldException {
        return findSetter(Introspector.getBeanInfo(clazz),fieldName);
    }
}
