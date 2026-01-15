package com.example.lib.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MergeSort {
    static int[] temp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        temp = new int[N + 1];
        mergeSortFun(arr, 0, N - 1);
        for (int i = 0; i < N; i++) {
            System.out.println(arr[i] + " ");
        }

    }

    private static void mergeSortFun(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + r >> 1;
        // 先分解成最小单元
        mergeSortFun(arr, l, mid);
        mergeSortFun(arr, mid + 1, r);

        // 然后合并
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];
        // 注意这里的复制
        for (i = l, j = 0; i <= r; i++, j++) arr[i] = temp[j];
    }
}
