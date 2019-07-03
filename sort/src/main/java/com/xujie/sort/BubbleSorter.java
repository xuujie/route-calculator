package com.xujie.sort;

public class BubbleSorter {

    private int[] array;

    public BubbleSorter(int[] array) {
        this.array = array;
    }

    public void sort() {
        int n = array.length;
        int idx = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (array[j] > array[j + 1]) {
                    System.out.print(++idx + ":\t\t\t");
                    swap(array, j, j + 1);
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

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
