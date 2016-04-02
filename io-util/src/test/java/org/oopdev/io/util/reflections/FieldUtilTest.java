package org.oopdev.io.util.reflections;

import com.google.common.base.Predicate;
import org.junit.Test;
import org.oopdev.io.util.bean.IOField;
import org.oopdev.io.util.testmodel.ExampleTestPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Field;

import java.util.Set;
import static org.junit.Assert.*;
/**
 * Created by kamilbukum on 26/03/16.
 */
public class FieldUtilTest {


    private static Logger logger = LoggerFactory.getLogger(ClassUtilTest.class);


    @Test
    public void  getDeclaredFieldOfClassByClass(){

        logger.info("Starting to Test FieldUtilTest.getDeclaredFieldOfClassByClass");

       int expectedFieldsSize = 5;

        Set<Field> fields = FieldUtil.getDeclaredFieldOfClass(ExampleTestPojo.class);

        assertEquals(expectedFieldsSize,fields.size());

        logger.info("Finished to Test FieldUtilTest.getDeclaredFieldOfClassByClass");
    }

    @Test
    public void  getDeclaredFieldOfClassByClassAndPredicate(){
        logger.info("Starting to Test FieldUtilTest.getDeclaredFieldOfClassByClassAndPredicate");

        int expectedFieldsSize = 3;

        Set<Field> fields = FieldUtil.getDeclaredFieldOfClass(ExampleTestPojo.class, new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Deprecated.class);
            }
        });

        assertEquals(expectedFieldsSize,fields.size());

        logger.info("Finished to Test FieldUtilTest.getDeclaredFieldOfClassByClassAndPredicate");
    }


    @Test
    public void getIOFieldsByClass() throws IntrospectionException, NoSuchFieldException {
        logger.info("Starting to Test FieldUtilTest.getIOFieldsByClass");

        int expectedFieldsSize = 5;


        Set<IOField> fields = FieldUtil.getIOFields(ExampleTestPojo.class);

        assertEquals(expectedFieldsSize,fields.size());

        logger.info("Finished to Test FieldUtilTest.getIOFieldsByClass");
    }

    @Test
    public void getIOFieldsByClassAndPredicate() throws IntrospectionException, NoSuchFieldException {
        logger.info("Starting to Test FieldUtilTest.getIOFieldsByClassAndPredicate");

        int expectedFieldsSize = 3;


        Set<IOField> fields = FieldUtil.getIOFields(ExampleTestPojo.class, new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Deprecated.class);
            }
        });

        assertEquals(expectedFieldsSize,fields.size());

        logger.info("Finished to Test FieldUtilTest.getIOFieldsByClassAndPredicate");
    }

    @Test
    public void getIOFieldsByBeanInfo() throws IntrospectionException, NoSuchFieldException {
        logger.info("Starting to Test FieldUtilTest.getIOFieldsByBeanInfo");

        int expectedFieldsSize = 5;

        BeanInfo beanInfo  = Introspector.getBeanInfo(ExampleTestPojo.class);
        Set<IOField> fields = FieldUtil.getIOFields(beanInfo);

        assertEquals(expectedFieldsSize,fields.size());

        logger.info("Finished to Test FieldUtilTest.getIOFieldsByBeanInfo");
    }

    @Test
    public void getIOFieldsByBeanInfoAndPredicate() throws IntrospectionException, NoSuchFieldException {
        logger.info("Starting to Test FieldUtilTest.getIOFieldsByBeanInfoAndPredicate");

        int expectedFieldsSize = 5;

        BeanInfo beanInfo  = Introspector.getBeanInfo(ExampleTestPojo.class);
        Set<IOField> fields = FieldUtil.getIOFields(beanInfo, new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Deprecated.class);
            }
        });

        assertEquals(expectedFieldsSize,fields.size());

        logger.info("Finished to Test FieldUtilTest.getIOFieldsByBeanInfoAndPredicate");
    }

    @Test
    public void getIOFieldsByBeanInfoAndField() throws IntrospectionException, NoSuchFieldException {
        logger.info("Starting to Test FieldUtilTest.getIOFieldsByBeanInfo");

        Set<Field> expectedFields = FieldUtil.getDeclaredFieldOfClass(ExampleTestPojo.class);

        BeanInfo beanInfo  = Introspector.getBeanInfo(ExampleTestPojo.class);
        Set<IOField> fields = FieldUtil.getIOFields(beanInfo,expectedFields);

        assertEquals(expectedFields.size(),fields.size());

        logger.info("Finished to Test FieldUtilTest.getIOFieldsByBeanInfo");
    }
}