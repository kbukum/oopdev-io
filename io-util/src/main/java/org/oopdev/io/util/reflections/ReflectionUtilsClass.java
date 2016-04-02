package org.oopdev.io.util.reflections;

import com.google.common.base.Predicate;
import org.oopdev.io.util.bean.IOField;
import org.oopdev.io.util.bean.field.IOFieldPrivateImpl;
import org.oopdev.io.util.bean.field.IOFieldPublicImpl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class ReflectionUtilsClass {

    private final Class<?> clazz;
    private final BeanInfo info ;

    /**
     *
     * @param clazz
     * @throws IntrospectionException
     */
    ReflectionUtilsClass(Class<?> clazz) throws IntrospectionException {
        this.clazz = clazz;
        info = Introspector.getBeanInfo(clazz);
    }

    /**
     *
     * @param predicate
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public  Set<IOField> getIOFields(Predicate<Field> predicate) throws IntrospectionException, NoSuchFieldException {
        return FieldUtil.getIOFields(info,predicate);
    }

    /**
     *
     * @param predicate
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public  Map<String,IOField> getIOFieldMap(Predicate<Field> predicate) throws IntrospectionException, NoSuchFieldException {
        Set<IOField> fields = getIOFields(predicate);
        return getMapOfIOFields(fields);
    }

    /**
     *
     * @return
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public  Map<String,IOField> getIOFieldMap() throws IntrospectionException, NoSuchFieldException {
        return getMapOfIOFields(FieldUtil.getIOFields(info));
    }

    /**
     *
     * @param fields
     * @return
     */
    private Map<String,IOField> getMapOfIOFields(Set<IOField> fields){
        Map<String,IOField> ioFieldMap = new LinkedHashMap<>();
        for(IOField ioField : fields){
            ioFieldMap.put(ioField.getName(),ioField);
        }
        return ioFieldMap;
    }

}
