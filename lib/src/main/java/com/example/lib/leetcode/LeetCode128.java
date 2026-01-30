package com.example.lib.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode128 {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int max = 0;
        // 注意这里遍历的set 不是数组
        for (int num : set) {
            int currentNum = num;
            int tempMaxLen = 1;
            // 表示这是起始点，避免重复计算
            if (!set.contains(num - 1)) {
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    tempMaxLen++;
                }
                max = Math.max(max, tempMaxLen);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 2};
        int max = longestConsecutive(nums);
        System.out.println(max);
    }
}
