package org.oopdev.io.util.bean;


/**
 * Created by kamilbukum on 25/03/16.
 */
public abstract class IOField {
    /**
     *
     */
    private final String name;

    /**
     *
     * @param name
     */
    public IOField(String name){
        this.name = name;
    }

    /**
     *
     * @param obj
     * @param value
     * @param <C>
     * @param <F>
     */
    public abstract <C,F> void set(C obj, F value);

    /**
     *
     * @param obj
     * @param <C>
     * @param <F>
     * @return
     */
    public abstract <C,F> F get(C obj);

    public String getName() {
        return name;
    }
}
