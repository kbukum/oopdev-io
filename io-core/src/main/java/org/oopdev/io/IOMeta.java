package org.oopdev.io;

import org.oopdev.io.util.bean.IOField;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class IOMeta<T>{

    /**
     * name of the component
     */
    private final String name;
    /**
     * type of the component
     */
    private final Class<T> clazz;
    /**
     * String name of the Field
     * IOField is field that define in component class as member.
     */
    private final Map<String,IOField> ioFieldMap = new LinkedHashMap<>();

    /**
     *
     * @param clazz
     */
    public IOMeta(Class<T> clazz){
        this.name = clazz.getName();
        this.clazz = clazz;
    }

    /**
     *
     * @param name
     * @param clazz
     */
    public IOMeta(String name,Class<T> clazz){
        this.name = name;
        this.clazz = clazz;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Class<T> getClazz() {
        return clazz;
    }

    /**
     *
     * @return
     */
    public Map<String, IOField> getIoFieldMap() {
        return ioFieldMap;
    }
}
