package com.xujie.sort;

public class Sorter {

    public void bubbleSort(int[] a) {
        int idx = 0;
        for (int i = 0; i < a.length -1; i++) {
            for (int j = 0; j < a.length -1; j++) {
                if (a[j] > a[j+1]) {
                    System.out.print("bubbleSort " + ++idx  + "\t\t");
                    swap(a, j, j+1);
                }
            }
        }
    }


    public void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
        print(array);
    }

    public void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
