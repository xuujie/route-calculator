package com.xujie.collection;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollectionTest {

    private List<String> randomAlphabeticList = new ArrayList<>();
    private List<String> sortedAlphabeticList = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z");

    @Before
    public void init() {
        for (int j = 0; j < 20000; j++) {
            randomAlphabeticList.add(RandomStringUtils.randomAlphabetic(1));
        }
    }

    @Test
    public void testDistinctUsingStream() {
        for (int i = 0; i < 10; i++) {
            long time1 = System.currentTimeMillis();
            List<String> list = CollectionUtil.distinctUsingStream(randomAlphabeticList);
            System.out.println(list);
            System.out.println("Time to process list: " + (System.currentTimeMillis() - time1));
            System.out.println();
            assertEquals(list, sortedAlphabeticList);
        }
    }

    @Test
    public void testDistinctUsingParallelStream() {
        for (int i = 0; i < 10; i++) {
            long time1 = System.currentTimeMillis();
            List<String> list = CollectionUtil.distinctUsingParallelStream(randomAlphabeticList);
            System.out.println(list);
            System.out.println("Time to process list: " + (System.currentTimeMillis() - time1));
            System.out.println();
            assertEquals(list, sortedAlphabeticList);
        }
    }

    @Test
    public void testAnyMatchTraditional() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add("A");
        }
        list.add("B");
        for (int i = 0; i < 1000000; i++) {
            list.add("C");
        }
        long time = System.currentTimeMillis();
        System.out.println(CollectionUtil.anyMatchTraditional(list, "D", "E"));
        System.out.println("Time to process list: " + (System.currentTimeMillis() - time));
    }

}
