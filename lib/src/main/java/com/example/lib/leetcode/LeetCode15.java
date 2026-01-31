package com.example.lib.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        int N = nums.length;
        if (N < 3)
            return ans;
        for (int k = 0; k < N - 2; k++) {
            int i = k + 1, j = N - 1;
            if (nums[k] > 0) break;

            if (k > 0 && nums[k] == nums[k - 1])
                continue;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    // new ArrayList<>().add(a).add(b).add(c)）  不可以写，因为add返回的是boolean
                    // 注意下面的写法最好也不要写 虽然编译可以过 但是匿名内部类持有外部类引用 会出现问题
                    // ans.add(new ArrayList<Integer>() {
                    //     {
                    //         // 初始化块：创建对象时自动执行，往列表里加元素
                    //         add(nums[k]);
                    //         add(nums[i]);
                    //         add(nums[j]);
                    //     }
                    // });
                    // new ArrayList<>(){nums[k],nums[i],nums[j]} 为什么不行
                    // 实际写的：new ArrayList<>(){nums[k],nums[i],nums[j]} →
                    // 这里的{}是匿名内部类的类体，只能写方法重写、成员变量定义、初始化块代码，绝对不能直接写变量 / 值的罗列。
                    ans.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }

        }

        return ans;
    }
}
