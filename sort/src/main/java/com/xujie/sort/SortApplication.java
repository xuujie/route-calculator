package com.xujie.sort;

public class SortApplication {
    public static void main(String[] args) {
        int[] array = new int[]{23, 18, 1, 4, 6, 2, 19, 22, 5, 3, 8};

        Sorter sorter = new Sorter();
        long t = System.currentTimeMillis();
        System.out.print("before:\t\t");
        sorter.print(array);

        sorter.bubbleSort(array);

        System.out.print("after:\t\t");
        sorter.print(array);
        System.out.println("time spent: " + (System.currentTimeMillis() - t));
    }
}
