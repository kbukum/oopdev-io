package org.oopdev.io.util.testmodel;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class ExampleParentTestPojo {

    private String parentName;
    @Deprecated
    public String  description;

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }
}
