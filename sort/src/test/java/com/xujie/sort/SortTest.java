package com.xujie.sort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    @Test
    public void testBubbleSort() {
        int[] array = new int[]{23, 18, 1, 4, 6, 2, 19, 22, 5, 3, 8};
        int[] sorted = new int[]{1, 2, 3, 4, 5, 6, 8, 18, 19, 22, 23};

        BubbleSorter sorter = new BubbleSorter(array);
        long t = System.currentTimeMillis();
        System.out.print("before:\t\t");
        sorter.print(array);

        sorter.sort();

        System.out.print("after:\t\t");
        sorter.print(array);
        System.out.println("time spent: " + (System.currentTimeMillis() - t));
        assertArrayEquals(array, sorted);
    }
}
