package com.example;



import java.util.Arrays;

/**
 * @Author: yzy
 * @Date: 2022/9/30-8:55
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(arr));
        moveArray(arr, 4);
        System.out.println(Arrays.toString(arr));
    }

    public static void moveArray(int[] arr, int p) {
        if(arr.length == 1 || arr.length == 0) {
            return;
        }
        int tmp = 0;

        for (int i = 0; i < p; i++) {
            tmp = arr[arr.length - 1];
            for (int j = 0; j < arr.length - 1; j++) {
                arr[arr.length - 1 - j] = arr[arr.length - 2 - j];
            }
            arr[0] = tmp;
        }

    }
}
