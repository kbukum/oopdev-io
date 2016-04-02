package org.oopdev.io.util.reflections;

import com.google.common.base.Predicate;
import org.junit.Test;
import org.oopdev.io.util.testmodel.ExampleTestPojo;
import org.oopdev.io.util.bean.IOField;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class ReflectionUtilsTest {

    @Test
    public void testGetIOFieldMapOfClassByPredicate() throws Exception {

        Map<String,IOField> ioFieldMap = ReflectionUtils.getIOFieldMapOfClass(ExampleTestPojo.class, new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Deprecated.class);
            }
        });

        Set<String> resulSet = ioFieldMap.keySet();
        assertThat(resulSet,hasItems("lastName","age","description"));
    }

    @Test
    public void testGetIOFieldMapOfClass() throws Exception {
        Map<String,IOField> ioFieldMap = ReflectionUtils.getIOFieldMapOfClass(ExampleTestPojo.class);

        Set<String> resulSet = ioFieldMap.keySet();
        assertThat(resulSet,hasItems("firstName","lastName","age","parentName","description"));

    }

    @Test
    public void testSet() throws Exception {
       Map<String,IOField> ioFieldMap =  ReflectionUtils.set(ExampleTestPojo.class).getIOFieldMap();

        assertThat(ioFieldMap.keySet(),hasItems("firstName","lastName","age","parentName","description"));
    }
}