package org.oopdev.io.util.bean;

import org.junit.Test;
import org.oopdev.io.util.testmodel.ExampleTestPojo;
import org.oopdev.io.util.bean.field.IOFieldPrivateImpl;
import org.oopdev.io.util.bean.field.IOFieldPublicImpl;
import org.oopdev.io.util.reflections.FieldUtil;
import org.oopdev.io.util.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class IOFieldTest {

    private static Logger logger = LoggerFactory.getLogger(IOFieldTest.class);


    /**
     * Test IOField public field
     */
    @Test
    public void testPublicField(){

        try {

            logger.info("Testing IOField as public field... ");

            String expectedValue = "FirstNameExample";

            ExampleTestPojo exampleTestPojo = new ExampleTestPojo();
            exampleTestPojo.firstName = expectedValue;

            IOField ioField = new IOFieldPublicImpl(ExampleTestPojo.class.getDeclaredField("firstName"));
            String resultValue  = ioField.get(exampleTestPojo);

            assertEquals(expectedValue, resultValue);
            logger.info("Finished Testing IOField as public field. ");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test IOField public field
     */
    @Test
    public void testPrivateField(){
        try {

            logger.info("Testing IOField as private field... ");

            String expectedValue = "LastNameExample";

            ExampleTestPojo exampleTestPojo = new ExampleTestPojo();
            exampleTestPojo.firstName = expectedValue;

            String fieldName = "lastName";

            Field field = ExampleTestPojo.class.getDeclaredField("lastName");

            Method setter = FieldUtil.findSetter(ExampleTestPojo.class,field.getName());
            Method getter =  FieldUtil.findGetter(ExampleTestPojo.class,field.getName());

            IOField ioField = new IOFieldPrivateImpl(fieldName,setter,getter);

            ioField.set(exampleTestPojo,expectedValue);

            String resultValue  = ioField.get(exampleTestPojo);

            assertEquals(expectedValue, resultValue);
            logger.info("Finished Testing IOField as private field. ");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAllField() throws IntrospectionException, NoSuchFieldException {

            logger.info("Testing All IOField of the class.. ");


            ExampleTestPojo expectedValue = new ExampleTestPojo();
            expectedValue.firstName = "firstNameExample";
            expectedValue.setLastName("lastNameExample");
            expectedValue.age = 4;




            ExampleTestPojo resultValue = new ExampleTestPojo();

            Map<String,IOField> ioFieldMap = ReflectionUtils.set(ExampleTestPojo.class).getIOFieldMap();

            for( Map.Entry<String,IOField> ioFieldEntry : ioFieldMap.entrySet()){
               IOField ioField =  ioFieldEntry.getValue();
               ioField.set(resultValue,ioField.get(expectedValue));
            }

            assertEquals(expectedValue, resultValue);

            logger.info("Finished testing All IOField of the class.. ");

    }
}
