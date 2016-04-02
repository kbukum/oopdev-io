package org.oopdev.io.util.reflections;

import com.google.common.base.Predicate;
import org.junit.Test;
import org.oopdev.io.util.testmodel.ExampleTestPojo;
import org.oopdev.io.util.bean.IOField;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

/**
 * Created by kamilbukum on 26/03/16.
 */
public class ReflectionUtilsClassTest {

    @Test
    public void testGetIOFields() throws Exception {
        Set<IOField> fields =  ReflectionUtils.set(ExampleTestPojo.class).getIOFields(new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Deprecated.class);
            }
        });

        Set<String> fieldNameSet = new LinkedHashSet<>();
        for(IOField ioField : fields){
            fieldNameSet.add(ioField.getName());
        }

        assertThat(fieldNameSet,hasItems("description","lastName","age"));
    }

    @Test
    public void testGetIOFieldMap() throws Exception {
        Map<String,IOField> ioFieldMap =  ReflectionUtils.set(ExampleTestPojo.class).getIOFieldMap();

        assertThat(ioFieldMap.keySet(),hasItems("firstName","lastName","age","parentName","description"));

    }

    @Test
    public void testGetIOFieldMap1() throws Exception {
        Map<String,IOField> ioFieldMap =  ReflectionUtils.set(ExampleTestPojo.class).getIOFieldMap(new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field field) {
                return field.isAnnotationPresent(Deprecated.class);
            }
        });

        assertThat(ioFieldMap.keySet(),hasItems("lastName","age","description"));

    }
}