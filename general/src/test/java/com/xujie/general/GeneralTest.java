package com.xujie.general;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class GeneralTest {

    @Test
    public void testLongEquals() {
        assertEquals(new Long(100000L), new Long(100000L));
    }

    @Test
    public void testSystemProperty() {
        Runnable runnable = () -> {
            String val = "com.xujie.test.Test Value " + Math.random();
            System.setProperty("TEST.PROPERTY", val);
            System.out.println("set value " + System.getProperty("TEST.PROPERTY"));
        };
        for (int i = 0; i < 1000; i++) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            System.out.println("Start " + i + " at " + new Date());
            executor.execute(runnable);
            System.out.println("Stop " + i + " at " + new Date());
            executor.shutdown();
        }
    }
}
