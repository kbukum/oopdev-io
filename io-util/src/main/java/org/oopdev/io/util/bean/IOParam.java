package org.oopdev.io.util.bean;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class IOParam<T>{
    private final String name;
    private final T obj;

    public IOParam(String name,T obj){
        this.name = name;
        this.obj = obj;
    }

    public String getName() {
        return name;
    }

    public T getObj() {
        return obj;
    }
}
