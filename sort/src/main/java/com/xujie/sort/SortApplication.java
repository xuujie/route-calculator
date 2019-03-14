package com.xujie.sort;

public class SortApplication {
    public static void main(String[] args) {
        int[] array = new int[]{23, 18, 1, 4, 6, 2, 19, 22, 5, 3, 8};

        Sorter sorter = new Sorter();
        long t = System.currentTimeMillis();
        System.out.println("before:");
        sorter.print(array);

        sorter.bubbleSort(array);

        System.out.println("after:");
        sorter.print(array);
        System.out.println("time spent: " + (System.currentTimeMillis() - t));
    }
}
