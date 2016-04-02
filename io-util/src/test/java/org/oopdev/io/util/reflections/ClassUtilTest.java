package org.oopdev.io.util.reflections;


import org.junit.Test;
import org.oopdev.io.util.testmodel.ExampleParentTestPojo;
import org.oopdev.io.util.testmodel.ExampleTestPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class ClassUtilTest {

    private static Logger logger = LoggerFactory.getLogger(ClassUtilTest.class);

    @Test
    public void getSuperClassesWithoutObject(){

        logger.info("Starting to Test ClassUtilTest.getSuperClassesWithoutObject");

        Set<Class<?>> expectedClass = new HashSet<>();
        expectedClass.add(ExampleParentTestPojo.class);

        final Set<Class<?>> classSet = ClassUtil.getSuperClassesWithoutObject(ExampleTestPojo.class);

        assertThat(expectedClass,is(classSet));

        logger.info("Finished to Test ClassUtilTest.getSuperClassesWithoutObject");
    }

    @Test
    public void getClassWithSuperClassesWithoutObject(){
        logger.info("Starting to Test ClassUtilTest.getClassWithSuperClassesWithoutObject");

        Set<Class<?>> expectedClass = new HashSet<>();
        expectedClass.add(ExampleTestPojo.class);
        expectedClass.add(ExampleParentTestPojo.class);

        final Set<Class<?>> classSet = ClassUtil.getClassWithSuperClassesWithoutObject(ExampleTestPojo.class);

        assertThat(expectedClass,is(classSet));

        logger.info("Finished to Test ClassUtilTest.getClassWithSuperClassesWithoutObject");
    }
}