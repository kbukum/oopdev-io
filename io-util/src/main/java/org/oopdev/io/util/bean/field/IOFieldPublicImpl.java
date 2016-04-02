package org.oopdev.io.util.bean.field;

import org.oopdev.io.util.bean.IOField;

import java.lang.reflect.Field;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class IOFieldPublicImpl extends IOField {
    /**
     *
     */
    private final Field field;

    /**
     *
     * @param field
     */
    public IOFieldPublicImpl(Field field){
        super(field.getName());
        this.field = field;
    }

    /**
     *
     * @param obj
     * @param value
     * @param <C>
     * @param <V>
     */
    @Override
    public <C,V> void set(C obj, V value) {
        try {
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param obj
     * @param <C>
     * @param <V>
     * @return
     */
    @Override
    public <C,V> V get(C obj) {
        try {
            return (V)field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
