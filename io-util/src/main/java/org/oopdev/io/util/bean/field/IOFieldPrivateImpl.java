package org.oopdev.io.util.bean.field;

import org.oopdev.io.util.bean.IOField;

import java.lang.reflect.Method;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class IOFieldPrivateImpl extends IOField {
    /**
     *
     */
    private final Method setter;
    /**
     *
     */
    private final Method getter;

    /**
     *
     * @param name
     * @param setter
     * @param getter
     */
    public IOFieldPrivateImpl(String name,Method setter,Method getter){
        super(name);
        this.setter = setter;
        this.getter = getter;
    }

    /**
     *
     * @param obj
     * @param value
     * @param <C>
     * @param <R>
     */
    @Override
    public <C,R> void set(C obj, R value) {
        try {
            setter.invoke(obj,value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param obj
     * @param <C>
     * @param <R>
     * @return
     */
    @Override
    public <C,R> R get(C obj) {
        try {
            return (R)getter.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
