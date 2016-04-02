package org.oopdev.io.util.converter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by kamilbukum on 25/03/16.
 */
public class DataUtilTest {

    private static Logger logger = LoggerFactory.getLogger(DataUtilTest.class);

    @Test
    public void testToByteArray() throws Exception {

        logger.info("Testing DataUtil.toByteArray ... ");
        byte[] expectedValue = "Example Data".getBytes();

        InputStream is  = new ByteArrayInputStream(expectedValue);

        byte[] resultValue = DataUtil.toByteArray(is);

        assertArrayEquals(expectedValue,resultValue);

        logger.info("Finished Testing DataUtil.toByteArray. ");
    }

    @Test
    public void testCopy() throws Exception {
        logger.info("Testing DataUtil.toByteArray ... ");
        byte[] expectedValue = "Example Data".getBytes();

        InputStream is  = new ByteArrayInputStream(expectedValue);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
         int res = DataUtil.copy(is,os);

        byte[] resultValue = os.toByteArray();

        assertEquals(res,expectedValue.length);
        assertArrayEquals(expectedValue,resultValue);

        logger.info("Finished Testing DataUtil.toByteArray. ");
    }

    @Test
    public void testCopyLarge() throws Exception {
        logger.info("Testing DataUtil.toByteArray ... ");
        byte[] expectedValue = "Example Data".getBytes();

        InputStream is  = new ByteArrayInputStream(expectedValue);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int res = (int) DataUtil.copyLarge(is,os);

        byte[] resultValue = os.toByteArray();

        assertEquals(res,expectedValue.length);
        assertArrayEquals(expectedValue,resultValue);

        logger.info("Finished Testing DataUtil.toByteArray. ");

    }

    @Test
    public void testCopyLarge1() throws Exception {
        logger.info("Testing DataUtil.toByteArray ... ");
        byte[] expectedValue = "Example Data".getBytes();

        InputStream is  = new ByteArrayInputStream(expectedValue);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int res = (int) DataUtil.copyLarge(is,os,new byte[DataUtil.DEFAULT_BUFFER_SIZE]);

        byte[] resultValue = os.toByteArray();

        assertEquals(res,expectedValue.length);
        assertArrayEquals(expectedValue,resultValue);

        logger.info("Finished Testing DataUtil.toByteArray. ");

    }
}