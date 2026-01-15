package com.example.lib.leetcode;

public class LeetCode88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m - 1, q = n - 1, tail = m + n - 1;
        while (p >= 0 && q >= 0) {
            nums1[tail--] = nums1[p] >= nums2[q] ? nums1[p--] : nums2[q--];
        }
        while (q >= 0) {
            // 为了防止m<n 导致nums2未放入nums1中，进行遍历nums2
            nums1[tail--] = nums2[q--];
        }
    }
}
