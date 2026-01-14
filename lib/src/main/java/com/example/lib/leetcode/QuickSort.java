package com.example.lib.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class QuickSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        quickSort(arr, 0, N - 1);
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int i = l - 1, j = r + 1, mid = arr[l + r >> 1];
        while (i < j) {
            do {
                i++;
            } while (arr[i] < mid);
            do {
                j--;
            } while (arr[j] > mid);
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        quickSort(arr, l, j);
        quickSort(arr, j + 1, r);
    }
}
